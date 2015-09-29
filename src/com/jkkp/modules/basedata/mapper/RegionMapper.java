package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.basedata.view.VRegionName;

public interface RegionMapper extends Mapper<Region> {

	Region findRegionByName(Map<String, Object> map);

	List<Region> findByParentid(Map<String, Object> map);
	
	//区域管理
	List<Region> selectAllConditionRegion(Map<String, Object> params);
	public long selectAllConditionRegionCount(Map<String, Object> params);
	
	
	void regionPass(@Param("id") Integer id);
	
	void regionNoPass(@Param("id") Integer id);
	
	void updatePoint(@Param("pointx")String pointx,@Param("pointy")String pointy,@Param("id")Integer id);
	
	List<Region> selectAllParentRegion();
	
	List<Region> selectAllChildRegion(@Param("pid") int pid);
	
	/**
	 * 获取服务区域名称
	 * @param id
	 * @return
	 */
	VRegionName selectRegionName(@Param("id")int id);
}