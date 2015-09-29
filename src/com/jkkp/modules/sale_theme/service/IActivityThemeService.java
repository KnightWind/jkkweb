package com.jkkp.modules.sale_theme.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.view.VActivityTheme;

public interface IActivityThemeService extends IService<ActivityTheme, VActivityTheme, Integer> {
	public List<VActivityTheme> selectAllActivityThemeWX();

	public ActivityTheme getlatest();

	/**
	 * 
	 * @param activityId
	 * @return
	 */
	public VActivityTheme getActivityThemeById(int id);
}
