package com.jkkp.modules.member.service;

import java.math.BigDecimal;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.MemberLevelRule;
import com.jkkp.modules.member.view.VMemeberLevelRule;

public interface IMemberLevelRuleService extends IService<MemberLevelRule, VMemeberLevelRule, Integer> {
    public void updateOne(Integer id,BigDecimal price);
}
