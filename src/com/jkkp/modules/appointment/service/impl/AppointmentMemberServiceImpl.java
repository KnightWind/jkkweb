package com.jkkp.modules.appointment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMemberMapper;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.service.IAppointmentMemberService;
import com.jkkp.modules.appointment.view.VAppointmentMember;

@Service("appointmentMemberService")
public class AppointmentMemberServiceImpl extends ServiceSupport<AppointmentMember, VAppointmentMember, Integer>
		implements IAppointmentMemberService {

	@Autowired
	private AppointmentMemberMapper appointmentMemberMapper;

	@Override
	protected Mapper<AppointmentMember> getMapper() {
		return appointmentMemberMapper;
	}

	@Override
	public AppointmentMember findByAppointmentId(Integer appointmentId) {
		List<AppointmentMember> list = this.selectByProperty("aid", appointmentId);
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}

}
