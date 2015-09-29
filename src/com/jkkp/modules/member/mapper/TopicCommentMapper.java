package com.jkkp.modules.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VITopicCommentDetailsSV;
import com.jkkp.appapi.modules.mapper.VITopicCommentSV;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.view.VTopicComment;

public interface TopicCommentMapper extends Mapper<TopicComment> {
	// --------------web-----------
	public List<VTopicComment> selectAllParentComment(@Param("pid") int pid);

	public List<VTopicComment> selectAllChildComment(@Param("pid") int pid);

	public void noPass(@Param("id") int id);

	public void pass(@Param("id") int id);
	// -------------web-----------
	public VITopicCommentSV getTopicById(@Param("tid") Integer tid,@Param("uid") Integer uid);
	public List<VITopicCommentDetailsSV> queryCaseTopicComment(Map<String, Object> map);
}