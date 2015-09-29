package com.jkkp.modules.order.view;

import java.math.BigDecimal;
import com.jkkp.modules.order.model.OrderQuan;

public class VOrderQuan extends OrderQuan {

    private String nickName;
    private String spName;
    private BigDecimal parValue;
    private BigDecimal price;
    private int type; 
    private String city;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public BigDecimal getParValue() {
		return parValue;
	}
	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
		
	public String getTypeHandle(){
		if(type==1){
			return "全品券";
		}else if(type==2){
			return "店铺券";
		}else if(type==3){
			return "O2O券";
			
		}
		
		return "";
	}
}
