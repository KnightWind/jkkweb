/**
 * 
 */
package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.modules.appointment.view.VAppointmentVwWorksite;

/**
 * @author Administrator
 *
 */
public interface IAppointmentVWorksiteSV  extends IService<AppointmentVwWorksite,VAppointmentVwWorksite, Integer>{
	
	public Map addViewWorksiteAppointment(Map paramMap,HttpServletRequest request,Map resultMap) throws Exception ;
	public List<VAppointmentVwWorksite>  queryAppointmentVWorksiteList(Map paramMap) throws Exception ;
	public VAppointmentVwWorksite queryAppointmentVWorksiteDetail(Map paramMap) throws Exception ;
	public Map appointmentVWorksiteRespond(Map paramMap) throws Exception ;
}
