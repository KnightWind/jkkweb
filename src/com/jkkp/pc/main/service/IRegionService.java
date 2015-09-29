package com.jkkp.pc.main.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.basedata.view.VRegionName;
import com.jkkp.pc.main.view.VRegion;

public interface IRegionService extends IService<Region, VRegion, Integer> {
	/*
	 * 获取根服务区域
	 */
	public List<Region> getParentRegions();

	/**
	 * 获取子服务区域
	 */
	public List<Region> getChileRegions(int pid);
	
	/**
	 * 获取子区域列表   对北京，天津，上海，重庆做特殊处理
	 * 注：二级区域获取子服务区域，应先进入此方法
	 * @param pid
	 * @return
	 */
	public List<Region> getChileRegionsHandle(Integer pid);
	
	/**
	 * 审核服务区域（通过）
	 * @param id
	 * @return
	 */
	public Boolean regionPass(Integer id);
	
	/**
	 * 审核服务区域(不通过)
	 * @param id
	 * @return
	 */
	public Boolean regionNoPass(Integer id);
	
	void updatePoint(String pointx,String pointy,Integer id);
	
	/**
	 * 获取服务区域名称
	 * @param id
	 * @return
	 */
	VRegionName selectRegionName(int id);
}
