package com.jkkp.modules.supplier.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.supplier.model.Jiakebao;

public interface JiakebaoMapper extends Mapper<Jiakebao>{
	List<Jiakebao> qryJiakebaoBygcdId(@Param("gcdId") Integer gcdId);
}
