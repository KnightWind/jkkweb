package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.CrowdItemMapper;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.crowdfunding.service.ICrowdItemService;
import com.jkkp.modules.crowdfunding.view.VCrowdItem;

@Service("crowdItemService")
public class CrowdItemServiceImpl extends ServiceSupport<CrowdItem, VCrowdItem, Integer> implements ICrowdItemService {

	@Autowired
	private CrowdItemMapper crowdItemMapper;
	
	@Override
	protected Mapper<CrowdItem> getMapper() {
		return crowdItemMapper;
	}

	@Override
	public VCrowdItem itemInfo(Integer id) {
		return crowdItemMapper.zcItemInfo(id);
	}

	@Transactional(readOnly=true)
	public CrowdItem findByItemId(Integer itemId) {
		return crowdItemMapper.findByItemId(itemId);
	}
	
	
	

}
