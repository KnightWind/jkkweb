package com.jkkp.modules.supplier.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCertificateMapper;
import com.jkkp.modules.supplier.model.SupplierCertificate;
import com.jkkp.modules.supplier.service.ISupplierCertificateService;
import com.jkkp.modules.supplier.view.VSupplierCertificate;
@Service("supplierCertificateService")
public class SupplierCertificateServiceImpl extends ServiceSupport<SupplierCertificate,VSupplierCertificate,Integer> implements ISupplierCertificateService {
	@Autowired
	private SupplierCertificateMapper supplierCertificateMapper;
	@Override
	protected Mapper<SupplierCertificate> getMapper() {
		return supplierCertificateMapper;
	}
	@Override
	public List<SupplierCertificate> fin(Integer id) {
		return supplierCertificateMapper.fin(id);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void del(Integer id) {
		supplierCertificateMapper.del(id);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(String[] picture, String name,Integer sid) {
		for (int i = 0; i < picture.length; i++) {
			SupplierCertificate supplierCertificate=new SupplierCertificate();
			supplierCertificate.setCreateTime(new Date());
			supplierCertificate.setPicture(picture[i]);
			supplierCertificate.setSpId(sid);
			supplierCertificate.setCreateName(name);
			this.save(supplierCertificate);
		}
		
	}
}
