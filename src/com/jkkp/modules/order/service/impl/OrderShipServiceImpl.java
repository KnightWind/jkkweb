package com.jkkp.modules.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.OrderShipMapper;
import com.jkkp.modules.order.model.OrderShip;
import com.jkkp.modules.order.service.IOrderShipService;
import com.jkkp.modules.order.view.VOrderShip;

@Service("orderShipService")
public class OrderShipServiceImpl extends
ServiceSupport<OrderShip,VOrderShip,Integer> implements IOrderShipService{
	@Autowired
	private OrderShipMapper orderShipMapper;
	@Override
	public List<VOrderShip> findAll() {
		return orderShipMapper.findAll();
	}

	@Override
	protected Mapper<OrderShip> getMapper() {	
		return orderShipMapper;
	}
	@Override
	public VOrderShip convertBeanToView(OrderShip orderShip){
		List<VOrderShip> list=this.findAll();
		VOrderShip vOrderShip=super.convertBeanToView(orderShip);
		for (VOrderShip v : list) {
			vOrderShip.setSpName(v.getSpName());
			vOrderShip.setPrice(v.getPrice());
			vOrderShip.setNum(v.getNum());
			vOrderShip.setBankName(v.getBankName());
			vOrderShip.setBankAuthor(v.getBankAuthor());
			vOrderShip.setBankAccount(v.getBankAccount());
		}
		return vOrderShip;
	}
}
