/**
 * 
 */
package com.jkkp.modules.refund.view;

import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.utils.DateUtil;

/**
 * @author Administrator
 *
 */
public class VRefundApplyAudit extends RefundApplyAudit{
	private String applyDateStr;
	private String auditDateStr;
	private String nickName;
	private String mobile;
	
	public String getNickName() {
		return nickName==null?"(无)":nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile==null?"(无)":mobile;
	}
	
	//处理js数据    发信息
	public String getRefundMobile(){
		return mobile==null?"0":mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getApplyDateStr() {
		return super.getApplyDate()==null?"":DateUtil.formatDateTime(super.getApplyDate());
	}
	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}
	public String getAuditDateStr() {
		return super.getAuditDate()==null?"":DateUtil.formatDateTime(super.getAuditDate());
	}
	public void setAuditDateStr(String auditDateStr) {
		this.auditDateStr = auditDateStr;
	}
	
	
}
