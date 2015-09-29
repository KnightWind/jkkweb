package com.jkkp.modules.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.ItemFreeRuleCart;
import com.jkkp.modules.product.view.VItemFreeRuleCart;

public interface ItemFreeRuleCartMapper extends Mapper<ItemFreeRuleCart> {
	public List<VItemFreeRuleCart> selectAllFreeRuleCart(
			@Param("ruleId") int ruleId);
}