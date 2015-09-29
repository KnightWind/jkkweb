package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.view.VJtopic;

public interface IJtopicService extends IService<Jtopic, VJtopic, Integer> {

	VJtopic findVJtopicById(Integer id);

	public VJtopic businessDetail(int id);

	public void noPass(int id);

	public void pass(int id);
	
	public void deleteOneTopic(int id);
}
