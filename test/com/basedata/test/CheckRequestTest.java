package com.basedata.test;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.jkkp.modules.basedata.mapper.CheckRequestMapper;
import com.jkkp.modules.basedata.view.VCheckRequest;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class CheckRequestTest {
	@Autowired public CheckRequestMapper dao;
	private Integer id=1;
	
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VCheckRequest> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VCheckRequest bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			//ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			ss+=JSONObject.fromObject(bean);
			System.out.println(ss);
		}
	}
	@Test
	public void getListByStageId(){
		Integer stageId=20;
		List<VCheckRequest> list=dao.getListByStageId(stageId);
		for (int i = 0; i < list.size(); i++) {
			VCheckRequest bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】";
			ss+="stageId【"+bean.stageId+"】";
			ss+="engineeringStage【"+bean.engineeringStage+"】";
			ss+="engineerings【"+bean.engineerings+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and=" ";
		and+=" and id=8 ";
		List<VCheckRequest> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VCheckRequest bean=list.get(i);
			VCheckRequest temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】";
//			if(bean.engineeringStage!=null){
//				ss+="状态【"+bean.engineeringStage.getStatusVal()+"】";
//				ss+="根节点简称【"+bean.engineeringStage.abbreviation+"】";
//				ss+="验收二级节点【"+bean.engineeringStage.stagName+"】";
//			}
			ss+="stageId【"+bean.stageId+"】";
			ss+="engineeringStage【"+bean.engineeringStage+"】";
			ss+="engineerings【"+bean.engineerings+"】";
//			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			ss+="id【"+bean.id+"】\n";
			ss+="根节点简称【"+bean.abbreviation+"】\n";
			ss+="stagName【"+bean.stagName+"】\n";
			ss+="engineerings【"+bean.engineerings+","+temp.engineerings+"】\n";
			ss+="engineeringStage【"+bean.engineeringStage+","+temp.engineeringStage+"】\n";
//			if(bean.engineeringStage!=null){
//				ss+="状态【"+bean.engineeringStage.getStatusVal()+"】";
//				ss+="根节点简称【"+bean.engineeringStage.abbreviation+"】";
//				ss+="验收二级节点【"+bean.engineeringStage.stagName+"】";
//			}
//			ss+="stageId【"+bean.stageId+"】";
//			ss+="engineeringStage【"+bean.engineeringStage+"】";
//			ss+="engineerings【"+bean.engineerings+"】";
//			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VCheckRequest bean=dao.getBeanById(id);
		System.out.println("id【"+id+"】bean【"+bean+"】");
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
	}
	@Test
	public void updateByPrimaryKey(){
		VCheckRequest bean=dao.getBeanById(id);
		bean.checkTime=new Date();
		bean.requestType=1;
		int flag=dao.updateByPrimaryKey(bean);
		System.out.println("bean【"+bean+"】flag【"+flag+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <1; i++) {
			VCheckRequest bean=new VCheckRequest();
			bean.createTime=new Date();
			bean.gcdId=4;
			bean.stageId=52;
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
		VCheckRequest bean=dao.getBeanById(id);
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
