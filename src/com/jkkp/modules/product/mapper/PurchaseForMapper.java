package com.jkkp.modules.product.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.PurchaseFor;

public interface PurchaseForMapper extends Mapper<PurchaseFor> {
	public void deleteOnePurchaseFor(@Param("id") int id);
}