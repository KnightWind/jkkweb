package com.jkkp.client.alipay.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jkkp.client.alipay.config.AlipayConfig;
import com.jkkp.client.alipay.util.AlipayNotify;
import com.jkkp.client.alipay.util.AlipaySubmit;
import com.jkkp.client.alipay.util.SignUtils;
import com.jkkp.client.alipay.util.UtilDate;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.utils.CommonUtil;

public class AlipayService {

	public static final int WAP_TYPE = 1; // 微信端支付
	public static final int ANDROID_TYPE = 2; // Android支付
	public static final int IOS_TYPE = 3; // IOS支付
	public static final int NETBANK_TYPE = 4; // PC网银

	public String createRequestParams(PaymentRecord record, String returnUrl) {
		return this.createRequestParams(record.getSerialCode(), record.getTitle(), String.valueOf(record.getAmount()),
				returnUrl);
	}

	public String createRequestParams(String orderNo, String subject, String orderAmount, String returnUrl) {
		return this.createRequestParams(orderNo, subject, orderAmount, null, null, null, null, returnUrl);
	}

	public String createRequestParams(String orderNo, String subject, String orderAmount, String show_url, String body,
			String timeout, String token, String returnUrl) {
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("notify_url", AlipayConfig.NOTIFY_URL); // 服务器异步通知页面路径
		sParaTemp.put("return_url", returnUrl); // 页面跳转同步通知页面路径
		sParaTemp.put("out_trade_no", orderNo); // 商户订单号,必填
		sParaTemp.put("subject", subject); // 订单名称,必填,最长为 128 个汉字
		sParaTemp.put("total_fee", orderAmount); // 付款金额,必填,精确到小数点后两位
		sParaTemp.put("body", CommonUtil.isNull(body, "")); // 订单描述，选填
		sParaTemp.put("show_url", show_url); // 商品展示地址，选填
		sParaTemp.put("it_b_pay", CommonUtil.isNull(timeout, "")); // 超时时间，选填
		sParaTemp.put("extern_token", CommonUtil.isNull(token, ""));// 钱包token，选填

		// 建立请求
		return AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
	}

	public boolean verifyResult(Map<String, String> data) {
		// String out_trade_no = data.get("out_trade_no"); // 商户订单号
		// String trade_no = data.get("trade_no"); // 支付宝交易号
		// String trade_status = data.get("trade_status"); // 交易状态

		// 计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(data, AlipayConfig.sign_type_md5);
		if (verify_result) {// 验证成功
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String orderNo, String price) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + AlipayConfig.seller_email + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + "商品详情" + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + AlipayConfig.NOTIFY_APP_URL + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	public String createAppPayment(String subject, String orderNo, String price) {
		String orderInfo = this.getOrderInfo(subject, orderNo, price);
		String sign = SignUtils.sign(orderInfo, AlipayConfig.APP_PRIV_KEY);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 完整的符合支付宝参数规范的订单信息
		return orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
	}

	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	public String createAppParams(PaymentRecord record) {
		return this.createAppPayment(record.getTitle(), record.getSerialCode(), String.valueOf(record.getAmount()));
	}
	
	
	/**
	 * 获取退款报文
	 * @param record
	 * @return
	 */
	public String createAppRefundParams(PaymentRecord record){
		String rerundStr= createAppRefundInfo(record);
		String sign = SignUtils.sign(rerundStr, AlipayConfig.APP_PRIV_KEY);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		// 完整的符合支付宝参数规范的订单信息
		return rerundStr + "&sign=" + sign + "&sign_type=RSA";
	}
	
	/**
	 * 组装退款信息报文
	 * @param record
	 * @return
	 */
	private String createAppRefundInfo(PaymentRecord record){
		
		String orderInfo = "service=refund_fastpay_by_platform_pwd";
		orderInfo += "&partner=" + AlipayConfig.partner;
		orderInfo += "&_input_charset=UTF-8" ;
		orderInfo += "&notify_url="+AlipayConfig.NOTIFY_APP_REFUND_URL;
		orderInfo += "&seller_user_id=" + AlipayConfig.seller_email;
		// yyyy-MM-dd HH:mm:ss
		orderInfo += "&refund_date=" + UtilDate.getDateFormatter();
		/**
		 * 退款批次号
		 *  格式为：退款日期（ 8 位）+流水号（ 3～24 位）。
			不可重复，且退款日期必须
			是当天日期。
			String.valueOf(new Date().getTime())以这个方式获取唯一值有风险，后期需要修改
		 */
		orderInfo += "&batch_no="+UtilDate.getDate()+String.valueOf(new Date().getTime());		

		// 总笔数
		orderInfo += "&batch_num=1";

		// 单笔数据集  原付款支付宝交易号^退款总金额^退款理由#
		orderInfo += "&detail_data="+record.getTradeNo()+"^"+record.getAmount()+"^"+"无";

		return orderInfo;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(new AlipayService().createAppPayment("定金", "12122121", "0.01"));
		PaymentRecord record = new PaymentRecord();
		record.setTradeNo("fe6fdda3bff3-832246239ZGT");
		record.setAmount(0.02f);
		System.out.println(new AlipayService().createAppRefundParams(record));
	}
}
