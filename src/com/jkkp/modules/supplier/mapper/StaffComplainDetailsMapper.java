package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.StaffComplainDetails;
import com.jkkp.modules.supplier.view.VStaffComplaintsDetails;

public interface StaffComplainDetailsMapper extends
		Mapper<StaffComplainDetails> {
	public List<VStaffComplaintsDetails> selectAllDetailsByCid(
			Map<String, Object> param);

	public long selectAllDetailsCount(Map<String, Object> param);
}
