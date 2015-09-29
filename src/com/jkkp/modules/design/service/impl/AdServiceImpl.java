package com.jkkp.modules.design.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.AdMapper;
import com.jkkp.modules.design.model.Ad;
import com.jkkp.modules.design.service.IAdService;
import com.jkkp.modules.design.view.VAd;
@Service("adService")
public  class AdServiceImpl extends ServiceSupport<Ad,VAd,Integer> implements IAdService{
	@Autowired
	private AdMapper adMapper;
	@Override
	protected Mapper<Ad> getMapper() {	
		return adMapper;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdate(Ad ad) {
		if(ad.getId()!=null&&ad.getId()>0){
			Ad ddAd=this.findById(ad.getId());
		ddAd.setUrl(ad.getUrl());
		ddAd.setPid(ad.getPid());
		this.update(ddAd);
		}else {
			this.save(ad);
		}
	}
	
}
