package com.jkkp.modules.order.view;

import java.math.BigDecimal;

import com.jkkp.modules.design.model.PackageItem;

public class VOrderPackageItem extends PackageItem {
	private String title;
	private BigDecimal itemPrice;
	private int num;
	private String pid;
	private int vitemId;

	public int getVitemId() {
		return vitemId;
	}

	public void setVitemId(int vitemId) {
		this.vitemId = vitemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}


}
