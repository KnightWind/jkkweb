package com.jkkp.appapi.modules.mapper;

public class VAcceptanceItem {

	private Integer id;
	private String name;
	private Integer status;
	private String statusName;
	private Integer mxId;
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getMxId() {
		return mxId;
	}

	public void setMxId(Integer mxId) {
		this.mxId = mxId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
