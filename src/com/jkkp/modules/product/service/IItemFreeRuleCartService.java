package com.jkkp.modules.product.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.ItemFreeRuleCart;
import com.jkkp.modules.product.view.VItemFreeRuleCart;

public interface IItemFreeRuleCartService extends IService<ItemFreeRuleCart, VItemFreeRuleCart, Integer> {
	public List<VItemFreeRuleCart> selectAllFreeRuleCart(int ruleId);
}
