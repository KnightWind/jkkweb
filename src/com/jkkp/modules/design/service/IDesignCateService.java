package com.jkkp.modules.design.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.modules.design.view.VDesignCate;

public interface IDesignCateService extends IService<DesignCate, VDesignCate, Integer> {

	Map<String, List<DesignCate>> findCateList();

	void saveOrUpdate(DesignCate designCate);
	
	String findname(int id);
    List<DesignCate> findtype(String type);
    List<DesignCate> findtypefengge();
}
