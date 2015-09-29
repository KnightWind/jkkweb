package com.jkkp.modules.appointment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppoinConditionMapper;
import com.jkkp.modules.appointment.model.AppoinCondition;
import com.jkkp.modules.appointment.service.IAppConditionService;
import com.jkkp.modules.appointment.view.VAppoinCondition;
import com.jkkp.modules.appointment.view.VAppointment;

@Service("appoinConditionService")
public class AppoinConditionServiceImpl extends ServiceSupport<AppoinCondition, VAppoinCondition, Integer> implements
		IAppConditionService {

	@Autowired
	private AppoinConditionMapper appoinConditionMapper;

	@Override
	protected Mapper<AppoinCondition> getMapper() {
		return appoinConditionMapper;
	}

	@Override
	public AppoinCondition getById(Integer id) {
		return appoinConditionMapper.getById(id);
	}

	@Override
	public List<VAppointment> ajaxGetDate() {
		return appoinConditionMapper.ajaxGetDate();
	}
	
	
	
}
