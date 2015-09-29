package com.jkkp.pc.apply.service;

import com.jkkp.common.IService;
import com.jkkp.pc.apply.model.EcshopHomepageApply;
import com.jkkp.pc.apply.view.VEcshopHomepageApply;

public interface IEcshopHomepageApplyService extends IService<EcshopHomepageApply, VEcshopHomepageApply, Integer> {
	public void insertHomepageApply(EcshopHomepageApply ecshopHomepageApply);
}
