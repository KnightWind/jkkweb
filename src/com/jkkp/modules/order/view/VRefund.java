package com.jkkp.modules.order.view;

import java.math.BigDecimal;
import com.jkkp.modules.order.model.Refund;

public class VRefund extends Refund {
	private BigDecimal paySumPrice;
	private BigDecimal itemSumPrice;
	private BigDecimal discount;
	private String contactUser;
	private String contactMobile;

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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getContactUser() {
		return contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

}

