package com.jkkp.modules.order.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jkkp.modules.order.model.OrderPackage;

public class VOrderPackage extends OrderPackage {
	private int oid;
	private String packageName;
	private BigDecimal packagePrice;
	private int status;
    private Date createTime;
    private BigDecimal itemPrice;
    private String title;
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public BigDecimal getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(BigDecimal packagePrice) {
		this.packagePrice = packagePrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getCreateTimeVal(){
		if(createTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(createTime);
		}
		return "";
	}
	
	public String getStatusHandle(){
		if(status==0){
			return "待付款";
		}
		if(status==1){
			return "待发货";
		}
		if(status==2){
			return "已发货";
		}
		if(status==3){
			return "已收货";
		}
		if(status==4){
			return "已完成";
		}
		if(status==-1){
			return "已取消";
		}
		
		return "";
	}
}
