package com.jkkp.modules.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IEngineeringsService;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

@Service("engineeringsServiceWEB")
public class EngineeringsServiceImpl extends
		ServiceSupport<Engineerings, VEngineerings, Integer> implements
		IEngineeringsService {

	@Autowired
	private EngineeringsMapper engineeringsMapper;

	@Override
	protected Mapper<Engineerings> getMapper() {
		return engineeringsMapper;
	}

	@Override
	public VEngineerings engineeringDetail(int id) {
		return engineeringsMapper.engineeringDetail(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePoint(String pointx, String pointy, int id) {
		engineeringsMapper.updatePoint(pointx, pointy, id);
	}

	@Override
	public Boolean updateZXStage(int engineerId,int zxstage) {
		// TODO Auto-generated method stub
		Engineerings eng = this.findById(engineerId);
		if (eng != null) {
			if (zxstage < 50) {
				eng.setZxStage(zxstage + 10);
				this.update(eng);
			}
		}
		return true;
	}

	
	@Override
	@Transactional
	public void updateEngineeringsState() {
		String daystr = Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.engineerCheckoutDays);
		if(daystr!=null&&!"".equals(daystr)){
			Map map = new HashMap();
			map.put("days", Integer.parseInt(daystr));
			List<VEngineerings> list = engineeringsMapper.queryEngineerings(map);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					map.put("id",list.get(i).getId());
					map.put("status",1);
					engineeringsMapper.updateEngineeringsStatus(map);
				}
			}
		}else{
			System.out.println("->>>>>>没有配置参数-工程结款时间");
		}
		
	}
}
