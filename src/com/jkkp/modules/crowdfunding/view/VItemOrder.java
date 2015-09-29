package com.jkkp.modules.crowdfunding.view;

import java.util.Date;

import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.utils.DateUtil;

public class VItemOrder extends ActiivityOrder {
	private String mobile;
	private String title;
	private Integer refundId;
	private String spName;
	private Integer jsId;
	private Date jsTime;
	private Integer codeType;
	private Integer itemType;
	private Float condePrice;
	private String recommendMobile;
	private String recommendName;

	public String getRecommendMobile() {
		return recommendMobile==null?"":recommendMobile;
	}

	public void setRecommendMobile(String recommendMobile) {
		this.recommendMobile = recommendMobile;
	}

	public String getRecommendName() {
		return recommendName==null?"":recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public Float getCondePrice() {
		return condePrice;
	}

	public void setCondePrice(Float condePrice) {
		this.condePrice = condePrice;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public Integer getJsId() {
		return jsId;
	}

	public void setJsId(Integer jsId) {
		this.jsId = jsId;
	}

	public Date getJsTime() {
		return jsTime;
	}

	public void setJsTime(Date jsTime) {
		this.jsTime = jsTime;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public Integer getRefundId() {
		return refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderStatus(){
		if(this.getRefundId()==null){
			if(super.getMoneyState()==0){
				return "未付款";
			}
			return "已付款";
		}
		return "已退款";
	}
	
	public String getJsTimeHandle(){
		return DateUtil.formatDateTime(jsTime);
	}
	
	public String getJsStatusVal(){
		if(this.getJsId()!=null){
			return "已结算";
		}
		return "未结算";
	}
	
	//判断显示扫码还是商品   标题
	public String getItemTitleShowVal(){
		if(this.getItemType()!=null){
			if(itemType==1){
				return title==null?"":title;
			}
			if(itemType==2){
			    return "扫码订单";
			}
			
		}
		return "";
	}
	//判断显示扫码还是商品 价格
	public Object getItemPriceShowVal(){
		if(itemType!=null){
			if(itemType==1){
				return super.getOrderPrice();
			}
			if(itemType==2){
				if(codeType!=null&&codeType==1){
					return "定金-"+condePrice;
				}
				if(codeType!=null&&codeType==2){
					return "全款-"+condePrice;
				}
			}
		}
		return "";
	}
	
	//判断显示的支付信息
	public Object getPayMoneyVal(){
		if(itemType!=null){
			if(itemType==1){
				return super.getMoneyCount();
			}
			if(itemType==2){
				return condePrice;
			}
		}
		return "";
	}
}
