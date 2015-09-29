package com.jkkp.appapi.modules.mapper;

import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

public class VPaymentRecordApi extends PaymentRecord {
	private String imgurl;// 头像
	private String paynameString;// 支付名称
	private String payresultString;// 支付结果
	private String paytypeString;// 交易类型
	private String paywayString;// 交易方式
	private String paynameLongString;// /支付名称长
	private String detialimgString;// 商家/用户头像
	private Integer paymentresult;
	/*
	 * public static final int TYPE_DEPOSIT = 1; // 订金 public static final int
	 * TYPE_WALLET = 2; // 充值 public static final int TYPE_JIANLI = 3; // 监理款
	 * 
	 * public static final int PAY_TYPE_ALIPAY = 1; // 支付宝 public static final
	 * int PAY_TYPE_YEEPAY = 2; // 易宝
	 */

	public static final int RESULT_FAILURE = 0; // 支付失败（没有产生支付记录）
	public static final int RESULT_SUCCESS = 1; // 支付成功（支付成功）
	public static final int RESULT_WAIT_BUYER_PAY = 2; // 等待用户支付（支付宝产生交易记录）
	public static final int RESULT_CLOSED = 3; // 支付关闭（产生支付记录，但是没有支付）

	public String getImgurl() {
		if (this.getType() == this.TYPE_DEPOSIT)
			imgurl = Sysconfig.CONFIG_PARAM.get(ConfigConstant.IMG_DEPOSIT);
		// return
		// "http://dev.jiakeke.com:8090/attachment/uploadFile/2015-07-25/dd82c99d-90c9-4e37-bd47-11e3ef09f30a.png";
		if (this.getType() == this.TYPE_JIANLI)
			imgurl = Sysconfig.CONFIG_PARAM.get(ConfigConstant.IMG_JIANLI);
		// return
		// "http://dev.jiakeke.com:8090/attachment/uploadFile/2015-07-25/1a4d963d-902a-4991-afcc-5c3d02bd270c.png";
		return imgurl;
	}

	public Integer getPaymentresult() {
		return paymentresult;
	}

	public void setPaymentresult(Integer paymentresult) {
		this.paymentresult = paymentresult;
	}

	public String getDetialimgString() {
		return detialimgString;
	}

	public void setDetialimgString(String detialimgString) {
		this.detialimgString = detialimgString;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getPaynameString() {
		if (this.getType() == this.TYPE_DEPOSIT)
			return "订金";
		if (this.getType() == this.TYPE_JIANLI)
			return "监管款";
		if (this.getType() == this.TYPE_WALLET)
			return "充值";
		return paynameString;
	}

	public void setPaynameString(String paynameString) {
		this.paynameString = paynameString;
	}

	public String getPayresultString() {
		return payresultString;
	}

	public void setPayresultString(String payresultString) {
		this.payresultString = payresultString;
	}

	public String getPaytypeString() {
		return paytypeString;
	}

	public void setPaytypeString(String paytypeString) {
		this.paytypeString = paytypeString;
	}

	public String getPaywayString() {
		if (this.getPayType() == this.PAY_TYPE_ALIPAY)
			return "支付宝";
		if (this.getPayType() == this.PAY_TYPE_YEEPAY)
			return "易宝";
		if (this.getPaymentresult() == PAY_TYPE_WEIXIN)
			return "微信支付";
		return paywayString;
	}

	public void setPaywayString(String paywayString) {
		this.paywayString = paywayString;
	}

	public String getPaynameLongString() {
		return paynameLongString;
	}

	public void setPaynameLongString(String paynameLongString) {
		this.paynameLongString = paynameLongString;
	}

}
