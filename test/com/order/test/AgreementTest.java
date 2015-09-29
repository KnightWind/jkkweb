package com.order.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jkkp.modules.appointment.controller.JlappointmentController;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.modules.mapper.VAgreement;
import com.jkkp.modules.order.mapper.AgreementMapper;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
import com.jkkp.utils.Pagination;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class AgreementTest {
	@Autowired public AgreementMapper dao;
	private Integer id=901;

	@Test
	public void getPageList(){
		String and="";
		int pageSize=10;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VAgreement> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VAgreement bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		List<VAgreement> list=dao.getList("");
		for (int i = 0; i < list.size(); i++) {
			VAgreement bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VAgreement bean=dao.getBeanById(id);
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <1; i++) {
			Agreement bean=new Agreement();
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
