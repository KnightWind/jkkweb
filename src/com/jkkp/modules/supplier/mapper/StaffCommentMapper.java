package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.*;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.utils.Pager;

public interface StaffCommentMapper extends Mapper<StaffComment> {
	
	public List<VStaffComment> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	//ysc=========================================
	public List<VStaffComment> getList(String and);
	public List<VStaffComment> getPageList(Pager pager);
	public long getCount(String and);
	public VStaffComment getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
}
