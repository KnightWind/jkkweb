package com.jkkp.modules.member.service.impl;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.MemberLevelRuleMapper;
import com.jkkp.modules.member.model.MemberLevelRule;
import com.jkkp.modules.member.service.IMemberLevelRuleService;
import com.jkkp.modules.member.view.VMemeberLevelRule;

@Service("memberLevelRuleService")
public class MemberLevelRuleServiceImpl extends ServiceSupport<MemberLevelRule, VMemeberLevelRule, Integer>
		implements IMemberLevelRuleService {
	@Autowired
	private MemberLevelRuleMapper memberLevelRuleMapper;

	protected Mapper<MemberLevelRule> getMapper() {
		
		return memberLevelRuleMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateOne(Integer id, BigDecimal price) {
		  if(id>0){
			  MemberLevelRule MLR=this.findById(id);
			  MLR.setPrice(price);
			  this.update(MLR);
		  }
	}

}
