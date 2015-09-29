package com.jkkp.pc.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.RegionMapper;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.basedata.view.VRegionName;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.pc.main.view.VRegion;

@Component("regionServicePC")
public class RegionServiceImpl extends ServiceSupport<Region, VRegion, Integer>
		implements IRegionService {

	@Autowired
	private RegionMapper regionMapper;

	@Override
	protected Mapper<Region> getMapper() {
		return regionMapper;
	}

	@Transactional(readOnly=true)
	public List<Region> getParentRegions() {
		return regionMapper.selectAllParentRegion();
	}

	
	@Transactional(readOnly=true)
	public List<Region> getChileRegions(int pid) {
		return regionMapper.selectAllChildRegion(pid);
	}

	
	@Override
	public List<Region> getChileRegionsHandle(Integer pid) {
		List<Integer> cities=new ArrayList<Integer>();
		cities.add(110000);
		cities.add(120000);
		cities.add(310000);
		cities.add(500000);
		for (Integer i : cities) {
			if(pid.equals(i)){
				List<Region> region=this.getChileRegions(i);
					if(region.size()>0){
					return this.getChileRegions(region.get(0).getRegionid());
			      }
		      }
		}
		return this.getChileRegions(pid);
	}

	//支持最高三层更新状态
	@Transactional
	public Boolean regionPass(Integer id) {
		try {
			regionMapper.regionPass(id);
			List<Region> twoParent= this.selectByProperty("parentid", id);
			for (Region region : twoParent) {
				regionMapper.regionPass(region.getRegionid());
				List<Region> threeParant = this.selectByProperty("parentid", region.getRegionid());
				for (Region item : threeParant) {
					regionMapper.regionPass(item.getRegionid());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public Boolean regionNoPass(Integer id) {
        try {
        	regionMapper.regionNoPass(id);
        	List<Region> twoParent= this.selectByProperty("parentid", id);
			for (Region region : twoParent) {
				regionMapper.regionNoPass(region.getRegionid());
				List<Region> threeParant = this.selectByProperty("parentid", region.getRegionid());
				for (Region item : threeParant) {
					regionMapper.regionNoPass(item.getRegionid());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void updatePoint(String pointx, String pointy, Integer id) {
		regionMapper.updatePoint(pointx, pointy, id);
	}

	
	public VRegionName selectRegionName(int id) {
		return regionMapper.selectRegionName(id);
	}

	
}
