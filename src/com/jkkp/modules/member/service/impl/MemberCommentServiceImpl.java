package com.jkkp.modules.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.MemberCommentMapper;
import com.jkkp.modules.member.model.MemberComment;
import com.jkkp.modules.member.service.IMemberCommentService;
import com.jkkp.modules.member.view.VMemberComment;

@Service("memberCommentService")
public class MemberCommentServiceImpl extends
		ServiceSupport<MemberComment, VMemberComment, Integer> implements
		IMemberCommentService {

	@Autowired
	private MemberCommentMapper memberCommentMapper;

	@Override
	protected Mapper<MemberComment> getMapper() {
		return memberCommentMapper;
	}

	@Override
	public List<MemberComment> findByInstanceId(Integer instanceId) {
		return memberCommentMapper.findByInstanceId(instanceId, 1);
	}

}
