package com.jkkp.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.RegionMapper;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.system.service.IRegionService;
import com.jkkp.modules.system.view.VRegion;

/**
 * 梁怡立
 * 
 * @author Administrator
 *
 */
@Service("regionService")
public class RegionServiceImpl extends ServiceSupport<Region, VRegion, Integer> implements IRegionService {

	@Autowired
	private RegionMapper regionMapper;

	@Override
	protected Mapper<Region> getMapper() {
		return regionMapper;
	}

	@Override
	public List<Region> finAll() {
		// TODO Auto-generated method stub
		return this.selectByProperty("level", 1, "sortby", true);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Region operate(Integer id, int isOpen) {
		Region bean = this.findById(id);
		if (bean.getStatus()==0) {
			bean.setStatus(1);
			this.update(bean);
		}else{
			bean.setStatus(0);
			this.update(bean);
		}
		return bean;
	}

	@Override
	public List<Region> finPrivoce() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getRegionIdList(Integer regionId,List<Integer> list) {
		if(regionId!=null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("regionid", regionId);
			List<Region> regionlist = regionMapper.findByParentid(map); //根据区域id查询区域信息，该id可能是省市区id
				
			if(regionlist!=null && regionlist.size() > 0){
				if(regionlist.get(0).getLevel()!=3){
					list.add(regionlist.get(0).getRegionid());
					Map<String,Object> mps = new HashMap<String,Object>();
					mps.put("parentid", regionlist.get(0).getRegionid());
					List<Region> rlist = regionMapper.findByParentid(mps); //根据父id查询区域信息
					for (Region region2 : rlist) {
						getRegionIdList(region2.getRegionid(),list);
					}
				}else{
					list.add(regionlist.get(0).getRegionid());
				}
			} 
		}
		return list;
	}

}
