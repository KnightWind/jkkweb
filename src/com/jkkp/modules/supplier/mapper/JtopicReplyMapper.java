package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.JtopicReply;
import com.jkkp.modules.supplier.view.VJtopicReply;

public interface JtopicReplyMapper extends Mapper<JtopicReply> {

	List<VJtopicReply> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	List<VJtopicReply> findReplyByPid(@Param(value = "pid") Integer pid);

	// ---------------web-----------
	public List<VJtopicReply> selectAllParentComment(@Param("pid") int pid);

	public List<VJtopicReply> selectAllChildComment(@Param("pid") int pid);

	public void noPass(@Param("id") int id);

	public void pass(@Param("id") int id);
	// -----------------web-----------
}