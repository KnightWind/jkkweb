package com.jkkp.appapi.putapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

public class PushApp {
	@Autowired
	static	ISuppMessagePushSV suppMessagePushSV;
	
	static Map getuiConfigMap = new HashMap();
	static String appId = "q2sVaqS59i9mHm9S5dacfA";
	static String appkey = "7d6Lcy9nsh6nxRhoCHxaq3";
	static String master = "cX4kkTbuG39Pvqgs2c7iiA";
	static String host = "http://sdk.open.api.igexin.com/serviceex";
	static {
		// b端 装修公司
		getuiConfigMap.put("B", new GeTuiAppParam("q2sVaqS59i9mHm9S5dacfA",
				"7d6Lcy9nsh6nxRhoCHxaq3", "cX4kkTbuG39Pvqgs2c7iiA"));
		// 用户
		getuiConfigMap.put("C", new GeTuiAppParam("sauPo1tEjp6mZOp2TdqQs6",
				"xUTiqBh8Cm8W3jpV5bcVE7", "PsHg0JBEy0APAJcKpYJad7"));
		// 监理
		getuiConfigMap.put("J", new GeTuiAppParam("6vlNXPcVZ69RC9CVDr74x5",
				"30HVT5Gk3K6Uy9UcZZulh4", "XSIJymcQc26chHLSUqJnL"));
	}

	private static GeTuiAppParam getTuiAppParam(SuppMessagePush userPush) {
		GeTuiAppParam getuiParam = null;
		if (userPush.getUserType() == 1) {
			getuiParam = (GeTuiAppParam) getuiConfigMap.get("B");
		} else if (userPush.getUserType() == 20) {
			getuiParam = (GeTuiAppParam) getuiConfigMap.get("C");
		} else if (userPush.getUserType() == 5) {
			getuiParam = (GeTuiAppParam) getuiConfigMap.get("J");
		}
		return getuiParam;
	}

	public static void main(String[] args) throws Exception {
		pushApp1("225bd013328998da3c3a84fff0b6d016");
	}

	public static void pushApp1(String CID1) throws Exception {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appkey, master);
		// 建立连接，开始鉴权
		push.connect();
		// 通知透传模板
		LinkTemplate template = linkTemplateDemo();

		ListMessage message = new ListMessage();
		message.setData(template);

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);

		// 配置推送目标
		List targets = new ArrayList();
		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setClientId(CID1);
		targets.add(target1);
		// 获取taskID
		String taskId = push.getContentId(message);
		// 使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		// 打印服务器返回信息
		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 通知推送
	 * 
	 * @param userPush
	 * @param CID1
	 * @param title
	 * @param content
	 * @throws Exception
	 */
	public static void pushApp(SuppMessagePush userPush, String CID1,
			String title, String content) throws Exception {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appkey, master);
		// 建立连接，开始鉴权
		push.connect();
		// 通知透传模板
		LinkTemplate template = linkTemplateDemo();
		template.setTitle(title);
		template.setText(content);

		ListMessage message = new ListMessage();
		message.setData(template);

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);

		// 配置推送目标
		List targets = new ArrayList();
		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setClientId(CID1);
		targets.add(target1);
		// 获取taskID
		String taskId = push.getContentId(message);
		// 使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		// 打印服务器返回信息
		System.out.println(ret.getResponse().toString());
	}

	public static LinkTemplate linkTemplateDemo() {
		String title = Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.APP_PUSH_TITLE);
		String content = Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.APP_PUSH_CONTENT);

		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 设置通知栏标题与内容
		template.setTitle(title);
		template.setText(content);
		// 配置通知栏图标
		template.setLogo("icon.png");
		// 配置通知栏网络图标
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 设置打开的网址地址
		template.setUrl("jkkb://main/order");
		return template;
	}

	public static TransmissionTemplate transmissionTemplateDemo(String content) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		//template.setTransmissionContent("没有输入消息");
		template.setTransmissionContent(content);
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	public static void pushApp(SuppMessagePush userPush) throws Exception {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		// 根据用户类型，获取不同的推送参数
		GeTuiAppParam getuiParam = null;
		if (userPush.getUserType() == 1) {
			getuiParam = (GeTuiAppParam) getuiConfigMap.get("B");
		} else if (userPush.getUserType() == 20) {
			getuiParam = (GeTuiAppParam) getuiConfigMap.get("C");
		} else if (userPush.getUserType() == 5) {
			getuiParam = (GeTuiAppParam) getuiConfigMap.get("J");
		}
		if (getuiParam == null)
			return;
		String appId = getuiParam.getAppId();
		String appkey = getuiParam.getAppkey();
		String master = getuiParam.getMaster();

		IGtPush push = new IGtPush(host, appkey, master);
		// 建立连接，开始鉴权
		push.connect();
		// 通知透传模板
		LinkTemplate template = linkTemplateDemo();

		ListMessage message = new ListMessage();
		message.setData(template);

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);

		// 配置推送目标
		List targets = new ArrayList();
		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setClientId(userPush.getCid());
		targets.add(target1);
		// 获取taskID
		String taskId = push.getContentId(message);
		// 使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		// 打印服务器返回信息
		System.out.println(ret.getResponse().toString());
	}
	private static String jsonString(String mess,String title,int type){
		 JSONObject jsonObjSon = new JSONObject();  
		 jsonObjSon.put("mess", mess);
		 jsonObjSon.put("title", title);
		 jsonObjSon.put("type", type);
		 return jsonObjSon.toString();
	}
	/**
	 * 
	 * @param userPush
	 * @param mess 内容
	 * @param title 标题
	 * @param type 0默认消息 1 红包消息 ...
	 * @throws Exception
	 */
	public static void pushApp(SuppMessagePush userPush, String mess,String title,int type)
			throws Exception {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		// 根据用户类型，获取不同的推送参数
		GeTuiAppParam getuiParam = getTuiAppParam(userPush);

		if (getuiParam == null)
			return;
		String appId = getuiParam.getAppId();
		String appkey = getuiParam.getAppkey();
		String master = getuiParam.getMaster();

		IGtPush push = new IGtPush(host, appkey, master);
		// 建立连接，开始鉴权
		push.connect();
		// 通知透传模板
		TransmissionTemplate template = transmissionTemplateDemo(jsonString(mess,title,type));

		ListMessage message = new ListMessage();
		message.setData(template);

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);

		// 配置推送目标
		List targets = new ArrayList();
		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setClientId(userPush.getCid());
		targets.add(target1);
		// 获取taskID
		String taskId = push.getContentId(message);
		// 使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		// 打印服务器返回信息
		System.out.println(ret.getResponse().toString());
	}
	/**
	 * 
	 * @param userId 用户id
	 * @param userType 用户类型 1装修公司2建材商3工长5监理20用户
	 * @param mess 内容
	 * @param title 标题
	 * @param type 0默认消息 1 红包消息 ...
	 * @throws Exception
	 */
	public static void pushApp(int userId,int userType, String mess,String title,int type)
			throws Exception {
		List<SuppMessagePush> userPush=suppMessagePushSV.selectByUserIdAndType(userId, userType);
		if(userPush.size()>0){
			for (SuppMessagePush suppMessagePush : userPush) {
				pushApp(suppMessagePush,  mess, title, type);
			}
		}
		else {
			//throw new Exception("user is not login");
		}
	}
}