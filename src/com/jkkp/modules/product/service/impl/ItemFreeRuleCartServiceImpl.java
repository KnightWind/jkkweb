package com.jkkp.modules.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.ItemFreeRuleCartMapper;
import com.jkkp.modules.product.model.ItemFreeRuleCart;
import com.jkkp.modules.product.service.IItemFreeRuleCartService;
import com.jkkp.modules.product.view.VItemFreeRuleCart;

@Service("itemFreeRuleCartService")
public class ItemFreeRuleCartServiceImpl extends
		ServiceSupport<ItemFreeRuleCart, VItemFreeRuleCart, Integer> implements
		IItemFreeRuleCartService {

	@Autowired
	private ItemFreeRuleCartMapper itemFreeRuleCartMapper;
	
	@Override
	public List<VItemFreeRuleCart> selectAllFreeRuleCart(int ruleId) {
		return itemFreeRuleCartMapper.selectAllFreeRuleCart(ruleId);
	}

	@Override
	protected Mapper<ItemFreeRuleCart> getMapper() {
		return itemFreeRuleCartMapper;
	}

}
