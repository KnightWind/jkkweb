package com.jkkp.modules.order.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "agreement")
public class Agreement {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 @Column(name = "gcd_id")
	 private Integer gcdId;
	 @Column(name = "appointmentpush_id")
	 private Integer appointmentpushId;
	 private String remark;
	private String user;
	 @Column(name = "create_time")
	 private Date createTime;
	 
	 
	 
	 public Integer getAppointmentpushId() {
		return appointmentpushId;
	}
	public void setAppointmentpushId(Integer appointmentpushId) {
		this.appointmentpushId = appointmentpushId;
	}
	public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getGcdId() {
			return gcdId;
		}
		public void setGcdId(Integer gcdId) {
			this.gcdId = gcdId;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
}
