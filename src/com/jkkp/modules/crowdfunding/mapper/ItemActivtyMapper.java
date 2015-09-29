package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import com.jkkp.modules.product.model.Item;

public interface ItemActivtyMapper {
	
	public List<Item> findCrowdItemList(Map<String,Object> map);

}
