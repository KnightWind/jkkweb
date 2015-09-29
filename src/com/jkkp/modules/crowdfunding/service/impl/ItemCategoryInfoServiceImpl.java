package com.jkkp.modules.crowdfunding.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.crowdfunding.mapper.ItemCategoryInfoMapper;
import com.jkkp.modules.crowdfunding.model.ItemCategoryInfo;
import com.jkkp.modules.crowdfunding.service.IItemCategoryInfoService;
import com.jkkp.modules.crowdfunding.view.VItemCategoryInfo;

@Service("itemCategoryInfoService")
public class ItemCategoryInfoServiceImpl extends
		ServiceSupport<ItemCategoryInfo, VItemCategoryInfo, Integer> implements
		IItemCategoryInfoService {

	@Autowired
	private ItemCategoryInfoMapper itemCategoryInfoMapper;
	@Autowired
	private IAttachmentService attachmentService;

	@Override
	protected Mapper<ItemCategoryInfo> getMapper() {
		return itemCategoryInfoMapper;
	}

	@Transactional
	public void saveOne(ItemCategoryInfo bean) {
		bean.setCreateTime(new Date());
		bean.setStatus(1);
		this.save(bean);
	}

	@Override
	public List<VItemCategoryInfo> getAllItemCategoryInfo() {
		List<VItemCategoryInfo> list = itemCategoryInfoMapper
				.selectAllItemCategoryItems();
		for (VItemCategoryInfo bean : list) {
			List<String> urlList=attachmentService.findForDownload(bean.getId(),AttachmentConstant.ACTIVITY_HOME);
			if(urlList.size()>0){
				bean.setImgUrl(urlList.get(0));
			}
		}
		return list;
	}

	@Override
	public void deleteOneZCItemCategory(int id) {
		itemCategoryInfoMapper.deleteOneZCItemCategory(id);
	}

}
