package com.jkkp.modules.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.ItemFreeRecommand;
import com.jkkp.modules.product.view.VItemFreeRecommand;

public interface ItemFreeRecommandMapper extends Mapper<ItemFreeRecommand> {
	List<ItemFreeRecommand> findBy(@Param("city") String city);
	List<VItemFreeRecommand> index(@Param("name") String name);
	List<VItemFreeRecommand> yugao(@Param("name") String name);
	List<VItemFreeRecommand> list(@Param("name") String name);
	void dele(@Param("name") String name);
}