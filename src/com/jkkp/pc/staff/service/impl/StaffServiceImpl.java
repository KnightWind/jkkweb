package com.jkkp.pc.staff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.pc.staff.service.IStaffService;
import com.jkkp.pc.staff.view.VStaff;

@Component("StaffServicePC")
public class StaffServiceImpl extends ServiceSupport<SupplierCompanyStaff, VStaff, Integer> implements IStaffService {

	@Autowired
	private SupplierCompanyStaffMapper supplierCompanyStaffMapper;
	@Autowired
	private AttachmentServiceImpl impl;
	
	
	
	@Transactional(readOnly=true)
	protected Mapper<SupplierCompanyStaff> getMapper() {
		return supplierCompanyStaffMapper;
	}

	@Transactional(readOnly=true)
	public List<VStaff> query4Staff(Integer id) {
		List<VStaff> list = supplierCompanyStaffMapper.query4Staff(id);
		for (VStaff vs : list) {
			vs.setPath(impl.getAccessPath() + vs.getPath());
		}
		return list;
	}
	
	

}
