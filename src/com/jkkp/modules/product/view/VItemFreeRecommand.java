package com.jkkp.modules.product.view;

import java.math.BigDecimal;

import com.jkkp.modules.product.model.ItemFreeRecommand;

public class VItemFreeRecommand extends ItemFreeRecommand{
	public String title;
	public BigDecimal price;
	public String pid;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}
