package com.jkkp.modules.appointment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VAppointmentPushV1;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.view.VAppointmentPush;

public interface AppointmentPushMapper extends Mapper<AppointmentPush> {
	List<VAppointmentPush> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	int queryAppPushlGrab(Map<String, Object> map);

	int queryAppPush(Map<String, Object> map);

	List<VIAppointmentPush> queryAppPushlByAppid(Map<String, Object> map);

	List<VIAppointmentPush> queryAppPushDetail(Map<String, Object> map);
	VIAppointmentPush queryAppPushbn(@Param("appointmentPushId") Integer appointmentPushId);
	List<VAppointmentPushV1> fin(@Param("id") Integer id);

	List<VIAppPushAndAppAndDesignCase> queryAppPushAndAppAndDesignCaseByAppid(
			Map<String, Object> map);

	//----------------web------------------
	// 20150626@黄宇健 针对最新数据库结构重新获取与用户推送信息
	public List<VAppointmentPush> selectAllAppointmentPushes(
			Map<String, Object> map);

	public long selectAllAppointmentPushCount(Map<String, Object> map);

	public void updateStatusTo10(@Param("id") int id);

	public void closePush(@Param("id") int id);
	
	public void closeAppointmentPushes(@Param("aid")int aid);
	
	public void updateAppointmentLDStatus(@Param("id") int id);
	
	AppointmentPush findPushBySpIdAndAid(@Param("spId")Integer spId, @Param("aid")Integer aid);
	//--------------web--------------
	
	public int upadteStatusByParam(Map<String, Object> params);

	public int upadteStatusByAid(Map<String, Object> params);
	public int updatePayStatusInfo(Map map);

}