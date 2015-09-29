package com.jkkp.modules.supplier.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JlCommentMapper;
import com.jkkp.modules.supplier.model.JlComment;
import com.jkkp.modules.supplier.service.IJlCommentService;
import com.jkkp.modules.supplier.view.VJlComment;

@Service("jlCommentService")
public class JlCommentServiceImpl extends
		ServiceSupport<JlComment, VJlComment, Integer> implements
		IJlCommentService {

	@Autowired
	private JlCommentMapper jlCommentMapper;

	@Override
	protected Mapper<JlComment> getMapper() {
		return jlCommentMapper;
	}

	@Override
	public List<JlComment> findByMxInstId(Integer mxInstId) {
		return this.selectByProperty("instId", mxInstId, "create_time", false);
	}
}
