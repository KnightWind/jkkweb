package com.jkkp.modules.crowdfunding.view;

import com.jkkp.modules.crowdfunding.model.ActivityItem;

public class VZCItem extends ActivityItem {
	private String title;
	private Boolean zcFlag;
	private Integer itemId;
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getZcFlag() {
		return zcFlag;
	}

	public void setZcFlag(Boolean zcFlag) {
		this.zcFlag = zcFlag;
	}
	
	public String getFlagVal(){
		if(this.getZcFlag()!=null){
			if(this.getZcFlag()){
				return "是";
			}else{
				return "否";
			}
		}
		return "否";
	}
}
