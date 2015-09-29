package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierCondition;

public interface SupplierConditionMapper  extends Mapper<SupplierCondition>{

	List<SupplierCondition> judgeSuppCondition(Map<String, Object> map);

}
