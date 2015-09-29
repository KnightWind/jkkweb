package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 活动礼包领取、使用记录
 * @author ccn
 *
 */

@Table(name = "activity_gift_log")
public class ActivityGiftLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "gift_id")
	private Integer giftId; //活动ID 引用ActivityGift
	
	private String phone; //业主手机号

	private Boolean used; //是否已经使用, true 已经使用, false 未使用
	
	@Column(name = "create_time")
	private Date createTime; //领取时间
	
	@Column(name = "update_time")
	private Date updateTime; //更新时间

	
	public String getReceiveVal(){
		if(this.used != null){
			if(used){
				return "已领取";
			}else{
				return "未领取";
			}
		}
		return "未领取";
	}
	
	public String getCreateTimeHandle(){
		if(this.createTime != null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
	
	public String getReceiveTimeHandle(){
		if(this.updateTime != null){
			return DateUtil.formatDateTime(updateTime);
		}
		return "";
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}
	
}
