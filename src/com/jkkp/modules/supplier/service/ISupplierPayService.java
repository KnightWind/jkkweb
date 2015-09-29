package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierPay;
import com.jkkp.modules.supplier.view.VSupplierPay;

public interface ISupplierPayService extends IService<SupplierPay, VSupplierPay, Integer> {
     //商户充值
	public int recharge(int spId);
	//商户批充值
	public int manyRecharge(String idString);
}
