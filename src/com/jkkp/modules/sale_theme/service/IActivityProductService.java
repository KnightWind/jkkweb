package com.jkkp.modules.sale_theme.service;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.sale_theme.model.ActivityProduct;
import com.jkkp.modules.sale_theme.view.VActivityProduct;

public interface IActivityProductService extends IService<ActivityProduct, VActivityProduct, Integer> {
    /**
     * 获取一活动商品详情
     * @param id  活动商品表id
     * @return
     */
	public  VActivityProduct selectOneWXActivityProductDetail(int id);

	public void save(Integer id, Integer aid, String name, Double iprice, Double price, HttpServletRequest request, Integer order);

	public void remove(ActivityProduct ap, Item item);

	public VActivityProduct findProductInfoById(Integer id);
}
