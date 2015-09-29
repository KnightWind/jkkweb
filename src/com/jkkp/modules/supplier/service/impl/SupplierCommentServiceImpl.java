package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCommentMapper;
import com.jkkp.modules.supplier.model.SupplierComment;
import com.jkkp.modules.supplier.service.ISupplierCommentService;
import com.jkkp.modules.supplier.view.VSupplierComment;

@Service("supplierCommentService")
public class SupplierCommentServiceImpl extends
		ServiceSupport<SupplierComment, VSupplierComment, Integer> implements
		ISupplierCommentService {

	@Autowired
	private SupplierCommentMapper supplierCommentMapper;
	
	@Override
	protected Mapper<SupplierComment> getMapper() {
		return supplierCommentMapper;
	}
	
	
	

}
