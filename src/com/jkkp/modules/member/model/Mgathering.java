package com.jkkp.modules.member.model;


import java.util.Date;

import javax.persistence.*;

@Table(name = "mgathering")
public class Mgathering {
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public Date getSkTime() {
		return skTime;
	}
	public void setSkTime(Date skTime) {
		this.skTime = skTime;
	}
	public String getSkUser() {
		return skUser;
	}
	public void setSkUser(String skUser) {
		this.skUser = skUser;
	}
	public String getSkUName() {
		return skUName;
	}
	public void setSkUName(String skUName) {
		this.skUName = skUName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gname;
    private Float amount;
    private Integer type;
    private Integer method;
    private Date skTime;
    private String skUser;
    private String skUName;
    private String operator;
    
    public static final int TYPE_DEPOSIT = 1; // 订金
    public static final int TYPE_PROJECT = 2; // 工程监管款
    public static final int TYPE_WALLET = 3; // 充值

	public static final int METHOD_PAY = 1; // 收款
	public static final int METHOD_REFUND = 2; // 退款
}