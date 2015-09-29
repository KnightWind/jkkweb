package com.jkkp.modules.appointment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.JlappointmentPushMapper;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.service.IJlAppointmentPushService;
import com.jkkp.modules.appointment.service.IJlAppointmentService;
import com.jkkp.modules.appointment.view.VJlappointmentPush;

@Service("jlAppointmentPushService")
public class JlAppointmentPushServiceImpl extends
		ServiceSupport<JlappointmentPush, VJlappointmentPush, Integer>
		implements IJlAppointmentPushService {

	@Autowired
	private JlappointmentPushMapper jlappointmentPushMapper;
	
	@Autowired
	private IJlAppointmentService jlAppointmentService;

	@Override
	protected Mapper<JlappointmentPush> getMapper() {
		return jlappointmentPushMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closeAppointmentPushes(int aid) {
		jlappointmentPushMapper.closeAppointmentPushes(aid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void openOne(int id,int aid) {
		jlAppointmentService.reStartJLAppointment(aid);
		jlappointmentPushMapper.openOne(id);
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closeOne(int id) {
		jlappointmentPushMapper.closeOne(id);	
	}

}
