package com.jkkp.modules.product.service;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.modules.product.view.VPorderType;

public interface IPorderTypeService extends
		IService<PorderType, VPorderType, Integer> {
	public void hide(int id);

	public void show(int id);

	public void saveOrUpdate(PorderType pt);
}
