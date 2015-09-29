package com.jkkp.modules.supplier.mapper;


import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.StaffCommentReply;
import com.jkkp.modules.supplier.view.VStaffComment;
import com.jkkp.modules.supplier.view.VStaffCommentReply;
import com.jkkp.utils.Pager;

public interface StaffCommentReplyMapper extends Mapper<StaffCommentReply>{

	//ysc=========================================
	public List<VStaffCommentReply> getList(String and);
	public List<VStaffCommentReply> getPageList(Pager pager);
	public long getCount(String and);
	public VStaffCommentReply getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
}
