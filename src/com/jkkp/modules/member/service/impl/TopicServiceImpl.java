package com.jkkp.modules.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.service.ITopicService;
import com.jkkp.modules.member.view.VMemberTopic;

@Service("topicService")
public class TopicServiceImpl extends
		ServiceSupport<Topic, VMemberTopic, Integer> implements ITopicService {

	@Autowired
	private TopicMapper topicMapper;

	@Override
	protected Mapper<Topic> getMapper() {
		return topicMapper;
	}

	@Override
	public VMemberTopic detail(int id) {
		return topicMapper.detail(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noPass(int id) {
		topicMapper.noPass(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pass(int id) {
		topicMapper.pass(id);
	}

}
