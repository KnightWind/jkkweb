package com.jkkp.modules.order.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.order.model.OrderId;
import com.jkkp.modules.order.view.VOrderId;
import com.jkkp.modules.system.view.VAdmin;

public interface OrderIdMapper extends Mapper<OrderId> {
    public List<VAdmin> findPage(Map<String, Object> params);	
	public long countPage(Map<String, Object> params);
	public VOrderId findOneById(@Param("id") int id);
}