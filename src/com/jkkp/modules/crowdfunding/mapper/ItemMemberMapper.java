package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.ItemMember;
import com.jkkp.modules.crowdfunding.view.VItemMember;

public interface ItemMemberMapper extends Mapper<ItemMember> {

	public List<VItemMember> findPage(Map<String,Object> param);
	public long countPage(Map<String,Object> param);
	
}
