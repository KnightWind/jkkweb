package com.jkkp.modules.member.model;

import java.util.Date;

import javax.persistence.*;

@Table(name = "money_bag")
public class MoneyBag {

	public static final int SOURCE_TYPE_1 = 1; // 充值
	public static final int SOURCE_TYPE_2 = 2; // 积分兑换
	public static final int SOURCE_TYPE_3 = 3; // 红包
	public static final int SOURCE_TYPE_4 = 4; // 定金冲监管款余款

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer uid;
	private String uname;
	private Float amount;
	private Integer source;
	@Column(name = "createTime")
	private Date createTime;
	@Column(name = "createUser")
	private String createUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}