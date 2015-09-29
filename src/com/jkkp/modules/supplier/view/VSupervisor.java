package com.jkkp.modules.supplier.view;

import com.jkkp.modules.basedata.view.*;
import com.jkkp.modules.supplier.model.*;

public class VSupervisor extends Supervisor{
	public VEngineerings engineerings=null;
	public VEngineeringStageInst engineeringStageInst=null;
	
	public VEngineeringStageInst getEngineeringStageInst() {
		return engineeringStageInst;
	}

	public void setEngineeringStageInst(VEngineeringStageInst engineeringStageInst) {
		this.engineeringStageInst = engineeringStageInst;
	}

	public VEngineerings getEngineerings() {
		return engineerings;
	}

	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}

	
}
