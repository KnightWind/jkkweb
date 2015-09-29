package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IDesigner;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesignCaseSV;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.view.VDesignerCollect;
import com.jkkp.modules.design.mapper.DesignMapper;
import com.jkkp.modules.design.mapper.DesignerCollectMapper;
import com.jkkp.modules.design.model.DesignerCollect;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
@Service("designer")
public class DesignerImpl extends ServiceSupport<DesignerCollect,VDesignerCollect,Integer> implements IDesigner{
	@Autowired DesignerCollectMapper designerCollectMapper;
	@Autowired SupplierCompanyStaffMapper supplierCompanyStaffMapper;
	@Autowired DesignMapper designMapper;
	@Override
	protected Mapper<DesignerCollect> getMapper() {
		return designerCollectMapper;
	}
	@Override
	public List<Staff> all(Integer spid) {
		return supplierCompanyStaffMapper.all(spid);
	}
	@Override
	public long get(Integer spid) {
		return designerCollectMapper.get(spid);
	}
	@Override
	public long count(String name) {
		return designMapper.get(name);
	}
	@Override
	public VDesing every(Integer spid, Integer sid, String name) {
		return supplierCompanyStaffMapper.every(spid, sid, name);
	}
	@Override
	public List<VDesignCaseSV> getCaseDesign(Map<String, Object> map) {
		return designMapper.getCaseDesign(map);
	}
	
}
