package com.jkkp.modules.sale_theme.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityGiftMapper;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.service.IActivityGiftService;
import com.jkkp.modules.sale_theme.view.VActivityGift;
import com.jkkp.utils.CheckedUtil;

@Service("activityGiftService")
public class ActivityGiftServiceImpl extends ServiceSupport<ActivityGift,VActivityGift,Integer> implements IActivityGiftService {

	@Autowired
	private ActivityGiftMapper activityGiftMapper;
	
	@Override
	protected Mapper<ActivityGift> getMapper() {
		return activityGiftMapper;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void savaOrUpdate(ActivityGift gift) {
		gift.setUpdateTime(new Date());
		if(CheckedUtil.isNotEmpty(gift.getId())){
			this.update(gift);
		}else{
			this.save(gift);
		}
		
	}
	@Override
	public List<VActivityGift> selectMyActivityGift(Map<String, Object> map) {
		return activityGiftMapper.selectMyActivityGift(map);
	}
}
