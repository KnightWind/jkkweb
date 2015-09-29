package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierComplainDetails;
import com.jkkp.modules.supplier.view.VSupplierComment;

public interface SupplierComplainDetailsMapper extends
		Mapper<SupplierComplainDetails> {
	public List<VSupplierComment> selectAllDetailsByCid(Map<String, Object> params);

	public long selectAllDetailsCount(Map<String, Object> params);
}
