package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCommentReplyMapper;
import com.jkkp.modules.supplier.model.SupplierCommentReply;
import com.jkkp.modules.supplier.service.ISupplierCommentReplyService;
import com.jkkp.modules.supplier.view.VSupplierCommentReply;

@Service("supplierCommentReplyService")
public class SupplierCommentReplyServiceImpl extends
		ServiceSupport<SupplierCommentReply, VSupplierCommentReply, Integer>
		implements ISupplierCommentReplyService {

	@Autowired
	private SupplierCommentReplyMapper supplierCommentReplyMapper;
	
	
	@Override
	protected Mapper<SupplierCommentReply> getMapper() {
		return supplierCommentReplyMapper;
	}

	

}
