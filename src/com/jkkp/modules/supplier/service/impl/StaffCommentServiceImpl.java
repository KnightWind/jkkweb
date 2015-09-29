package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.StaffCommentMapper;
import com.jkkp.modules.supplier.model.StaffComment;
import com.jkkp.modules.supplier.service.IStaffCommentService;
import com.jkkp.modules.supplier.view.VStaffComment;

@Service("staffCommentService")
public class StaffCommentServiceImpl extends ServiceSupport<StaffComment, VStaffComment, Integer> implements
		IStaffCommentService {

	@Autowired
	private StaffCommentMapper staffCommentMapper;

	@Override
	protected Mapper<StaffComment> getMapper() {
		return staffCommentMapper;
	}

	

}
