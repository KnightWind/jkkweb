package com.jkkp.modules.appointment.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.mapper.AppointmentPushMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentMemberService;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.appointment.view.VAppointmentPush;
import com.jkkp.modules.member.model.MoneyBag;
import com.jkkp.modules.member.service.IMoneyBagService;
import com.jkkp.utils.Arith;

@Service("appointmentPushService")
public class AppointmentPushServiceImpl extends ServiceSupport<AppointmentPush, VAppointmentPush, Integer> implements
		IAppointmentPushService {
	@Autowired
	private AppointmentPushMapper appointmentPushMapper;
	@Autowired
	private AppointmentMapper appointmentMapper;
	@Autowired
	private IAppointmentMemberService appointmentMemberService;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IMoneyBagService moneyBagService;
	@Autowired 
	private IEngineeringsService engservice;
	@Override
	protected Mapper<AppointmentPush> getMapper() {
		return appointmentPushMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateStatusTo10(int id, int aid) {
		AppointmentPush entity = this.findById(id);
		if(entity.getStatus()==70){
			//流单推送
			appointmentPushMapper.updateAppointmentLDStatus(id);
		}else{
		    appointmentPushMapper.updateStatusTo10(id);
		}
		Appointment bean = appointmentService.findById(aid);
		if(bean.getStatus()==90||bean.getStatus()==0){
		   appointmentMapper.updateStatusTo10(aid);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closePush(int id) {
		appointmentPushMapper.closePush(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void closeAppointmentPushes(int aid) {
		appointmentPushMapper.closeAppointmentPushes(aid);
	}

	/**
	 * 计算需要支付的金额
	 * 
	 * @param appointmentPushId
	 * @return
	 */
	public float calculatePayAmountArith(AppointmentPush appointmentPush) {
		float payamount = (float) Arith.mul(appointmentPush.getQuote(), 0.2, 3); // 监管款为20%
		if (!appointmentPush.getStatus().equals(ConstantAppStatus.PUSH_YI_FU_DINGJIN)) { // 如果未付定金，支付金额为全部监管款
			return payamount;
		}
		// 已付定金，求需要支付的余款
		//return (float) Arith.sub(payamount, appointmentPush.getMoney());
		return (float) Arith.sub(String.valueOf(payamount), String.valueOf(appointmentPush.getMoney()));
	}

	/**
	 * 计算需要支付的金额，保留两位小数 BigDecimal.setScale()方法用于格式化小数点
	 * setScale(1)表示保留一位小数，默认用四舍五入方式
	 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
	 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
	 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
	 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
	 * @param appointmentPushId
	 * @return
	 */
	public float calculatePayAmountPoint(AppointmentPush appointmentPush) {
		BigDecimal b = new BigDecimal(String.valueOf(calculatePayAmountArith(appointmentPush)));
		double f1 = b.setScale(2, BigDecimal.ROUND_UP).doubleValue();// 截取两位小数
		return (float) f1;
	}
	/**
	 * 计算需要支付的金额，保留两位小数 BigDecimal.setScale()方法用于格式化小数点
	 * setScale(1)表示保留一位小数，默认用四舍五入方式
	 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
	 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
	 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
	 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
	 * @param appointmentPushId
	 * @return
	 */
	public float calculatePayDuePoint(AppointmentPush appointmentPush) {
		BigDecimal b = new BigDecimal(String.valueOf((float) Arith.mul(appointmentPush.getQuote(), 0.2, 3)));
		double f1 = b.setScale(2, BigDecimal.ROUND_UP).doubleValue();// 截取两位小数
		return (float) f1;
	}
	/**
	 * 计算需要支付的金额 实付款 监管款20%-定金
	 * 张金华 20150902修改为根据指定精度返回值
	 * @param appointmentPushId
	 * @return
	 */
	public float calculatePayAmount(AppointmentPush appointmentPush) {
		return (float) calculatePayAmountPoint(appointmentPush);
	}
	/**
	 * 计算需要支付的金额 应付款 监管款的20%
	 * 张金华 20150902修改为根据指定精度返回值
	 * @param appointmentPushId
	 * @return
	 */
	public float calculatePayDue(AppointmentPush appointmentPush) {
		return (float) calculatePayDuePoint(appointmentPush);
	}
	@Override
	public void saveToMoneyBag(AppointmentPush appointmentPush, float amount) {
		Appointment appointment = appointmentService.findById(appointmentPush.getAid());
		AppointmentMember appointmentMember = appointmentMemberService.findByAppointmentId(appointment.getId());
		moneyBagService.saveMoneyBag(appointmentMember.getMid(), amount, MoneyBag.SOURCE_TYPE_4);
	}
	
	@Override
	public Boolean depositPayProject(Integer appointmentPushId) {
		AppointmentPush appointmentPush=this.findById(appointmentPushId);
		float amount=this.calculatePayAmount(appointmentPush);
		if(amount>0){
			return false;
		}
		if(amount<0){
			amount=-amount;
		}
		this.saveToMoneyBag(appointmentPush, amount);
		appointmentPush.setProjectPayStatus(1);//定金支付监管款
		appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_FU_KUAN);
		engservice.addEngineerings(appointmentPushId);
		this.update(appointmentPush);
		return true;
	}

	@Override
	public int updatePayStatusInfo(Map map) {
		return appointmentPushMapper.updatePayStatusInfo(map);
	}

	@Override
	public Boolean checkPayStatus(Integer aid) {
		AppointmentPush bean = this.findById(aid);
		if(bean!=null||bean.getStatus()==50||bean.getStatus()==60){
			return true;
		}
		return false;
	}

	@Override
	public AppointmentPush findPushBySpIdAndAid(Integer spId, Integer aid) {
		// TODO Auto-generated method stub
		return appointmentPushMapper.findPushBySpIdAndAid(spId,aid);
	}

}
