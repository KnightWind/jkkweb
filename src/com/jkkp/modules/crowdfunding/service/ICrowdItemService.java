package com.jkkp.modules.crowdfunding.service;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.crowdfunding.view.VCrowdItem;

public interface ICrowdItemService extends IService<CrowdItem, VCrowdItem, Integer> {

	VCrowdItem itemInfo(Integer id);

	CrowdItem findByItemId(Integer id);

}
