package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.BankMapper;
import com.jkkp.modules.basedata.model.Bank;
import com.jkkp.modules.basedata.service.IBankService;
import com.jkkp.modules.basedata.view.VBank;

@Service("bankService")
public class BankServiceImpl extends ServiceSupport<Bank, VBank, Integer> implements
		IBankService {

	@Autowired
	private BankMapper bankMapper;
	
	@Override
	public List<Bank> getBankList() {
		return this.select(null);
	}

	@Override
	protected Mapper<Bank> getMapper() {
		return bankMapper;
	}

	@Transactional
	public void saveOrUpdate(Bank bank) {
		 if(bank.getId()!=null){
			 this.updateByPrimaryKey(bank);
		 }else{
			 this.save(bank);
		 }
	}

}
