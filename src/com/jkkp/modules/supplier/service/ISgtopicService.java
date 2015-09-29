package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Sgtopic;
import com.jkkp.modules.supplier.view.VSgtopic;

public interface ISgtopicService extends IService<Sgtopic, VSgtopic, Integer> {
	public VSgtopic sgtopicDetail(int id);

	public void noPass(int id);

	public void pass(int id);
}
