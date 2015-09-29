package com.jkkp.modules.supplier.service;
import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.view.VSupplierUser;

public interface ISupplierUserService extends IService<SupplierUser,VSupplierUser,Integer>{
	public Long getc(Integer sid);
	void saveUpdate(SupplierUser supplierUser);
	public VSupplierUser login(String name,String pass,Integer loginType);
	public long checkUserName(String name, Integer id);
	
	VSupplierUser findSupplier(String name, Integer loginType);
	
	public Boolean checkSupplierUserMobile(String mobile,Integer id);
	
	
	public List queryUserInfo(Map<String, Object> params);
}
