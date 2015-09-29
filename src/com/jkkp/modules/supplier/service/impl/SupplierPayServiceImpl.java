package com.jkkp.modules.supplier.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierPayMapper;
import com.jkkp.modules.supplier.model.SupplierPay;
import com.jkkp.modules.supplier.service.ISupplierPayService;
import com.jkkp.modules.supplier.view.VSupplierPay;
import com.jkkp.utils.CommonUtil;

@Service("supplierPayService")
public class SupplierPayServiceImpl extends
		ServiceSupport<SupplierPay, VSupplierPay, Integer> implements
		ISupplierPayService {

	@Autowired
	private SupplierPayMapper supplierPayMapper;

	@Override
	protected Mapper<SupplierPay> getMapper() {
		return this.supplierPayMapper;
	}

	//单一商家充值
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int recharge(int spId) {
		SupplierPay pay = this.findById(spId);
		if (pay.getLeftNum() > 0) {
			pay.setNum(pay.getNum() + 1);
			pay.setLeftNum(pay.getLeftNum() - 1);
			pay.setLastPayTime(new Date());
			this.update(pay);
			return 1;
		}
		return 0;
	}

	// 批充值处理
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int manyRecharge(String idString) {
		String[] idsHandle = idString.split(",");		
		try {
			for (String s: idsHandle) {
				SupplierPay pay = this.findById(CommonUtil.stringToInteger(s));
				if(pay.getLeftNum() > 0){
					pay.setNum(pay.getNum() + 1);
					pay.setLeftNum(pay.getLeftNum() - 1);
					pay.setLastPayTime(new Date());
					this.update(pay);
				}
			}	
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
