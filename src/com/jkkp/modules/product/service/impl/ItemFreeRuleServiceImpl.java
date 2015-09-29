package com.jkkp.modules.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.ItemFreeRuleMapper;
import com.jkkp.modules.product.model.ItemFreeRule;
import com.jkkp.modules.product.service.IItemFreeRuleService;
import com.jkkp.modules.product.view.VItemFreeRule;

@Service("itemFreeRuleService")
public class ItemFreeRuleServiceImpl extends ServiceSupport<ItemFreeRule,VItemFreeRule,Integer> implements IItemFreeRuleService{
	@Autowired
	private ItemFreeRuleMapper itemFreeRuleMapper;
	@Override
	protected Mapper<ItemFreeRule> getMapper() {
		return itemFreeRuleMapper;
	}

}
