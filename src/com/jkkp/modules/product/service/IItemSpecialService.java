package com.jkkp.modules.product.service;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.ItemSpecial;
import com.jkkp.modules.product.view.VItemSpecial;

public interface IItemSpecialService extends IService<ItemSpecial, VItemSpecial, Integer> {

	void saveOrUpdate(ItemSpecial special);

	void offline(Integer id);

	void online(Integer id);
}
