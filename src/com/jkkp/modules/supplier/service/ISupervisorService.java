package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Supervisor;
import com.jkkp.modules.supplier.view.VSupervisorWeb;

public interface ISupervisorService extends
		IService<Supervisor, VSupervisorWeb, Integer> {
	public VSupervisorWeb selectSupervisorDetail(int id);
}
