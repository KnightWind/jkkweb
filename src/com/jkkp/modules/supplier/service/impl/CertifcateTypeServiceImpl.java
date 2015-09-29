package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.CertifcateTypeMapper;
import com.jkkp.modules.supplier.model.CertifcateType;
import com.jkkp.modules.supplier.service.ICertifcateTypeService;
import com.jkkp.modules.supplier.view.VCertifcateType;

@Service("certifcateTypeService")
public class CertifcateTypeServiceImpl extends
		ServiceSupport<CertifcateType, VCertifcateType, Integer> implements
		ICertifcateTypeService {

	@Autowired
	private CertifcateTypeMapper certifcateTypeMapper;
	
	@Override
	protected Mapper<CertifcateType> getMapper() {
		return certifcateTypeMapper;
	}

	

}
