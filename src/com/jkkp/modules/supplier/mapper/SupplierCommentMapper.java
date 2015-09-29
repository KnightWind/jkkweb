package com.jkkp.modules.supplier.mapper;


import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.supplier.model.SupplierComment;
import com.jkkp.modules.supplier.view.VSupplierComment;
import com.jkkp.utils.Pager;

public interface SupplierCommentMapper extends Mapper<SupplierComment>{
	public List<VSupplierComment> findPage(Map<String,Object> params);
	public long countPage(Map<String,Object> params);
	

	//ysc=========================================
	public List<VSupplierComment> getList(String and);
	public List<VSupplierComment> getPageList(Pager pager);
	public long getCount(String and);
	public VSupplierComment getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
}
