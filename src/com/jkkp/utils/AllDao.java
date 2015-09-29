package com.jkkp.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkkp.modules.basedata.mapper.*;
import com.jkkp.modules.supplier.mapper.*;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * 所有Dao实例化类
 * @author ysc
 *
 */
@SuppressWarnings("unchecked")
@Component
public class AllDao {
	public static String shotFmt="yyyy-MM-dd";
	public static String longFmt="yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat dfmt=new SimpleDateFormat(shotFmt);
	public static SimpleDateFormat tfmt=new SimpleDateFormat(longFmt);
	public static boolean DEBUG=true;
	public static JsonCfg jcfg=new JsonCfg();
	public static JsonCfg notBlankJcfg=new JsonCfg("1");
	//↓↓统一实例化操作数据库类mapper
	@Autowired public JlComplainMapper jlComplainMapper;
	@Autowired public EngineeringsMapper engineeringsMapper;
	@Autowired public SupplierMapper supplierMapper;
	@Autowired public AttachmentMapper attachmentMapper;
	@Autowired public CheckRequestMapper checkRequestMapper; 
	//↑↑统一实例化操作数据库类mapper
	public static Map<String,Object> copyProperties(Map<String,Object> map,Object obj){
		JSONObject jobj=JSONObject.fromObject(obj,jcfg);
		Iterator<String> it=jobj.keys();
		while (it.hasNext()) {
			String kk=it.next();
			Object vv=jobj.get(kk);
			map.put(kk,vv);
		}
		return map;
	}
	public static Object copyProperties(Object dest,Object orig){
		try {
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {longFmt}) );
			JSONObject mp1=JSONObject.fromObject(dest);
			JSONObject mp2=JSONObject.fromObject(orig);
			Iterator<String> it=mp2.keys();
			while (it.hasNext()) {
				String kk=it.next();
				Object vv1=mp1.get(kk);
				Object vv2=mp2.get(kk);
				if(vv1==null||vv2==null) continue;
				mp1.put(kk,vv2);
			}
			dest=JSONObject.toBean(mp1,dest.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}return dest;
	}
	public static Object copyProperties2(Object dest,Object orig){
		try {
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {longFmt}) );
			JSONObject mp1=JSONObject.fromObject(dest);
			JSONObject mp2=JSONObject.fromObject(orig);
			Field[] fds=dest.getClass().getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field fd=fds[i];
				String kk=fd.getName();
				Class<?> ftype=fd.getType();
				Object vv2=mp2.get(kk);
				if(ftype==Date.class){
					if(vv2!=null){
						if(vv2 instanceof String){
							if(StringUtils.isNotBlank(vv2.toString())){
								if(vv2.toString().length()==10){
									mp1.put(kk,dfmt.parseObject(vv2.toString()));
								}else if(vv2.toString().length()==19){
									mp1.put(kk,tfmt.parseObject(vv2.toString()));
								}
							}
						}
					}
				}else if(vv2!=null){
					mp1.put(kk,vv2);
				}
			}
			fds=dest.getClass().getSuperclass().getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field fd=fds[i];
				String kk=fd.getName();
				String ftype=fd.getType().getName();
				Object vv2=mp2.get(kk);
				if(ftype.equals("java.util.Date")){
					if(vv2!=null){
						if(vv2 instanceof String){
							if(StringUtils.isNotBlank(vv2.toString())){
								if(vv2.toString().length()==10){
									mp1.put(kk,dfmt.parseObject(vv2.toString()));
								}else if(vv2.toString().length()==19){
									mp1.put(kk,tfmt.parseObject(vv2.toString()));
								}
							}
						}
					}
				}else if(vv2!=null){
					mp1.put(kk,vv2);
				}
			}
			dest=JSONObject.toBean(mp1,dest.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}return dest;
	}
	public static void pln(Object obj){
		System.out.println(obj);
	}
}
