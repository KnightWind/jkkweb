package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VCaseShare;
import com.jkkp.appapi.modules.mapper.VIAllTopicSV;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.appapi.modules.mapper.VITopicList;
import com.jkkp.appapi.modules.mapper.VITopicMjkSV;
import com.jkkp.appapi.modules.mapper.VITopicSV;
import com.jkkp.common.IService;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;

public interface ITopicSV extends IService<Topic,VTopic,Integer>{
	List<VTopic> findList(Map<String, Object> params);
	Topic findById(Map<String, Object> params);
	void deleteByPk(Map<String, Object> map);
	List<VITopicSV> queryTopic(Map<String, Object> params);
	public List<VIAllTopicSV> getAllJtopic(Map<String, Object> map);
	public VCaseShare getCommonnJtopic(Integer spid);
	
	List<VITopicSV> queryTopicList(Map<String, Object> params);
	
	public VITopicSV queryAd();
	public VISMember getById(Integer uid);
	public List<VIAllTopicSV> getAllTopic(Map<String, Object> params);
	public VITopicMjkSV getAllTopicDetails(Integer uid,Integer aid);
	List<VITopicList> queryCasesTopicList(Map<String, Object> map);
}
