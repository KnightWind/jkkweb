package com.jkkp.modules.sale_theme.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityCardMapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.view.VActivityCard;
import com.jkkp.utils.CheckedUtil;

@Service("activityCardService")
public class ActivityCardService extends ServiceSupport<ActivityCard, VActivityCard, Integer> implements IActivityCardService {

	@Autowired
	private ActivityCardMapper activityCardMapper;
	
	@Override
	protected Mapper<ActivityCard> getMapper() {
		return activityCardMapper;
	}

	@Transactional(readOnly=true)
	public VActivityCard selectActivityCardById(Integer id) {
		return activityCardMapper.selectActivityCardById(id);
	}

	@Transactional(readOnly=false)
	public void savaOrUpdate(ActivityCard card) {
		card.setUpdateTime(new Date());
		if(CheckedUtil.isNotEmpty(card.getId())){
			this.update(card);
		}else{
			this.save(card);
		}
	}

}
