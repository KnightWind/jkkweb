package com.jkkp.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
/**
 * JSON工具类
 * @author ysc
 *
 */
public class JsonCfg extends JsonConfig{
	private String fmtStr="yyyy-MM-dd HH:mm:ss";
	public JsonCfg(){
		this.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
		this.registerJsonValueProcessor(Date.class,new MyJsonDateProcessor(getFmtStr()));  
		this.registerJsonValueProcessor(String.class,new MyJsonStringProcessor());
		PropertyFilter filter = new PropertyFilter() {
		    public boolean apply(Object object, String fieldName,Object fieldValue) {
		    	boolean flag=fieldValue==null;
		    	flag=flag||(fieldValue+"").equals("[]");
		    	return flag;
		    }
		};
		this.setJsonPropertyFilter(filter);
	}
	public JsonCfg(final String notBlank){
		this.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
		this.registerJsonValueProcessor(Date.class,new MyJsonDateProcessor(getFmtStr()));  
		this.registerJsonValueProcessor(String.class,new MyJsonStringProcessor());
		PropertyFilter filter = new PropertyFilter() {
		    public boolean apply(Object object, String fieldName,Object fieldValue) {
		    	boolean flag=fieldValue==null;
		    	flag=flag||(fieldValue+"").equals("[]");
		    	if("1".equals(notBlank)){
		    		flag=flag||StringUtils.isBlank(fieldValue.toString());
		    		flag=flag||"0".equals(fieldValue+"");
		    	}
		    	return flag;
		    }
		};
		this.setJsonPropertyFilter(filter);
	}
	public String getFmtStr() {
		return fmtStr;
	}
	public void setFmtStr(String fmtStr) {
		this.fmtStr = fmtStr;
	}
}
class MyJsonStringProcessor implements JsonValueProcessor {
	public MyJsonStringProcessor() {
		super();
	}
	public Object processArrayValue(Object param,JsonConfig jcfg) {
		return process(param);
	}
	public Object processObjectValue(String key,Object value,JsonConfig jcfg) {
		return process(value);
	}
	private Object process(Object value) {
		return value == null ? "" : value.toString();
	}
}
class MyJsonDateProcessor implements JsonValueProcessor {
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private boolean isDate=false;
	public MyJsonDateProcessor() {
		super();
	}
	public MyJsonDateProcessor(String format) {
		super();
		sdf2=new SimpleDateFormat(format);
	}
	public Object processArrayValue(Object param,JsonConfig jcfg) {
		return process(param);
	}
	public Object processObjectValue(String pname, Object pvalue,JsonConfig jcfg) {
		isDate=pname!=null&&(pname.endsWith("Date")||pname.endsWith("date"));
		return process(pvalue);
	}
	private Object process(Object value) {
		if (value instanceof Date) {
			return isDate?sdf1.format(value):sdf2.format(value);
		}
		return value == null ? "" : value.toString();
	}
}