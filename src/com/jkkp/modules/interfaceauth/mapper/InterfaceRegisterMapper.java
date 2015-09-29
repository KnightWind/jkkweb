package com.jkkp.modules.interfaceauth.mapper;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.interfaceauth.model.InterfaceRegister;
import com.jkkp.modules.refund.model.RefundApplyAudit;


public interface InterfaceRegisterMapper extends Mapper<InterfaceRegister>{
	int deleteByPrimaryKey(Integer id);

    int insert(InterfaceRegister record);

    int insertSelective(InterfaceRegister record);

    RefundApplyAudit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterfaceRegister record);

    int updateByPrimaryKey(InterfaceRegister record);
}