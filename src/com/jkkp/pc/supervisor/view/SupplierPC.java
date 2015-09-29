package com.jkkp.pc.supervisor.view;

import java.math.BigDecimal;

public class SupplierPC {
    private int id;
    private String spName;
    private BigDecimal assess;
    private String filepath;
    private Long caseNum;
    private Long collectCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public BigDecimal getAssess() {
		return assess;
	}
	public void setAssess(BigDecimal assess) {
		this.assess = assess;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Long getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(Long caseNum) {
		this.caseNum = caseNum;
	}
	public Long getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(Long collectCount) {
		this.collectCount = collectCount;
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
		return assess == null ? BigDecimal.valueOf(0) : formate(assess);
	}
	
    
}
