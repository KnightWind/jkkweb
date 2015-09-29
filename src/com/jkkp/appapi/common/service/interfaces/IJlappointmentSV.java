package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.modules.appointment.view.VJlappointment1;

public interface IJlappointmentSV extends IService<Jlappointment,VJlappointment,Integer>{

	List<VJlappointment> queryJAppDetail(Map<String, Object> map);
	List<VJlappointment1> queryJAppDetail1(Map<String, Object> map);
}
