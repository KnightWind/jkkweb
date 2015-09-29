package com.jkkp.modules.activities.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.activities.model.PrizeResult;
import com.jkkp.modules.activities.view.VPrizeResult;

public interface PrizeResultMapper extends Mapper<PrizeResult> {

	List<VPrizeResult> findPage(Map<String, Object> params);
	long countPage(Map<String, Object> params);
	
}
