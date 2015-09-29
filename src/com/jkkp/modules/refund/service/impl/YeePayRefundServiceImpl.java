/**
 * 
 */
package com.jkkp.modules.refund.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.control.common.IdCreator;
import com.jkkp.client.yeepay.constant.YeePayCode;
import com.jkkp.client.yeepay.service.YeePayService;
import com.jkkp.client.yeepay.service.YeePayUtil;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.order.mapper.PaymentRecordMapper;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.service.IRefundService;

/**
 * @author Administrator
 *
 */
@Service("yeePayRefundServiceImpl")
public class YeePayRefundServiceImpl implements IRefundService{
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
	private YeePayService yeePayService = new YeePayService();
	
	@Override
	public Object refundNotity(HttpServletRequest request)  {
//		String returnCode = result.get("code");
//		String msg = YeePayCode.getErrorMessage(returnCode);
//		String refundexternalid = result.get("refundexternalid");
//		
//		if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
//			resultMap = updateBusiSuccess(refundApplyAudit, appointmentPush, resultMap,refundRecord);
//		}else{
//			resultMap.put("resultCode", returnCode);
//			resultMap.put("resultInfo", msg);
//		}
//		refundRecord.setBatchNo(refundexternalid);
//		refundRecord.setStatus(returnCode);
//		// 更新退款日志信息
//		paymentRecordService.update(refundRecord);
		
		return null;
	}

	@Override
	public Object refund(Map map) {
		//-------
		RefundApplyAudit refundApplyAudit = (RefundApplyAudit)map.get("refundApplyAudit");
		PaymentRecord payRecord = (PaymentRecord)map.get("payRecord");
		AppointmentPush appointmentPush = (AppointmentPush)map.get("appointmentPush");
		//--------
		
		Map resultMap = new HashMap();
		resultMap.put("payway", 2);//易宝
		
		//生成一条退款记录
		payRecord.setTitle("定金退款");// 重置标题
		PaymentRecord refundRecord =  paymentRecordService.insertRefundRecord(appointmentPush.getId(), payRecord);
		//更新申请表recordid字段
		refundApplyAudit.setRefundRecordId(refundRecord.getRecordId());
		refundApplyAuditMapper.updateByPrimaryKey(refundApplyAudit);
		
		//调用退款接口直接退款
		Map<String, String> result = yeePayService.refund(IdCreator.getSeqId(), refundRecord.getSerialCode(), Double.valueOf(refundRecord.getAmount()), null, "退定金");
		String returnCode = result.get("code");
		String msg = YeePayCode.getErrorMessage(returnCode);
		String refundexternalid = result.get("refundexternalid");
		
		if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
			resultMap = updateBusiSuccess(refundApplyAudit, appointmentPush, resultMap,refundRecord);
		}else{
			resultMap.put("resultCode", returnCode);
			resultMap.put("resultInfo", msg);
		}
		refundRecord.setBatchNo(refundexternalid);
		refundRecord.setStatus(returnCode);
		// 更新退款日志信息
		paymentRecordService.update(refundRecord);
		
		return resultMap;
	}
	
	public Map updateBusiSuccess(RefundApplyAudit refundApplyAudit,
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
