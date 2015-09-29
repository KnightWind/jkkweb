package com.jkkp.client.alipay.config;

import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088711639685651";
	
	// 收款支付宝账号
	public static String seller_email = "bjjutianxia@163.com";
	// 商户的私钥
	public static String key = "xactw13ufg17zge5ijgda5g6h1r4lb5j";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type_md5 = "MD5";
	
	// 签名方式 不需修改
	public static String sign_type_RSA = "RSA";
	
	public static String NOTIFY_URL = Sysconfig.CONFIG_PARAM.get(ConfigConstant.NOTIFY_URL);
	
	public static String NOTIFY_APP_URL = Sysconfig.CONFIG_PARAM.get(ConfigConstant.NOTIFY_APP_URL);

	public static String NOTIFY_APP_REFUND_URL = Sysconfig.CONFIG_PARAM.get(ConfigConstant.NOTIFY_APP_REFUND_URL);
	
	//商户私钥，pkcs8格式
	public static final String APP_PRIV_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOvKwhQ28i2kgoS5gRYLfmV" +
			"1qeTX3E3q6jVvfl/iDltRjaQcvYb4hwFuclZnEIbR/QVD77KQqS+iMuPwuw4UOnONV4ITToekVg28Ux0+WXu4iBCbznNNe0uKkXbEMNKgw2s" +
			"Rn1deDkp9wFdIec/FffTdr1fWn5GouML7HlE6ZM1nAgMBAAECgYB8xB8J/0EKccA2ZsotVguduDMbyjTibelcORDPv5gFLhQawLJw1tTpJv4N" +
			"Rh/ylu6heJ8/B1jewZ7YCiuQeZerROrHJKPxmjwNr+6hIbVE2TdHouo0SAlwjLAHCewXwB4AgewysvDA1IOIv2Bou3uI6s/sz81aUkDnA+O2" +
			"EinqoQJBAP+VnrSaEMTQdK9tWQLQWKOf+XzPoqZiWB9jmTUO5EpvEvzn1S0H8CXafBfuNaLrKoigguZkBU4hOCr/Muc1oUkCQQDsLOZu" +
			"MxUDaqsMXRlveVH8DqJGLmgarN7/oYglvGJECcd6mcxJH9aEdEOrrAJXz9aViuokYRfdmiz6tiTSe6kvAkEA4f0N42sGwMABLMxnFBHdDnD5TN" +
			"7bVntGqBqM8VlFintEMtNarwKB2Tai2xYJ3xBI/53NoKF3/2loHLyh6caF4QJBAMYhlN72M7a+Eek5YVFHciQHEtPVsrhONnY2SUV/GOEDYzn" +
			"TNLJT518POoYWs5B/aAHknDEQ0zixUvKxQ4rg+zkCQFSv2MH5vjKAqrxWKov7my9Vrbu3ZZhQbWGH6dumneQQPc90OvzySEkVhbMXW7" +
			"YAw1LHldCZ47zayab86XBi8es=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	//支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrysIUNvItpI" +
			"KEuYEWC35ldank19xN6uo1b35f4g5bUY2kHL2G+IcBbnJWZxCG0f0FQ++ykKkvojLj8LsOFDpzjVeCE06Hp" +
			"FYNvFMdPll7uIgQm85zTXtLipF2xDDSoMNrEZ9XXg5KfcBXSHnPxX303a9X1p+RqLjC+x5ROmTNZwIDAQAB";
}
