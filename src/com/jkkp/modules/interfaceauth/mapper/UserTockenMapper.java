package com.jkkp.modules.interfaceauth.mapper;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.interfaceauth.model.InterfaceRegister;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.refund.model.RefundApplyAudit;



public interface UserTockenMapper extends Mapper<UserTocken>{
	
	int deleteByPrimaryKey(Integer id);

    int insert(UserTocken record);

    int insertSelective(UserTocken record);

    RefundApplyAudit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTocken record);

    int updateByPrimaryKey(UserTocken record);
}