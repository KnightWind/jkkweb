package com.jkkp.modules.appointment.service;

import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.view.VAppointmentPush;

public interface IAppointmentPushService extends IService<AppointmentPush, VAppointmentPush, Integer> {
	public void updateStatusTo10(int id, int aid);

	public void closePush(int id);

	public void closeAppointmentPushes(int aid);

	/**
	 * 计算需要支付的金额 实付款
	 * 
	 * @param appointmentPush
	 * @return
	 */
	public float calculatePayAmount(AppointmentPush appointmentPush);
	/**
	 * 计算需要支付的金额 应付款
	 * 
	 * @param appointmentPush
	 * @return
	 */
	public float calculatePayDue(AppointmentPush appointmentPush);
	/**
	 * 保存到钱包
	 * 
	 * @param appointmentPush
	 * @param amount
	 */
	public void saveToMoneyBag(AppointmentPush appointmentPush, float amount);
	/**
	 * 定金支付监管款
	 * 
	 * @param appointmentPush
	 * @param amount
	 */
	public Boolean depositPayProject(Integer appointmentPushId) ;
	
	/**
	 * 更新支付的相关状态
	 * @param map
	 * @return
	 */
	public int updatePayStatusInfo(Map map);
	
	/**
	 * 检查预约单状态是否处于支付范围
	 */
	public Boolean checkPayStatus(Integer aid);

	/**
	 * 根据spId 跟aid 查寻一个push预约单
	 * @param spId
	 * @param aid
	 * @return
	 */
	public AppointmentPush findPushBySpIdAndAid(Integer spId, Integer aid);
}
