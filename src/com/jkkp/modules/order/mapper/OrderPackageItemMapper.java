package com.jkkp.modules.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.OrderPackageItem;
import com.jkkp.modules.order.view.VOrderPackageItem;

public interface OrderPackageItemMapper extends Mapper<OrderPackageItem> {
	public List<VOrderPackageItem> orderItemDetail(@Param("id")int id);
}