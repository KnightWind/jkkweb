package com.jkkp.modules.crowdfunding.service;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.ZcBankCard;
import com.jkkp.modules.crowdfunding.view.VZcBankCard;

public interface IZcBankCardService extends IService<ZcBankCard, VZcBankCard, Integer> {
    public void saveOne(ZcBankCard bean);
    public void deleteSupplierBankCard(Integer spId);
}
