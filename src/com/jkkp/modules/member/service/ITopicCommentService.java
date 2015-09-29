package com.jkkp.modules.member.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.view.VTopicComment;

public interface ITopicCommentService extends
		IService<TopicComment, VTopicComment, Integer> {
	public List<VTopicComment> selectAllParentComment(int pid);

	public List<VTopicComment> selectAllChildComment(int pid);

	public void noPass(int id);

	public void pass(int id);

}
