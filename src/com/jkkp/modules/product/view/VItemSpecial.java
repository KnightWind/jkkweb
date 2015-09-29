package com.jkkp.modules.product.view;

import com.jkkp.modules.product.model.ItemSpecial;

public class VItemSpecial extends ItemSpecial {

	private Integer productId; // 商品编号
	private String productName; // 商品名称
	private Integer stock; // 库存
	private Double productPrice; // 商品价格

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

}
