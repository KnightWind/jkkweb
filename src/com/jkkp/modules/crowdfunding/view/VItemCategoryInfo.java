package com.jkkp.modules.crowdfunding.view;

import com.jkkp.modules.crowdfunding.model.ItemCategoryInfo;

public class VItemCategoryInfo extends ItemCategoryInfo {
	private String name;
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
