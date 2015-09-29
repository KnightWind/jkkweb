package com.jkkp.modules.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.OrderFree;
import com.jkkp.modules.order.view.VOrderFree;

public interface OrderFreeMapper extends Mapper<OrderFree> {
	public List<VOrderFree> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	//获取用户0元购订单
	public List<VOrderFree> selectUserOrderFree(@Param("id") int id);
	
	
	public List<VOrderFree> findOrderFreeBySpId(
			Map<String, Object> params);

	public long findOrderFreeBySpIdCount(Map<String, Object> params);
}