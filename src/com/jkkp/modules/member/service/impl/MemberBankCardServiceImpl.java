package com.jkkp.modules.member.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.CommonResult;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.MemberBankCardMapper;
import com.jkkp.modules.member.model.MemberBankCard;
import com.jkkp.modules.member.service.IMemberBankCardService;
import com.jkkp.modules.member.view.VMemberBankCard;

@Service("memberBankCardService")
public class MemberBankCardServiceImpl extends ServiceSupport<MemberBankCard, VMemberBankCard, Integer> implements
		IMemberBankCardService {

	@Autowired
	private MemberBankCardMapper memberBankCardMapper;

	@Override
	protected Mapper<MemberBankCard> getMapper() {
		return memberBankCardMapper;
	}

	@Override
	public List<MemberBankCard> findByMemberId(Integer memberId) {
		return this.selectByProperty("memberId", memberId, "create_date", false);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CommonResult<String> saveBindCard(MemberBankCard entity) {
		List<MemberBankCard> cardList = this.selectByProperty(new String[] { "memberId", "cardnum" }, new Object[] {
				entity.getMemberId(), entity.getCardnum() });
		if (!cardList.isEmpty()) {
			return new CommonResult<String>(false, "对不起，你已经绑定了此银行卡");
		}
		entity.setCreateDate(new Date());
		this.save(entity);
		return new CommonResult<String>(true);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeBankCard(Integer memberId, Integer cardid) {
		List<MemberBankCard> cardList = this.selectByProperty(new String[] { "memberId", "id" }, new Object[] {
				memberId, cardid });
		if (cardList.isEmpty()) {
			return;
		}
		for (MemberBankCard item : cardList) {
			this.delete(item);
		}
	}

}
