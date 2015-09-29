package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VDesignCollect;
import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignCollect;
import com.jkkp.modules.design.view.VDesign;

public interface IDesignCollectSV extends IService<DesignCollect, VDesignCollect, Integer>{
	public List<VIDesign>designcollectlist(Map<String, Object> map);
	public List<DesignCollect>queryDesignByUidSpid(Map<String, Object> map);
}
