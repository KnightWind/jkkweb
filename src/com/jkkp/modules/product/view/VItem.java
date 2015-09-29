package com.jkkp.modules.product.view;

import com.jkkp.modules.product.model.Item;

/**
 * 梁怡立
 * 
 * @author Administrator 2015-05-08
 */
public class VItem extends Item {
	public String bname;
	
	/**
	 * 标识商品是否为众筹商品，如果是应为1，不是为0
	 */
	private Integer cnt;
	
	/**
	 * 如果是商品是众筹商品，众筹商品拿出状态
	 */
	private Integer zitemStatus;
	
	
	public String getCrowdStatus(){
		if(zitemStatus != null){
			switch(zitemStatus){
			case -1:
				return "未通过审核";
			case 0:
				return "待审核";
			case 1:
				return "已通过审核";
			case 2:
				return "申请取消众筹";
			case 3:
				return "申请取消众筹通过";
			case 4:
				return "审核取消众筹未通过";
			}
			
		}
		return "未设置众筹";
	}

	public Integer getZitemStatus() {
		return zitemStatus;
	}

	public void setZitemStatus(Integer zitemStatus) {
		this.zitemStatus = zitemStatus;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
}
