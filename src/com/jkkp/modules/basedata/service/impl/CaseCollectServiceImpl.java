package com.jkkp.modules.basedata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.CaseCollectMapper;
import com.jkkp.modules.basedata.model.CaseCollect;
import com.jkkp.modules.basedata.service.ICaseCollectService;
import com.jkkp.modules.basedata.view.VCaseCollect;

@Service("caseCollectService")
public class CaseCollectServiceImpl extends ServiceSupport<CaseCollect, VCaseCollect, Integer>
		implements ICaseCollectService {

	@Autowired
	private CaseCollectMapper caseCollectMapper;

	@Override
	protected Mapper<CaseCollect> getMapper() {	
		return caseCollectMapper;
	}
	
	
	

	
	

}
