package com.jkkp.modules.basedata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.SettlementMapper;
import com.jkkp.modules.basedata.model.Settlement;
import com.jkkp.modules.basedata.service.ISettlementService;
import com.jkkp.modules.basedata.view.VSettlement;

@Service("settlementService")
public class SettlementServiceImpl extends ServiceSupport<Settlement, VSettlement, Integer> implements ISettlementService {

	@Autowired
	private SettlementMapper settlementMapper;
	
	
	
	@Override
	protected Mapper<Settlement> getMapper() {
		// TODO Auto-generated method stub
		return settlementMapper;
	}



	@Override
	@Transactional(readOnly=true)
	public Double findUnCalePushMoney(String phone) {
		// TODO Auto-generated method stub
		return settlementMapper.findUnCalePushMoney(phone);
	}

}
