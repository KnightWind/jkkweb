package com.jkkp.modules.supplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SgtopicReply;
import com.jkkp.modules.supplier.view.VSgtopicReply;

public interface SgtopicReplyMapper extends Mapper<SgtopicReply> {
	// --------------web-----------
	public List<VSgtopicReply> selectAllParentComment(@Param("pid") int pid);

	public List<VSgtopicReply> selectAllChildComment(@Param("pid") int pid);

	public void noPass(@Param("id") int id);

	public void pass(@Param("id") int id);
	// -------------web-----------
}
