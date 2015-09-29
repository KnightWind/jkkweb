package com.jkkp.modules.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.ItemRecommand;
import com.jkkp.modules.product.view.VItemRecommand;

public interface ItemRecommandMapper extends Mapper<ItemRecommand> {
	List<VItemRecommand> index(@Param("name") String name);
	List<VItemRecommand> yugao(@Param("name") String name);
	void dele(@Param("name") String name);
}