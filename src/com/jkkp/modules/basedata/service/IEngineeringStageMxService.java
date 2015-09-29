package com.jkkp.modules.basedata.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.EngineeringStageMx;
import com.jkkp.modules.basedata.view.VEngineeringStageMx;

public interface IEngineeringStageMxService extends
		IService<EngineeringStageMx, VEngineeringStageMx, Integer> {
//	public void saveOrUpdate(EngineeringStageMx eng);

//	// 显示验收标准
//	public void show(int id);
//
//	// 隐藏验收标准
//	public void hide(int id);

	public List<EngineeringStageMx> findByStageId(Integer id);
	
	public void deleteOne(int id);
	
	public void saveOrUpdate(EngineeringStageMx stage,HttpServletRequest request);
}
