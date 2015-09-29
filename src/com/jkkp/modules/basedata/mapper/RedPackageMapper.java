package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.modules.basedata.view.VRedPackage;
import com.jkkp.modules.basedata.view.VSendRedPackageBean;

public interface RedPackageMapper extends Mapper<RedPackage> {
	
	java.util.List<VRedPackage> findPage(Map<String, Object> map);
	long countPage(Map<String, Object> map);
	VRedPackage findPackageById(@Param("id") Integer id);
	
	List<VSendRedPackageBean> sendRedPackageList(Map<String, Object> map);
	long sendRedPackageListCount(Map<String, Object> map);
	
}