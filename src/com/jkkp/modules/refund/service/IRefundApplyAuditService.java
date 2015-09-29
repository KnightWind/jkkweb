/**
 * 
 */
package com.jkkp.modules.refund.service;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.view.VRefundApplyAudit;

/**
 * @author Administrator
 *
 */
public interface IRefundApplyAuditService extends	IService<RefundApplyAudit, VRefundApplyAudit, Integer>{
	public Object refund(HttpServletRequest request,Integer id);
	
	public Object refundAlipay(HttpServletRequest request,Integer id);
	
	VRefundApplyAudit detailInfo(Integer id);
	
	/**
	 * 退款审核
	 * @param id  退款id
	 * @param status 审核状态
	 */
	public Boolean refundAuditing(Integer id,Integer status,String mobile);
	
}
