package com.jkkp.modules.appointment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VJLAppointmentPush1;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointmentPush;
import com.jkkp.utils.Pager;

public interface JlappointmentPushMapper extends Mapper<JlappointmentPush> {

	List<VJlappointmentPush> queryJAppPushDetail(Map<String, Object> map);

	List<VJLAppointmentPush1> queryJAppPushDetail1(Map<String, Object> map);

	// ysc=========================================
	public List<VJlappointmentPush> getList(String and);
	public List<VJlappointmentPush> getPageList(Pager pager);
	public long getCount(String and);
	public VJlappointmentPush getBeanById(Integer id);
	public int deleteByAnd(String and);

	// ysc=========================================

	// 监理端 获取预约列表
	public List<VJlappointmentPush> selectJlAppointmentPushes(Map<String, Object> params);

	public long selectJlAppointmentPushCount(Map<String, Object> params);

	// -------------------------------------

	// web后台==================
	public List<VJlappointmentPush> selectAllJLAppointmentPush(
			Map<String, Object> params);

	public long selectAllJLAppointmentPushCount(Map<String, Object> params);

	public void closeAppointmentPushes(@Param("aid") int aid);
	
	public void openOne(@Param("id")int id);
	
	public void closeOne(@Param("id")int id);
	// ===========
}