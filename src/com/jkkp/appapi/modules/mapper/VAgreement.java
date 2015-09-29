package com.jkkp.appapi.modules.mapper;

import com.jkkp.modules.basedata.view.*;
import com.jkkp.modules.order.model.Agreement;
public class VAgreement extends Agreement{
	public VEngineerings engineerings;

	public VEngineerings getEngineerings() {
		return engineerings;
	}

	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}
	
}
