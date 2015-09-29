package com.jkkp.modules.supplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierConditionCity;
import com.jkkp.modules.supplier.view.VSimpleConditionCity;

public interface SupplierConditionCityMapper extends Mapper<SupplierConditionCity>{

	List<VSimpleConditionCity> getCityBySpId(@Param("spId")Integer spId);

	List<SupplierConditionCity> getConditionCityBySpId(@Param("spId")Integer spId);

}
