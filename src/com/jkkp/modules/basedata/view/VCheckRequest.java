package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.CheckRequest;

public class VCheckRequest extends CheckRequest {
	public String requestTypeVal;
	public String stagName;
	public String abbreviation;
	
	public VEngineerings engineerings;			//工程单详情
	public VEngineeringStage engineeringStage;	//验收阶段详情
	
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getStagName() {
		return stagName;
	}

	public void setStagName(String stagName) {
		this.stagName = stagName;
	}

	public VEngineeringStage getEngineeringStage() {
		return engineeringStage;
	}

	public void setEngineeringStage(VEngineeringStage engineeringStage) {
		this.engineeringStage = engineeringStage;
	}

	public VEngineerings getEngineerings() {
		return engineerings;
	}
	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}
	public String getRequestTypeVal() {
		if(requestType==null) return "";
		switch (requestType) {
			case 1:requestTypeVal="首次验收";break;
			case 2:requestTypeVal="整改验收";break;
			default:requestTypeVal=requestType.toString();break;
		}return requestTypeVal;
	}

	public void setRequestTypeVal(String requestTypeVal) {
		this.requestTypeVal = requestTypeVal;
	}
	
}
