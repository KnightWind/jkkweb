package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VRetCondition;
import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCondition;

public interface ISupplierConditionSV extends IService<SupplierCondition, SupplierCondition, Integer>{

	public VRetCondition judgeSuppConditionResult(int supplierId,float debuge,int regionId);
	
	public List<SupplierCondition> judgeSuppCondition(Map<String, Object> map);
}
