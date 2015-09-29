package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.AreaDomain;
import com.jkkp.modules.basedata.view.VAreaDomain;

/**
 * 梁怡立
 * 
 * @author Administrator
 *
 */
public interface IAreaDomainService extends IService<AreaDomain,VAreaDomain, Integer> {

	AreaDomain operate(Integer id, boolean isOpen);
	List<AreaDomain> finAll();
	List<AreaDomain> finName(String name);
}
