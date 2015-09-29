package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VJLAppointmentPush1;
import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointmentPush;


public interface IJlappointmentPushSV extends IService<JlappointmentPush,VJlappointmentPush,Integer>{

	List<VJlappointmentPush> queryJAppPushDetail(Map<String, Object> map);
	List<VJLAppointmentPush1> queryJAppPushDetail1(Map<String, Object> map);
	public Object doTrasacChooseJLSp(Map<String, Object> map,
			Map<String, Object> mapBusi) throws Exception ;
	/**
	 * @param map
	 * @param mapBusi
	 * @return
	 */
	public Map<String, Object> queryJlSpPushNum(Map<String, Object> map,
			Map<String, Object> mapBusi);
	
	public void overOtherJLPush(int successJlpushId);
}
