package com.jkkp.modules.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.DesignImageMapper;
import com.jkkp.modules.design.model.DesignImage;
import com.jkkp.modules.design.service.IDesignImageService;

@Service("designImageService")
public class IDesignImageServiceImpl extends ServiceSupport<DesignImage, DesignImage, Integer> implements IDesignImageService {

	@Autowired
	private DesignImageMapper designImageMapper;
	
	@Override
	protected Mapper<DesignImage> getMapper() {
		return designImageMapper;
	}

	public void delete(List<Integer> imgIds) {
		designImageMapper.deleteByIds(imgIds);
	}

	

}
