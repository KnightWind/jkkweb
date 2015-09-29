package com.jkkp.modules.order.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.OrderQuan;
import com.jkkp.modules.order.view.VOrderQuan;

public interface OrderQuanMapper extends Mapper<OrderQuan> {
	public List<VOrderQuan> findPage(Map<String, Object> params);	
	public long countPage(Map<String, Object> params);
	public long resultCount(Map<String, Object> params);
	public List<VOrderQuan> selectAllQuanRefund(Map<String, Object> params);
}