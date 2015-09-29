package com.jkkp.modules.product.view;

import java.math.BigDecimal;

import com.jkkp.modules.product.model.ItemRecommand;

public class VItemRecommand extends ItemRecommand{
	public String title;
	public BigDecimal price;
	public String img;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

}
