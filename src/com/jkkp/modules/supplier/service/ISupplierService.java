package com.jkkp.modules.supplier.service;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.view.VSupplier;

public interface ISupplierService extends IService<Supplier, VSupplier, Integer> {
	public void saveOrUpdate(Supplier supplier);

	public Supplier supplierDetail(int id);

	public VSupplier login(String name, String pass);

	public String getAndByRequest(HttpServletRequest request);

	public Supplier supervisorLogin(String name, String pwd);

	public void updateSupervisor(Supplier supplier);

	public void update(Supplier supplier, Integer cid, String remark, HttpServletRequest request);

	public String findSupplierName(Integer jlId);
	
	public void updatebusinessCodeAndIdCard(String businessCode,String idCard,Integer spId);
	
	//---------管理后台
	/**
	 * 删除商户信息
	 * @param spId  商户id
	 */
	public void deleteSupplierInfosBySpid(Integer spId);
	
	/**
	 * 审核商户
	 * @param level
	 */
	public void proxyOneSupplier(Integer level,Integer proxyStatus,Integer spId,Float gainRate);

}
