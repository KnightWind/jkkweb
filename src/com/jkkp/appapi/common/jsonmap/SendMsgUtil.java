package com.jkkp.appapi.common.jsonmap;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhugz
 * @Date: 2015年4月17日 上午9:24:48
 * @ModifyUser: zhugz
 * @ModifyDate: 2015年4月17日 上午9:24:48
 * @Version:V6.0
 */
public class SendMsgUtil {

	// String httpPath = "http://58.68.247.151:8022/sendSms.ashx ";/*老HTTP测试环境*/
	// String httpPath =
	// "http://58.68.247.137:9053/communication/sendSms.ashx";/*新HTTP线上环境*/

	//	private static String httpPath = "http://58.68.247.137:9053/communication/sendSms.ashx";/* 新HTTP测试环境 */
	//	private static final String cid = "8280"; // 客户端ID
	//	private static final String pwd = "jtx12345"; // 客户端密码
	//	private static final String productid = "201503101"; // 通道组id
	//	private static final String lcode = "123"; // 子号码
	//	private static final String ssid = "100000"; // 短信唯一标识,用于匹配状态报告
	//	private static final String format = "32"; // 短信类型:15普通短信,32长短信
	//	private static final String sign = "[签名]"; // 客户自定义签名,可以不填

	
	//*************************************************************************
		//新公司短信方式
	    private static String httpPath = "http://GATEWAY.IEMS.NET.CN/GsmsHttp";/* 新HTTP测试环境 */
		
		private static final String cid = "admin"; // 客户端ID
		private static final String pwd = "56554992"; // 客户端密码
		private static final String orgid = "69351"; // 机构id
		private static final String productid = "201503101"; // 通道组id
		private static final String lcode = "123"; // 子号码
		private static final String ssid = "100000"; // 短信唯一标识,用于匹配状态报告
		private static final String format = "32"; // 短信类型:15普通短信,32长短信
		private static final String sign = "[家可可]"; // 客户自定义签名,可以不填
		//private static final String sign = "[签名]"; // 客户自定义签名,可以不填
		private  String DateTime = ""; //当前时间
	
	
	/**
	 * 发送短信消息 方法说明
	 * 
	 * @Discription:扩展说明
	 * @param phones
	 * @param content
	 * @return
	 * @return String
	 * @Author: zhugz
	 * @Date: 2015年4月17日 下午7:18:08
	 * @ModifyUser：zhugz
	 * @ModifyDate: 2015年4月17日 下午7:18:08
	 * 
	 */

	private static String smsUser = "jiakeke2015";
	private static String smsPsw = "keke2015juTxia";
	private static String smsType = "";
	private static String smsBusiNo = "";

	public static String sendMsg(String phones, String content) {
		// 短信接口URL提交地址
		String url = "http://dev.getui.com/dos4.0/index.html#login";

		Map params = new HashMap();

		params.put(smsUser, "用户账号");
		params.put(smsPsw, "用户密码");
		params.put(smsType, "短信类别编号");
		params.put(smsBusiNo, "扩展编号");

		// 手机号码，多个号码使用英文逗号进行分割
		params.put("hm", phones);
		// 将短信内容进行URLEncoder编码
		params.put("nr", URLEncoder.encode(content));

		return HttpRequestUtil.sendPost(url, params.toString(), true);
	}

	/**
	 * 随机生成6位随机验证码 方法说明
	 * 
	 * @Discription:扩展说明
	 * @return
	 * @return String
	 * @Author: zhugz
	 * @Date: 2015年4月17日 下午7:19:02
	 * @ModifyUser：zhugz
	 * @ModifyDate: 2015年4月17日 下午7:19:02
	 */
	public static String createRandomVcode() {
		// 验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

	public static void main(String[] args) {
//		System.out.println(sendMsg("18123456789,15123456789", "尊敬的用户，您的验证码为" + SendMsgUtil.createRandomVcode()
//				+ "有效期为60秒，如有疑虑请详询400-069-2886（客服电话）【XXX中心】"));
		send("15914205037",createRandomVcode());
	}

	public static void send(String mobile, String content) {
		StringBuffer params = new StringBuffer();
		try {
			String custom = "";
			
			/*天时利接口，暂停，如果要开启，只需去掉注释就可以
			params.append("cid=").append(CodingUtils.encodeBase64URL(cid)).append("&").append("pwd=")
					.append(CodingUtils.encodeBase64URL(pwd)).append("&").append("productid=")
					.append(CodingUtils.encodeURL(productid)).append("&").append("mobile=")
					.append(CodingUtils.encodeBase64URL(mobile + "")).append("&").append("content=")
					.append(CodingUtils.encodeBase64URL(content)).append("&").append("lcode=").append(lcode)
					.append("&").append("ssid=").append(ssid).append("&").append("format=").append(format).append("&")
					.append("sign=").append(CodingUtils.encodeBase64URL(sign)).append("&").append("custom=")
					.append(CodingUtils.encodeURL(custom));
			*/
			
			//****新短信接口调用参数
			String txt = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat fmt = new SimpleDateFormat(txt);
			String DateTime2 = fmt.format(new java.util.Date());
			//System.out.println("DateTime2="+DateTime2);
			params.append("username=").append("69351:"+cid).append("&").append("password=")
			.append(pwd).append("&").append("from=000").append("&").append("to=")
			.append(mobile + "").append("&").append("content=")
			.append(CodingUtils.encodeURLGBK(content)).append("&").append("presendTime=")
			.append(DateTime2);
			
			System.out.println(params.toString());
			String result = HttpUtil.sendPostRequestByParam(httpPath, params.toString());
			System.out.println("result=" + result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void sendEx(String mobile, String content) {
		StringBuffer params = new StringBuffer();
		try {
			String custom = "";
			params.append("cid=").append(CodingUtils.encodeBase64URL(cid)).append("&").append("pwd=")
					.append(CodingUtils.encodeBase64URL(pwd)).append("&").append("productid=")
					.append(CodingUtils.encodeURL(productid)).append("&").append("mobile=")
					.append(CodingUtils.encodeBase64URL(mobile + "")).append("&").append("content=")
					.append(CodingUtils.encodeBase64URL(content)).append("&").append("lcode=").append(lcode)
					.append("&").append("ssid=").append(ssid).append("&").append("format=").append(format).append("&")
					.append("sign=").append(CodingUtils.encodeBase64URL(sign)).append("&").append("custom=")
					.append(CodingUtils.encodeURL(custom));
			System.out.println(params.toString());
			String result = HttpUtil.sendPostRequestByParam(httpPath, params.toString());
			System.out.println("result=" + result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}