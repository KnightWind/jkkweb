package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.Activity;

public interface ActivityMapper extends Mapper<Activity>  {
	
	public List<Map<String, Object>> getCrowdItemType(Map<String,Object> map);

	public List<Map<String,Object>>  getJoinUserInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> getActivityList();
	
	public List<Map<String, Object>> getActivityHotItem(Map<String,Object> map);
}
