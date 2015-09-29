package com.jkkp.modules.appointment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.modules.appointment.view.VJlappointment1;
import com.jkkp.utils.Pager;

public interface JlappointmentMapper extends Mapper<Jlappointment> {

	List<VJlappointment> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	public VJlappointment findJlappointmentInfoById(Integer id);

	List<Jlappointment> findOverTimeList(@Param("time") String time);

	List<VJlappointment> queryJAppDetail(Map<String, Object> map);

	List<VJlappointment1> queryJAppDetail1(Map<String, Object> map);

	// web后台
	public List<VJlappointment> selectAllJLAppointment(Map<String, Object> map);

	public long selectAllJLAppointmentCount(Map<String, Object> map);

	public void closeOneJLAppointment(@Param("id") int id);

	public void reStartJLAppointment(@Param("id") int id);

	// -web

	// ysc=========================================
	public List<VJlappointment> getList(String and);
	public List<VJlappointment> getPageList(Pager pager);
	public long getCount(String and);
	public VJlappointment getBeanById(Integer id);
	public int deleteByAnd(String and);
	// ysc=========================================
}