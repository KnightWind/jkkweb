package com.jkkp.appapi.modules.mapper;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VSupervisorRequest {

	private String status;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date starttime;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date accepttime;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getAccepttime() {
		return accepttime;
	}

	public void setAccepttime(Date accepttime) {
		this.accepttime = accepttime;
	}

}
