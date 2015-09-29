package com.jkkp.modules.sale_theme.view;

import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;

public class VMyVouchers extends ActivityVoucherNum {
	public ActivityVoucher activityVoucher;
	private String path;
	
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ActivityVoucher getActivityVoucher() {
		return activityVoucher;
	}

	public void setActivityVoucher(ActivityVoucher activityVoucher) {
		this.activityVoucher = activityVoucher;
	}
	
}
