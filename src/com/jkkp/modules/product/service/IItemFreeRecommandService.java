package com.jkkp.modules.product.service;

import java.util.List;
import com.jkkp.common.IService;
import com.jkkp.modules.product.model.ItemFreeRecommand;
import com.jkkp.modules.product.view.VItemFreeRecommand;

public interface IItemFreeRecommandService extends IService<ItemFreeRecommand,VItemFreeRecommand,Integer> {
	List<ItemFreeRecommand> findBy(String city);
	public void saveOrUpdate(ItemFreeRecommand itemFreeRecommand);
	List<VItemFreeRecommand> index(String name);
	List<VItemFreeRecommand> yugao(String name);
	List<VItemFreeRecommand> list(String name);
	void saveOrUpdate(Integer[] itemIdList,String city,Integer[] itId,Integer[] iId);
	void dele(String name);
}
