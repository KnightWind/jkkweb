package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.modules.mapper.VCaseShare;
import com.jkkp.appapi.modules.mapper.VIAllTopicSV;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.appapi.modules.mapper.VITopicList;
import com.jkkp.appapi.modules.mapper.VITopicMjkSV;
import com.jkkp.appapi.modules.mapper.VITopicSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
/**
 * 
 * @author 朱国忠
 *
 */


@Service("TopService")
public class TopicSVImpl extends ServiceSupport<Topic, VTopic, Integer>
		implements ITopicSV {

	@Autowired TopicMapper topicMapper;
	
	

	@Override
	public List<VTopic> findList(Map<String, Object> params) {
		return topicMapper.findList(params);
	}

	@Override
	public Topic findById(Map<String, Object> params) {
		Integer topicId=Integer.valueOf((String) params.get("topicId"));
		return this.findById(topicId);
	}

	@Override
	protected Mapper<Topic> getMapper() {
		
		return topicMapper;
	}

	@Override
	public void deleteByPk(Map<String, Object> map) {
		Integer topicId=Integer.valueOf((String) map.get("topicId"));
		this.deleteById(topicId);
	}

	@Override
	public List<VITopicSV> queryTopic(Map<String, Object> params) {
		
		return topicMapper.queryTopic(params);
	}

	@Override
	public List<VITopicSV> queryTopicList(Map<String, Object> params) {
		return topicMapper.queryTopicList(params);
	}

	@Override
	public VITopicSV queryAd() {
		return topicMapper.queryAd();
	}

	@Override
	public VISMember getById(Integer uid) {
		return topicMapper.getById(uid);
	}

	@Override
	public List<VIAllTopicSV> getAllTopic(Map<String, Object> params) {
		return topicMapper.getAllTopic(params);
	}

	@Override
	public VITopicMjkSV getAllTopicDetails(Integer uid, Integer aid) {
		return topicMapper.getAllTopicDetails(uid, aid);
	}

	@Override
	public List<VIAllTopicSV> getAllJtopic(Map<String, Object> map) {
		return topicMapper.getAllJtopic(map);
	}

	@Override
	public VCaseShare getCommonnJtopic(Integer spid) {
		return topicMapper.getCommonnJtopic(spid);
	}

	@Override
	public List<VITopicList> queryCasesTopicList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return topicMapper.queryCasesTopicList(map);
	}

	
}
