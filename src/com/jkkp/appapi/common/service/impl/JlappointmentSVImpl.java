package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.modules.appointment.view.VJlappointment1;
/**
 * 
 * @author 朱国忠
 *
 */
/**
 * 
 * @author 朱国忠
 *
 */


@Service("jlappointmentSV")
public class JlappointmentSVImpl extends ServiceSupport<Jlappointment, VJlappointment, Integer>
		implements IJlappointmentSV {
	@Autowired JlappointmentMapper jlappointmentMapper;
	@Override
	protected Mapper<Jlappointment> getMapper() {
		return jlappointmentMapper;
	}	
	public List<VJlappointment> queryJAppDetail(Map<String, Object> map){
		return jlappointmentMapper.queryJAppDetail(map);
	}
	public List<VJlappointment1> queryJAppDetail1(Map<String, Object> map){
		return jlappointmentMapper.queryJAppDetail1(map);
	}
}
