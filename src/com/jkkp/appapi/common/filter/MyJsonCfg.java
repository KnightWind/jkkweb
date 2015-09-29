package com.jkkp.appapi.common.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
@Service("myJsonCfg")
public class MyJsonCfg extends JsonConfig{
	private String fmtStr="yyyy-MM-dd HH:mm:ss";
	public MyJsonCfg(){
		this.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
		this.registerJsonValueProcessor(Date.class,new MyJsonDateProcessor(getFmtStr()));  
		this.registerJsonValueProcessor(String.class,new MyJsonStringProcessor());
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName,Object fieldValue) {
                return fieldValue==null||fieldValue.equals("");
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
	private String format = "yyyy-MM-dd";
	public MyJsonDateProcessor() {
		super();
	}
	public MyJsonDateProcessor(String format) {
		super();
		this.format = format;
	}
	public Object processArrayValue(Object param,JsonConfig jcfg) {
		return process(param);
	}
	public Object processObjectValue(String paramString, Object param,JsonConfig jcfg) {
		return process(param);
	}
	private Object process(Object value) {
		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}
}