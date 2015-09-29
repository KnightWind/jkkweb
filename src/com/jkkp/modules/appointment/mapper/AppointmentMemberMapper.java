package com.jkkp.modules.appointment.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VIAppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentMember;

public interface AppointmentMemberMapper extends Mapper<AppointmentMember> {

	List<VIAppointmentMember> qryMemberDetailByMid(Map<String, Object> map);
	List<AppointmentMember> qryMidByAid(Map<String, Object> map);
}
