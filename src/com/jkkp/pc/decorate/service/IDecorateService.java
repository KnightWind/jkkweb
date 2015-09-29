package com.jkkp.pc.decorate.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.pc.decorate.view.VDecorate;

public interface IDecorateService extends IService<DesignCate, VDecorate, Integer> {
	 public List<DesignCate> getDesignCases();

	public List<VDecorate> queryDecorateByPBL(Map<String, Object> paramMap);

	public VDecorate queryDecorateById(Integer id);

	public long queryDecorateByPBLCount(Map<String, Object> paramMap);
}
