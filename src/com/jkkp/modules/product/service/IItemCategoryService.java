package com.jkkp.modules.product.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.view.VItemCategory;

public interface IItemCategoryService extends IService<ItemCategory, VItemCategory, Integer> {

	String findNameById(Integer id);

	void saveOrUpdate(ItemCategory entity);

	public int countSubItem(Integer parentId);
	
	//获取根分类
	public List<ItemCategory> getAllParentItemCategory();
}
