package com.jkkp.modules.supplier.service.impl;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierDomainMapper;
import com.jkkp.modules.supplier.model.SupplierDomain;
import com.jkkp.modules.supplier.service.ISupplierDomainService;
import com.jkkp.modules.supplier.view.VSupplierDomain;
@Service("supplierDomainService")
public class SupplierDomainServiceImpl extends ServiceSupport<SupplierDomain,VSupplierDomain,Integer> implements ISupplierDomainService{

	@Autowired
	private SupplierDomainMapper supplierDomainMapper;
	@Override
	protected Mapper<SupplierDomain> getMapper() {
		
		return supplierDomainMapper;
	}
	@Override
	public void add(Integer id, String yu,Integer pp) {
		
		if(pp!=null&&pp>0){
			SupplierDomain supplierDomain=new SupplierDomain();
			supplierDomain.setSpId(id);
			supplierDomain.setId(pp);
			supplierDomain.setBindStatus(new Byte("1"));
			supplierDomain.setCreateTime(new Date());
			supplierDomain.setDomain(yu);
			this.update(supplierDomain);			
		}else{
		SupplierDomain supplierDomain=new SupplierDomain();
		supplierDomain.setSpId(id);
		supplierDomain.setBindStatus(new Byte("1"));
		supplierDomain.setCreateTime(new Date());
		supplierDomain.setDomain(yu);
		this.save(supplierDomain);
		}
	}

}
