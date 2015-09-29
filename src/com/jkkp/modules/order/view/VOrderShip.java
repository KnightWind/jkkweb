package com.jkkp.modules.order.view;

import com.jkkp.modules.order.model.OrderShip;

public class VOrderShip extends OrderShip {
	public String spName;
	public double price;
	public Integer num;
	public String bankName;
	public String bankAuthor;
	public String bankAccount;
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAuthor() {
		return bankAuthor;
	}
	public void setBankAuthor(String bankAuthor) {
		this.bankAuthor = bankAuthor;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	 public double getBd(){
	    	return this.getPrice()*this.getNum();
	    }
	 public Integer mid;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public double ga;
	public double getGa() {
		return ga;
	}
	public void setGa(double ga) {
		this.ga = ga;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String img;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer tid;
	public String title;
}
