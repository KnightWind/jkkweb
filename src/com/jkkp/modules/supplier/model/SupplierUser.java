package com.jkkp.modules.supplier.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;


@Table(name = "supplier_user")
public class SupplierUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@Column(name = "sp_code")
	private String spCode;
	@Column(name = "sp_id")
	public Integer spId;
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public Integer getClas() {
		return clas;
	}
	public void setClas(Integer clas) {
		this.clas = clas;
	}
	public Integer getQdFlag() {
		return qdFlag;
	}
	public void setQdFlag(Integer qdFlag) {
		this.qdFlag = qdFlag;
	}
	public String username;
	public String userpwd;
	public Integer clas;
	@Column(name="qd_flag")
	public Integer qdFlag;
	@Column(name="is_audit")
	public Integer isAudit;
	@Column(name="is_admin")
	public Integer isAdmin;
	@Column(name="is_merchandiser")
	public Integer isMerchandiser;
	@Column(name="is_designer")
	public Integer isDesigner;
	@Column(name="create_time")
	private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getIsMerchandiser() {
		return isMerchandiser;
	}
	public void setIsMerchandiser(Integer isMerchandiser) {
		this.isMerchandiser = isMerchandiser;
	}
	public Integer getIsDesigner() {
		return isDesigner;
	}
	public void setIsDesigner(Integer isDesigner) {
		this.isDesigner = isDesigner;
	}
	public String getTy(){
		if(this.qdFlag!=null&&this.qdFlag.toString()!=""){
			if(this.qdFlag==1){
				return "否";
			}else {
				return "是";
			}
		}
		return "";
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	
	public String getCreateTimeString(){
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}
	
}
