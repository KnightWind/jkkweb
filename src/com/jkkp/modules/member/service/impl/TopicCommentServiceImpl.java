package com.jkkp.modules.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicCommentMapper;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.service.ITopicCommentService;
import com.jkkp.modules.member.view.VTopicComment;

@Service("topicCommentService")
public class TopicCommentServiceImpl extends
		ServiceSupport<TopicComment, VTopicComment, Integer> implements
		ITopicCommentService {

	@Autowired
	private TopicCommentMapper topicCommentMapper;

	@Override
	protected Mapper<TopicComment> getMapper() {
		return topicCommentMapper;
	}

	@Override
	public List<VTopicComment> selectAllParentComment(int pid) {
		return topicCommentMapper.selectAllParentComment(pid);
	}

	@Override
	public List<VTopicComment> selectAllChildComment(int pid) {
		return topicCommentMapper.selectAllChildComment(pid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noPass(int id) {
		topicCommentMapper.noPass(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pass(int id) {
		topicCommentMapper.pass(id);
	}

}
