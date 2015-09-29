/**
 * 
 */
package com.jkkp.modules.refund.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.control.common.IdCreator;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.client.alipay.config.AlipayConfig;
import com.jkkp.client.alipay.util.AlipaySubmit;
import com.jkkp.client.yeepay.service.YeePayService;
import com.jkkp.common.ServiceSupport;
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
import com.jkkp.modules.refund.view.VRefundApplyAudit;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.DateUtil;

/**
 * @author Administrator
 *
 */
@Service("refundApplyAuditService")
public class RefundApplyAuditServiceImpl extends ServiceSupport<RefundApplyAudit, VRefundApplyAudit, Integer> implements IRefundApplyAuditService{
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
	@Resource(name = "alipayRefundServiceImpl")
	private IRefundService alipayRefundService;
	
	@Resource(name = "yeePayRefundServiceImpl")
	private IRefundService yeepayRefundService;
	
	private YeePayService yeePayService = new YeePayService();
	@Override
	protected Mapper<RefundApplyAudit> getMapper() {
		return this.refundApplyAuditMapper;
	}

	@Override
	public Object refund(HttpServletRequest request, Integer id) {
		Map resultMap = new HashMap();
		RefundApplyAudit refundApplyAudit =null;
		List<RefundApplyAudit> list = this.selectByProperty("id", id);
		if(list==null||list.size()==0){
			resultMap.put("resultCode", "9001");
			resultMap.put("resultInfo", "没有查询到记录");
			return resultMap;
		}
		refundApplyAudit = list.get(0);
		if(refundApplyAudit.getStatus()!=1){
			resultMap.put("resultCode", "9002");
			resultMap.put("resultInfo", "审核状态["+refundApplyAudit.getStatusName()+"]");
			return resultMap;
		}
		
		//定金退款
		if(refundApplyAudit.getRefundType()==1){
			Map paramMap = new HashMap();
			//查询push表具体信息
			AppointmentPush appointmentPush = appointmentPushService.findById(refundApplyAudit.getBusinessId());
			// 找到支付的那一条记录 获取相关信息
			PaymentRecord payRecord = paymentRecordService.findById(appointmentPush.getDepositPayRecId());
			if (payRecord == null) {
				resultMap.put("resultCode", "9002");
				resultMap.put("resultInfo", "该订单没有支付成功的记录");
				return resultMap;
			}
			//支付宝
			if(payRecord.getPayway()==1){
				paramMap.put("refundApplyAudit", refundApplyAudit);
				paramMap.put("payRecord", payRecord);
				paramMap.put("appointmentPush", appointmentPush);
				resultMap = (Map)alipayRefundService.refund(paramMap);
				
	        //易宝
			}else if(payRecord.getPayway()==2){
				paramMap.put("refundApplyAudit", refundApplyAudit);
				paramMap.put("payRecord", payRecord);
				paramMap.put("appointmentPush", appointmentPush);
				resultMap = (Map)yeepayRefundService.refund(paramMap);
			}
		}
		return resultMap;
	}
	/**
	 * 创建支付宝退款表单
	 * @param resultMap
	 * @param payRecord
	 */
	private void createAliRefundForm(Map resultMap, PaymentRecord payRecord) {
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
	}

	
	public Object refundAlipay(HttpServletRequest request,Integer id){
		Map resultMap = new HashMap();
		RefundApplyAudit refundApplyAudit =null;
		List<RefundApplyAudit> list = this.selectByProperty("id", id);
		if(list==null||list.size()==0){
			resultMap.put("resultCode", "9001");
			resultMap.put("resultInfo", "没有查询到记录");
			return resultMap;
		}
		refundApplyAudit = list.get(0);
		if(refundApplyAudit.getStatus()!=1){
			resultMap.put("resultCode", "9002");
			resultMap.put("resultInfo", "审核状态不正确，不允许退款");
			return resultMap;
		}
		
		Map paramMap = new HashMap();
		paramMap.put("appointmentPushId", refundApplyAudit.getBusinessId());
		paramMap.put("reason", refundApplyAudit.getApplyReason());
		
		AppointmentPush appointmentPush = appointmentPushService
				.findById(refundApplyAudit.getBusinessId());
		Appointment appointment = appointmentService.findById(appointmentPush
				.getAid());
		PaymentRecord payRecord = null;
		// 找到支付的那一条记录 获取相关信息
		payRecord = paymentRecordService.findById(appointmentPush.getDepositPayRecId());
		
		if (payRecord == null) {
			return null;
		}
		payRecord.setTitle("定金退款");// 重置标题
		PaymentRecord record =  paymentRecordService.insertRefundRecord(appointmentPush.getId(), payRecord);
		
		//插入一条新的退款日志
		//PaymentRecord record = paymentRecordService.dealDepositRefundRecord(paramMap);
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", AlipayConfig.NOTIFY_APP_REFUND_URL);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("refund_date", DateUtil.getCurDateTime());
		sParaTemp.put("batch_no", IdCreator.getDateStr()+IdCreator.getSeqId());
		sParaTemp.put("batch_num", "1");
		sParaTemp.put("detail_data", payRecord.getTradeNo()+"^"+payRecord.getAmount()+"^"+"订金退款");//"2015071000001000810059380074^0.01^");
		
		sParaTemp.put("dback_notify_url", "http://api.test.alipay.net/atinterface/receive_notify.htm");
		sParaTemp.put("use_freeze_amount", "N");
		sParaTemp.put("return_type", "xml");
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		resultMap.put("formHtml", sHtmlText);
		resultMap.put("resultCode", "0");
		resultMap.put("resultInfo", "表单生成成功");
		return resultMap;
	}

	@Override
	public VRefundApplyAudit detailInfo(Integer id) {
		return refundApplyAuditMapper.detailInfo(id);
	}

	@Transactional
	public Boolean refundAuditing(Integer id, Integer status,String mobile) {
		RefundApplyAudit bean = this.findById(id);
		try{
			bean.setStatus(status);
			bean.setAuditDate(new Date());
			this.update(bean);
			String content=Sysconfig.CONFIG_PARAM.get(ConfigConstant.REFUND_AUD_SUCCESS)==null?"退款将返还您的支付账号,敬请查收":Sysconfig.CONFIG_PARAM.get(ConfigConstant.REFUND_AUD_SUCCESS);
			if(mobile!=null&&!mobile.equals("0")){
				SendMsgUtil.send(mobile,content );
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
