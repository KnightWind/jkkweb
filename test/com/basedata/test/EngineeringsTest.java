package com.basedata.test;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.view.VEngineerings;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class EngineeringsTest {
	@Autowired public EngineeringsMapper dao;
	private Integer id=16;
	@Test
	public void testGoogle(){
		String address="XXX";
		String key ="f247cdb592eb43ebac6ccd27f796e2d2";
		String url="http://api.map.baidu.com/geocoder?address="+address+"&output=json&key="+key;
		String str=BaseTools.doPostByUrl(url, "UTF-8", null,null);
		System.out.println(str);
	}
	@Test
	public void testBaidu(){
		String address="广东省广州白云区";
		String ak="qwQYrfw1zyiadB590lVhFhc1";
		String url="http://api.map.baidu.com/geocoder/v2/?output=json&output=json";
		url+="&address="+address;
		url+="&ak="+ak;
		String str=BaseTools.doPostByUrl(url, "UTF-8", null,null);
		System.out.println(str);
	}
	@Test
	public void getPageList(){
		String and="";
		int pageSize=15;
		int pageNo=1;
		Pager pager=new Pager(and, pageSize, pageNo);
		List<VEngineerings> list=dao.getPageList(pager);
		for (int i = 0; i < list.size(); i++) {
			VEngineerings bean=list.get(i);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+=JSONObject.fromObject(bean,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getList(){
		String and=" ";
//		and+=" and id=46 ";
		and+=" and huxing is not null ";
		and+=" and fengge is not null ";
		List<VEngineerings> list=dao.getList(and);
		for (int i = 0; i < list.size(); i++) {
			VEngineerings bean=list.get(i);
//			VEngineerings temp=dao.getBeanById(bean.id);
			String ss=(i+1)+"/"+list.size()+"\t";
			ss+="id【"+bean.id+"】\n";
			ss+="户型【"+bean.huxing+"】\n";
			ss+="风格【"+bean.fengge+"】\n";
			ss+="报价【"+bean.baojia+"】\n";
			ss+="面积【"+bean.mianji+"】\n";
			ss+="半包全包【"+bean.method2+","+bean.getMethod2Val()+"】\n";
//			ss+="公司名称【"+bean.spName+"】\n";						//公司名称
//			ss+="监理姓名【"+bean.jlName+"】\n";						//监理姓名
//			ss+="业主姓名【"+bean.nickName+"】\n";					//业主姓名
//			ss+="设计师姓名【"+bean.designerName+"】\n";				//设计师姓名
//			ss+="装修风格【"+bean.cateName+"】\n";					//装修风格
//			
//			ss+="工地详情【"+bean.appointment+","+temp.appointment+"】\n";				//工地详情
//			ss+="业主投诉详情【"+bean.complain+"】\n";				//业主投诉详情
//			ss+="业主投诉详情【"+bean.jlSupplier+"】\n";				//监理详情
//			ss+="监理详情【"+bean.spSupplier+"】\n";					//装修公司
//			ss+="监理投诉列表【"+bean.jlcomplainList.size()+"】\n";	//监理投诉列表
//			ss+="spName【"+bean.spName+"】";
//			ss+="设计师姓名【"+bean.designerName+"】";
//			ss+="aid【"+bean.aid+"】";
//			ss+="appointment【"+bean.appointment+"】";
//			ss+="complain【"+bean.complain+"】";
//			ss+="装修风格【"+bean.cateName+"】";
//			ss+="jlList.size【"+bean.jlcomplainList.size()+"】";
//			ss+="complain【"+bean.complain+"】";
//			ss+="pointx【"+bean.pointx+"】pointy【"+bean.pointy+"】";
//			ss+=JSONObject.fromObject(temp,AllDao.jcfg);
			System.out.println(ss);
		}
	}
	@Test
	public void getBeanById(){
		id=1;
		VEngineerings bean=dao.getBeanById(id);
		System.out.println(bean.jlcomplainList);
		System.out.println("监理详情【"+bean.jlSupplier.spName+"】公司详情【"+bean.spSupplier.spName+"】");
		System.out.println("监理姓名【"+bean.jlName+"】装修公司【"+bean.spName+"】");
		System.out.println(JSONObject.fromObject(bean,AllDao.jcfg));
		System.out.println("bean【"+bean+"】id【"+bean.getId()+"】");
	}
	@Test
	public void updateByPrimaryKey(){
		VEngineerings bean=dao.getBeanById(id);
		bean.community="你好";
		int flag=dao.updateByPrimaryKey(bean);
		System.out.println("flag【"+flag+"】bean.id【"+bean.getId()+"】");
	}
	@Test
	public void insert(){
		for (int i = 0; i <1; i++) {
			VEngineerings bean=new VEngineerings();
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
