package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.JlComplainDetails;
import com.jkkp.modules.supplier.view.VJlComplaintsDetails;

public interface JlComplainDetailsMapper extends Mapper<JlComplainDetails> {
	public List<VJlComplaintsDetails> selectAllDetailsByCid(
			Map<String, Object> param);

	public long selectAllDetailsCount(Map<String, Object> param);
}
