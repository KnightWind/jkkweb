package com.jkkp.modules.supplier.mapper;


import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.supplier.model.StaffLabel;
import com.jkkp.modules.supplier.model.SupplierLabel;
import com.jkkp.modules.supplier.view.VSupplierLabel;
import com.jkkp.utils.Pager;

public interface SupplierLabelMapper extends Mapper<SupplierLabel>{

	//ysc=========================================
	public List<VSupplierLabel> getList(String and);
	public List<VSupplierLabel> getPageList(Pager pager);
	public long getCount(String and);
	public VSupplierLabel getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
}
