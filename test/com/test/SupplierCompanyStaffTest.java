package com.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.mapper.JlappointmentPushMapper;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.*;
import com.jkkp.modules.supplier.mapper.SupervisorMapper;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.Supervisor;
import com.jkkp.modules.supplier.view.VSupervisor;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;

import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class SupplierCompanyStaffTest {
	@Autowired public SupplierCompanyStaffMapper dao;
	private Integer id=29;
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VSupplierCompanyStaff> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VSupplierCompanyStaff bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		List<VSupplierCompanyStaff> list=dao.getList("");
		for (int i = 0; i < list.size(); i++) {
			VSupplierCompanyStaff bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="设计师头像【"+bean.photoUrl+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VSupplierCompanyStaff bean=dao.getBeanById(id);
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			VSupplierCompanyStaff bean=new VSupplierCompanyStaff();
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
	public void deleteByAnd(){
		String and=" and id='XXX' ";
		int flag=dao.deleteByAnd(and);
		System.out.println("flag【"+flag+"】");
	}
	@Test
	public void demo(){
		System.out.println("dao【"+dao+"】");
	}
}
