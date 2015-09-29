package com.jkkp.modules.supplier.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierComplainDetailsMapper;
import com.jkkp.modules.supplier.model.SupplierComplainDetails;
import com.jkkp.modules.supplier.service.ISupplierComplaintsDetailsService;
import com.jkkp.modules.supplier.view.VSupplierComplaintsDetails;

@Service("supplierComplaintsDetailsService")
public class SupplierComplaintsDetailsServiceImpl
		extends
		ServiceSupport<SupplierComplainDetails, VSupplierComplaintsDetails, Integer>
		implements ISupplierComplaintsDetailsService {

	@Autowired
	private SupplierComplainDetailsMapper SupplierComplaintsDetailsMapper;

	
	@Override
	protected Mapper<SupplierComplainDetails> getMapper() {
		return SupplierComplaintsDetailsMapper;
	}

	//客服跟进
	@Transactional
	public void saveOne(SupplierComplainDetails s) {
		// 只提供新增功能
		s.setCreateTime(new Date());
		s.setTypeId(3);
		s.setTypeName("客服");
		// 注：暂不能获取登录用户 此处写死
		s.setUserName("测试");
		this.save(s);
	}
//商家回复
	@Transactional
	public void saveOneSU(SupplierComplainDetails s) {
		s.setCreateTime(new Date());
		s.setTypeId(2);
		s.setTypeName("商家");
		this.save(s);
	}

}
