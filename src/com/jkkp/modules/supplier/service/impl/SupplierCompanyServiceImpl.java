package com.jkkp.modules.supplier.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCompanyMapper;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompany;
import com.jkkp.modules.supplier.service.ISupplierCompanyService;
import com.jkkp.modules.supplier.view.VSupplierCompany;
@Service("supplierCompanyService")
public class SupplierCompanyServiceImpl extends ServiceSupport<SupplierCompany,VSupplierCompany,Integer> implements ISupplierCompanyService {
	@Autowired
	private SupplierCompanyMapper supplierCompanyMapper;
	
	@Autowired
	private SupplierServiceImpl supplierimpl;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	
	@Override
	protected Mapper<SupplierCompany> getMapper() {
		return supplierCompanyMapper;
	}
	@Override
	public SupplierCompany fin(Integer id) {
		return supplierCompanyMapper.fin(id);
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdate(SupplierCompany supplierCompany) {
		SupplierCompany supplier=this.findById(supplierCompany.getId());
		if(supplierCompany.getId() == null){
			supplierCompany.setCreateTime(new Date());
			this.save(supplierCompany);
		}else{			
			supplier.setIntro(supplierCompany.getIntro());
			supplier.setAddress(supplierCompany.getAddress());
			supplier.setScale(supplierCompany.getScale());
			supplier.setCapital(supplierCompany.getCapital());
			supplier.setEstablish(supplierCompany.getEstablish());
			supplier.setInspection(supplierCompany.getInspection());
			supplier.setDurationStart(supplierCompany.getDurationStart());
			supplier.setDurationEnd(supplierCompany.getDurationEnd());
			Supplier sp = supplierimpl.findById(supplier.getSpId());
			sp.setProxyStatus(0);
			try {
				supplierimpl.update(sp);
				this.update(supplierCompany);
			} catch (Exception e) {
				new RuntimeException("修改商家信息失败!",e);
			}
			
		}
	}
	
}
