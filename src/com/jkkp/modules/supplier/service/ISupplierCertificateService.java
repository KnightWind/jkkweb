package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCertificate;
import com.jkkp.modules.supplier.view.VSupplierCertificate;
public interface ISupplierCertificateService extends IService<SupplierCertificate,VSupplierCertificate,Integer> {
	public List<SupplierCertificate> fin(Integer id);
	public void del(Integer id);
	public void saveOrUpdate(String[] picture,String name,Integer sid);
}
