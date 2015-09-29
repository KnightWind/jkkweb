package com.jkkp.modules.product.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.ItemFreeRecommandMapper;
import com.jkkp.modules.product.model.ItemFreeRecommand;
import com.jkkp.modules.product.service.IItemFreeRecommandService;
import com.jkkp.modules.product.view.VItemFreeRecommand;
@Service("itemFreeRecommandService")
public class ItemFreeRecommandServiceImpl extends ServiceSupport<ItemFreeRecommand,VItemFreeRecommand,Integer> implements IItemFreeRecommandService {

	@Autowired
	private ItemFreeRecommandMapper itemFreeRecommandMapper;
	@Override
	protected Mapper<ItemFreeRecommand> getMapper() {
		
		return itemFreeRecommandMapper;
	}

	@Override
	public List<ItemFreeRecommand> findBy(String city) {
		return itemFreeRecommandMapper.findBy(city);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(ItemFreeRecommand itemFreeRecommand) {
		if(itemFreeRecommand.getId()!=null&&itemFreeRecommand.getId()>0){
			this.update(itemFreeRecommand);
		}else {
			this.save(itemFreeRecommand);
		}
		
	}

	@Override
	public List<VItemFreeRecommand> index(String name) {
		return itemFreeRecommandMapper.index(name);
	}

	@Override
	public List<VItemFreeRecommand> yugao(String name) {
		return itemFreeRecommandMapper.yugao(name);
	}

	@Override
	public List<VItemFreeRecommand> list(String name) {
		return itemFreeRecommandMapper.list(name);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Integer[] itemIdList, String city, Integer[] itId,
			Integer[] iId) {
		for (int i = 0; i < itemIdList.length; i++) {
			ItemFreeRecommand itemFreeRecommand=new ItemFreeRecommand();
			itemFreeRecommand.setCityDomain(city);
			itemFreeRecommand.setItemId(itemIdList[i]);
			itemFreeRecommand.setLabel("yugao");
			itemFreeRecommand.setStatus(new Byte("0"));
			itemFreeRecommand.setCreateTime(new Date());
			this.save(itemFreeRecommand);
		}
		for (int i = 0; i < iId.length; i++) {
			ItemFreeRecommand item=this.findById(iId[i]);
			item.setItemId(itId[i]);
			item.setCreateTime(new Date());
			this.update(item);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dele(String name) {
	itemFreeRecommandMapper.dele(name);
	}

}
