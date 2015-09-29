package com.jkkp.modules.supplier.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SgtopicReplyMapper;
import com.jkkp.modules.supplier.model.SgtopicReply;
import com.jkkp.modules.supplier.service.ISgtopicReplyService;
import com.jkkp.modules.supplier.view.VSgtopicReply;

@Service("sgtopicReplyService")
public class SgtopicReplyServiceImpl extends
		ServiceSupport<SgtopicReply, VSgtopicReply, Integer> implements
		ISgtopicReplyService {

	@Autowired
	private SgtopicReplyMapper sgtopicReplyMapper;

	@Override
	public List<VSgtopicReply> selectAllParentComment(int pid) {
		return sgtopicReplyMapper.selectAllParentComment(pid);
	}

	@Override
	public List<VSgtopicReply> selectAllChildComment(int pid) {
		return sgtopicReplyMapper.selectAllChildComment(pid);
	}

	@Override
	public void noPass(int id) {
		sgtopicReplyMapper.noPass(id);

	}

	@Override
	public void pass(int id) {
		sgtopicReplyMapper.pass(id);
	}

	@Override
	protected Mapper<SgtopicReply> getMapper() {
		return sgtopicReplyMapper;
	}

}
