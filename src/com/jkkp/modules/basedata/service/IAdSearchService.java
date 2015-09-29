package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.view.VAdSearch;

public interface IAdSearchService extends IService<AdSearch,VAdSearch,Integer> {
	public void saveOrUpdate(AdSearch adSearch,String aname);
	public AdSearch operate(Integer id, boolean isOpen);
	public List<VAdSearch> findAll();
}
