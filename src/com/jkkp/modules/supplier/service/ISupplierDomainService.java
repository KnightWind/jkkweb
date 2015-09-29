package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierDomain;
import com.jkkp.modules.supplier.view.VSupplierDomain;

public interface ISupplierDomainService extends IService<SupplierDomain,VSupplierDomain,Integer>{
  void add(Integer id,String yu,Integer pp);
}
