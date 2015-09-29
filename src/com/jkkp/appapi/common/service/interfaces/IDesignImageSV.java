package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.appapi.modules.mapper.VIDesign2;
import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignImage;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.modules.design.view.VDesignImage;

public interface IDesignImageSV extends IService<DesignImage, VDesignImage, Integer>{

	List<VIDesign> queryDesignDetail(Map<String, Object> map);
	List<VIDesign2> queryDesignDetail2(Map<String, Object> map);
	List<VIDesign> queryAllDesign(Map<String, Object> map);
	List<VIDesign> queryOneDesign(Map<String, Object> map);
}
