package com.jkkp.pc.main.service;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.pc.main.view.VAppointment;

public interface IAppointmentService extends
		IService<Appointment, VAppointment, Integer> {
	public void saveOneAppointment(Appointment appointment);
}
