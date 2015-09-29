package com.jkkp.modules.product.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.ItemSpecialMapper;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.model.ItemSpecial;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.product.service.IItemSpecialService;
import com.jkkp.modules.product.view.VItemSpecial;

@Service("itemSpecialService")
public class ItemSpecialServiceImpl extends ServiceSupport<ItemSpecial, VItemSpecial, Integer> implements
		IItemSpecialService {

	@Autowired
	private ItemSpecialMapper itemSpecialMapper;
	@Autowired
	private IItemService itemService;

	@Override
	protected Mapper<ItemSpecial> getMapper() {
		return itemSpecialMapper;
	}

	@Override
	public VItemSpecial convertBeanToView(ItemSpecial entity) {
		VItemSpecial view = super.convertBeanToView(entity);
		Item item = itemService.findById(entity.getItemId());
		view.setProductId(item.getId());
		view.setProductName(item.getTitle());
		view.setProductPrice(item.getPrice());
		view.setStock(item.getStock());
		return view;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(ItemSpecial entity) {
		Item item = itemService.findById(entity.getItemId());
		entity.setCityDomain(item.getCityDomain());
		if (entity.getId()!=null && entity.getId() > 0) {
			this.update(entity);
		} else {
			entity.setCreateTime(new Date());
			entity.setStatus(0);
			entity.setSaleNum(0);
			this.save(entity);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void offline(Integer id) {
		ItemSpecial entity = this.findById(id);
		entity.setStatus(-1);
		this.update(entity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void online(Integer id) {
		ItemSpecial entity = this.findById(id);
		entity.setStatus(0);
		this.update(entity);
	}
}
