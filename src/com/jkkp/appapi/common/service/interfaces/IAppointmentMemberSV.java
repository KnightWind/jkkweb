 package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VAppointmentMember;
import com.jkkp.appapi.modules.mapper.VIAppointmentMember;
import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppointmentMember;

public interface IAppointmentMemberSV extends IService<AppointmentMember, VAppointmentMember, Integer>{

	List<VIAppointmentMember> qryMemberDetailByMid(Map<String, Object> map);

}
