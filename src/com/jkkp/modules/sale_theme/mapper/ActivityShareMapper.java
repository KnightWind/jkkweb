package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityShare;

public interface ActivityShareMapper extends Mapper<ActivityShare> {

	List<ActivityShare> findHasLotteryChanceRecords(Map<String, Object> map);
	
}
