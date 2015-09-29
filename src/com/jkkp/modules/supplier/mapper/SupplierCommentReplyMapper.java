package com.jkkp.modules.supplier.mapper;


import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierCommentReply;
import com.jkkp.modules.supplier.view.VSupplierComment;
import com.jkkp.modules.supplier.view.VSupplierCommentReply;
import com.jkkp.utils.Pager;

public interface SupplierCommentReplyMapper extends Mapper<SupplierCommentReply>{

	//ysc=========================================
	public List<VSupplierCommentReply> getList(String and);
	public List<VSupplierCommentReply> getPageList(Pager pager);
	public long getCount(String and);
	public VSupplierCommentReply getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
}
