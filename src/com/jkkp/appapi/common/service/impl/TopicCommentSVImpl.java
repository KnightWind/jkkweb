package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ITopicCommentSV;
import com.jkkp.appapi.modules.mapper.VITopicCommentDetailsSV;
import com.jkkp.appapi.modules.mapper.VITopicCommentSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicCommentMapper;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.view.VTopicComment;
/**
 * 
 * @author 朱国忠
 *
 */


@Service("topicCommentSV")
public class TopicCommentSVImpl extends ServiceSupport<TopicComment,VTopicComment,Integer>
		implements ITopicCommentSV {

	@Autowired TopicCommentMapper topicCommentMapper;

	@Override
	protected Mapper<TopicComment> getMapper() {
		
		return topicCommentMapper;
	}

	@Override
	public VITopicCommentSV getTopicById(Integer tid,Integer uid) {
		
		return topicCommentMapper.getTopicById(tid,uid);
	}

	@Override
	public List<VITopicCommentDetailsSV> queryCaseTopicComment(
			Map<String, Object> map) {
		
		return topicCommentMapper.queryCaseTopicComment(map);
	}
}
