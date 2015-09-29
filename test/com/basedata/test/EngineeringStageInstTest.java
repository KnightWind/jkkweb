package com.basedata.test;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jkkp.modules.basedata.mapper.CheckRequestMapper;
import com.jkkp.modules.basedata.mapper.EngineeringStageInstMapper;
import com.jkkp.modules.basedata.mapper.EngineeringStageMapper;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.view.VEngineeringStage;
import com.jkkp.modules.basedata.view.VEngineeringStageInst;
import com.jkkp.modules.supplier.mapper.JlComplainMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class EngineeringStageInstTest {
	@Autowired public EngineeringStageInstMapper dao;
	private Integer id=1;
	
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VEngineeringStageInst> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VEngineeringStageInst bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		List<VEngineeringStageInst> list=dao.getList("");
		for (int i = 0; i < list.size(); i++) {
			VEngineeringStageInst bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】";
			ss+="监理【"+bean.supplier+"】";
			ss+="reportPass【"+bean.reportPass+"】";
			ss+="验收是否通过【"+bean.getReportPassVal()+"】";
			ss+="finishFlag【"+bean.finishFlag+"】";
			ss+="是否通过【"+bean.getFinishFlagVal()+"】";
			ss+="工程单详情【"+bean.engineerings+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
		System.out.println(JSONArray.fromObject(list,AllDao.jcfg));
	}
	@Test
	public void getBeanById(){
		VEngineeringStageInst bean=dao.getBeanById(id);
		System.out.println("bean【"+bean+"】");
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
	}
	@Test
	public void updateByPrimaryKey(){
		VEngineeringStageInst bean=dao.getBeanById(id);
		int flag=dao.updateByPrimaryKey(bean);
		System.out.println("bean【"+bean+"】flag【"+flag+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <1; i++) {
			VEngineeringStageInst bean=new VEngineeringStageInst();
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
		VEngineeringStageInst bean=dao.getBeanById(id);
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
