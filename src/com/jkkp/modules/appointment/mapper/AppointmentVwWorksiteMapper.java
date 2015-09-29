package com.jkkp.modules.appointment.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.modules.appointment.view.VAppintmentSimpleVw;
import com.jkkp.modules.appointment.view.VAppointmentVwWorksite;

public interface AppointmentVwWorksiteMapper extends Mapper<AppointmentVwWorksite> {
	
	
	public List<VAppointmentVwWorksite> queryAppointmentVWorksiteList(Map paramMap) throws Exception;
	
	public int updateVWorksiteInfo(VAppointmentVwWorksite view) throws Exception;
	
	List<VAppintmentSimpleVw> appointmentBuilding(Map<String, Object> params);

	public long appointmentBuildingCount(Map<String, Object> params);
	
	
}