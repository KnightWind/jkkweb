package com.jkkp.modules.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.ComplainMapper;
import com.jkkp.modules.system.model.Complain;
import com.jkkp.modules.system.service.IComplainService;
import com.jkkp.modules.system.view.VComplain;

@Service("complainService")
public class ComplainServiceImpl extends ServiceSupport<Complain, VComplain, Integer> implements
		IComplainService {

	@Autowired
	private ComplainMapper complaintsMapper;
	
	@Override
	protected Mapper<Complain> getMapper() {
		return complaintsMapper;
	}

	@Override
	public VComplain complaintDetail(int id) {
		return complaintsMapper.complaintDetail(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateTime(int cid) {
		if(cid>0){
			Complain CD = this.findById(cid);
			CD.setUpdateTime(new Date());
			this.update(CD);
		}
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closeOneComplaint(int id) {
		if(id>0){
			complaintsMapper.closeOneComplaint(id);
		}	
	}
	
}
