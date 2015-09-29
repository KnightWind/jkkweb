package com.jkkp.modules.appointment.service;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointmentPush;

public interface IJlAppointmentPushService extends
		IService<JlappointmentPush, VJlappointmentPush, Integer> {
	public void closeAppointmentPushes(int aid);
	public void openOne(int id,int aid);
	public void closeOne(int id);
}
