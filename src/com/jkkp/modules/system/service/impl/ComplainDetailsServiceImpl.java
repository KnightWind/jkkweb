package com.jkkp.modules.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.ComplainDetailsMapper;
import com.jkkp.modules.system.model.ComplainDetails;
import com.jkkp.modules.system.service.IComplainDetailsService;
import com.jkkp.modules.system.view.VComplainDetails;

@Service("complainDetailsService")
public class ComplainDetailsServiceImpl extends
		ServiceSupport<ComplainDetails, VComplainDetails, Integer>
		implements IComplainDetailsService {

	@Autowired
	private ComplainDetailsMapper complaintsDetailsMapper;

	
	@Override
	public List<ComplainDetails> complaintDetailList(int cid) {
		return complaintsDetailsMapper.complaintDetailList(cid);
	}

	@Override
	protected Mapper<ComplainDetails> getMapper() {
		return complaintsDetailsMapper;
	}

	// 2015-6-4写入投诉跟进 因登录帐号不能获取，字段值写死
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(ComplainDetails com) {
		if (com.getId() != null) {
			
		} else {
			com.setCreateTime(new Date());
			com.setTypeId(2);
			com.setTypeName("客服");
			com.setUserId(4);
			com.setUserName("小彩姑娘");
			this.save(com);		
		}
	}

}
