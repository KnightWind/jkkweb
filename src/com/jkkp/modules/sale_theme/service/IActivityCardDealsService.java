package com.jkkp.modules.sale_theme.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.view.Statistics;
import com.jkkp.modules.sale_theme.view.VActivityCardDeals;

public interface IActivityCardDealsService extends IService<ActivityCardDeals, VActivityCardDeals, Integer> {

	VActivityCardDeals findById(Integer businessId, int wxActivityVoucher);
    
	/**
	 * 检查用户是否已经购卡   。。    判断是否为会员
	 * @param phone  用户手机号
	 * @return
	 */
	List<ActivityCardDeals> check(String phone);

	long select(String phone, Integer cardId, Integer status);

	List<Statistics> caclSaleInfo(Map<String, Object> params);

	long getPrecedBySpId(int spId);

	String randomLottery(Map<String, Object> paramMap);
}
