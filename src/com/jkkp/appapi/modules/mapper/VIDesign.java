package com.jkkp.appapi.modules.mapper;

public class VIDesign {
	private String pid;
	private String fileUrl;
	private String fielType;
	private String remark;
    private Integer designerid;
    private Integer designid;
    private String headurl;
    private String designname;
    private Integer aid;
    
	public String getDesignname() {
		return designname;
	}

	public void setDesignname(String designname) {
		this.designname = designname;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getDesignid() {
		return designid;
	}

	public void setDesignid(Integer designid) {
		this.designid = designid;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public Integer getDesignerid() {
		return designerid;
	}

	public void setDesignerid(Integer designerid) {
		this.designerid = designerid;
	}

	public String getPid() {
		return pid;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getFielType() {
		return fielType;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public void setFielType(String fielType) {
		this.fielType = fielType;
	}

}
