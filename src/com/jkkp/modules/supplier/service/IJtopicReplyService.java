package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.JtopicReply;
import com.jkkp.modules.supplier.view.VJtopicReply;

public interface IJtopicReplyService extends
		IService<JtopicReply, VJtopicReply, Integer> {

	List<VJtopicReply> findReplyByPid(Integer pid);

	void deleteReply(List<VJtopicReply> list, Integer id);
	
	public List<VJtopicReply> selectAllParentComment(int pid);
	
	public List<VJtopicReply> selectAllChildComment(int pid);
	
	public void noPass(int id);
	
	public void pass(int id);
}
