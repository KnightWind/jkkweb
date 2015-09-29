package com.basedata.test;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;

import com.jkkp.modules.basedata.mapper.CaseMapper;
import com.jkkp.modules.basedata.view.VCase;
import com.jkkp.modules.supplier.mapper.SupplierCommentMapper;
import com.jkkp.modules.supplier.mapper.SupplierLabelMapper;
import com.jkkp.modules.supplier.model.SupplierLabel;
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
public class CaseTest {//商家评论
	@Autowired public CaseMapper dao;
	private Integer id=1;
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VCase> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VCase bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getKgdFlag0ListBySpId(){//不可参观工地
		List<VCase> list=dao.getKgdFlag0ListBySpId(8);
		for (int i = 0; i < list.size(); i++) {
			VCase bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getKgdFlag1ListBySpId(){//可参观工地
		List<VCase> list=dao.getKgdFlag1ListBySpId(8);
		for (int i = 0; i < list.size(); i++) {
			VCase bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getListBySpId(){
		List<VCase> list=dao.getListBySpId(8);
		for (int i = 0; i < list.size(); i++) {
			VCase bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+="coverurl【"+bean.coverurl+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		List<VCase> list=dao.getList("");
		for (int i = 0; i < list.size(); i++) {
			VCase bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+="stylename【"+bean.stylename+"】";
			ss+="stylename【"+bean.housestyle+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VCase bean=dao.getBeanById(id);
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		BaseTools.showMessageByMap(JSONObject.fromObject(bean,AllDao.jcfg),"");
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			VCase bean=new VCase();
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
