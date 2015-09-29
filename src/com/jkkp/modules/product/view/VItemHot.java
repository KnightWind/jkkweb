package com.jkkp.modules.product.view;

import com.jkkp.modules.product.model.Item;

/**
 * 梁怡立
 * 
 * @author Administrator 2015-05-08
 */
public class VItemHot extends Item{
private Double promotionalprice;//促销价钱
private Double saveprice;//节省价钱
private String itemimg;//活动图片
public Double getPromotionalprice() {
	return promotionalprice;
}
public void setPromotionalprice(Double promotionalprice) {
	this.promotionalprice = promotionalprice;
}
public Double getSaveprice() {
	return saveprice;
}
public void setSaveprice(Double saveprice) {
	this.saveprice = saveprice;
}
public String getItemimg() {
	return itemimg;
}
public void setItemimg(String itemimg) {
	this.itemimg = itemimg;
}


}
