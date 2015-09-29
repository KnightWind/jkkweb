package com.jkkp.modules.product.service;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.PorderList;
import com.jkkp.modules.product.view.VPorderList;
public interface IPorderListService extends IService<PorderList, VPorderList, Integer> {

	VPorderList findPordeerById(Integer id);
	
}
