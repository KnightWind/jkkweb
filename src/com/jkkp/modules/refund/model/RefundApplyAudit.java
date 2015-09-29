package com.jkkp.modules.refund.model;

import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "refund_apply_audit")
public class RefundApplyAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//1:deposit
//
	@Column(name="refund_type")
	private Integer refundType;

	//push单id
	@Column(name="business_id")
	private Integer businessId;

	//预约单id
	@Column(name="appoint_id")
	private Integer appointId;

	//申请日期
	@Column(name="apply_date")
	private Date applyDate;

	//申请理由
	@Column(name="apply_reason")
	private String applyReason;

	//申请金额
	@Column(name="apply_amount")
	private Double applyAmount;

	//申请人id
	@Column(name="apply_user_id")
	private Integer applyUserId;

	//0：待审核
	//1：审核通过
	//2：审核不通过
	//3：退款完成
	@Column(name="status")
	private Integer status;

	//审核备注
	@Column(name="audit_remark")
	private String auditRemark;

	//审核日期
	@Column(name="audit_date")
	private Date auditDate;

	//审核人id
	@Column(name="audit_user_id")
	private Integer auditUserId;

	//审核人姓名
	@Column(name="audit_user_name")
	private String auditUserName;

	//退款日志id
	@Column(name="refund_record_id")
	private Integer refundRecordId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getAppointId() {
		return appointId;
	}

	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Double getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Integer getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Integer getRefundRecordId() {
		return refundRecordId;
	}

	public void setRefundRecordId(Integer refundRecordId) {
		this.refundRecordId = refundRecordId;
	}
	public String getApplyDateStr() {
		return this.applyDate==null?"":DateUtil.formatDateTime(this.applyDate);
	}
	public String getAuditDateStr() {
		return this.auditDate==null?"":DateUtil.formatDateTime(this.auditDate);
	}
	public String getRefundTypeName() {
		if(this.refundType!=null&&refundType==1){
			return "定金";
		}else{
			return "";
		}
	}
	
	public String getStatusName() {
		if(status!=null){
			if(this.status==0){
				return "待审核";
			}
            if(this.status==1){
				return "审核通过";
			}
            if(this.status==2){
				return "审核不通过";
			}
            if(this.status==3){
				return "退款完成";
			}
		}
		return "未知状态";
	}
	
	public String getApplyAmountF() {
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		if(applyAmount!=null){
			return df.format(applyAmount);
		}else{
			return "0";
		}
	}
	
	//申请日期
	public String getApplyDateHandle(){
		return DateUtil.formatDate(applyDate);
	}
	
	//审核日期
	public String getAuditDateHandle(){
		return DateUtil.formatDate(auditDate);
	}
	
	public String getRefundTypeVal(){
		if(this.getRefundType()!=null){
			if(refundType==5){
				return "众筹订单退款";
			}
		}
		return "其他类型";
	}
}