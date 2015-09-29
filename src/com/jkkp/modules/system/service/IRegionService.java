package com.jkkp.modules.system.service;



import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.system.view.VRegion;

public interface IRegionService extends IService<Region,VRegion,Integer> {

	
	List<Region> finAll();
	//
	public Region operate(Integer id, int isOpen);
	//省
	List<Region> finPrivoce();
	
	/**
	 * 获取相关地区子类
	 * @param regionId
	 * @return
	 */
	public List<Integer> getRegionIdList(Integer regionId,List<Integer> list);
}
