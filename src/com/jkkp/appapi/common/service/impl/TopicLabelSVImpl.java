package com.jkkp.appapi.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ITopicLabelSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicLabelMapper;
import com.jkkp.modules.member.model.TopicLabel;

@Service("iTopicLabelSV")
public class TopicLabelSVImpl extends ServiceSupport<TopicLabel, TopicLabel, Integer> implements ITopicLabelSV{

	@Autowired
	
	TopicLabelMapper mapper;
	@Override
	protected Mapper<TopicLabel> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
