package com.jkkp.modules.appointment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.mapper.AppointmentPushMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.IAdminService;
import com.jkkp.utils.CommonUtil;

@Service("appointmentService")
public class AppointmentServiceImpl extends
		ServiceSupport<Appointment, VAppointment, Integer> implements
		IAppointmentService {

	@Autowired
	private AppointmentMapper appointmentMapper;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IAppointmentPushService appointmentPushService;
	@Autowired
	private AppointmentPushMapper appointmentPushMapper;
	@Override
	protected Mapper<Appointment> getMapper() {
		return appointmentMapper;
	}

	@Override
	public VAppointment convertBeanToView(Appointment bean) {
		VAppointment view = CommonUtil.copyBean(bean, VAppointment.class);
		view.setAdmin(adminService.findNameById(bean.getAdminId()));
		return view;
	}

	@Override
	public List<VAppointment> findAll() {
		return appointmentMapper.findAll();
	}

	@Override
	public Appointment operate(Integer id, boolean isOpen) {
		Appointment appointment = this.findById(id);
		if (isOpen) {
			appointment.setStatus(3);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse("0000-00-00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			appointment.setGetRoomDate(date);
		}
		this.update(appointment);
		return appointment;
	}

	@Override
	public Appointment oper(Integer id, boolean isOpen) {
		Appointment appointment = this.findById(id);
		if (isOpen) {
			appointment.setStatus(-1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse("0000-00-00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			appointment.setGetRoomDate(date);
		}
		this.update(appointment);
		return appointment;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int moveAppointments(int oldId, int newId) {
		try {
			List<Appointment> appointmentList = appointmentMapper
					.selectAllAdminAppo(oldId);
			for (Appointment appo : appointmentList) {
				appo.setAdminId(newId);
				this.update(appo);
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateFailure() {
		String time = Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.APPOINTMENT_FAIL_TIME);
		List<Appointment> dataList = appointmentMapper.findOverTimeList(time);
		Map map = new HashMap();
		for (Appointment entity : dataList) {
			entity.setStatus(0);
			this.update(entity);
			//将关联的push单更新为已结束
			map.put("aid", entity.getId());
			map.put("status", 70);
			appointmentPushMapper.upadteStatusByAid(map);
		}
	}

	// 关闭appointment表记录 同时把推送表的所有记录都给关闭
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closeOneAppointment(int id) {
		appointmentPushService.closeAppointmentPushes(id);
		appointmentMapper.closeOneAppointment(id);
	}

	// 20150626@黄宇健 push表重新推送，appointment表状态值跟着改变
	public void updateStatusTo10(int id) {
		Appointment appo = this.findById(id);
		// 当状态为流单或关闭的时候，把状态改为10（待抢单）
		if (appo.getStatus() == 0 || appo.getStatus() == 90) {
			appointmentMapper.updateStatusTo10(id);
		}
	}

	@Override
	public VAppointment sayView(Integer id) {
		return appointmentMapper.sayView(id);
	}


	


	@Override
	public List<VAppointment> ajaxGetDate() {
		return appointmentMapper.ajaxGetDate();
	}

	@Override
	public List<VAppointment> selectAppointmentBySupplierId(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VAppointment selectAppointmentDetail(int id) {
		return appointmentMapper.selectAppointmentDetail(id);
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		List<VAppointment> VAppointment = appointmentMapper.findAll();
		if(VAppointment.size()>0)
			return VAppointment.get(0).getId();
		else {
			return 0;
		}
	}

	@Override
	public VAppointment selectAppointmentDetailWeb(int id) {
		return appointmentMapper.selectAppointmentDetailWeb(id);
	}

	@Override
	public void saveActivityAppointment(Appointment bean) {
		Appointment beanHandle=this.findById(bean.getId());
		beanHandle.setMobile(bean.getMobile());
		beanHandle.setCommunity(bean.getCommunity());
		beanHandle.setReviewTime(bean.getReviewTime());
		if(bean.getHouseType()!=beanHandle.getHouseType()&&!bean.getHouseType().isEmpty()){
		       beanHandle.setHouseType(bean.getHouseType());
		}
		beanHandle.setBudget(bean.getBudget());
		beanHandle.setSpace(bean.getSpace());
		this.update(beanHandle);
	}

}
