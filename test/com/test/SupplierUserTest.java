package com.test;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.CommonUtil;

import net.sf.json.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class SupplierUserTest {
	@Autowired public SupplierUserMapper dao;
	@Autowired private ISupplierService supplierService;
	private String id="192";
	@Test
	public void demo(){
		System.out.println("dao【"+dao+"】");
		System.out.println("supplierService【"+supplierService+"】");
	}
//	@Test
//	public void pagination(){
//		Pagination<VSupplier> page=(Pagination<VSupplier>)supplierService.pagination();
//		System.out.println(page);
//		List<VSupplier> list=(List<VSupplier>)page.getDataList();
//		System.out.println("list.size【"+list.size()+"】");
//		for (int i = 0; i < list.size(); i++) {
//			VSupplier bean=list.get(i);
//			String ss=(i+1)+"/"+list.size()+"\t";
//			ss+="id【"+bean.getId()+"】";
//			ss+="商家名称【"+bean.getSpName()+"】";
//			ss+="地址【"+bean.getAddress()+"】";
//			System.out.println(ss);
//		}
//	}
//	@Test
//	public void deleteByAnd(){
//		String and=" and id='"+id+"' ";
//		int flag=dao.deleteByAnd(and);
//		System.out.println("delete【"+flag+"】");
//	}
//	@Test
//	public void deleteById(){
//		Supplier bean=dao.getBeanById(id);
//		int flag=dao.deleteByPrimaryKey(bean);
//		System.out.println("delete【"+flag+"】");
//	}
//	@Test
//	public void update(){
//		Supplier bean=dao.getBeanById(id);
//		System.out.println(bean.getId());
//		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
//		bean.setSpName("你好");
//		int flag=dao.updateByPrimaryKey(bean);
//		System.out.println("flag【"+flag+"】");
//	}
//	@Test
//	public void getBeanById(){
//		VSupplier bean=dao.getBeanById(" 22 ");
//		System.out.println(bean);
//		if(bean!=null){
//			System.out.println(bean.getId());
//			System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
//		}
//	}
	@Test
	public void getBeanByUsername() throws Exception{
		VSupplierUser bean=dao.getBeanByUsername(" test2 ");
		System.out.println(bean);
		if(bean!=null){
			System.out.println(bean.getId());
			System.out.println(bean.getUsername());
			System.out.println(bean.getUserpwd());
			System.out.println(CommonUtil.md5("test1"));
			System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		}
	}
//	@Test
//	public void insert(){
//		for (int i = 0; i < 1; i++) {
//			Supplier bean=new Supplier();
//			int flag=dao.insert(bean);
//			System.out.println("flag【"+flag+"】id【"+bean.getId()+"】");
//		}
//	}
//	@Test
//	public void getPageList(){
//		int pageSize=20;
//		int pageNo=2;
//		String and="";
//		and+=" and id='169' ";
//		and+=" order by id desc ";
//		long count=dao.getCount(and);
//		Pager pager=new Pager(and,pageSize,pageNo,count);
//		List<VSupplier> list=dao.getPageList(pager);
//		System.out.println("记录数【"+count+"】总页数【"+pager.getPageCount()+"】");
//		System.out.println("list.size【"+list.size()+"】");
//		for (int i = 0; i < list.size(); i++) {
//			VSupplier bean=list.get(i);
//			String ss=(i+1)+"/"+list.size()+"\t";
//			ss+="id【"+bean.getId()+"】";
//			ss+="商家名称【"+bean.getSpName()+"】";
//			ss+="地址【"+bean.getAddress()+"】";
//			System.out.println(ss);
//		}
//	}
	@Test
	public void getList(){
		String and="";
		List<VSupplierUser> list=dao.getList(and);
		System.out.println("list.size【"+list.size()+"】");
		for (int i = 0; i < list.size(); i++) {
			VSupplierUser bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.getId()+"】";
			ss+="spId【"+bean.spId+"】";
			if(bean.supplier!=null){
				ss+="supplier.id【"+bean.supplier.id+"】";
			}
			ss+="supplier【"+bean.supplier+"】";
//			ss+="username【"+bean.getUsername()+"】";
//			ss+="userpwd【"+bean.getUserpwd()+"】";
			System.out.println(ss);
		}
	}
	@Test
	public void getCount(){
		String and="   ";
		System.out.println("记录数【"+dao.getCount(and)+"】");
	}
}
