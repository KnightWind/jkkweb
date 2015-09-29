package com.jkkp.modules.supplier.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;


@Table(name = "staff_opus")
public class StaffOpus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sp_id")
	private Integer spId;
	@Column(name = "staff_id")
	private Integer staffId;
	@Column(name="create_time")
	private Date createTime;
	@Column
	private Integer status;
	
	
	
	public StaffOpus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StaffOpus(Integer id, Integer spId, Integer staffId,
			Date createTime, Integer status, String title) {
		super();
		this.id = id;
		this.spId = spId;
		this.staffId = staffId;
		this.createTime = createTime;
		this.status = status;
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getCreateTimeString(){
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}
	
	public String getStatusString(){
		switch(status){
		case -1:
			return "未通过";
		case 0:
			return "待审核";
		case 1:
			return "已通过";
		default :
			return "";
		}
	}
	
}
