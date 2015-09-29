package com.jkkp.modules.design.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.DesignImage;

public interface IDesignImageService extends IService<DesignImage, DesignImage, Integer> {

	void delete(List<Integer> ids);
	
}
