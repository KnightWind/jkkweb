package com.jkkp.modules.order.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jkkp.modules.order.model.OrderId;


public class VOrderId extends OrderId {
	private String nickName;
	private int shipCity;
	private String contactUser;
	private String spName;
	private String contactMobile;
	private String address;
	private BigDecimal paySumPrice;	
	private BigDecimal itemSumPrice;
	private Date createTime;
	private Date shipTime;
	private int vstatus;
	private String city;
	private String area;
	private String regionName;
	private String province;
	private int mainId;
	private int receiptTitleType;
	private int receipType;
    private BigDecimal discount;

	
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public int getMainId() {
		return mainId;
	}
	public void setMainId(int mainId) {
		this.mainId = mainId;
	}
	public int getReceiptTitleType() {
		return receiptTitleType;
	}
	public void setReceiptTitleType(int receiptTitleType) {
		this.receiptTitleType = receiptTitleType;
	}
	public int getReceipType() {
		return receipType;
	}
	public void setReceipType(int receipType) {
		this.receipType = receipType;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setVstatus(int vstatus) {
		this.vstatus = vstatus;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getShipCity() {
		return shipCity;
	}
	public void setShipCity(int shipCity) {
		this.shipCity = shipCity;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getPaySumPrice() {
		return paySumPrice;
	}
	public void setPaySumPrice(BigDecimal paySumPrice) {
		this.paySumPrice = paySumPrice;
	}
	public BigDecimal getItemSumPrice() {
		return itemSumPrice;
	}
	public void setItemSumPrice(BigDecimal itemSumPrice) {
		this.itemSumPrice = itemSumPrice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getShipTime() {
		return shipTime;
	}
	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}
	public int getVstatus() {
		return vstatus;
	}
	public void setvstatus(int vstatus) {
		this.vstatus = vstatus;
	}
	
	public String getVstatusVal(){
		if(vstatus==0){
			return "待付款";
		}
		if(vstatus==1){
			return "待发货";
		}
		if(vstatus==2){
			return "已发货";
		}
		if(vstatus==3){
			return "已收货";
		}
		if(vstatus==4){
			return "已完成";
		}
		if(vstatus==-1){
			return "已取消";
		}
		return "";
		
	}
	
	public String getVcreateTime(){
		if(createTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(createTime);
		}
		return "";
	}
	
	public String getVshipTime(){
		if(shipTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(shipTime);
		}
		return "";
	}
	
	
	public String getReceiptTitleTypeVal(){
		if(receiptTitleType==1){
			return "个人";
		}else if(receiptTitleType==2){
			return "单位";
		}
		return "";
	}
	
	public String getReceipTypeVal(){
		if(receipType==1){
			return "日常用品";
		}else if(receipType==2){
			return "办公用品";
		}else if(receipType==3){
			return "明细";
		}
		return "";
	}
	
}
