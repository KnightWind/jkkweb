package com.jkkp.modules.supplier.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierCertificate;
public interface SupplierCertificateMapper extends Mapper<SupplierCertificate>{
	public List<SupplierCertificate> fin(@Param("id") Integer id);
	public void del(@Param("id") Integer id);
}
