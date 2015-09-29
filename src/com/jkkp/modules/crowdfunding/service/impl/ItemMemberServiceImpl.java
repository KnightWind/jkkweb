package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.ItemMemberMapper;
import com.jkkp.modules.crowdfunding.model.ItemMember;
import com.jkkp.modules.crowdfunding.service.IitemMemberService;
import com.jkkp.modules.crowdfunding.view.VItemMember;

@Service("itemMemberService")
public class ItemMemberServiceImpl extends ServiceSupport<ItemMember, VItemMember, Integer> implements IitemMemberService {

	@Autowired
	private ItemMemberMapper itemMemberMapper;
	
	
	@Override
	protected Mapper<ItemMember> getMapper() {
		return itemMemberMapper;
	}

}
