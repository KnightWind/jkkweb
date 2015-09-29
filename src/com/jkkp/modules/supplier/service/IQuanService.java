package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Quan;
import com.jkkp.modules.supplier.view.VQuan;

public interface IQuanService extends IService<Quan, VQuan, Integer> {
	List<VQuan> finAll();

	Quan operate(Integer id, boolean isOpen);
	
	void offline(Integer id);

	void online(Integer id);
}
