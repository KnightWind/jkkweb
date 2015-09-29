package com.jkkp.modules.supplier.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.SupplierCompanyStaffInfo;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;

public interface ISupplierCompanyStaffService extends IService<SupplierCompanyStaff,VSupplierCompanyStaff,Integer>{
	public List<SupplierCompanyStaffInfo> findSaffListBySpId(Map<String,Object> params);
	public void saveUpdate(Integer id,String[] tra,String[] name,String [] job);
	public void del(Integer id);
	public void deleteStaffById(Integer id, List<Attachment> att);
	
	/**
	 * 获取商户员工列表
	 * @param spId 商户id
	 * @return
	 */
	public List<SupplierCompanyStaff> getAllSupplierStaff(Integer spId);
	
	/**
	 * 保存或更新一员工信息
	 * @param bean
	 */
	public void saveOrUpdate(SupplierCompanyStaff bean);
	
	//获取建材商员工列表
	public List<VSupplierCompanyStaff> selectJCSupplierStaff(Integer spId,Integer status);
	
	/**
	 * 审核商家员工
	 * @param staffId id号
	 */
	public void updateSupplierStaffStatus(int staffId,Float gainRate,Integer status);
}
