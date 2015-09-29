package com.jkkp.modules.sale_theme.view;

import com.jkkp.modules.sale_theme.model.ActivityProduct;

public class VActivityProduct extends ActivityProduct {
	private String itemTile;
	private String detail;
	private String imagePath;
	private Boolean isExist=false;
	private Double iprice;
	private String filepath;

	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Double getIprice() {
		return iprice;
	}

	public void setIprice(Double iprice) {
		this.iprice = iprice;
	}

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

	public String getItemTile() {
		return itemTile;
	}

	public void setItemTile(String itemTile) {
		this.itemTile = itemTile;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
