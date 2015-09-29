/**
 * 
 */
package com.jkkp.modules.refund.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.refund.model.RefundApplyAudit;

/**
 * @author Administrator
 *
 */
public interface IRefundService {
	public Object refund(Map map) ;
	public Object refundNotity(HttpServletRequest request)throws Exception  ;
	
}
