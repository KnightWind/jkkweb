package com.jkkp.appapi.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISupplierUserSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.view.VSupplierUser;

@Service("supplierUserSV")
public class SupplierUserSVImpl extends ServiceSupport<SupplierUser, VSupplierUser, Integer> implements ISupplierUserSV{

	@Autowired
	SupplierUserMapper supplierUserMapper;
	@Override
	protected Mapper<SupplierUser> getMapper() {
		// TODO Auto-generated method stub
		return supplierUserMapper;
	}
	@Override
	public SupplierUser findByUserName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return supplierUserMapper.findByUserName(map);
	}

}
