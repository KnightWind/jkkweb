package com.jkkp.modules.member.mapper;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.member.model.MoneyBag;

public interface MoneyBagMapper extends Mapper<MoneyBag> {
	MoneyBag sumMoneyBagByUId(Integer memberId);
}