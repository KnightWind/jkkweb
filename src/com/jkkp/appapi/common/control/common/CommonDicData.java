/**
 * 
 */
package com.jkkp.appapi.common.control.common;

import java.util.HashMap;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class CommonDicData {
	private static HashMap<Integer,String> appointmentStatusMap  =  new HashMap<Integer,String>();
	private static HashMap<Integer,String> appointmentVWorksiteStatusMap  =  new HashMap<Integer,String>();
	//appointment表字段
	//房子户型
	private final static String[] houseTypes = new String[]{"","出租","自用","婚房","儿童房","会所","工装"};
	//新/旧房 1新房 2老房
	private final static String[] suTypes=new String[]{"","新房","老房"};
	//装修方式1.半包2.全包
	private final static String[] methodNames=new String[]{"","半包","全包"};
	
	static{
		initAppointmentStatusMap();
		initAppointmentVWorksiteStatusMap();
	}
	
	private static void initAppointmentStatusMap(){
		appointmentStatusMap.put(0, "流单");
		appointmentStatusMap.put(1, "草稿");
		appointmentStatusMap.put(10, "待抢单");
		appointmentStatusMap.put(20, "待量房");
		appointmentStatusMap.put(30, "已量房待报价");
		appointmentStatusMap.put(40, "已提交方案待签约");
		appointmentStatusMap.put(50, "待开工");
		appointmentStatusMap.put(60, "施工中");
		appointmentStatusMap.put(70, "已完工");
		appointmentStatusMap.put(80, "暂停");
		appointmentStatusMap.put(90, "关闭");
		appointmentStatusMap.put(100, "结束抢单");
	}
	private static void initAppointmentVWorksiteStatusMap(){
		appointmentVWorksiteStatusMap.put(0, "待应单");
		appointmentVWorksiteStatusMap.put(10, "已应单");
		appointmentVWorksiteStatusMap.put(20, "已结束");
	}
	
	public static String getAppointmentStatusName(int status){
		return appointmentStatusMap.get(status);
	}
	public static String getAppointmentVWorksiteStatusName(int status){
		return appointmentVWorksiteStatusMap.get(status);
	}
	public static String[] getHousetypes() {
		return houseTypes;
	}
	public static String[] getMethodnames() {
		return methodNames;
	}
	public static String[] getSutypes() {
		return suTypes;
	}
	
}
