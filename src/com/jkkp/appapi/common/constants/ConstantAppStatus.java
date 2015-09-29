package com.jkkp.appapi.common.constants;

public class ConstantAppStatus {
	public static final Integer LIU_DAN = 0;
	public static final Integer STATUS_DRAFT=1;
	public static final Integer DAI_QIANG_DAN = 10;
	public static final Integer DAI_LIANG_FANG = 20;
	public static final Integer DAI_BAO_JIA = 30;
	public static final Integer DAI_QIAN_YUE = 40;
	public static final Integer DAI_KAI_GONG = 50;
	public static final Integer SHI_GONG_ZHONG = 60;
	public static final Integer DONE = 70;
	public static final Integer STOP = 80;
	public static final Integer CLOSE = 90;
	public static final Integer CLOSE_GRAB = 100;//结束抢单
	public static final Integer REFUND_DEPOSIT_AUDIT = 45;//结束抢单
	
	
	
	public static final Byte PUSH_DAI_YING_DAN=0;
	public static final Byte PUSH_YI_YING_DAN=10;
	public static final Byte PUSH_DAI_ShangJia_YINGDAN = 15;//待收藏商家应单
	public static final Byte PUSH_CANSLE=11;
	public static final Byte PUSH_OVERGRAB=12;//结束抢单
	public static final Byte PUSH_YU_YUE_LIANG_FANG=20;
	public static final Byte PUSH_YI_LIANG_FANG=30;
	public static final Byte PUSH_YI_QIAN_YUE=40;
	public static final Byte PUSH_YI_FU_KUAN=50;
	public static final Byte PUSH_YI_FU_DINGJIN=60;
	public static final Byte PUSH_JIE_SHU_QIANGDAN=70;//结束抢单
	public static final Byte PUSH_REFUND_DEPOSIT_AUDIT=61;//定金退款审核中
	public static final Byte PUSH_JIE_SHU=71;//结束
	
	
	
	public static final Integer J_LIU_DAN = 0;
	public static final Integer J_WEI_FA_BU = 5;
	public static final Integer J_DAI_QIANG_DAN = 10;
	public static final Integer J_DAI_XUAN_DING = 20;
	public static final Integer J_XUAN_DING = 30;

	public static final Byte J_PUSH_DAI_YING_DAN=0;
	public static final Byte J_PUSH_YI_YING_DAN=10;
	public static final Byte J_PUSH_CANSLE=11;
	public static final Byte J_PUSH_XUANDING=20;
	public static final Byte J_PUSH_JIE_SU_QIANGDAN=30;
	//预约看工地单状态
	public static final Byte VW_WORKSITE_DYD=0;//待应单
	public static final Byte VW_WORKSITE_YYD=10;//已应单
	public static final Byte VW_WORKSITE_YJS=20;//已结束

}
