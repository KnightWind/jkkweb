package com.jkkp.modules.member.model;


import java.util.Date;

import javax.persistence.*;

@Table(name = "mrefundment")
public class Mrefundment {
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
	public Date getTkTime() {
		return tkTime;
	}
	public void setTkTime(Date tkTime) {
		this.tkTime = tkTime;
	}
	public String getTkUser() {
		return tkUser;
	}
	public void setTkUser(String tkUser) {
		this.tkUser = tkUser;
	}
	public String getTkUName() {
		return tkUName;
	}
	public void setTkUName(String tkUName) {
		this.tkUName = tkUName;
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
    private String tname;
    private Float amount;
    public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	private Integer type;
    private Integer method;
    private Date tkTime;
    private String tkUser;
    private String tkUName;
    private String operator;
    
}