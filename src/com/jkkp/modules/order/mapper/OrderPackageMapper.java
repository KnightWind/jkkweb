package com.jkkp.modules.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.OrderPackage;
import com.jkkp.modules.order.view.VOrderPackage;

public interface OrderPackageMapper extends Mapper<OrderPackage> {
	public List<VOrderPackage> selectAllUserOrderPackage(@Param("id")int id);
	public List<VOrderPackage> selectAllUserOrderPackageItem(@Param("id")int id);
}