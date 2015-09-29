package com.jkkp.modules.supplier.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JtopicReplyMapper;
import com.jkkp.modules.supplier.model.JtopicReply;
import com.jkkp.modules.supplier.service.IJtopicReplyService;
import com.jkkp.modules.supplier.view.VJtopicReply;

@Service("jtopicReplyService")
public class JtopicReplyServiceImpl extends
		ServiceSupport<JtopicReply, VJtopicReply, Integer> implements
		IJtopicReplyService {

	@Autowired
	private JtopicReplyMapper jtopicReplyMapper;

	@Override
	protected Mapper<JtopicReply> getMapper() {
		return jtopicReplyMapper;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<VJtopicReply> findReplyByPid(Integer pid) {
		return jtopicReplyMapper.findReplyByPid(pid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteReply(List<VJtopicReply> list, Integer id) {
		jtopicReplyMapper.deleteByPrimaryKey(id);
		for (JtopicReply jtopicReply : list) {
			jtopicReplyMapper.delete(jtopicReply);
		}
	}

	public List<VJtopicReply> selectAllParentComment(int pid) {
		return jtopicReplyMapper.selectAllParentComment(pid);
	}

	@Override
	public List<VJtopicReply> selectAllChildComment(int pid) {
		return jtopicReplyMapper.selectAllChildComment(pid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noPass(int id) {
		jtopicReplyMapper.noPass(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pass(int id) {
		jtopicReplyMapper.pass(id);
	}

}
