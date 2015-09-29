/**
 * 
 */
package com.jkkp.appapi.common.jsonmap;

/**
 * @author Administrator
 *
 */
public class SendMsgUtilNew {
	private static String httpPath = "http://GATEWAY.IEMS.NET.CN/GsmsHttp";/* 新HTTP测试环境 */
	private static final String cid = "8280"; // 客户端ID
	private static final String pwd = "jtx12345"; // 客户端密码
	private static final String productid = "201503101"; // 通道组id
	private static final String lcode = "123"; // 子号码
	private static final String ssid = "100000"; // 短信唯一标识,用于匹配状态报告
	private static final String format = "32"; // 短信类型:15普通短信,32长短信
	private static final String sign = "[签名]"; // 客户自定义签名,可以不填
	
	
	public static void send(String mobile, String content) {
		StringBuffer params = new StringBuffer();
		try {
			String custom = "";
//			params.append("cid=").append(CodingUtils.encodeBase64URL(cid)).append("&").append("pwd=")
//					.append(CodingUtils.encodeBase64URL(pwd)).append("&").append("productid=")
//					.append(CodingUtils.encodeURL(productid)).append("&").append("mobile=")
//					.append(CodingUtils.encodeBase64URL(mobile + "")).append("&").append("content=")
//					.append(CodingUtils.encodeBase64URL(content)).append("&").append("lcode=").append(lcode)
//					.append("&").append("ssid=").append(ssid).append("&").append("format=").append(format).append("&")
//					.append("sign=").append(CodingUtils.encodeBase64URL(sign)).append("&").append("custom=")
//					.append(CodingUtils.encodeURL(custom));
			String ps = "username=user&password=pass&from=001&to=15873147150&content=hell,world&presendTime=2003-02-03%2012:12:03";
			System.out.println(params.toString());
			String result = HttpUtil.sendPostRequestByParam(httpPath, ps.toString());
			System.out.println("result=" + result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
}
