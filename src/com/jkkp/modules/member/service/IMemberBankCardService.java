package com.jkkp.modules.member.service;

import java.util.List;

import com.jkkp.common.CommonResult;
import com.jkkp.common.IService;
import com.jkkp.modules.member.model.MemberBankCard;
import com.jkkp.modules.member.view.VMemberBankCard;

public interface IMemberBankCardService extends IService<MemberBankCard, VMemberBankCard, Integer> {

	public List<MemberBankCard> findByMemberId(Integer memberId);

	public CommonResult<String> saveBindCard(MemberBankCard entity);

	public void removeBankCard(Integer memberId, Integer cardid);
}
