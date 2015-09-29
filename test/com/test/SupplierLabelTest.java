package com.test;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.service.IActivitiesService;
import com.jkkp.modules.supplier.mapper.SupplierLabelMapper;
import com.jkkp.modules.supplier.model.SupplierLabel;
import com.jkkp.modules.supplier.view.VSupplierLabel;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.Pager;

import net.sf.json.JSONObject;
@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class SupplierLabelTest {
	@Autowired public SupplierLabelMapper dao;
	@Autowired
	public IActivitiesService activitiesService;
	private Integer id=1;
	
	@Test
	public void testFindAwards() {
		int saleActivityThemeId = 1;
		List<Awards> awardList = activitiesService.findAwards(saleActivityThemeId);
		System.out.println("awardList size = " + awardList.size());
	}
	
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VSupplierLabel> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VSupplierLabel bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	
	
	@Test
	public void getList(){
		List<VSupplierLabel> list=dao.getList("");
		for (int i = 0; i < list.size(); i++) {
			VSupplierLabel bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+="标签【"+bean.lname+"】";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		SupplierLabel bean=dao.getBeanById(id);
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		BaseTools.showMessageByMap(JSONObject.fromObject(bean,AllDao.jcfg),"");
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <25; i++) {
			SupplierLabel bean=new SupplierLabel();
			int flag=dao.insert(bean);
			System.out.println("flag【"+flag+"】bean.id【"+bean.getId()+"】");
		}
	}
	@Test
	public void getCount(){
		String and="  ";
//		ConstantAppStatus
		System.out.println("记录数【"+dao.getCount(and)+"】");
		System.out.println("null【"+StringUtils.isBlank(null)+"】");
		System.out.println("null【"+StringUtils.isBlank("")+"】");
		System.out.println("【"+StringUtils.isBlank("    ")+"】");
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
