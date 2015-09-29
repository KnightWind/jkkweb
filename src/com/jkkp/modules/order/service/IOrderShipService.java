package com.jkkp.modules.order.service;

import java.util.List;
import com.jkkp.modules.order.view.VOrderShip;

public interface IOrderShipService {
	List<VOrderShip> findAll();
}
