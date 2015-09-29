package com.jkkp.modules.interfaceauth.mapper;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.interfaceauth.model.InterfaceRecord;


public interface InterfaceRecordMapper  extends Mapper<InterfaceRecord>{
    int deleteByPrimaryKey(Integer id);

    int insert(InterfaceRecord record);

    int insertSelective(InterfaceRecord record);

    InterfaceRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterfaceRecord record);

    int updateByPrimaryKey(InterfaceRecord record);
}