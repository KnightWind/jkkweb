package com.jkkp.modules.member.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.MoneyBagMapper;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.MoneyBag;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.member.service.IMoneyBagService;
import com.jkkp.modules.member.view.VMoneyBag;
import com.jkkp.modules.order.model.PaymentRecord;

@Service("moneyBagService")
public class MoneyBagServiceImpl extends ServiceSupport<MoneyBag, VMoneyBag, Integer> implements IMoneyBagService {

	@Autowired
	private MoneyBagMapper moneyBagMapper;
	@Autowired
	private IMemberService memberService;

	@Override
	protected Mapper<MoneyBag> getMapper() {
		return moneyBagMapper;
	}

	@Override
	public MoneyBag findByUId(Integer memberId) {
		List<MoneyBag> datalist = this.selectByProperty("uid", memberId);
		return datalist != null && !datalist.isEmpty() ? datalist.get(0) : null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MoneyBag addMoneyBag(Integer memberId) {
		Member member = memberService.findById(memberId);
		MoneyBag entity = new MoneyBag();
		entity.setUid(memberId);
		entity.setUname(member.getReallName());
		entity.setSource(MoneyBag.SOURCE_TYPE_1);
		entity.setCreateUser(member.getNickname());
		entity.setCreateTime(new Date());
		entity.setAmount(0f);
		this.save(entity);

		return entity;
	}
	
	public void saveMoneyBag(Integer memberId, float amount, Integer source) {
		Member member = memberService.findById(memberId);
		MoneyBag entity = new MoneyBag();
		entity.setUid(memberId);
		if(member.getReallName()!=null)
			entity.setUname(member.getReallName());
		entity.setSource(source);
		if(member.getNickname()!=null)
			entity.setCreateUser(member.getNickname());
		entity.setCreateTime(new Date());
		entity.setAmount(amount);
		this.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMoneyBag(PaymentRecord record) {
		Integer memberId = record.getBusinessId();
		this.saveMoneyBag(memberId, record.getAmount(), MoneyBag.SOURCE_TYPE_1);
	}

	@Override
	public MoneyBag sumMoneyBagByUId(Integer memberId) {
		return moneyBagMapper.sumMoneyBagByUId(memberId);
	}

}
