package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.SupplierCompanyStaffInfo;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;
import com.jkkp.pc.staff.view.VStaff;
import com.jkkp.utils.Pager;

public interface SupplierCompanyStaffMapper extends Mapper<SupplierCompanyStaff> {
	
	public List<SupplierCompanyStaffInfo> selectSaffListBySpId(Map<String,Object> params);
	
	public List<SupplierCompanyStaffInfo> findSaffListBySpId(Map<String,Object> params);
	
	//获取设计师列表--start
	public List<SupplierCompanyStaffInfo> selectStaffDesgins(Map<String,Object> params);
	public long selectStaffDesginsCount(Map<String,Object> params);
	//获取设计师列表 --end
	
	public long findSaffListCountBySpId(Map<String,Object> params);
	
	public void del(@Param("id") Integer id);
	
	public List<Staff> all(@Param("spid") Integer spid);
	
	public VDesing every(@Param("spid") Integer spid,@Param("sid") Integer sid,@Param("name") String name);
	
	public VIStaff  getById(@Param("sid") Integer sid);
	
	
	//ysc=========================================
	public List<VSupplierCompanyStaff> getList(String and);
	public List<VSupplierCompanyStaff> getPageList(Pager pager);
	public long getCount(String and);
	public VSupplierCompanyStaff getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================

	public List<SupplierCompanyStaff> queryDesignStaffBySpId(Map<String, Object> map);

	
	//pc web say
	public List<VStaff> query4Staff(Integer id);
	
	//获取建材商员工列表
	public List<VSupplierCompanyStaff> selectJCSupplierStaff(@Param("spId")Integer spId,@Param("status")Integer status);
	
	//  家可可管理后台商家员工审核 
	 public List<VSupplierCompanyStaff>  adminSupplierStaff(Map<String, Object> map);
	 public Long  adminSupplierStaffCount(Map<String, Object> map);
	 
	 public void updateSupplierStaffStatus(@Param("staffId")int staffId,@Param("gainRate")Float gainRate,@Param("status")Integer status);
	//  ----------家可可管理后台商家员工审核 -----------
}