package com.jkkp.modules.appointment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.service.IJlAppointmentPushService;
import com.jkkp.modules.appointment.service.IJlAppointmentService;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

@Service("jlAppointmentService")
public class JlAppointmentServiceImpl extends
		ServiceSupport<Jlappointment, VJlappointment, Integer> implements
		IJlAppointmentService {

	@Autowired
	private JlappointmentMapper jlappointmentMapper;

	@Autowired
	private IJlAppointmentPushService jlAppointmentPushService;

	public Mapper<Jlappointment> getMapper() {
		return jlappointmentMapper;
	}

	public VJlappointment findJlappointmentInfoById(Integer id) {
		return jlappointmentMapper.findJlappointmentInfoById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateFailure() {
		String time = Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.APPOINTMENT_FAIL_TIME);
		List<Jlappointment> dataList = jlappointmentMapper
				.findOverTimeList(time);
		for (Jlappointment entity : dataList) {
			entity.setStatus(0);
			this.update(entity);
		}
	}

	// 关闭appointment 同时把push表相应的记录关闭
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closeOneJLAppointment(int id) {
		jlAppointmentPushService.closeAppointmentPushes(id);
		jlappointmentMapper.closeOneJLAppointment(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void reStartJLAppointment(int id) {
		Jlappointment appo=this.findById(id);
		//当状态为关闭或流单的时候把状态改为10（待应单）
		if(appo.getStatus()==0||appo.getStatus()==40){
			jlappointmentMapper.reStartJLAppointment(id);
		}		
	}

}
