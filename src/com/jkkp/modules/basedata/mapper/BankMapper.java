package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Bank;

public interface BankMapper extends Mapper<Bank> {
	
	public List<Bank> selectAllBank(Map<String, Object>map);
	
	public Long selectAllBankCount(Map<String, Object>map);
}