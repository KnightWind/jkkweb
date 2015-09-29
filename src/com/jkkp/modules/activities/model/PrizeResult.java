package com.jkkp.modules.activities.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="prize_result")
public class PrizeResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nickname;
	private String phone;
	@Column(name="awards_id")
	private Integer awardsId;
	@Column(name="active_id")
	private Integer activeId;
	@Column(name="create_time")
	protected Date createTime;
	@Column(name="is_unclaimed")
	protected Integer isUnclaimed;
	
	
	
	
	public Integer getActiveId() {
		return activeId;
	}
	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAwardsId() {
		return awardsId;
	}
	public void setAwardsId(Integer awardsId) {
		this.awardsId = awardsId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsUnclaimed() {
		return isUnclaimed;
	}
	public void setIsUnclaimed(Integer isUnclaimed) {
		this.isUnclaimed = isUnclaimed;
	}
	public PrizeResult(Integer id, String nickname, String phone,
			Integer awardsId, Date createTime, Integer isUnclaimed) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.phone = phone;
		this.awardsId = awardsId;
		this.createTime = createTime;
		this.isUnclaimed = isUnclaimed;
	}
	public PrizeResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
