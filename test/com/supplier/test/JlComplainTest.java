package com.supplier.test;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.supplier.mapper.JlComplainMapper;
import com.jkkp.modules.supplier.view.VJlComplain;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class JlComplainTest {
	@Autowired public JlComplainMapper dao;
	@Autowired public AllDao allDao;
	private Integer id=1;
	@Test
	public void demo(){
		System.out.println("dao【"+dao+"】");
		System.out.println("allDao【"+allDao+"】");
		System.out.println("jlComplainMapper【"+allDao.jlComplainMapper+"】");
	}
	@Test
	public void test(){
		String json="{gcdId:\"1\",login_user:\"test\",login_flag:\"WhBei51A4TKXgNYuoiZdig==\",os:\"1\"}";
		JSONObject jobj=JSONObject.fromObject(json);
		VJlComplain bean=new VJlComplain();
		bean=(VJlComplain)AllDao.copyProperties(bean,jobj);
		pln("gcdId【"+bean.gcdId+"】gcdId【"+jobj.get("gcdId")+"】");
	}
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VJlComplain> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VJlComplain bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getListByGcdId(){
		List<VJlComplain> list=dao.getListByGcdId(1);
		for (int i = 0; i < list.size(); i++) {
			VJlComplain bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="gcdId【"+bean.gcdId+"】";
			ss+="engineerings【"+bean.engineerings+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and=" ";
		and+=" ";
		List<VJlComplain> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VJlComplain bean=list.get(i);
			VJlComplain temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="gcdId【"+bean.gcdId+"】";
			ss+="engineerings【"+bean.engineerings+","+temp.engineerings+"】";
			//ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VJlComplain bean=dao.getBeanById(id);
		//System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			VJlComplain bean=new VJlComplain();
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
	public void pln(Object obj){
		System.out.println(obj);
	}
}
