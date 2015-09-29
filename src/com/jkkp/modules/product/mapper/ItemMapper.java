package com.jkkp.modules.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.view.VItem;
import com.jkkp.modules.product.view.VItemHot;

public interface ItemMapper extends Mapper<Item> {
	List<Item> name(@Param("name") String name);
	List<Item> fin(@Param("name") String name);
	List<VItem> findPage(Map<String, Object> params);
	public long countPage(Map<String, Object> params);
	List<VItemHot> activityitem(Map<String, Object> params);
	
	//建材
	List<VItem> selecSpItemList(Map<String, Object> params);
	public long selectSpItemCount(Map<String, Object> params);
	Item selectById(@Param("id")Integer id);
}