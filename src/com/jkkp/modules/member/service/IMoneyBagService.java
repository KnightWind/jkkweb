package com.jkkp.modules.member.service;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.MoneyBag;
import com.jkkp.modules.member.view.VMoneyBag;
import com.jkkp.modules.order.model.PaymentRecord;

public interface IMoneyBagService extends IService<MoneyBag, VMoneyBag, Integer> {

	MoneyBag findByUId(Integer memberId);

	MoneyBag addMoneyBag(Integer memberId);

	void saveMoneyBag(PaymentRecord record);

	MoneyBag sumMoneyBagByUId(Integer memberId);

	public void saveMoneyBag(Integer memberId, float amount, Integer source);
}
