package com.jkkp.modules.appointment.service;

import org.apache.ibatis.annotations.Param;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.view.VJlappointment;

public interface IJlAppointmentService extends
		IService<Jlappointment, VJlappointment, Integer> {

	VJlappointment findJlappointmentInfoById(@Param(value = "id") Integer id);

	public void updateFailure();

	public void closeOneJLAppointment(int id);
	
	public void reStartJLAppointment(int id);
}
