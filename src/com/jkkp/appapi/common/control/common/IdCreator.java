/**
 * 
 */
package com.jkkp.appapi.common.control.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 *
 */
public class IdCreator {
	private static long seq=100001;
	
	private static Object lock=new Object();
	
	public static String getSeqId(){
		String str="";
		synchronized (lock) {
			if(seq==999999){
				seq=100001;
			}
			seq=seq+1;
			str = getDateStr()+String.valueOf(seq);
			return str;
		}
	}
	
	
	 /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getDateStr(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}
	
	/**
	 * 生成TransactionId
	 * @return String
	 * @Exception
	 */
	public static String generateTockenId() {
		return UUID.randomUUID().toString();
	}

}
