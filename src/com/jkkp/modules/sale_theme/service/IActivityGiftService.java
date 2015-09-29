package com.jkkp.modules.sale_theme.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.view.VActivityGift;

public interface IActivityGiftService extends IService<ActivityGift, VActivityGift, Integer> {

	void savaOrUpdate(ActivityGift gift);
	
	public List<VActivityGift> selectMyActivityGift(Map<String, Object> map);
}
