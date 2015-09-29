package com.jkkp.modules.shop.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.shop.mapper.ShopBannerMapper;
import com.jkkp.modules.shop.model.ShopBanner;
import com.jkkp.modules.shop.service.IShopBannerService;
import com.jkkp.modules.shop.view.VShopBanner;


@Service("shopBannerService")
public class ShopBannerServiceImpl  extends
ServiceSupport<ShopBanner,VShopBanner,Integer> implements IShopBannerService {
	@Autowired
	private  ShopBannerMapper shopBannerMapper;
	@Override
	protected Mapper<ShopBanner> getMapper() {		
		return shopBannerMapper;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ShopBanner operate(Integer id, boolean isOpen) {
		ShopBanner shopBanner=this.findById(id);
		if(isOpen){
			shopBanner.setStatus(new Byte("-1"));
			shopBanner.setCreateTime(new Date());
		}else {
			shopBanner.setStatus(new Byte("0"));
			shopBanner.setCreateTime(new Date());
		}
		this.update(shopBanner);
		return shopBanner;
	}
	
}
