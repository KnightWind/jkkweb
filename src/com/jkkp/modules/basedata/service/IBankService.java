package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Bank;
import com.jkkp.modules.basedata.view.VBank;

public interface IBankService extends IService<Bank, VBank, Integer> {
	/**
	 * 获取平台所有银行信息
	 * @return
	 */
    public List<Bank> getBankList();
    
    /**
     * 更新或保存
     * @param bank
     */
    public void saveOrUpdate(Bank bank);
}
