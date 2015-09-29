package com.jkkp.modules.sale_theme.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityShareMapper;
import com.jkkp.modules.sale_theme.model.ActivityShare;
import com.jkkp.modules.sale_theme.service.IActivityShareService;
import com.jkkp.modules.sale_theme.view.VActivityShare;

@Service
public class ActivityShareService extends ServiceSupport<ActivityShare, VActivityShare, Integer> implements IActivityShareService {

	@Autowired
	private ActivityShareMapper mapper;
	
	@Override
	protected Mapper<ActivityShare> getMapper() {
		return mapper;
	}

	@Override
	public List<ActivityShare> findHasLotteryChanceRecords(int activityId, String phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("phone", phone);
		return mapper.findHasLotteryChanceRecords(map);
	}


}
