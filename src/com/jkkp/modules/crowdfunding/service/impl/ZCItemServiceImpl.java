package com.jkkp.modules.crowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.crowdfunding.mapper.ActivityItemMapper;
import com.jkkp.modules.crowdfunding.model.ActivityItem;
import com.jkkp.modules.crowdfunding.service.IZCItemService;
import com.jkkp.modules.crowdfunding.view.VZCItem;

@Service("zcItem")
public class ZCItemServiceImpl extends ServiceSupport<ActivityItem, VZCItem, Integer> implements
		IZCItemService {

	@Autowired
	private ActivityItemMapper activityItemMapper;
	@Autowired
	private IAttachmentService attachmentService;
	
	@Override
	protected Mapper<ActivityItem> getMapper() {
		return activityItemMapper;
	}

	@Override
	public void updateOneItemFlag(int id) {
		activityItemMapper.updateOneItemFlag(id);
	}

	@Override
	public void updateOneItemFlagFalse(Long id) {
		activityItemMapper.updateOneItemFlagFalse(id);		
	}

	@Override
	public void deleteOneZCItem(int id) {
		  this.deleteById(id);
	}

	@Override
	public Long selectItemId(Long id) {
		return activityItemMapper.selectItemId(id);
	}

	@Override
	public List<VZCItem> selectAllHomeZCItem() {
		List<VZCItem> homeItemList = activityItemMapper.selectAllHomeZCItem();
		//设置图片
		if(homeItemList.size()>0){
			for (VZCItem item : homeItemList) {
				List<String> imgUrlList = attachmentService.findForDownload(item.getId(), AttachmentConstant.ACTIVITY_ZC_ITEM);
				if(imgUrlList.size()>0){
					item.setImgUrl(imgUrlList.get(0));
				}
			}
		}
		return homeItemList;
	}

}
