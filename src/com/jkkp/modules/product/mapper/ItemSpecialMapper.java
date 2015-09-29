package com.jkkp.modules.product.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.ItemSpecial;
import com.jkkp.modules.product.view.VItemSpecial;

public interface ItemSpecialMapper extends Mapper<ItemSpecial> {

	public List<VItemSpecial> findPage(Map<String, Object> params);
	
	public long countPage(Map<String, Object> params);
}