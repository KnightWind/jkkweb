package com.jkkp.modules.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.member.model.MemberComment;

public interface MemberCommentMapper extends Mapper<MemberComment> {
	
	public List<MemberComment> findByInstanceId(@Param("pid") Integer pid, @Param("type") Integer type);
}