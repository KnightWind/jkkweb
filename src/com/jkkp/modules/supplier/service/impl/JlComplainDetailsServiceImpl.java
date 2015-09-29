package com.jkkp.modules.supplier.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JlComplainDetailsMapper;
import com.jkkp.modules.supplier.model.JlComplainDetails;
import com.jkkp.modules.supplier.service.IJlComplainDetailsService;
import com.jkkp.modules.supplier.view.VJlComplaintsDetails;

@Service("jlComplainDetails")
public class JlComplainDetailsServiceImpl extends
		ServiceSupport<JlComplainDetails, VJlComplaintsDetails, Integer>
		implements IJlComplainDetailsService {
    @Autowired
    private JlComplainDetailsMapper jlComplaintsDetailsMapper;
	
	@Override
	protected Mapper<JlComplainDetails> getMapper() {
		return jlComplaintsDetailsMapper;
	}

	//
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOne(JlComplainDetails j) {
		// 只提供新增功能
		j.setCreateTime(new Date());
		j.setTypeId(3);
		j.setTypeName("客服");
		//j.setUserId(1);
		// 注：暂不能获取登录用户 此处写死
		//j.setUserName("admin");
		this.save(j);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOneJL(JlComplainDetails j) {
		//监理回复投诉
		j.setCreateTime(new Date());
		j.setTypeId(2);
		j.setTypeName("监理");
		this.save(j);
	}

}
