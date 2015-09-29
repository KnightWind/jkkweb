package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 参加活动报名、签到记录表
 * 
 * @author ccn
 * 
 */

@Table(name = "activity_join_sign")
public class ActivityJionSign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "activity_id")
	private Integer activityId; // 活动ID 引用ActivityTheme

	private String name; // 报名业主姓名

	private Integer sex; // 0 保密，1 先生，2 女士 //TODO 以后改为枚举类

	private String phone; // 业主手机号码

	private String community; // 业主所要装修小区

	private Integer decoration;// 是否有选装修公司
	
	/** 是否为vip 0普通用户，1为vip用户 **/
	private Integer vip;// 是否有选装修公司
	
	@Column(name="admin_id")
	private Integer adminId;

	@Column(name = "sp_id")
	private Integer spId;
	
	
	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getDecoration() {
		return decoration;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public void setDecoration(Integer decoration) {
		this.decoration = decoration;
	}

	@Column(name = "join_first")
	private Boolean joinFirst;

	@Column(name = "jion_time")
	private Date joinTime;

	@Column(name = "sign_time")
	private Date signTime;

	@Column(name = "sms_inform")
	private Boolean smsInform; // 是否已经发送短信通知

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Boolean getJoinFirst() {
		return joinFirst;
	}

	public void setJoinFirst(Boolean joinFirst) {
		this.joinFirst = joinFirst;
	}

	public Boolean getSmsInform() {
		return smsInform;
	}

	public void setSmsInform(Boolean smsInform) {
		this.smsInform = smsInform;
	}

	public String getSexVal() {
		if (this.getSex() != null) {
			if (sex == 0) {
				return "保密";
			}
			if (sex == 1) {
				return "先生";
			}
			if (sex == 2) {
				return "女士";
			}
		}
		return "";
	}
	
	public String getSignTimeHandle(){
		if(this.getSignTime()!=null){
			return DateUtil.formatDateTime(signTime);
		}
		return "";
	}
	
	public String getJoinTimeHandle(){
		if(this.getJoinTime()!=null){
			return DateUtil.formatDateTime(joinTime);
		}
		return "";
	}
	
	public String getDecorationVal(){
		if(this.getDecoration()!=null){
			if(decoration==0){
				return "(无)";
			}
			if(decoration==1){
				return "(有)";
			}
		}
		return "";
	}

	
	
}
