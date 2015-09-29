package com.jkkp.modules.system.constants;

public class ConfigConstant {

	public static final String APPOINTMENT_FAIL_TIME = "APPOINTMENT_FAIL_TIME";
	
	public static final String SEND_SMSCODE_VALID_TIME = "SEND_SMSCODE_VALID_TIME";
	
	public static final String SMS_SEND_CONTENT = "SMS_SEND_CONTENT";
	
	public static final String APP_PUSH_TITLE = "APP_PUSH_TITLE";
	
	public static final String APP_PUSH_CONTENT = "APP_PUSH_CONTENT";
	
	public static final String PHOTO_SAVE_DIR = "PHOTO_SAVE_DIR";
	
	public static final String PHOTO_PREFIX_URL  = "PHOTO_PREFIX_URL";//图片URL前缀
	
	public static final String SMS_SEND_CONTENT_CHANGEROOMTIME ="SMS_SEND_CONTENT_CHANGEROOMTIME";//商家修改量房时间，通知用户
	
	public static final String SMS_SEND_TITLE_CHANGEROOMTIME ="SMS_SEND_TITLE_CHANGEROOMTIME";//商家修改量房时间，通知用户标题
	
	public static final String APPOINTMENT_DEPOSIT  = "APPOINTMENT_DEPOSIT";//定金
	
	public static final String APPOINTMENT_MAX_SUPPLIER = "APPOINTMENT_MAX_SUPPLIER"; //抢单上限人数
	
	public static final String SMS_SEND_RANDOM_PAPSSWORD = "SMS_SEND_RANDOM_PAPSSWORD"; //随机密码
	
	public static final String SMS_THANKYOU_USED_JKK = "SMS_THANKYOU_USED_JKK"; //游客参加活动感谢短信内容
	
	public static final String SMS_BUY_CARD_SUCCESS = "SMS_BUY_CARD_SUCCESS"; //购卡成功通知短信内容
	
	public static final String AUTH_LOGIN_USER = "AUTH_LOGIN_USER"; // 登录已授权用户
	
	public static final String APP_PUSH_NEWAPP = "APP_PUSH_NEWAPP"; // 提示商家有新的预约单
	
	public static final String UPDATE_MAX = "UPDATE_MAX"; // 修改最大次数 

	public static final Object SMS_RECOMMEND_SUCC = "SMS_RECOMMEND_SUCC"; //推荐好友
	
	public static final Object SMS_RECOMMENDED_SUCC = "SMS_RECOMMENDED_SUCC"; //被推荐好友
	
	public static final Object JL_MAX_SELECT_NUM = "JL_MAX_SELECT_NUM";//用户在选择监理的时候限制的可选数量

	public static final Object SMS_JIAKEKE_VIP_SUCC = "SMS_JIAKEKE_VIP_SUCC"; //成功推荐十位好友成为家可可vip
	
	public static final String SMS_SEND_CONTENT_CHANGEROOMTIME_USER ="SMS_SEND_CONTENT_CHANGEROOMTIME_USER";//用户修改量房时间，通知用户

	public static final Object MSG_ACTIVITY_TAKE_QUAN = "MSG_ACTIVITY_TAKE_QUAN";//领券成功短信

	public static final Object MSG_ACTIVITY_UP_VIP = "MSG_ACTIVITY_UP_VIP";//成功升级VIP短信

	/**
	 * 支付链接配置-----勿动----start------------------------>
	 */
	
	public static final Object NOTIFY_URL = "NOTIFY_URL";

	public static final Object NOTIFY_APP_URL = "NOTIFY_APP_URL";

	public static final Object NOTIFY_APP_REFUND_URL = "NOTIFY_APP_REFUND_URL";
	
	/**
	 * 支付链接配置-----勿动----end------------------------>
	 */

	public static final Object WEIXIN_URL = "WEIXIN_URL";

	public static final Object MSG_SIGN_ACTIVITY = "MSG_SIGN_ACTIVITY";

	public static final Object SERVICER_RID = "SERVICER_RID"; //客服rid

	public static final Object IMAGE_URL = "IMAGE_URL";

	public static final Object YEEPAY_CALLBACK_URL = "YEEPAY_CALLBACK_URL";

	public static final Object INIT_PASSWORD = "INIT_PASSWORD";//新注册用户(Member表)初始密码
	
	
	//工程结款时间
	public static final Object engineerCheckoutDays="ENGINEER_CHECKOUT_DAYS";
	
	//退款审核成功提示用户信息
	public static final String REFUND_AUD_SUCCESS= "REFUND_AUD_SUCCESS";
	
	//账单显示定金图片
	public static final String IMG_DEPOSIT= "IMG_DEPOSIT";
		
	//账单显示监管款图片
	public static final String IMG_JIANLI= "IMG_JIANLI";
}
