package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SuppMessagePush;

public interface SuppMessagePushMapper extends Mapper<SuppMessagePush>{

	List<SuppMessagePush> qryByCid(Map<String, Object> map);
	public void deleteByCid(String cid);
}
