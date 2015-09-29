package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.view.VActivityTheme;

public interface ActivityThemeMapper extends Mapper<ActivityTheme> {
	// 微信引流活动 web后台
	public List<VActivityTheme> selectAllActivityTheme(Map<String, Object> map);

	public long selectAllActivityThemeCount(Map<String, Object> map);
	
	
	//微信引流活动主题   微信活动
	public List<VActivityTheme> selectAllActivityThemeWX();

	public ActivityTheme latest();
}
