package com.jkkp.modules.appointment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VAppointmentDetial;
import com.jkkp.appapi.modules.mapper.VIQiangDan;
import com.jkkp.appapi.modules.mapper.VISupplierQingDan;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.utils.Pager;

public interface AppointmentMapper extends Mapper<Appointment> {
	List<VAppointment> findAll();
	VIQiangDan qiangDan(@Param("aid") Integer aid);
	List<VISupplierQingDan>  qiangDanSupplier (Map<String, Object> params);
	List<VAppointment> findByAppointmentId(Map<String, Object> params);

	List<VAppointment> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	public List<Appointment> selectAllAdminAppo(@Param("id") int id);

	List<Appointment> findOverTimeList(@Param("time") String time);

	// 关闭预约 2015-6-11针对最新appointment表 status=90做关闭处理 @黄宇健
	public void closeOneAppointment(@Param("id") int id);

	// 20150625@黄宇健 针对最新的数据库结构做数据获取变更
	public List<VAppointment> selectMemberAppointments(
			Map<String, Object> params);

	public long selectMemberAppointmentsCount(Map<String, Object> params);

	/**
	 * 根据商家id查询用户预约
	 * 
	 * @param params
	 *            查询参数
	 * @param supplierId
	 *            商家id
	 * @param status
	 * @return 返回预约列表
	 */
	public List<VAppointment> selectAppointmentBySupplierId(
			Map<String, Object> params);

	public long selectAppointmentCount(Map<String, Object> params);

	public void updateStatusTo10(@Param("id") int id);
	

	//ysc=========================================
	public List<VAppointment> getList(String and);
	public List<VAppointment> getPageList(Pager pager);
	public long getCount(String and);
	public VAppointment getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
	
	/**
	 * 预约单总数
	 */
	public long getAppointmentCount();
	
	/**
	 * 预约单 量房状态数量
	 * @return 
	 */
	public int getAppointmentLiangFangCount();
	
	
	/**
	 * 北京活动
	 */
	public List<VAppointment> beijingSay(Map<String, Object> params);
	public long beijingSayCount(Map<String, Object> params);
	public VAppointment sayView(Integer id);
	public VAppointment  selectAppointmentDetail(@Param("id")int id);
	
	/**
	 * 查询预约信息
	 * @param params
	 * @return
	 */
	List<VAppointment> queryAppointmentList(Map<String, Object> params);
	
	
	List<VAppointment> ajaxGetDate();

	
	public VAppointment queryAppointmentDetailByViewAid(Map<String, Object> params);
	public VAppointmentDetial qrydetial(Map<String, Object> params);
	
	//web后台管理
	public VAppointment selectAppointmentDetailWeb(@Param("id") int id);
	
	
	//微信引流活动预约单列表
	public List<Appointment> selectAllWXActivityAppointment(Map<String, Object> params);
	
	public long selectAllWXActivityAppointmentCount(Map<String, Object> params);
}