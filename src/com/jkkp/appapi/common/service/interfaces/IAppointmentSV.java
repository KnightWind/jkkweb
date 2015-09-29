package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.appapi.modules.mapper.VAppointmentDetial;
import com.jkkp.appapi.modules.mapper.VIQiangDan;
import com.jkkp.appapi.modules.mapper.VISupplierQingDan;
import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.view.VAppointment;
/**
 * 
 * @author 朱国忠
 *
 */

public interface IAppointmentSV  extends IService<Appointment,VAppointment, Integer>{

	/*
	 *****根据appointmentId查找对应预约管理数据
	 */
	
	public List<VAppointment>  findByAppointmentId(Map<String, Object> params);	
	
	/*
	 *****根据字段查找对应预约管理数据
	 */
	public List<Appointment> findParentList(String columnName,String value);
	
	/*
	 *****保存新增修改预约管理数据
	 */
	List<VISupplierQingDan>  qiangDanSupplier (Map<String, Object> params);
	VIQiangDan qiangDan(Integer aid);
	public void saveOrUpdate(Appointment appointment);
	
	/*
	 *****根据appointmentId删除预约管理数据
	 */
	
	public void remove(Integer appointmentId);
	public boolean del(Integer appointmentId);
	public boolean updateCAP(Appointment a);

	/**
	 * 查询预约单总条数
	 */
	public long getAppointmentCount();
	public int getAppointmentCountLiangFang();
	
	
	public List<VAppointment> queryAppointmentList(Map<String, Object> params);
	
	public VAppointment queryAppointmentDetailByViewAid(Map<String, Object> params);
	
	public VAppointmentDetial querydetial(int id);
	public Map<String, Object> querydetial1(int id);
	
	public Map doTranscRespondAppointment(Map paramMap ,HttpServletRequest request) throws  Exception;
	public Boolean over(int appointmentid);
}
