package com.jkkp.modules.appointment.service;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.view.VAppointmentMember;

public interface IAppointmentMemberService extends IService<AppointmentMember, VAppointmentMember, Integer> {

	AppointmentMember findByAppointmentId(Integer appointmentId);

}
