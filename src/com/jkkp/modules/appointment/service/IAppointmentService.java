package com.jkkp.modules.appointment.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.view.VAppointment;

public interface IAppointmentService extends
		IService<Appointment, VAppointment, Integer> {
	List<VAppointment> findAll();
	public int countAll();
	public Appointment operate(Integer id, boolean isOpen);

	public Appointment oper(Integer id, boolean isOpen);

	/**
	 * 跟单员预约转移
	 * 
	 * @param oldId
	 *            转移跟单员id
	 * @param newId
	 *            接受预约跟单员id
	 */
	public int moveAppointments(int oldId, int newId);

	public void closeOneAppointment(int id);

	public List<VAppointment> selectAppointmentBySupplierId(
			Map<String, Object> params);

	public void updateStatusTo10(int id);

	// 微信活动
	VAppointment sayView(Integer id);

	public VAppointment selectAppointmentDetail(int id);

	List<VAppointment> ajaxGetDate();
	
	public VAppointment selectAppointmentDetailWeb(int id);
	
	//微信引流活动预约单保存
	public void saveActivityAppointment(Appointment bean);
	
}
