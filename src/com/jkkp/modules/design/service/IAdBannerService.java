package com.jkkp.modules.design.service;

import java.util.List;

import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.common.IService;
import com.jkkp.modules.design.model.AdBanner;
import com.jkkp.modules.design.view.VAdBanner;

public interface IAdBannerService extends IService<AdBanner,VAdBanner,Integer>{
	public AdBanner operate(Integer id, boolean isOpen);
	List<AdBanner> index(String name);
	List<AdBanner> news(String name);
	List<AdBanner> quan(String name);
	List<AdBanner> design(String name);
	void saveUpdate(AdBanner adBanner);
	VJlGcd querycount(Integer gcdId,Integer stageId );
}
