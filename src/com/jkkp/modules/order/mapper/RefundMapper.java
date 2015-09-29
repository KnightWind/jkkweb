package com.jkkp.modules.order.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.Refund;
import com.jkkp.modules.order.view.VOrderQuan;

public interface RefundMapper extends Mapper<Refund> {
	public List<VOrderQuan> findPage(Map<String, Object> params);	
	public long countPage(Map<String, Object> params);

}