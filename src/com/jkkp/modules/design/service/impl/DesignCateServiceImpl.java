package com.jkkp.modules.design.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.DesignCateMapper;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.modules.design.view.VDesignCate;

@Service("designCateService")
public class DesignCateServiceImpl extends ServiceSupport<DesignCate, VDesignCate, Integer> implements
		IDesignCateService {

	@Autowired
	private DesignCateMapper designCateMapper;

	@Override
	protected Mapper<DesignCate> getMapper() {
		return designCateMapper;
	}

	public Map<String, List<DesignCate>> findCateList() {
		Map<String, List<DesignCate>> map = new HashMap<String, List<DesignCate>>();
		List<DesignCate> cateList = this.select(null);
		for (DesignCate designCate : cateList) {
			List<DesignCate> datalist = map.get(designCate.getLabel());
			if (datalist == null) {
				map.put(designCate.getLabel(), datalist = new ArrayList<DesignCate>());
			}
			datalist.add(designCate);
		}
		return map;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(DesignCate entity) {
		if (entity.getId() != null && entity.getId() > 0) {
			DesignCate bean = this.findById(entity.getId());
			entity.setCreateTime(bean.getCreateTime());
			this.update(entity);
		} else {
			entity.setAdminId(1);
			entity.setCreateTime(new Date());
			this.save(entity);
		}
	}

	@Override
	public String findname(int id) {
		// TODO Auto-generated method stub
		DesignCate dsCate=this.findById(id);
		if(dsCate==null)
			return "";
		else {
			return dsCate.getCateName();
		}
	}

	@Override
	public List<DesignCate> findtype(String type) {
		// TODO Auto-generated method stub
		return this.selectByProperty("label", type);
	}

	@Override
	public List<DesignCate> findtypefengge() {
		// TODO Auto-generated method stub
		return this.findtype("fengge");
	}
	
	
}
