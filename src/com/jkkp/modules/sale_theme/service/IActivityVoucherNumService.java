package com.jkkp.modules.sale_theme.service;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.view.VActivityVoucherNum;

public interface IActivityVoucherNumService extends
		IService<ActivityVoucherNum, VActivityVoucherNum, Integer> {
	// 领取现金券
	public Integer insertOneVoucher(String phone, int voucherId);
}
