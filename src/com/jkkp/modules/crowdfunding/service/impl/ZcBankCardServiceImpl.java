package com.jkkp.modules.crowdfunding.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.ZcBankCardMapper;
import com.jkkp.modules.crowdfunding.model.ZcBankCard;
import com.jkkp.modules.crowdfunding.service.IZcBankCardService;
import com.jkkp.modules.crowdfunding.view.VZcBankCard;
import com.jkkp.utils.CommonUtil;

@Service("zcBankCardService")
public class ZcBankCardServiceImpl extends
		ServiceSupport<ZcBankCard, VZcBankCard, Integer> implements
		IZcBankCardService {

	@Autowired
	private ZcBankCardMapper zcBankCardMapper;

	@Transactional
	public void saveOne(ZcBankCard bean) {
		bean.setCreateTime(new Date());
		if (bean.getPayPwd()!=null&&bean.getPayPwd()!="") {
			bean.setPayPwd(CommonUtil.md5(bean.getPayPwd()));
		}
		this.save(bean);
	}

	@Override
	protected Mapper<ZcBankCard> getMapper() {
		return zcBankCardMapper;
	}

	@Override
	public void deleteSupplierBankCard(Integer spId) {
		zcBankCardMapper.deleteSupplierBankCard(spId);
	}

}
