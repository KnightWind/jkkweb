package com.jkkp.modules.sale_theme.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityShare;
import com.jkkp.modules.sale_theme.view.VActivityShare;

public interface IActivityShareService extends IService<ActivityShare, VActivityShare, Integer> {
	/**查找有可以有抽奖记录的分享记录*/
	public List<ActivityShare> findHasLotteryChanceRecords(int activityId, String phone);
}
