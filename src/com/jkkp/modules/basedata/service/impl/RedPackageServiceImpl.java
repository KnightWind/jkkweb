package com.jkkp.modules.basedata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.RedPackageMapper;
import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.modules.basedata.service.IRedPackageService;
import com.jkkp.modules.basedata.view.VRedPackage;

@Service("redPackageService")
public class RedPackageServiceImpl extends ServiceSupport<RedPackage, VRedPackage, Integer> implements IRedPackageService {

	@Autowired
	private RedPackageMapper redPackageMapper;
	
	
	
	
	@Override
	protected Mapper<RedPackage> getMapper() {
		return redPackageMapper;
	}




	@Transactional(readOnly=true)
	public VRedPackage findPackageById(Integer id) {
		return redPackageMapper.findPackageById(id);
	}

}
