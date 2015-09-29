package com.jkkp.modules.sale_theme.view;

import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.model.ActivityTheme;

public class VUserActivityList extends ActivityJionSign {
	public ActivityTheme activityTheme;
	private String nickname;
	public  String activityThemeImg ;
	public ActivityTheme getActivityTheme() {
		return activityTheme;
	}
	public void setActivityTheme(ActivityTheme activityTheme) {
		this.activityTheme = activityTheme;
	}
	public String getActivityThemeImg() {
		return activityThemeImg;
	}
	public void setActivityThemeImg(String activityThemeImg) {
		this.activityThemeImg = activityThemeImg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
