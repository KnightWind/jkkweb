package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.MemberCollectMapper;
import com.jkkp.modules.supplier.model.MemberCollect;
import com.jkkp.modules.supplier.service.IMemberCollectService;
import com.jkkp.modules.supplier.view.VMemberCollect;

@Service("memberCollectService")
public class MemberCollectServiceImpl extends
		ServiceSupport<MemberCollect, VMemberCollect, Integer> implements
		IMemberCollectService {
	@Autowired
	private MemberCollectMapper memberCollectMapper;
	@Override
	protected Mapper<MemberCollect> getMapper() {	
		return memberCollectMapper;
	}

}
