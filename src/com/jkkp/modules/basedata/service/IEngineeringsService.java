package com.jkkp.modules.basedata.service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VEngineerings;

public interface IEngineeringsService extends
		IService<Engineerings, VEngineerings, Integer> {
	public VEngineerings engineeringDetail(int id);

	public void updatePoint(String pointx, String pointy, int id);
	
	public Boolean updateZXStage(int engineerId,int zxstage);
	
	
	public void updateEngineeringsState() ;
}
