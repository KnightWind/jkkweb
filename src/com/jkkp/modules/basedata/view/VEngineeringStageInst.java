package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.EngineeringStageInst;
import com.jkkp.modules.supplier.view.VSupplier;
public class VEngineeringStageInst extends EngineeringStageInst{

	public VEngineerings engineerings;				//工程单详情
	public VSupplier supplier;						//监理详情
	public String finishFlagVal;					//是否完成
	public String reportPassVal;					//验收通过
	
	
	public String getReportPassVal() {
		if(reportPass!=null&&1==reportPass) reportPassVal="已通过";
		else reportPassVal="不通过";
		return reportPassVal;
	}

	public void setReportPassVal(String reportPassVal) {
		this.reportPassVal = reportPassVal;
	}

	public String getFinishFlagVal() {
		if(finishFlag!=null&&1==finishFlag) finishFlagVal="合格";
		else finishFlagVal="整改";
		return finishFlagVal;
	}

	public void setFinishFlagVal(String finishFlagVal) {
		this.finishFlagVal = finishFlagVal;
	}

	public VEngineerings getEngineerings() {
		return engineerings==null?new VEngineerings():engineerings;
	}

	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}
	
}