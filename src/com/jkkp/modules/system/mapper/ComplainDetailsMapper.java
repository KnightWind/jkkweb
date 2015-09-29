package com.jkkp.modules.system.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.ComplainDetails;



public interface ComplainDetailsMapper extends Mapper<ComplainDetails> {
	public List<ComplainDetails> complaintDetailList(@Param("cid") int cid);
}