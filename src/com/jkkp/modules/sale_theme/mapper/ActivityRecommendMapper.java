package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityRecommend;

public interface ActivityRecommendMapper extends Mapper<ActivityRecommend> {

	//web后台
	public List<ActivityRecommend> selectAllActivityRecommend(Map<String, Object> map);
	
	public Long selectAllActivityRecommendCount(Map<String, Object> map);
}
