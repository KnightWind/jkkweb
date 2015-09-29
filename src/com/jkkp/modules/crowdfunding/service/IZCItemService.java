package com.jkkp.modules.crowdfunding.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.ActivityItem;
import com.jkkp.modules.crowdfunding.view.VZCItem;

public interface IZCItemService extends IService<ActivityItem, VZCItem, Integer> {
	public void updateOneItemFlag(int id);
	
	public void updateOneItemFlagFalse(Long id);
	
	public void deleteOneZCItem(int id);
	
	public Long selectItemId(Long id);
	
	public List<VZCItem> selectAllHomeZCItem();
}
