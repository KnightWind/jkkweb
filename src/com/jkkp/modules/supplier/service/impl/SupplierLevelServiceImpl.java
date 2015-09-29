package com.jkkp.modules.supplier.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierLevelMapper;
import com.jkkp.modules.supplier.model.SupplierLevel;
import com.jkkp.modules.supplier.service.ISupplierLevelService;
import com.jkkp.modules.supplier.view.VSupplierLevel;

@Service("supplierLevelService")
public class SupplierLevelServiceImpl extends ServiceSupport<SupplierLevel, VSupplierLevel, Integer>
		implements ISupplierLevelService {

	@Autowired
	private SupplierLevelMapper supplierLevelMapper;
	
	@Override
	protected Mapper<SupplierLevel> getMapper() {
		return supplierLevelMapper;
	}

	@Override
	public List<SupplierLevel> findAll() {
		return supplierLevelMapper.findAll();
	}

	@Override
	public SupplierLevel saveOne(Integer levelMoney) {
		SupplierLevel bean=new SupplierLevel();
		if(levelMoney!=null){
			if(levelMoney<=5000){
				bean.setLevelName("等级一");
			}
			if(levelMoney>5000&&levelMoney<=10000){
				bean.setLevelName("银牌质保");
			}
			if(levelMoney>10000){
				bean.setLevelName("金牌质保");
			}
		}
		bean.setCreateTime(new Date());
		bean.setLevelMoney(levelMoney);
		this.save(bean);
		return bean;
	}

	

}
