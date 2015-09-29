package com.jkkp.modules.supplier.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.Pager;

public interface SupplierUserMapper extends Mapper<SupplierUser>{

	public Long getc(@Param("sid") Integer sid);

	public List<SupplierUser> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);
	SupplierUser findByUserName(Map<String, Object> map);
	public VSupplierUser login(@Param("name") String name,@Param("pass") String pass,@Param("loginType") Integer loginType);

	public VSupplierUser findSupplier(@Param("name") String name, @Param("loginType") Integer loginType);

	//ysc=========================================
	public List<VSupplierUser> getList(String and);
	public List<VSupplierUser> getPageList(Pager pager);
	public long getCount(String and);
	public VSupplierUser getBeanById(String id);
	public VSupplierUser getBeanByUsername(String username);
	public int deleteByAnd(String and);
	//ysc=========================================

	//web端 ->检测账号是否存在
	public long checkUserName(@Param("name")String name, @Param("id")Integer id);
	//web端->检测手机号码是否重复
	public List<SupplierUser> selectSupplierUserByMobile(@Param("mobile")String mobile,@Param("id")Integer id);
	
	//根据传入参数查询
	public VSupplierUser getBeanByMap(Map<String, Object> params);
	
	public VSupplierUser getBeanByMobile(String mobile);
	
	public VSupplierUser getBeanByParamMap(Map<String, Object> params);
	
	public List queryUserInfo(Map<String, Object> params);
	
}
