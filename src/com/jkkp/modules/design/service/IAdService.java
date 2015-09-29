package com.jkkp.modules.design.service;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Ad;
import com.jkkp.modules.design.view.VAd;

public interface IAdService extends IService<Ad,VAd,Integer> {
	void saveUpdate(Ad ad);
}
