package com.appointment.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkkp.modules.appointment.mapper.JlappointmentPushMapper;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointmentPush;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class JlappointmentPushTest {
	@Autowired public JlappointmentPushMapper dao;
	private Integer id=374;
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VJlappointmentPush> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VJlappointmentPush bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="jlappointment【"+bean.jlappointment+"】";
			ss+="supplier【"+bean.supplier+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void selectJlAppointmentPushes(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("rowStart", 0);
		map.put("limit", 5);
		List<VJlappointmentPush> list=dao.selectJlAppointmentPushes(map);
		for (int i = 0; i < list.size(); i++) {
			VJlappointmentPush bean=list.get(i);
//			VJlappointmentPush temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】aid【"+bean.aid+"】";
//			ss+="jlappointment【"+bean.jlappointment+","+temp.jlappointment+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and="";
//		and+=" and id='427' ";
//		and+=" and p_id is not null and sp_id is not null ";
		and+=" and pointx is not null ";
		List<VJlappointmentPush> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VJlappointmentPush bean=list.get(i);
//			VJlappointmentPush temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】aid【"+bean.aid+"】";
			//ss+="pointx【"+bean.pointx+"】";
			//ss+="pointy【"+bean.pointy+"】";
//			ss+="房型【"+bean.getSuTypeVal()+"】";
//			ss+="半包全包【"+bean.getMethodVal()+"】";
//			ss+="status【"+bean.status+"】status【"+bean.getStatusName()+"】";
//			ss+="小区名称【"+bean.community+"】\n";
//			ss+="业主【"+bean.user+"】\n";
//			ss+="报价【"+bean.payment+"】\n";
//			ss+="面积【"+bean.space+"】面积【"+bean.getSpaceVal()+"】\n";
//			ss+="风格【"+bean.cateName+"】\n";
//			ss+="整包半包【"+bean.getMethodVal()+"】\n";
//			ss+="装修地址【"+bean.address+"】\n";
//			ss+="整装局部【"+bean.getWholeHouseVal()+"】\n";
//			ss+="房屋用途【"+bean.getHouseTypeVal()+"】\n";
//			ss+="装修时间【"+bean.zxTime+"】\n";
//			ss+="需求发送时间【"+bean.createTime+"】\n";
//			ss+="装修时间【"+BaseTools.fmt.format(bean.zxTime)+"】\n";
//			ss+="需求发送时间【"+BaseTools.fmt.format(bean.createTime)+"】\n";
//			ss+=JSONObject.fromObject(temp,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		id=374;
		VJlappointmentPush bean=dao.getBeanById(id);
		String ss="id【"+bean.id+"】\n";
//		if(bean.jlappointment!=null){
//			ss+="小区名称【"+bean.jlappointment.community+"】\n";
//			ss+="业主【"+bean.jlappointment.user+"】\n";
//			ss+="报价【"+bean.jlappointment.payment+"】\n";
//			ss+="面积【"+bean.jlappointment.space+"】面积【"+bean.jlappointment.getSpaceVal()+"】\n";
//			ss+="风格【"+bean.jlappointment.cateName+"】\n";
//			ss+="整包半包【"+bean.jlappointment.getMethodVal()+"】\n";
//			ss+="装修地址【"+bean.jlappointment.address+"】\n";
//			ss+="新旧房【"+bean.jlappointment.getSuTypeVal()+"】\n";
//			ss+="整装局部【"+bean.jlappointment.getWholeHouseVal()+"】\n";
//			ss+="房屋用途【"+bean.jlappointment.getHouseTypeVal()+"】\n";
//			ss+="装修时间【"+BaseTools.fmt.format(bean.jlappointment.zxTime)+"】\n";
//			ss+="需求发送时间【"+BaseTools.fmt.format(bean.createTime)+"】\n";
//		}
		ss+="jlappointment【"+bean.jlappointment+"】\n";
		ss+="supplier【"+bean.supplier+"】\n";
		System.out.println(ss);
//		String str=JSONObject.fromObject(bean,AllDao.jcfg).toString();
//		BaseTools.showMessageByJSON(JSONObject.fromObject(bean,AllDao.jcfg), "");
//		System.out.println(str);
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			JlappointmentPush bean=new JlappointmentPush();
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
	public void deleteByAnd(){
		String and=" and id='111' ";
		int flag=dao.deleteByAnd(and);
		System.out.println("flag【"+flag+"】");
	}
	@Test
	public void demo(){
		System.out.println("dao【"+dao+"】");
	}
}
