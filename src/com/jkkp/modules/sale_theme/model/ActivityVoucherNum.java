package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 活动代金券数量记录
 * 
 * @author ccn
 */
@Table(name = "activity_voucher_num")
public class ActivityVoucherNum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String phone; // 业主手机号码

	@Column(name = "voucher_id")
	private Integer voucherId;

	@Column(name = "history_num")
	private Integer historyNum; // 历史总数量

	private Integer num; // 当前可用数量
	
	@Column(name = "is_use")
	private Integer isUse;

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getHistoryNum() {
		return historyNum;
	}

	public void setHistoryNum(Integer historyNum) {
		this.historyNum = historyNum;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "update_time")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeHandle() {
		if (this.getUpdateTime() != null) {
			return DateUtil.formatDateTime(updateTime);
		}
		return "";
	}
	
	
}
