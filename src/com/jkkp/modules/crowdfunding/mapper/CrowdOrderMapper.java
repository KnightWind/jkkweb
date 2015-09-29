package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.CrowdOrder;
import com.jkkp.modules.crowdfunding.view.VCrowdOrder;

public interface CrowdOrderMapper extends Mapper<CrowdOrder> {

		//-------web------>>
		public List<VCrowdOrder> findPage(Map<String, Object> map);
		public long countPage(Map<String, Object> map);
		//---------------->>
	
}
