package com.jkkp.modules.index.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkkp.common.search.ISearchService;
import com.jkkp.modules.index.service.ISupplierSearchService;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;

@Service("supplierSearchService")
public class SupplierSearchServiceImpl implements ISupplierSearchService {
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private ISearchService searchService;

	public List<Supplier> search(String queryString) {
		Supplier supplier = new Supplier();
		supplier.setSpName(queryString);
		List<Supplier> supplierList = searchService.queryEntity(supplier, 10);
		return supplierList;
	}

}
