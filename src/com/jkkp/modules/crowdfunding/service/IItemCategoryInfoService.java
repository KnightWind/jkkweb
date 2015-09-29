package com.jkkp.modules.crowdfunding.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.ItemCategoryInfo;
import com.jkkp.modules.crowdfunding.view.VItemCategoryInfo;

public interface IItemCategoryInfoService extends IService<ItemCategoryInfo, VItemCategoryInfo, Integer> {
	public void saveOne(ItemCategoryInfo bean);

	public List<VItemCategoryInfo> getAllItemCategoryInfo();
	
	public void deleteOneZCItemCategory(int id);
}
