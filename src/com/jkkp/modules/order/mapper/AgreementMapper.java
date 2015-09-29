package com.jkkp.modules.order.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VAgreement;
import com.jkkp.appapi.modules.mapper.VHeTong;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.utils.Pager;

public interface AgreementMapper extends Mapper<Agreement> {
	// ysc=========================================
	public List<VAgreement> getList(String and);
	public List<VAgreement> getPageList(Pager pager);
	public long getCount(String and);
	public VAgreement getBeanById(Integer id);
	public int deleteByAnd(String and);
	// ysc=========================================
	public VHeTong queryhetong(@Param("id") Integer id);
	
	
	public Agreement selectByPushId(@Param("pushId") Integer pushId);
}