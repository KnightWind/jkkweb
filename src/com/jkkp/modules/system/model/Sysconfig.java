package com.jkkp.modules.system.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sysconfig")
public class Sysconfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "para_code")
	private Integer paraCode;
	@Column(name = "para_name")
	private String paraName;
	@Column(name = "para_value")
	private String paraValue;
	@Column(name = "para_desc")
	private String paraDesc;
	public Integer getParaCode() {
		return paraCode;
	}
	public void setParaCode(Integer paraCode) {
		this.paraCode = paraCode;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getParaDesc() {
		return paraDesc;
	}
	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}
	
	public static final Map<String, String> CONFIG_PARAM = new HashMap<String, String>();
}
