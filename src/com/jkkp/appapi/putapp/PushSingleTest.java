/**
 * 
 */
package com.jkkp.appapi.putapp;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.jkkp.utils.CommonUtil;

/**
 * @author Administrator
 *
 */
public class PushSingleTest {
	 	static String appId = "fViJHtE9og7QElHY3B44e9";
	    static String appkey = "zvZ6joa7KM6z81LqHtcBH6";
	    static String master = "eZ0VRxnahX5OfUuYHBGqA";
	    static String host = "http://sdk.open.api.igexin.com/serviceex";
	    static String cId = "ef1712ca2f2f39820b64d52bf4fd59f6";
	    
	    public static void main(String[] args) throws Exception {
	    	
	    	String pwd=CommonUtil.md5("abc123".toString().trim());
	    	System.out.println(pwd);
	    	
	    	
	    	
	        IGtPush push = new IGtPush(host, appkey, master);
	         
	        LinkTemplate template = linkTemplateDemo();
	        SingleMessage message = new SingleMessage();
	        message.setOffline(true);
	        //离线有效时间，单位为毫秒，可选
	        message.setOfflineExpireTime(24 * 3600 * 1000);
	        message.setData(template);
	        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
	        Target target = new Target();
	 
	        target.setAppId(appId);
	        target.setClientId(cId);
	        //用户别名推送，cid和用户别名只能2者选其一
	        //String alias = "个";
	        //target.setAlias(alias);
	        IPushResult ret = null;
	        try{
	            ret = push.pushMessageToSingle(message, target);
	        }catch(Exception e){
	            e.printStackTrace();
	            //ret = push.pushMessageToSingle(message, target, e.getRequestId());
	        }
	        getPushResult(ret.getTaskId())  ; 
	        if(ret != null){
	            System.out.println(ret.getResponse().toString());
	        }else{
	            System.out.println("服务器响应异常");
	            
	            
	       
	    }
	}
	public static LinkTemplate linkTemplateDemo() {
	        LinkTemplate template = new LinkTemplate();
	        // 设置APPID与APPKEY
	        template.setAppId(appId);
	        template.setAppkey(appkey);
	        // 设置通知栏标题与内容
	        template.setTitle("请输入通知栏标题");
	        template.setText("请输入通知栏内容");
	        // 配置通知栏图标
	        template.setLogo("icon.png");
	        // 配置通知栏网络图标，填写图标URL地址
	        template.setLogoUrl("");
	        // 设置通知是否响铃，震动，或者可清除
	        template.setIsRing(true);
	        template.setIsVibrate(true);
	        template.setIsClearable(true);
	        // 设置打开的网址地址
	        template.setUrl("http://www.baidu.com");
	        return template;
	}
	    public static void getPushResult(String taskId){{
	    	   IGtPush push = new IGtPush(host, appkey, master);
	         try {
	             System.out.println("taskId=:" + taskId);
	             IPushResult pushResult = push.getPushResult(taskId);
	             System.out.println(pushResult);
	             int msgTotal = (Integer) pushResult.getResponse().get("msgTotal");
	             int clickNum = (Integer) pushResult.getResponse().get("clickNum");
	             int msgProcess = (Integer) pushResult.getResponse().get("msgProcess");
	         }catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	    }
}
