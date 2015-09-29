package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.modules.mapper.VAppointmentMember;
import com.jkkp.appapi.modules.mapper.VIAppointmentMember;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMemberMapper;
import com.jkkp.modules.appointment.model.AppointmentMember;

@Service("appointmentMemberSV")
public class AppointmentMemberSVImpl extends ServiceSupport<AppointmentMember, VAppointmentMember, Integer> implements IAppointmentMemberSV{

	@Autowired AppointmentMemberMapper appointmentMemberMapper;
	@Override
	protected Mapper<AppointmentMember> getMapper() {
		// TODO Auto-generated method stub
		return appointmentMemberMapper;
	}
	@Override
	public List<VIAppointmentMember> qryMemberDetailByMid(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appointmentMemberMapper.qryMemberDetailByMid(map);
	}

}
