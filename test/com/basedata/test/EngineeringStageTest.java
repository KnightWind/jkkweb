package com.basedata.test;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.jkkp.modules.basedata.mapper.EngineeringStageMapper;
import com.jkkp.modules.basedata.view.VEngineeringStage;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class EngineeringStageTest {
	@Autowired public EngineeringStageMapper dao;
	private Integer id=1;
	
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VEngineeringStage> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VEngineeringStage bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and=" and (pid is null or pid=0) ";
		List<VEngineeringStage> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VEngineeringStage bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】";
			ss+="stagName【"+bean.stagName+"】";
			ss+="checkRequests【"+bean.checkRequests.size()+"】";
//			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VEngineeringStage bean=dao.getBeanById(id);
		System.out.println("bean【"+bean+"】");
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
	}
	@Test
	public void updateByPrimaryKey(){
		VEngineeringStage bean=dao.getBeanById(id);
		int flag=dao.updateByPrimaryKey(bean);
		System.out.println("bean【"+bean+"】flag【"+flag+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <1; i++) {
			VEngineeringStage bean=new VEngineeringStage();
			bean.createTime=new Date();
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
	public void deleteById(){
		VEngineeringStage bean=dao.getBeanById(id);
		int flag=dao.delete(bean);
		System.out.println("delete【"+flag+"】");
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
