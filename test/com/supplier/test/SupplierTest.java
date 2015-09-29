package com.supplier.test;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;
import com.jkkp.utils.Pagination;

import net.sf.json.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class SupplierTest {
	@Autowired public SupplierMapper dao;
	@Autowired public SupplierUserMapper supplierUserdao;
	@Autowired private ISupplierService supplierService;
	private Integer id=192;
	@Test
	public void demo(){
		System.out.println("dao【"+dao+"】");
		System.out.println("supplierService【"+supplierService+"】");
	}
	@Test
	public void pagination(){
		Pagination<VSupplier> page=(Pagination<VSupplier>)supplierService.pagination();
		System.out.println(page);
		List<VSupplier> list=(List<VSupplier>)page.getDataList();
		System.out.println("list.size【"+list.size()+"】");
		for (int i = 0; i < list.size(); i++) {
			VSupplier bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+="商家名称【"+bean.getSpName()+"】";
			ss+="地址【"+bean.getAddress()+"】";
			ss+="type【"+bean.type+"】";
			System.out.println(ss);
		}
	}
	@Test
	public void deleteByAnd(){
		String and=" and id='"+id+"' ";
		int flag=dao.deleteByAnd(and);
		System.out.println("delete【"+flag+"】");
	}
	@Test
	public void deleteById(){
		Supplier bean=dao.getBeanById(id);
		int flag=dao.deleteByPrimaryKey(bean);
		System.out.println("delete【"+flag+"】");
	}
	@Test
	public void update(){
		Supplier bean=dao.getBeanById(id);
		System.out.println(bean.getId());
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		bean.setSpName("你好");
		int flag=dao.updateByPrimaryKey(bean);
		System.out.println("flag【"+flag+"】");
	}
	@Test
	public void getBeanById(){
		VSupplier bean=dao.getBeanById(8);
		System.out.println(bean);
		if(bean!=null){
			String ss="id【"+bean.id+"】";
			ss+="保证金【"+bean.levelMoney+"】";
			ss+="收藏数【"+bean.scs+"】";
			ss+="接单数【"+bean.engineeringsCount+"】";
			ss+="装修公司名称【"+bean.spName+"】";
			ss+="案例个数【"+bean.caseCount+"】";
			ss+="案例列表【"+bean.caseList.size()+"】";
			ss+="设计师个数【"+bean.staffCount+"】";
			ss+="设计师列表【"+bean.staffList.size()+"】";
			ss+="可参观工地列表【"+bean.kgd1List.size()+"】";
			ss+="可参观工地数【"+bean.kgd1Count+"】";
			System.out.println(ss);
			System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		}
	}
	@Test
	public void getBeanByUsername() throws Exception{
		VSupplier bean=dao.getBeanByUsername(" foshanzhaoming ");
		System.out.println(bean);
		if(bean!=null){
			System.out.println(bean.getId());
			System.out.println(bean.getUsername());
			System.out.println(bean.getPass());
			System.out.println(CommonUtil.md5("test1"));
			System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		}
	}
	@Test
	public void insert(){
		for (int i = 0; i < 1; i++) {
			Supplier bean=new Supplier();
			int flag=dao.insert(bean);
			System.out.println("flag【"+flag+"】id【"+bean.getId()+"】");
		}
	}
	@Test
	public void getPageList(){
		int pageSize=20;
		int pageNo=2;
		String and="";
		and+=" and id='169' ";
		and+=" order by id desc ";
		long count=dao.getCount(and);
		Pager pager=new Pager(and,pageSize,pageNo,count);
		List<VSupplier> list=dao.getPageList(pager);
		System.out.println("记录数【"+count+"】总页数【"+pager.getPageCount()+"】");
		System.out.println("list.size【"+list.size()+"】");
		for (int i = 0; i < list.size(); i++) {
			VSupplier bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+="商家名称【"+bean.getSpName()+"】";
			ss+="地址【"+bean.getAddress()+"】";
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and="";
		and+=" and levelMoney is not null ";
		List<VSupplier> list=dao.getList(and);
		System.out.println("list.size【"+list.size()+"】");
		for (int i = 0; i < list.size(); i++) {
			VSupplier bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】";
			ss+="类型【"+bean.getTypeVal()+"】";
			ss+="头像【"+bean.photoUrl+"】";
			ss+="姓名【"+bean.spName+"】";
			ss+="监理号【"+bean.legalPerson+"】";
			ss+="address【"+bean.address+"】";
			ss+="注册监理号【"+bean.spCode+"】";
			System.out.println(ss);
		}
	}
	@Test
	public void getCount(){
		String and=" ";
		System.out.println("记录数【"+dao.getCount(and)+"】");
	}
}
