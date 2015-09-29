package com.jkkp.pc.business.view;

import java.math.BigDecimal;


public class VBusiness {
	private Integer id;
	private Float money;
	private Integer engcount;
	private String spName;
	private Integer jkbFlag;
	private String address;
	private String filepath;
	private String intro;
	private BigDecimal grade;
	
	
	public BigDecimal getGrade() {
		return grade;
	}
	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Integer getEngcount() {
		return engcount == null ? 0 : engcount;
	}
	public void setEngcount(Integer engcount) {
		this.engcount = engcount;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Integer getJkbFlag() {
		return jkbFlag;
	}
	public void setJkbFlag(Integer jkbFlag) {
		this.jkbFlag = jkbFlag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	/**
	 * 格式化星级数字
	 */
	public BigDecimal formate(BigDecimal args){
		String sestimate = args.toString();
		int tmpStratStr = 0;
		if(sestimate.indexOf('.') > 0){
			tmpStratStr = Integer.valueOf(sestimate.substring(0,sestimate.indexOf('.')));
			String tmpEndStr = sestimate.substring(sestimate.indexOf('.') + 1);
			int tmpNum = Integer.valueOf(tmpEndStr);
			if(tmpNum > 0){
				return BigDecimal.valueOf(tmpStratStr + 0.5);
			}
		}
		return BigDecimal.valueOf(tmpStratStr);
	}
	
	/**
	 * 返回对应图片名字
	 */
	public BigDecimal getZongHe(){
		return grade == null ? BigDecimal.valueOf(0) : formate(grade);
	}
	
	
	
}
