package com.jkkp.modules.sale_theme.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.view.VActivityVoucher;

public interface IActivityVoucherService extends IService<ActivityVoucher, VActivityVoucher, Integer> {
	
	/***
	 * 
	 * @param activityId 促销活动ID
	 * @param pageNo
	 * @param pageSize
	 * @return 活动现金券列表
	 */
	List<VActivityVoucher> findActivityVouchers(Integer activityId, int pageNo, int pageSize);

	/**
	 * 现金券详情
	 * @param id
	 * @return
	 */
	VActivityVoucher getActivityVoucherById(Integer id);
	
	//和消现金券
	void useActivityVoucher(Integer id);
}
