package com.jkkp.pc.main.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.pc.main.service.IAppointmentService;
import com.jkkp.pc.main.view.VAppointment;

@Component("appointmentServicePC")
public class AppointmentServiceImpl extends
		ServiceSupport<Appointment, VAppointment, Integer> implements IAppointmentService {

	@Autowired
	private AppointmentMapper appointmentMapper;

	@Override
	protected Mapper<Appointment> getMapper() {
		return appointmentMapper;
	}

	//保存一pc端预约
	@Transactional(readOnly=false)
	public void saveOneAppointment(Appointment appointment) {
		appointment.setCreateTime(new Date());
		appointment.setType(6);
		this.save(appointment);
	}
}
