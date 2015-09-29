package com.jkkp.modules.sale_theme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityGiftLogMapper;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.service.IActivityGiftLogService;

@Service("activityGiftLogService")
public class ActivityGiftLogServiceImpl extends
		ServiceSupport<ActivityGiftLog, ActivityGiftLog, Integer> implements
		IActivityGiftLogService {

	@Autowired
	private ActivityGiftLogMapper activityGiftLogMapper;
	
	

	@Override
	protected Mapper<ActivityGiftLog> getMapper() {
		return activityGiftLogMapper;
	}

}
