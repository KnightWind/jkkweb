package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Region;


public interface IRegionSV extends IService<Region, Region,Integer> {

	Region findRegionByName(Map<String, Object> map);
	List<Region> findByParentid(Map<String, Object> map);
	String fullRegionName(int region);//返回上级所有的地区名字
	String RegionName(int region);//返回上级所有的地区名字
	List<Region> findOpenCity();
}
