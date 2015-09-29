package com.jkkp.modules.member.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 会员红包记录表
 * @author bruce
 * 
 */
@Table(name = "member_red_package")
public class MemberRedPackage  implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 会员红包记录表
	 */
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 关联会员表member的mobile
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 红包表red_packageID
	 */
	@Column(name = "red_package_id")
	private Integer redPackageId;
	
	/**
	 * 用户ID
	 */
	@Column(name = "member_id")
	private Integer memberId;

	/**
	 * 红包金额：单位元
	 */
	@Column(name = "price")
	private Float price;

	/**
	 * 已使用金额
	 */
	@Column(name = "used_money")
	private Float usedMoney;

	/**
	 * 领取红包的时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getRedPackageId() {
		return redPackageId;
	}

	public void setRedPackageId(Integer redPackageId) {
		this.redPackageId = redPackageId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getUsedMoney() {
		return usedMoney;
	}

	public void setUsedMoney(Float usedMoney) {
		this.usedMoney = usedMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}