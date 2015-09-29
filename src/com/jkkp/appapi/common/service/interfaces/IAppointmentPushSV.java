package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VAppointmentPushV1;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.view.VAppointmentPush;
public interface IAppointmentPushSV extends IService<AppointmentPush, VAppointmentPush, Integer>{

	AppointmentPush queryAppPushlByid(Map<String, Object> map) ;

	List<VIAppointmentPush> queryAppPushlByAppid(Map<String, Object> map);

	int queryAppPushlGrab(Map<String, Object> map);

	int queryAppPush(Map<String, Object> map);
	VIAppointmentPush queryAppPushbn(Integer appointmentPushId);
	List<VIAppointmentPush> queryAppPushDetail(Map<String, Object> map);
	List<VAppointmentPushV1> fin( Integer id);
	List<VIAppPushAndAppAndDesignCase>queryAppPushAndAppAndDesignCaseByAppid(Map<String, Object> map);
	
	Boolean updatetime(int pushid,java.util.Date amountTime,int TypeBC);
	Boolean updatetimeC(int pushid,java.util.Date amountTime);
	Boolean updatetimeB(int pushid,java.util.Date amountTime);
	void cancelOtherPush(int SuccessPush);
	Boolean pushToUserCollects(int appointmentid);
	Boolean pushToJudgeSuppCondition(int appointmentid);
	Boolean over(int appointmentid);
	
	public boolean applyForRefundDeposit(Map<String, Object> map) throws Exception ;

	/**
	 * @param map
	 * @return
	 */
	public AppointmentPush queryDepositStatus(Map<String, Object> map);
	/**
	 * 其他商家有没有支付监管款
	 * @param appointmentpushId 商家推送ID
	 * @return Boolean 支付结果 true 就是有其他商家付款了
	 */
	public Boolean queryOtherPayProjectStatus(int appointmentpushId);
	/**
	 * 查询能不能支付定金
	 * @param appointmentpushId 商家推送ID
	 * @return int -1别人支付了 0可以支付 1 等待支付结果 2 已经支付
	 */
	public int queryToPayDeposit(int appointmentpushId);
	/**
	 * 查询能不能支付定金
	 * @param appointmentpushId 商家推送ID
	 * @return int -1别人支付了 0可以支付 1 等待支付结果 2 已经支付
	 */
	public int queryToPayProject(int appointmentpushId);

}
