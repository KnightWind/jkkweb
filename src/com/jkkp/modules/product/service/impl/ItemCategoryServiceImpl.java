package com.jkkp.modules.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.ItemCategoryMapper;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;
import com.jkkp.modules.product.view.VItemCategory;

@Service("itemCategoryService")
public class ItemCategoryServiceImpl extends ServiceSupport<ItemCategory, VItemCategory, Integer> implements
		IItemCategoryService {

	@Autowired
	private ItemCategoryMapper itemCategoryMapper;

	@Override
	protected Mapper<ItemCategory> getMapper() {
		return itemCategoryMapper;
	}

	@Override
	public VItemCategory convertBeanToView(ItemCategory entity) {
		VItemCategory view = super.convertBeanToView(entity);
		view.setSubItemCount(this.countSubItem(view.getId()));
		return view;
	}

	public int countSubItem(Integer parentId) {
		ItemCategory entity = new ItemCategory();
		entity.setParentId(parentId);
		return itemCategoryMapper.selectCount(entity);
	}

	public String findNameById(Integer id) {
		ItemCategory category = this.findById(id);
		return category != null ? category.getName() : "";
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(ItemCategory entity) {
		if (entity.getId() !=null && entity.getId() > 0) {
			this.update(entity);
		} else {
			entity.setAdminId(0); // TODO
			entity.setStatus(1);
			entity.setSeq(1); // TODO
			this.save(entity);
		}
	}

	@Override
	public List<ItemCategory> getAllParentItemCategory() {
		ItemCategory icBean=new ItemCategory();
		icBean.setParentId(0);
		return this.select(icBean);
	}
}
