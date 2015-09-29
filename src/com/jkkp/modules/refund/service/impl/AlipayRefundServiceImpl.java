/**
 * 
 */
package com.jkkp.modules.refund.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.client.alipay.config.AlipayConfig;
import com.jkkp.client.alipay.util.AlipaySubmit;
import com.jkkp.modules.appointment.mapper.AppointmentPushMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.order.mapper.PaymentRecordMapper;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.service.IRefundApplyAuditService;
import com.jkkp.modules.refund.service.IRefundService;
import com.jkkp.utils.DateUtil;

/**
 * @author Administrator
 *
 */
@Service("alipayRefundServiceImpl")
public class AlipayRefundServiceImpl implements IRefundService{
	@Autowired
	private RefundApplyAuditMapper refundApplyAuditMapper;
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired
	private PaymentRecordMapper paymentRecordMapper;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IAppointmentPushService appointmentPushService;
	@Autowired
	private AppointmentPushMapper appointmentPushMapper;
	
	@Autowired	
	IRefundApplyAuditService refundApplyAuditService;
	
	@Override
	@Transactional
	public Object refundNotity(HttpServletRequest request) throws Exception {
		//退款结果明细 
		//2010031906272929^80^SUCCESS$jax_chuanhang@alipay.com^2088101003147483^0.01^SUCCESS
		//退手续费结果返回格式：交易 号 ^退 款 金 额 ^处 理 结 果$退费账号^退费账户 ID^退费金额^处理结果
		String result_details =  new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8"); 
		//批次号
		String batch_no =  new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8"); 
		if(result_details!=null){
			String[] details  = result_details.split("^");
			String tradeNo = details[0];
			String amount = details[1];
			String rt = details[2];
		    //根据批次号 和 tradeNo更新相应的日志和业务数据
			List<PaymentRecord> list = paymentRecordService.selectByProperty(new String[]{"trade_no","batch_no"}, new String[]{tradeNo,batch_no});
			if(list!=null&&list.size()>0){
				PaymentRecord refundRecord = list.get(0);
				if("SUCCESS".equals(rt)){
					//更新push表
					AppointmentPush appointmentPush  = appointmentPushMapper.selectByPrimaryKey(refundRecord.getBusinessId());
					appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_LIANG_FANG);// 跳到已量房 可以重新发起支付定金
					appointmentPush.setDepositPayStatus(2);// 定金支付状态：已退款
					appointmentPush.setDepositRefundTime(refundRecord.getCreateDate());// 定金退款时间
					appointmentPush.setDepositRefundRecId(refundRecord.getRecordId());// 退款记录id
					appointmentPushService.update(appointmentPush);
					Appointment appointment = appointmentService.findById(appointmentPush.getAid()); // 需求单
					// 预约单的状态应该更新到哪个状态？
					appointment.setStatus(ConstantAppStatus.DAI_QIAN_YUE);// 回退到待签约状态
					appointmentService.update(appointment);
					
					//审批单状态退款完成
					RefundApplyAudit refundApplyAudit=null;
					List<RefundApplyAudit> refundApplyAuditList = refundApplyAuditService.selectByProperty("refund_record_id", refundRecord.getRecordId());
					if(refundApplyAuditList!=null&&refundApplyAuditList.size()>0){
						refundApplyAudit = refundApplyAuditList.get(0);
						refundApplyAudit.setStatus(3);
						refundApplyAuditMapper.updateByPrimaryKey(refundApplyAudit);
					}else{
						System.out.println(">>>>>>>error: 没有查到申请单信息");
					}
				}
				//更新日志信息
				refundRecord.setStatus(rt);
				refundRecord.setNotifyTime(new Date());
				paymentRecordService.update(refundRecord);
			}else{
				System.out.println("回调错误--->>>>>>支付宝退款回调时没有查询到任何退款日志");
				throw new Exception("回调错误--->>>>>>支付宝退款回调时没有查询到任何退款日志");
			}
		}
		return null;
	}

	@Override
	public Object refund(Map map) {
		RefundApplyAudit refundApplyAudit = (RefundApplyAudit)map.get("refundApplyAudit");
		PaymentRecord payRecord = (PaymentRecord)map.get("payRecord");
		AppointmentPush appointmentPush = (AppointmentPush)map.get("appointmentPush");
		
		Map resultMap = new HashMap();
		Map paramMap = new HashMap();
		payRecord.setTitle("定金退款");// 重置标题
		//生成一条退款记录
		PaymentRecord refundRecord =  paymentRecordService.insertRefundRecord(appointmentPush.getId(), payRecord);
		//更新申请表recordid字段
		refundApplyAudit.setRefundRecordId(refundRecord.getRecordId());
		refundApplyAuditMapper.updateByPrimaryKey(refundApplyAudit);
		//创建alipay退款表单
		resultMap = createAliRefundForm(resultMap, refundRecord);
		return resultMap;
	}
	/**
	 * 创建支付宝退款表单
	 * @param resultMap
	 * @param payRecord
	 */
	private Map createAliRefundForm(Map resultMap, PaymentRecord payRecord) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", AlipayConfig.NOTIFY_APP_REFUND_URL);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("refund_date", DateUtil.getCurDateTime());
		sParaTemp.put("batch_no",payRecord.getBatchNo());
		sParaTemp.put("batch_num", "1");
		sParaTemp.put("detail_data", payRecord.getTradeNo()+"^"+payRecord.getAmount()+"^"+"订金退款");//"2015071000001000810059380074^0.01^");
		
		sParaTemp.put("dback_notify_url", "http://api.test.alipay.net/atinterface/receive_notify.htm");
		sParaTemp.put("use_freeze_amount", "N");
		sParaTemp.put("return_type", "xml");
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		resultMap.put("payway", 1);//支付宝
		resultMap.put("formHtml", sHtmlText);
		resultMap.put("resultCode", "0");
		resultMap.put("resultInfo", "支付表单生成成功");
		return resultMap;
	}
	
	
	private Map updateBusiSuccess(RefundApplyAudit refundApplyAudit,
			AppointmentPush appointmentPush, Map resultMap,
			PaymentRecord refundRecord) {
		appointmentPush.setStatus(ConstantAppStatus.PUSH_JIE_SHU);// 跳到已量房 可以重新发起支付定金
		appointmentPush.setDepositPayStatus(2);// 定金支付状态：已退款
		appointmentPush.setDepositRefundTime(refundRecord.getCreateDate());// 定金退款时间
		appointmentPush.setDepositRefundRecId(refundRecord.getRecordId());// 退款记录id
		appointmentPushService.update(appointmentPush);

		Appointment appointment = appointmentService.findById(appointmentPush.getAid()); // 需求单
		// 预约单的状态应该更新到哪个状态？
		appointment.setStatus(ConstantAppStatus.DAI_QIAN_YUE);// 回退到待签约状态
		appointmentService.update(appointment);
		
		//退款完成
		refundApplyAudit.setStatus(3);
		refundApplyAudit.setRefundRecordId(refundRecord.getRecordId());
		refundApplyAuditMapper.updateByPrimaryKey(refundApplyAudit);
		resultMap.put("resultCode", "0");
		resultMap.put("resultInfo", "退款成功");
		return resultMap;
	}
}
