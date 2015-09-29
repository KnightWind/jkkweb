package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;


import com.jkkp.appapi.modules.mapper.VISupplierXiang;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.view.VSupplierCollect;

public interface ISupplierCollectSV extends IService<SupplierCollect, VSupplierCollect, Integer>{
	
	public Integer querySupCollByUid(Map<String, Object> map);
	public List<SupplierCollect> querySupCollDetailByUid(Map<String, Object> map);
	List<VSupplierBnjn> queryShouCan(Map<String, Object> map);
	List<SupplierCollect> querySupCollByUidSpid(Map<String, Object> map);
	List<VSupplierBnjn> querySupplierListDetial(Map<String, Object> map);
}
