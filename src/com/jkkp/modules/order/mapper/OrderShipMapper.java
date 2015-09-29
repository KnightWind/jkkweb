package com.jkkp.modules.order.mapper;

import java.util.List;
import java.util.Map;


import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.OrderShip;
import com.jkkp.modules.order.view.VOrderShip;


public interface OrderShipMapper extends Mapper<OrderShip> {
	List<VOrderShip> findAll();
	public List<VOrderShip> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);
	
	public List<VOrderShip> selectOrderShipBySupplierId(Map<String, Object> params);

	public long selectOrderShipCount(Map<String, Object> params);
}