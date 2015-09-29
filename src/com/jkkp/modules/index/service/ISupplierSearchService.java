package com.jkkp.modules.index.service;

import java.util.List;

import com.jkkp.modules.supplier.model.Supplier;

public interface ISupplierSearchService {

	public List<Supplier> search(String queryString);
}
