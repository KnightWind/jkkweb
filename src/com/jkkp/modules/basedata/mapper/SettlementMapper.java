package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Settlement;
import com.jkkp.modules.basedata.view.VSettlement;

public interface SettlementMapper extends Mapper<Settlement> {
	
	List<VSettlement> settlementByPhoneList(Map<String, Object> map);
	long settlementByPhoneListCount(Map<String, Object> map);
	Double findUnCalePushMoney(@Param("phone") String phone);
	
}