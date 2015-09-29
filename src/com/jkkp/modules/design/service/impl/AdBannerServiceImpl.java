package com.jkkp.modules.design.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.AdBannerMapper;
import com.jkkp.modules.design.model.AdBanner;
import com.jkkp.modules.design.service.IAdBannerService;
import com.jkkp.modules.design.view.VAdBanner;

@Service("adBannerService")
public class AdBannerServiceImpl extends
ServiceSupport<AdBanner,VAdBanner,Integer> implements IAdBannerService{
	@Autowired
	private AdBannerMapper adBannerMapper;
	
	@Override
	protected Mapper<AdBanner> getMapper() {		
		return adBannerMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AdBanner operate(Integer id, boolean isOpen) {
		AdBanner adBanner=this.findById(id);
		if(isOpen){
			adBanner.setStatus(new Byte("-1"));
			adBanner.setCreateTime(new Date());
		}else {
			adBanner.setStatus(new Byte("0"));
			adBanner.setCreateTime(new Date());
		}
		this.update(adBanner);
		return adBanner;
	}

	@Override
	public List<AdBanner> index(String name) {
		return adBannerMapper.index(name);
	}

	@Override
	public List<AdBanner> news(String name) {
		return adBannerMapper.news(name);
	}

	@Override
	public List<AdBanner> quan(String name) {
		return adBannerMapper.quan(name);
	}

	@Override
	public List<AdBanner> design(String name) {
		return adBannerMapper.design(name);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdate(AdBanner adBanner) {
	if(adBanner.getId()!=null&&adBanner.getId()>0){
		AdBanner adBanner1=this.findById(adBanner.getId());
		adBanner1.setPlace(adBanner.getPlace());
		adBanner1.setTitle(adBanner.getTitle());
		adBanner1.setSeq(adBanner.getSeq());
		adBanner1.setStatus(adBanner.getStatus());
		this.update(adBanner1);
	}else {
		adBanner.setCreateTime(new Date());
		adBanner.setAdminId(1);
		adBanner.setUpdateTime(new Date());
		this.save(adBanner);
	}
		
	}

	@Override
	public VJlGcd querycount(Integer gcdId, Integer stageId) {
		return adBannerMapper.querycount(gcdId, stageId);
	}

}
