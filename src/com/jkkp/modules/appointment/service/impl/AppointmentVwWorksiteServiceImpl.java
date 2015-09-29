package com.jkkp.modules.appointment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentVwWorksiteMapper;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.modules.appointment.service.IAppointmentVWorksiteService;
import com.jkkp.modules.appointment.view.VAppointmentVwWorksite;

@Service("appointmentVwWorksiteService")
public class AppointmentVwWorksiteServiceImpl extends ServiceSupport<AppointmentVwWorksite, VAppointmentVwWorksite, Integer>
		implements IAppointmentVWorksiteService {

	@Autowired
	private AppointmentVwWorksiteMapper appointmentVwWorksiteMapper;
	
	@Override
	protected Mapper<AppointmentVwWorksite> getMapper() {
		return appointmentVwWorksiteMapper;
	}

	

}
