package com.jkkp.modules.product.service;

import java.util.List;



import com.jkkp.common.IService;
import com.jkkp.modules.product.model.ItemRecommand;
import com.jkkp.modules.product.view.VItemRecommand;

public interface IItemRecommandService extends IService<ItemRecommand,VItemRecommand,Integer>{
	List<VItemRecommand> index(String name);
	List<VItemRecommand> yugao(String name);
	void dele(String name);
	void saveOrUpdate(Integer[] itemIdList,String city,Integer[] itId,Integer[] iId);
}
