package com.jkkp.modules.supplier.view;

import java.util.ArrayList;
import java.util.List;

import com.jkkp.modules.basedata.view.VCase;
import com.jkkp.modules.supplier.model.Supplier;

public class VSupplier extends Supplier {
	
    public String levelName;
    public String city;
    public String province;
    public Float xzYs;
	public Float xzMj;
	public String xzZxfs;
    public Integer levelMoney;											//保证金
	public Integer engineeringsCount,scs;								//接单数,收藏数
	public Integer caseCount,kgd1Count,staffCount;						//可参观工地数，设计师数

	public List<VJtopic> jtopics=new ArrayList<VJtopic>();				//日志列表
	public List<VSupplierLabel> labels=new ArrayList<VSupplierLabel>();	//标签列表
	public List<VCase> caseList=new ArrayList<VCase>();					//案例列表
	public List<VCase> kgd1List=new ArrayList<VCase>();					//可参观工地列表
	public List<VSupplierCompanyStaff> staffList=new ArrayList<VSupplierCompanyStaff>();//设计师列表
	public String coverurl,photoUrl;
	public String typeVal;
	
	public Integer getScs() {
		return scs;
	}
	public void setScs(Integer scs) {
		this.scs = scs;
	}
	public String getTypeVal() {
		if(type==null) return "";
		switch (type) {
			case 1:typeVal="装修公司";break;
			case 2:typeVal="建材商";break;
			case 3:typeVal="工长";break;
			case 4:typeVal="4";break;
			case 5:typeVal="监理";break;
			default:typeVal=type.toString();break;
		}return typeVal;
	}
	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public List<VCase> getCaseList() {
		return caseList;
	}
	public void setCaseList(List<VCase> caseList) {
		this.caseList = caseList;
	}
	public List<VSupplierCompanyStaff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<VSupplierCompanyStaff> staffList) {
		this.staffList = staffList;
	}
	public Integer getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
	}
	public List<VCase> getKgd1List() {
		return kgd1List;
	}
	public void setKgd1List(List<VCase> kgd1List) {
		this.kgd1List = kgd1List;
	}
	public Integer getKgd1Count() {
		return kgd1Count;
	}
	public void setKgd1Count(Integer kgd1Count) {
		this.kgd1Count = kgd1Count;
	}
	public Integer getCaseCount() {
		return caseCount;
	}
	public void setCaseCount(Integer caseCount) {
		this.caseCount = caseCount;
	}
	public Integer getEngineeringsCount() {
		return engineeringsCount;
	}
	public void setEngineeringsCount(Integer engineeringsCount) {
		this.engineeringsCount = engineeringsCount;
	}
	public List<VSupplierLabel> getLabels() {
		return labels;
	}
	public void setLabels(List<VSupplierLabel> labels) {
		this.labels = labels;
	}
	public List<VJtopic> getJtopics() {
		return jtopics;
	}
	public void setJtopics(List<VJtopic> jtopics) {
		this.jtopics = jtopics;
	}
	public Float getXzYs() {
		return xzYs;
	}
	public void setXzYs(Float xzYs) {
		this.xzYs = xzYs;
	}
	public Float getXzMj() {
		return xzMj;
	}
	public void setXzMj(Float xzMj) {
		this.xzMj = xzMj;
	}
	public String getXzZxfs() {
		return xzZxfs;
	}
	public void setXzZxfs(String xzZxfs) {
		this.xzZxfs = xzZxfs;
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getLevelMoney() {
		return levelMoney;
	}
	public void setLevelMoney(Integer levelMoney) {
		this.levelMoney = levelMoney;
	}
	
}
