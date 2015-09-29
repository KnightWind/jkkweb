package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCertifcateRelationMapper;
import com.jkkp.modules.supplier.model.SupplierCertifcateRelation;
import com.jkkp.modules.supplier.service.ISupplierCertifcateRelationService;
import com.jkkp.modules.supplier.view.VSupplierCertifcateRelation;

@Service("supplierCertifcateRelationService")
public class SupplierCertifcateRelationServiceImpl extends
		ServiceSupport<SupplierCertifcateRelation, VSupplierCertifcateRelation, Integer> implements ISupplierCertifcateRelationService {

	@Autowired
	private SupplierCertifcateRelationMapper supplierCertifcateRelationMapper;
	
	@Override
	protected Mapper<SupplierCertifcateRelation> getMapper() {
		return supplierCertifcateRelationMapper;
	}

	

}
