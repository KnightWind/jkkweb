package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.Case;
import com.jkkp.utils.DateUtil;

public class VCase extends Case {

	public String designName;
	public String engcommunity;
	public String subject;
	public String name;
	public String supervisorName;
	public String spName;
	public String jlName;
	public String stylename;
	public String housestyle;
	public String coverurl;
	
	public String getStylename() {
		return stylename;
	}
	public void setStylename(String stylename) {
		this.stylename = stylename;
	}
	public String getHousestyle() {
		return housestyle;
	}
	public void setHousestyle(String housestyle) {
		this.housestyle = housestyle;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public String getCreateTimeString(){
		if(createTime != null)
			return DateUtil.formatDateTime(createTime);
		return "";
	}
	public String getJlName() {
		return jlName == null ? "" : jlName;
	}
	public void setJlName(String jlName) {
		this.jlName = jlName;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getDesignName() {
		return designName == null ? "" : designName;
	}
	public void setDesignName(String designName) {
		this.designName = designName;
	}
	
	public String getEngcommunity() {
		return engcommunity == null ? "" : engcommunity;
	}
	public void setEngcommunity(String engcommunity) {
		this.engcommunity = engcommunity;
	}
	public String getSubject() {
		return subject == null ? "" : subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	
	
	
}
