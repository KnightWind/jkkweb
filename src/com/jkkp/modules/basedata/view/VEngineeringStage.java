package com.jkkp.modules.basedata.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jkkp.modules.basedata.model.EngineeringStage;
import com.jkkp.utils.DateUtil;

public class VEngineeringStage extends EngineeringStage {
	public String pstagName;
	public Date pcreateTime;
	public String firstStage;
	public String secondStage;
	public String statusVal;
	public List<VCheckRequest> checkRequests=new ArrayList<VCheckRequest>();
	
	public List<VCheckRequest> getCheckRequests() {
		return checkRequests;
	}

	public void setCheckRequests(List<VCheckRequest> checkRequests) {
		this.checkRequests = checkRequests;
	}

	public String getStatusVal() {
		if(status!=null&&status==1) statusVal="显示";
		else statusVal="隐藏";
		return statusVal;
	}
	private String parentStage;

	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}

	public String getParentStage() {
		return parentStage;
	}

	public void setParentStage(String parentStage) {
		this.parentStage = parentStage;
	}

	public String getFirstStage() {
		return firstStage;
	}

	public void setFirstStage(String firstStage) {
		this.firstStage = firstStage;
	}

	public String getSecondStage() {
		return secondStage;
	}

	public void setSecondStage(String secondStage) {
		this.secondStage = secondStage;
	}

	public String getPstagName() {
		return pstagName;
	}

	public void setPstagName(String pstagName) {
		this.pstagName = pstagName;
	}

	public Date getPcreateTime() {
		return pcreateTime;
	}

	public void setPcreateTime(Date pcreateTime) {
		this.pcreateTime = pcreateTime;
	}

	public String getTimeHandle() {
		if (this.getPcreateTime() != null) {
			if (super.getCreateTime() != null) {
				return DateUtil.formatDateTime(super.getCreateTime());
			}
			return DateUtil.formatDateTime(this.getPcreateTime());
		}
		if (super.getCreateTime() != null) {
			return DateUtil.formatDateTime(super.getCreateTime());
		}
		return "";
	}
}
