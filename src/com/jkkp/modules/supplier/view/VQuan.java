package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.Quan;

public class VQuan extends Quan{
	public String city;
    public int qcStatus;
    public String spName;
    public int shopId;
	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(int qcStatus) {
		this.qcStatus = qcStatus;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public Integer num;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getSpNameHandle(){
		if(this.shopId>0){
			return spName;
		}
		return"所有线上店铺";
	}
}
