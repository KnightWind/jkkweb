package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VITopicCommentDetailsSV;
import com.jkkp.appapi.modules.mapper.VITopicCommentSV;
import com.jkkp.common.IService;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.view.VTopicComment;

public interface ITopicCommentSV extends IService<TopicComment,VTopicComment,Integer>{
	public VITopicCommentSV getTopicById(Integer tid,Integer uid);
	public List<VITopicCommentDetailsSV> queryCaseTopicComment(Map<String, Object> map);
}


