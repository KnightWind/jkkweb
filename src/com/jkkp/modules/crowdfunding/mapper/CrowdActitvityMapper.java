package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.CrowdActitvity;
import com.jkkp.modules.crowdfunding.view.VCrowdActitvity;

public interface CrowdActitvityMapper extends Mapper<CrowdActitvity> {

	public List<VCrowdActitvity> findPage(Map<String,Object> param);
	public long countPage(Map<String,Object> param);
	
}
