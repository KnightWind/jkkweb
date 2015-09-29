package com.jkkp.appapi.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
//"yyyy-MM-dd HH:mm:ss",注意大小写，时间是24小时制
//2015-01-01 11:22:33
public class StringAndDate {
	public static Date StringToDate(String datestring){
	Date date=new Date();
	try  
	{  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    date = sdf.parse(datestring);  
	    return date;
	}  
	catch (ParseException e)  
	{  
	    System.out.println(e.getMessage());  
	}  
	finally{
		return date;
	}
	}
	
	public static String DateToString(Date ddate){
		return(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
	}
	
	public static Timestamp getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
		String strTime = sdf.format(time);
		return Timestamp.valueOf(strTime);
	}
}
