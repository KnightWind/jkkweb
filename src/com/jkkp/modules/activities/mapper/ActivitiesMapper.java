package com.jkkp.modules.activities.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.activities.model.Activities;
import com.jkkp.modules.activities.view.VActivities;

public interface ActivitiesMapper extends Mapper<Activities> {

	List<VActivities> findPage(Map<String, Object> params);
	long countPage(Map<String, Object> params);
	VActivities findByActId(Integer id);
}
