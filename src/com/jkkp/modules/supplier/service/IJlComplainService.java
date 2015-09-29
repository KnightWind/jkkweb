package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.JlComplain;
import com.jkkp.modules.supplier.view.VJlComplain;

public interface IJlComplainService extends
		IService<JlComplain, VJlComplain, Integer> {
	public void close(int id);

	public VJlComplain complainDetail(int id);
}
