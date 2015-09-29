package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IDesignImageSV;
import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.appapi.modules.mapper.VIDesign2;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.DesignImageMapper;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignImage;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.modules.design.view.VDesignImage;

@Service("deignImageSV")
public class DesignImageSVImpl extends ServiceSupport<DesignImage, VDesignImage, Integer> implements IDesignImageSV{

	@Autowired
	DesignImageMapper designImageMapper;
	@Override
	protected Mapper<DesignImage> getMapper() {
		return designImageMapper;
	}
	@Override
	public List<VIDesign> queryDesignDetail(Map<String, Object> map) {
		return designImageMapper.queryDesignDetail(map);
		
	}
	@Override
	public List<VIDesign2> queryDesignDetail2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return designImageMapper.queryDesignDetail2(map);
	}
	@Override
	public List<VIDesign> queryAllDesign(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return designImageMapper.queryAllDesign(map);
	}
	@Override
	public List<VIDesign> queryOneDesign(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return designImageMapper.queryOneDesign(map);
	}

}
