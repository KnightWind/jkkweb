package com.supplier.test;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;

import com.jkkp.modules.supplier.mapper.SupplierCommentMapper;
import com.jkkp.modules.supplier.mapper.SupplierLabelMapper;
import com.jkkp.modules.supplier.model.SupplierLabel;
import com.jkkp.modules.supplier.view.VSupplierComment;

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
public class SupplierCommentTest {//商家评论
	@Autowired public SupplierCommentMapper dao;
	private Integer id=1;
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VSupplierComment> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VSupplierComment bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		List<VSupplierComment> list=dao.getList("");
		for (int i = 0; i < list.size(); i++) {
			VSupplierComment bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		VSupplierComment bean=dao.getBeanById(id);
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		BaseTools.showMessageByMap(JSONObject.fromObject(bean,AllDao.jcfg),"");
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			VSupplierComment bean=new VSupplierComment();
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
