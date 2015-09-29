package com.jkkp.modules.design.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.DesignRecommand;
import com.jkkp.modules.design.view.VDesignRecommand;

public interface IDesignRecommandService extends IService<DesignRecommand,VDesignRecommand,Integer>{
	List<VDesignRecommand> fin(String name);
}
