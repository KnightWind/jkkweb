package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCollectMapper;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.service.ISupplierCollectService;
import com.jkkp.modules.supplier.view.VSupplierCollect;
@Service("supplierCollectService")
public class SupplierCollectServiceImpl extends ServiceSupport<SupplierCollect,VSupplierCollect,Integer> implements ISupplierCollectService {
	@Autowired
	private SupplierCollectMapper supplierCollectMapper;
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(SupplierCollect supplierCollect) {
			if(supplierCollect.getId()!=null&&supplierCollect.getId()>0){
				this.update(supplierCollect);
			}else {
				this.save(supplierCollect);
			}
	}

	@Override
	protected Mapper<SupplierCollect> getMapper() {
		return supplierCollectMapper;
	}

}
