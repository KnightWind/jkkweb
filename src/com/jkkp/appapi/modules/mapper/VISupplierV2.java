package com.jkkp.appapi.modules.mapper;

import com.jkkp.modules.supplier.model.Supplier;

public class VISupplierV2 extends Supplier{

	private String intro;
	private int designnum;
	private int jinflag;
	private int sumstaff;
	private int sumcom;
	private int sumimg;
	private String headimg;
	private int capital;
	private int sumeng;
	
	public int getSumeng() {
		return sumeng;
	}
	public void setSumeng(int sumeng) {
		this.sumeng = sumeng;
	}
	public int getDesignnum() {
		return designnum;
	}
	public void setDesignnum(int designnum) {
		this.designnum = designnum;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getDesign_num() {
		return designnum;
	}
	public void setDesign_num(int design_num) {
		this.designnum = design_num;
	}
	public int getJinflag() {
		return jinflag;
	}
	public void setJinflag(int jinflag) {
		this.jinflag = jinflag;
	}
	public int getSumstaff() {
		return sumstaff;
	}
	public void setSumstaff(int sumstaff) {
		this.sumstaff = sumstaff;
	}
	public int getSumcom() {
		return sumcom;
	}
	public void setSumcom(int sumcom) {
		this.sumcom = sumcom;
	}
	public int getSumimg() {
		return sumimg;
	}
	public void setSumimg(int sumimg) {
		this.sumimg = sumimg;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	


}
