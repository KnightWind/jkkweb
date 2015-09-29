package com.jkkp.modules.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicSaygoodMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.TopicSaygood;
import com.jkkp.modules.member.service.ITopicSaygoodService;
import com.jkkp.modules.member.view.VTopicSaygood;

@Service("topicSaygoodService")
public class TopicSaygoodServiceImpl extends
		ServiceSupport<TopicSaygood, VTopicSaygood, Integer> implements ITopicSaygoodService {

	@Autowired
	private TopicSaygoodMapper topicSaygoodMapper;

	@Override
	protected Mapper<TopicSaygood> getMapper() {
		return topicSaygoodMapper;
	}
}
