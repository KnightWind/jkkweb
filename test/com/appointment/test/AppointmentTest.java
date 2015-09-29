package com.appointment.test;

import java.util.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import com.jkkp.modules.appointment.mapper.*;
import com.jkkp.modules.appointment.view.*;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.Pager;
@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class AppointmentTest {
	@Autowired public AppointmentMapper dao;
	private Integer id=1;
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VAppointment> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VAppointment bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and=" and id=2823 ";
		List<VAppointment> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VAppointment bean=list.get(i);
			VAppointment temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】\n";
			ss+="监管费【"+bean.jgamt+","+temp.jgamt+"】\n";
//			ss+="小区名称【"+bean.community+"】\n";
//			ss+="装修业主【"+bean.user+"】\n";
//			ss+="工程地址【"+bean.address+"】\n";
//			ss+="装修需求.价格【"+bean.payment+"】\n";
//			ss+="装修需求.面积【"+bean.space+"】【"+bean.getSpaceVal()+"】\n";
//			ss+="装修需求.风格【"+bean.cateName+"】\n";
//			ss+="装修需求.方式【"+bean.getMethodVal()+"】\n";
//			ss+="房屋属性.类型【"+bean.getSuTypeVal()+"】\n";
//			ss+="房屋属性.整装局部【"+bean.getWholeHouseVal()+"】\n";
//			ss+="房屋属性.房屋用途【"+bean.getHouseTypeVal()+"】\n";
//			ss+="装修工程款【"+bean.budget+"】(装修预算)\n";
//			ss+="装监管款【"+bean.jgamt+"】\n";
//			ss+="装修时间【"+bean.zxTime+"】\n";
//			ss+="竣工时间【"+bean.endTime+"】\n";
//			ss+="工程单ID【"+bean.gcdId+"】\n";
//			ss+="装修方案ID【"+bean.designId+"】\n";
//			ss+="工程单【"+bean.engineerings+","+temp.engineerings+"】\n";
//			ss+="装修方案【"+temp.engineerings.design+"】\n";
//			if(temp.engineerings!=null&&temp.engineerings.design!=null){
//				ss+="装修方案【"+JSONObject.fromObject(temp.engineerings.design)+"】\n";
//			}
//			ss+=JSONObject.fromObject(temp,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		id=15;
		VAppointment bean=dao.getBeanById(id);
		String ss="";
		ss+="id【"+bean.id+"】\n";
		ss+="小区名称【"+bean.community+"】\n";
		ss+="装修业主【"+bean.user+"】\n";
		ss+="工程地址【"+bean.address+"】\n";
		ss+="装修需求.价格【"+bean.payment+"】\n";
		ss+="装修需求.面积【"+bean.space+"】【"+bean.getSpaceVal()+"】\n";
		ss+="装修需求.风格【"+bean.cateName+"】\n";
		ss+="装修需求.方式【"+bean.getMethodVal()+"】\n";
		ss+="房屋属性.类型【"+bean.getSuTypeVal()+"】\n";
		ss+="房屋属性.整装局部【"+bean.getWholeHouseVal()+"】\n";
		ss+="房屋属性.房屋用途【"+bean.getHouseTypeVal()+"】\n";
		ss+="装修工程款【"+bean.budget+"】(装修预算)\n";
		ss+="装监管款【"+bean.jgamt+"】\n";
		ss+="装修时间【"+bean.zxTime+"】\n";
		ss+="竣工时间【"+bean.endTime+"】\n";
//		ss+="装修方案【"+bean.+"】\n";
		ss+="工程单ID【"+bean.gcdId+"】\n";
		ss+="设计方案ID【"+bean.designId+"】\n";
		System.out.println(ss);
//		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
//		BaseTools.showMessageByMap(JSONObject.fromObject(bean,AllDao.jcfg),"");
//		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			VAppointment bean=new VAppointment();
			bean.setCreateTime(new Date());
			int flag=dao.insert(bean);
			System.out.println("flag【"+flag+"】bean.id【"+bean.getId()+"】");
		}
	}
	@Test
	public void getCount(){
		JSONObject jobj=new JSONObject();
		String reson=BaseTools.getValueByKey(jobj,"reson");
		System.out.println("reson【"+reson+"】");
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
