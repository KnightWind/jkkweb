package com.jkkp.modules.activities.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.view.VAwards;

public interface AwardsMapper extends Mapper<Awards> {

	List<VAwards> findPage(Map<String, Object> params);
	long countPage(Map<String, Object> params);
	
}
