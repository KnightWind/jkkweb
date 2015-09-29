package com.jkkp.appapi.modules.mapper;

public class VRetCondition {
private boolean flag;	
private int spid;
private boolean city;
private boolean budget;
private String spname;
private String spimg;
public VRetCondition(){
	this.setBudget(false);
	this.setCity(false);
	this.setFlag(false);
	this.setSpimg("");
	this.setSpname("");
}
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
public int getSpid() {
	return spid;
}
public void setSpid(int spid) {
	this.spid = spid;
}
public boolean isCity() {
	return city;
}
public void setCity(boolean city) {
	this.city = city;
}
public boolean isBudget() {
	return budget;
}
public void setBudget(boolean budget) {
	this.budget = budget;
}
public String getSpname() {
	return spname;
}
public void setSpname(String spname) {
	this.spname = spname;
}
public String getSpimg() {
	return spimg;
}
public void setSpimg(String spimg) {
	this.spimg = spimg;
}



}
