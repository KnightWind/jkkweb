package com.jkkp.modules.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.CheckRequest;
import com.jkkp.modules.basedata.view.VCheckRequest;
import com.jkkp.utils.Pager;

public interface CheckRequestMapper extends Mapper<CheckRequest> {

	//ysc=========================================
	public List<VCheckRequest> getList(String and);
	public List<VCheckRequest> getPageList(Pager pager);
	public long getCount(String and);
	public VCheckRequest getBeanById(Integer id);
	public int deleteByAnd(String and);
	public List<VCheckRequest> getListByStageId(Integer stageId);
	//ysc=========================================
	List<CheckRequest> queryCheck(@Param("engineerId") Integer engineerId,@Param("stageId")Integer stageId);
    CheckRequest queryCreate(@Param("engineerId") Integer engineerId,@Param("stageId")Integer stageId);
}