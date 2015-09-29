package com.appointment.test;

import groovy.runtime.metaclass.net.sf.json.JSONObjectMetaClass;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jkkp.modules.appointment.controller.JlappointmentController;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.view.VJlappointment;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.Pager;
import com.jkkp.utils.Pagination;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class JlappointmentTest {
	@Autowired public JlappointmentMapper dao;
	private Integer id=901;
	@Test
	public void findJlappointmentInfoById(){
		Map<String,Object> map=new HashMap<String, Object>();
		List<VJlappointment> list=dao.queryJAppDetail(map);
		System.out.println(list.size());
//		for (int i = 0; i < list.size(); i++) {
//			VJlappointment bean=list.get(i);
//			String ss=(i+1)+"/"+list.size()+"\t";
//			ss+="engineerings【"+bean.engineerings+"】";
//			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
//			System.out.println(ss);
//		}
	}
	@Test
	public void getPageList(){
		String and="";
		int pageSize=5;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VJlappointment> list=dao.getPageList(pager);
		JSONArray data=JSONArray.fromObject(list,AllDao.jcfg);
		System.out.println(data.toString());
//		for (int i = 0; i < data.size(); i++) {
//			JSONObject bean=data.getJSONObject(i);
//			System.out.println(bean.getString("id"));
//		}
//		for (int i = 0; i < list.size(); i++) {
//			VJlappointment bean=list.get(i);
//			String ss=(i+1)+"/"+list.size()+"\t";
//			ss+="gcdId【"+bean.gcdId+"】";
//			ss+="engineerings【"+bean.engineerings+"】";
////			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
//			System.out.println(ss);
//		}
	}
	@Test
	public void getList(){
		String and="";
		and+=" and id=1035 ";
		List<VJlappointment> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VJlappointment bean=list.get(i);
			VJlappointment temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
//			ss+="engineerings【"+bean.engineerings+""+temp.engineerings+"】";
//			ss+="cateName【"+bean.cateName+"】";
//			ss+="cateName【"+bean.getMethodVal()+"】";
//			ss+="cateName【"+bean.getSpaceVal()+"】";
//			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
//			BaseTools.showMessageByJSON(JSONObject.fromObject(bean,AllDao.jcfg), "");
			ss+=JSONObject.fromObject(temp,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		id=893;
		VJlappointment bean=dao.getBeanById(id);
		String ss="id【"+id+"】bean【"+bean+"】";
		ss+=JSONObject.fromObject(bean,AllDao.jcfg);
		System.out.println(ss);
		//BaseTools.showMessageByMap(JSONObject.fromObject(bean,AllDao.jcfg),"");
	}
	@Test
	public void insert(){
		for (int i = 0; i <100; i++) {
			Jlappointment bean=new Jlappointment();
			bean.setCreateTime(new Date());
			int flag=dao.insert(bean);
			System.out.println("flag【"+flag+"】bean.id【"+bean.getId()+"】");
		}
	}
	@Test
	public void getCount(){
		String and="  ";
		System.out.println("记录数【"+dao.getCount(and)+"】");
	}
	@Test
	public void demo(){
		System.out.println("dao【"+dao+"】");
	}
}
