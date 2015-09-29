package com.jkkp.modules.shop.service;

import com.jkkp.common.IService;
import com.jkkp.modules.shop.model.ShopBanner;
import com.jkkp.modules.shop.view.VShopBanner;

public interface IShopBannerService extends IService<ShopBanner,VShopBanner,Integer>{
	public ShopBanner operate(Integer id, boolean isOpen);
}
