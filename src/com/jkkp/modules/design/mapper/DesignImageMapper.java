package com.jkkp.modules.design.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.appapi.modules.mapper.VIDesign2;
import com.jkkp.modules.design.model.DesignImage;

public interface DesignImageMapper extends Mapper<DesignImage> {

	List<VIDesign> queryDesignDetail(Map<String, Object> map);
	List<VIDesign2> queryDesignDetail2(Map<String, Object> map);
	List<VIDesign> queryDesignDetailByDesignerId(Map<String, Object> map);
	//根据pid集合删除对象
	void deleteByIds(@Param("params") List<Integer> params);
	List<VIDesign> queryAllDesign(Map<String, Object> map);
	List<VIDesign> queryOneDesign(Map<String, Object> map);
}