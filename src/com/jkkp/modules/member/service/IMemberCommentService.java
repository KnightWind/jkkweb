package com.jkkp.modules.member.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.MemberComment;
import com.jkkp.modules.member.view.VMemberComment;

public interface IMemberCommentService extends
		IService<MemberComment, VMemberComment, Integer> {

	List<MemberComment> findByInstanceId(Integer instanceId);
}
