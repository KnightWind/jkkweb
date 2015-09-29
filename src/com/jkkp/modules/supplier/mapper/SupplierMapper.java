package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.SupplierNameSN;
import com.jkkp.appapi.modules.mapper.VFangan;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.appapi.modules.mapper.VISupplier;
import com.jkkp.appapi.modules.mapper.VISupplierV1;
import com.jkkp.appapi.modules.mapper.VISupplierV2;
import com.jkkp.appapi.modules.mapper.VISupplierXiang;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.view.VSimpleSupplierBean;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.modules.supplier.view.VSupplierComment;
import com.jkkp.pc.business.view.VBusiness;
import com.jkkp.pc.supervisor.view.SupplierPC;
import com.jkkp.utils.Pager;

public interface SupplierMapper extends Mapper<Supplier> {
	
	//---------管理后台
	 public void deleteSupplierBySpid(@Param("spid")Integer spId);
	   
	 public void deleteSupplierUserBySpid(@Param("spid")Integer spId);
	 
	 public void deleteSupplierCompanyStaff(@Param("spid")Integer spId);
	 
	 public void proxyOneSupplier(@Param("level")Integer level,@Param("proxyStatus")Integer proxyStatus,@Param("spId")Integer spId,@Param("gainRate")Float gainRate);
	//----------管理后台
	
	public List<Supplier> findPage(Map<String, Object> params);

	public List<VSupplier> findList(Map<String, Object> params);

	public List<VFangan> fangAn(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	public VSupplier login(@Param("name") String name,
			@Param("pass") String pass);

	public Supplier findByUserName(Map<String, Object> map);
	public List<VISupplier> findlishi(Map<String, Object> map);
	public List<VISupplier> findAppDetaBySpId(Map<String, Object> map);

	public List<VISupplierV1> supplierNameById(Map<String, Object> map);

	public List<VSupplierBnjn> zhonghe(Map<String, Object> map);

	public List<VSupplierBnjn> xingji(Map<String, Object> map);

	public List<SupplierNameSN> finXiangQing(@Param("uid") Integer uid,
			@Param("spid") Integer spid);

	// ysc=========================================
	public List<VSupplier> getList(String and);

	public List<VSupplier> getPageList(Pager pager);

	public long getCount(String and);

	public VSupplier getBeanById(Integer id);

	public VSupplier getBeanByUsername(String username);

	public int deleteByAnd(String and);

	public List<VISupplierV1> getSupplierNameList(String and);

	// ysc=========================================
	public List<VISupplierXiang> querySupplier(@Param("spid") Integer spid,
			@Param("uid") Integer uid);

	public List<VSupplierComment> supplierCommentList(Map<String, Object> params);

	public List<VIStaff> querShouCan(Map<String, Object> params);

	public long supplierCommentListCount(Map<String, Object> params);

	public Supplier supervisorLogin(@Param("name") String name,
			@Param("pass") String pass);

	/**
	 * 根据装修公司列表维护员工信息
	 */
	// -----------------------------------------
	public List<VSimpleSupplierBean> findSupplierByType(
			Map<String, Object> params);

	public long findSupplierByTypeCount(Map<String, Object> params);

	// -----------------------------------------

	public List<VISupplierV2> SupplierList(Map<String, Object> params);

	// pc 活动页
	public List<VBusiness> selectBusinessList();

	// ----------pcweb前端------------
	public List<SupplierPC> selectAllJLSuppliers(Map<String, Object> params);

	public long selectAllJLSuppliersCount(Map<String, Object> params);
	
	//建材商后台
	public void updatebusinessCodeAndIdCard(@Param("businessCode")String businessCode,@Param("idCard")String idCard,@Param("spId")Integer spId);
     
	//pc 活动页
	public List<VBusiness> selectBusinessList(Map<String, Object> map);
	public VBusiness selectBusiness(@Param("id")Integer id);
	public VISupplierXiang querySupplierBySpId(@Param("spid")Integer spid);
	public long selectBusinessListCount(Map<String, Object> map);
}