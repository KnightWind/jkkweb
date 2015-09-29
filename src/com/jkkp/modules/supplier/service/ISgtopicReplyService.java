package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SgtopicReply;
import com.jkkp.modules.supplier.view.VSgtopicReply;

public interface ISgtopicReplyService extends
		IService<SgtopicReply, VSgtopicReply, Integer> {
	public List<VSgtopicReply> selectAllParentComment(int pid);

	public List<VSgtopicReply> selectAllChildComment(int pid);
	
	public void noPass(int id);
	
	public void pass(int id);
}
