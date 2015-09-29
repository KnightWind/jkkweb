package com.jkkp.modules.sale_theme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityRecommendMapper;
import com.jkkp.modules.sale_theme.model.ActivityRecommend;
import com.jkkp.modules.sale_theme.service.IActivityRecommendService;
import com.jkkp.modules.sale_theme.view.VActivityRecommend;

@Service("activityRecommendService")
public class ActivityRecommendServiceImpl extends ServiceSupport<ActivityRecommend, VActivityRecommend, Integer>
		implements IActivityRecommendService {

	@Autowired
	private ActivityRecommendMapper activityRecommendMapper;
	
	@Override
	protected Mapper<ActivityRecommend> getMapper() {
		return activityRecommendMapper;
	}

	
}
