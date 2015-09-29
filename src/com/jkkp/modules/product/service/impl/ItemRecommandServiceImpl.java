package com.jkkp.modules.product.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.ItemRecommandMapper;
import com.jkkp.modules.product.model.ItemRecommand;
import com.jkkp.modules.product.service.IItemRecommandService;
import com.jkkp.modules.product.view.VItemRecommand;

@Service("itemRecommandService")
public class ItemRecommandServiceImpl extends ServiceSupport<ItemRecommand,VItemRecommand,Integer> implements IItemRecommandService {
	@Autowired
	private ItemRecommandMapper itemRecommandMapper;
	@Override
	public List<VItemRecommand> index(String name) {
		return itemRecommandMapper.index(name);
	}

	@Override
	public List<VItemRecommand> yugao(String name) {
		return itemRecommandMapper.yugao(name);
	}

	@Override
	protected Mapper<ItemRecommand> getMapper() {
		return itemRecommandMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dele(String name) {
		itemRecommandMapper.dele(name);
	}

	@Override
	public void saveOrUpdate(Integer[] itemIdList,String city,Integer[] itId, Integer[] iId) {
		for (int i = 0; i < itemIdList.length; i++) {
			ItemRecommand item=new ItemRecommand();
			item.setCityDomain(city);
			item.setStatus(new Byte("0"));
			item.setCreateTime(new Date());
			item.setLabel("yugao");
			item.setSeq(0);
			item.setItemId(itemIdList[i]);
			this.save(item);
		}
		for (int i = 0; i < iId.length; i++) {
			ItemRecommand recommand=this.findById(iId[i]);
			recommand.setItemId(itId[i]);
			recommand.setCreateTime(new Date());
			this.update(recommand);
		}
	}

}
