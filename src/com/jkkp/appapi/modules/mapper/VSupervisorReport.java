package com.jkkp.appapi.modules.mapper;

public class VSupervisorReport {

	private Integer stageId;
	private Integer instanceId;
	private String name;
	private String status;
	private String reportStatus;
	private Integer checkRequestCount;

	public Integer getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getCheckRequestCount() {
		return checkRequestCount;
	}

	public void setCheckRequestCount(Integer checkRequestCount) {
		this.checkRequestCount = checkRequestCount;
	}

}
