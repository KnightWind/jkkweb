package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.ZcBankCard;
import com.jkkp.modules.crowdfunding.view.VZcBankCard;

public interface ZcBankCardMapper extends Mapper<ZcBankCard> {

	List<VZcBankCard> findPage(Map<String, Object> params);
	long countPage(Map<String, Object> params);
	
	public void deleteSupplierBankCard(@Param("spId")Integer spId);
}
