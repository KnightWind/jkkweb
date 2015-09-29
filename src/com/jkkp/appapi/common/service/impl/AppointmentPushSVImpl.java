package com.jkkp.appapi.common.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierConditionSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.modules.mapper.VAppointmentPushV1;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.appapi.modules.mapper.VRetCondition;
import com.jkkp.appapi.putapp.PushApp;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.mapper.AppointmentPushMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentMemberService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.appointment.view.VAppointmentPush;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.service.IRefundApplyAuditService;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.model.SupplierCondition;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.pc.main.controller.mainController;

@Service("appointmentPushSV")
public class AppointmentPushSVImpl extends
		ServiceSupport<AppointmentPush, VAppointmentPush, Integer> implements
		IAppointmentPushSV {
	@Autowired
	ISuppMessagePushSV suppMessagePushSV;
	@Autowired
	AppointmentPushMapper appointmentPushMapper;
	@Autowired
	IAppointmentService appointmentSV;
	@Autowired
	ISupplierCollectSV supplierCollectSV;
	@Autowired
	ISupplierConditionSV iSupplierConditionSV;
	@Autowired
	IAppointmentService IAppointmentServicesv;
	@Autowired
	ISupplierSV suppsv;
	@Autowired
	IAppointmentMemberService IAppointmentMemberServicesv;
	@Autowired
	IPaymentRecordService paymentRecordsv;
	@Autowired
	AppointmentMapper appointmentMapper;
	@Autowired
	RefundApplyAuditMapper refundApplyAuditMapper;
	@Autowired	
	IRefundApplyAuditService refundApplyAuditService;
	@Override
	protected Mapper<AppointmentPush> getMapper() {
		// TODO Auto-generated method stub
		return appointmentPushMapper;
	}

	@Override
	public AppointmentPush queryAppPushlByid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer id = Integer.valueOf((String) map.get("appointmentPushId"));
		return appointmentPushMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<VIAppointmentPush> queryAppPushlByAppid(Map<String, Object> map) {
		return appointmentPushMapper.queryAppPushlByAppid(map);
	}

	@Override
	public int queryAppPushlGrab(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appointmentPushMapper.queryAppPushlGrab(map);
	}

	public int queryAppPush(Map<String, Object> map) {
		return appointmentPushMapper.queryAppPush(map);
	}

	@Override
	public List<VIAppointmentPush> queryAppPushDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appointmentPushMapper.queryAppPushDetail(map);
	}

	@Override
	public List<VIAppPushAndAppAndDesignCase> queryAppPushAndAppAndDesignCaseByAppid(
			Map<String, Object> map) {
		return appointmentPushMapper
				.queryAppPushAndAppAndDesignCaseByAppid(map);
	}

	@Override
	public List<VAppointmentPushV1> fin(Integer id) {
		return appointmentPushMapper.fin(id);
	}

	@Override
	public Boolean updatetime(int pushid, java.util.Date amountTime, int TypeBC) {
		// TypeBC 1 用户修改次数 2 商家修改次数
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		AppointmentPush appointmentPush = null;
		map.put("appointmentPushId", String.valueOf(pushid));
		appointmentPush = this.queryAppPushlByid(map);
		appointmentPush.setAmountTime(amountTime);
		appointmentPush.setStatus(ConstantAppStatus.PUSH_YU_YUE_LIANG_FANG);
		if (TypeBC == 1) {
			if (appointmentPush.getChangNoC() == null) {
				appointmentPush.setChangNoC(1);
			} else {
				int cmax = Integer.parseInt(Sysconfig.CONFIG_PARAM
						.get(ConfigConstant.UPDATE_MAX));
				if (appointmentPush.getChangNoC() <= (cmax - 1))
					appointmentPush
							.setChangNoC(appointmentPush.getChangNoC() + 1);
				else {
					return false;
				}
			}
		}
		if (TypeBC == 2) {
			if (appointmentPush.getChangNoB() == null) {
				appointmentPush.setChangNoB(1);
			} else {
				int bmax = Integer.parseInt(Sysconfig.CONFIG_PARAM
						.get(ConfigConstant.UPDATE_MAX));
				if (appointmentPush.getChangNoB() <=(bmax - 1))
					//appointmentPush.getChangNoB() <= (bmax - 1)
					appointmentPush
							.setChangNoB(appointmentPush.getChangNoB() + 1);
				else {
					return false;
				}
			}
		}
		this.update(appointmentPush);
		// 假如预约单没有预约时间就设置一个时间
		Appointment app = appointmentSV.findById(appointmentPush.getAid());
		app.setReviewTime(amountTime);
		
		appointmentSV.update(app);
		return true;
	}

	@Override
	public void cancelOtherPush(int SuccessPush) {
		// TODO Auto-generated method stub
		AppointmentPush push = this.findById(SuccessPush);
		if (push != null) {
			List<AppointmentPush> other = this.selectByProperty("aid",
					push.getAid(), "aid", true);
			if (other == null)
				return;
			for (AppointmentPush o : other) {
				if (o.getId() != SuccessPush) {
					if (o.getStatus() != ConstantAppStatus.PUSH_DAI_YING_DAN) {
						// 正常状态或者空值
						if (o.getRandom() == null) {
							o.setRandom(2);
						} else if (o.getRandom() == 0) {
							o.setRandom(2);
						}
					}
					if (o.getStatus() == ConstantAppStatus.PUSH_DAI_YING_DAN)// 待抢单的变为结束
					{
						o.setStatus(ConstantAppStatus.PUSH_JIE_SHU_QIANGDAN);
					}
					this.update(o);
				}
			}
		}

	}

	@Override
	public Boolean updatetimeC(int pushid, java.util.Date amountTime) {
		return updatetime(pushid, amountTime, 1);
	}
   
	@Override
	public Boolean updatetimeB(int pushid, java.util.Date amountTime) {
		return updatetime(pushid, amountTime, 2);
	}

	@Override
	public Boolean pushToUserCollects(int appointmentid) {
		// TODO Auto-generated method stub
		List<SupplierCollect> supplierCollects = null;
		int memberId = 0;
		Boolean success = false;
		AppointmentPush appointmentPush = null;
		Appointment appointment = IAppointmentServicesv.findById(appointmentid);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		List<SuppMessagePush> suppMessagePushs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<AppointmentMember> memberlist = IAppointmentMemberServicesv
				.selectByProperty("aid", appointmentid);
		if (memberlist.size() <= 0)
			return false;
		memberId = memberlist.get(0).getMid();
		map.put("memberId", memberId);
		supplierCollects = supplierCollectSV.querySupCollDetailByUid(map);
		if (supplierCollects.size() > 0) {
			for (int i = 0; i < supplierCollects.size(); i++) {
				Integer spId = supplierCollects.get(i).getSpId();
				VRetCondition result = iSupplierConditionSV
						.judgeSuppConditionResult(spId,
								appointment.getBudget(),
								appointment.getRegionid());
				// 如果判断不满足商家设置条件，则跳过推送
				if (!result.isFlag())
					continue;
				success = true;
				List<AppointmentPush> applist = this.selectByProperty(
						new String[] { "aid", "spId" }, new Object[] {
								appointmentid, spId });
				if (applist.size() <= 0) {
					appointmentPush = new AppointmentPush();

					appointmentPush.setSpId(spId);
					appointmentPush.setAid(appointment.getId());
					appointmentPush.setSendCollectState(1);// sendCollectState：0为不勾选收藏推送
															// 1为勾选收藏推送
					appointmentPush.setCreateTime(time);
					appointmentPush
							.setStatus(ConstantAppStatus.PUSH_DAI_ShangJia_YINGDAN);
					Appointment a = appointmentSV.findById(appointment.getId());// 匹配到商家就修改预约主表状态
					a.setStatus(ConstantAppStatus.DAI_QIANG_DAN);
					appointmentSV.update(a);
					appointmentPush.setReminder(1);
					if (appointment.getReviewTime() != null) {
						appointmentPush.setAmountTime(appointment
								.getReviewTime());
					}
					appointmentPush.setMoney(appointment.getBudget());
					appointmentPush.setSupplierCount(0);
					appointmentPush.setRandom(0);
					this.save(appointmentPush);
					
					// 短信推送给商家
					Supplier supplier = suppsv.findById(spId);
					if (supplier != null) {
						String smsContent = Sysconfig.CONFIG_PARAM
								.get(ConfigConstant.APP_PUSH_NEWAPP);// xx装修公司修改了量房时间为
						SendMsgUtil.send(supplier.getBindMobile(), smsContent);
					}

					// 判断商家是否在线，如果在线则推送给B端商户
					suppMessagePushs = suppMessagePushSV.selectByUserIdAndType(supplier.getId(), supplier.getType());
					if (suppMessagePushs.size() > 0) {
						// 推送到B端，让其显示新增的预约信息
						for (int j = 0; j < suppMessagePushs.size(); j++)
							try {
								PushApp.pushApp(suppMessagePushs.get(j));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					
				}
			}

		}

		return success;
	}

	@Override
	public Boolean pushToJudgeSuppCondition(int appointmentid) {
		// TODO Auto-generated method stub
		List<SupplierCollect> supplierCollects = null;
		Boolean success = false;
		int memberId = 0;
		AppointmentPush appointmentPush = null;
		Appointment appointment = IAppointmentServicesv.findById(appointmentid);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		List<SuppMessagePush> suppMessagePushs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		AppointmentMember memberlist = IAppointmentMemberServicesv
				.findByAppointmentId(appointmentid);
		if (memberlist == null)
			return false;
		memberId = memberlist.getMid();
		map.put("memberId", memberId);
		if (appointment.getBudget() == null)
			return false;
		if (appointment.getRegionid() == null)
			return false;
		map.put("budget", appointment.getBudget());
		map.put("regionId", appointment.getRegionid());
		supplierCollects = supplierCollectSV.querySupCollDetailByUid(map);

		List<SupplierCondition> suppliers = iSupplierConditionSV
				.judgeSuppCondition(map);
		// 如果没找到满足商家条件匹配的单，操作失败
		if (suppliers.size() <= 0) {
			// mapBusi.put("doneCode", "0000");
			// mapBusi.put("mess", "未找到相匹配的商家推送，请核查对应预算和选择地市！");
			// return -1;
		}
		if (suppliers.size() > 0) {
			success = true;
		}
		for (int i = 0; i < suppliers.size(); i++) {
			System.out.print("spid" + suppliers.get(i).getSpId() + "\r\n");
			Integer spId = suppliers.get(i).getSpId();
			List<AppointmentPush> applist = this.selectByProperty(new String[] {
					"aid", "spId" }, new Object[] { appointmentid, spId });
			if (applist.size() <= 0) {

				appointmentPush = new AppointmentPush();

				appointmentPush.setSpId(spId);
				appointmentPush.setAid(appointmentid);
				appointmentPush.setSendCollectState(0);// sendCollectState：0为不勾选收藏推送
														// 1为勾选收藏推送
				appointmentPush.setCreateTime(time);
				appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_YING_DAN);
				appointmentPush.setReminder(1);
				String atime = (String) map.get("amountTime");
				if (appointment.getReviewTime() != null) {
					appointmentPush.setAmountTime(appointment.getReviewTime());
				}
				appointmentPush.setMoney(appointment.getBudget());
				appointmentPush.setSupplierCount(0);
				appointmentPush.setRandom(0);
				this.save(appointmentPush);

				// 判断商家是否在线，如果在线则推送给B端商户
				suppMessagePushs = suppMessagePushSV.selectByProperty("spId",
						spId);
				if (suppMessagePushs.size() > 0) {
					// 推送到B端，让其显示新增的预约信息
					for (int j = 0; j < suppMessagePushs.size(); j++)
						try {
							PushApp.pushApp(suppMessagePushs.get(j));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}

				// 短信推送给商家
				Supplier supplier = suppsv.findById(spId);
				if (supplier != null) {
					String smsContent = Sysconfig.CONFIG_PARAM
							.get(ConfigConstant.APP_PUSH_NEWAPP);// xx装修公司修改了量房时间为
					if (supplier.getBindMobile() != null)
						SendMsgUtil.send(supplier.getBindMobile(), smsContent);
				}
			}
		}
		if (success == true) {
			appointment.setStatus(ConstantAppStatus.DAI_QIANG_DAN);
			IAppointmentServicesv.update(appointment);
		}
		return success;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Boolean over(int appointmentid) {
		// TODO Auto-generated method stub
		List<AppointmentPush> other = this.selectByProperty("aid",
				appointmentid, "aid", true);
		if (other.size() <= 0)
			return true;
		for (AppointmentPush o : other) {
			if (o.getStatus() == ConstantAppStatus.PUSH_DAI_YING_DAN) {
				o.setStatus(ConstantAppStatus.PUSH_JIE_SHU_QIANGDAN);
				this.update(o);
			}
		}
		return true;
	}

	/**
	 * 申请退款(定金) 修改push表状态 修改appoint表状态 插入refund_apply_audit 退款申请表
	 * 
	 */
	@Override
	@Transactional
	public boolean applyForRefundDeposit(Map<String, Object> map)
			throws Exception {
		Integer pushId = Integer.valueOf((String) map.get("pushId"));
		String applyReason = (String) map.get("reason");
		AppointmentPush push = appointmentPushMapper.selectByPrimaryKey(pushId);
		// 判断是否是已支付
		if (push == null) {
			throw new RuntimeException("USER_DEFINED_ERROR#1001#push单不存在");
		}
		Appointment appointment = appointmentMapper.selectByPrimaryKey(push
				.getAid());
		if (appointment == null) {
			throw new RuntimeException("USER_DEFINED_ERROR#1002#关联的预约单不存在");
		}
		if (push.getDepositPayStatus() != 1) {
			throw new RuntimeException(
					"USER_DEFINED_ERROR#1003#push单定金状态不是已支付状态，不允许申请退款");
		}
		
		//是否重复提交退款
		RefundApplyAudit p = new RefundApplyAudit();
		p.setBusinessId(pushId);
		List<RefundApplyAudit> applylist = refundApplyAuditMapper.select(p);
		if(applylist!=null&&applylist.size()>0){
			for(RefundApplyAudit apply: applylist){
				//定金退款
				if(apply.getRefundType()!=null&&apply.getRefundType()==1){
//					0：待审核  
//					1：审核通过
//					2：审核不通过
//					3：退款完成
					if(apply.getStatus()!=null&&(apply.getStatus()==0||apply.getStatus()==1)){
						throw new RuntimeException(
								"USER_DEFINED_ERROR#1004#该单还有未审批完成的退款申请");
					}
				}
			}
			
		}
		
		push.setStatus(ConstantAppStatus.PUSH_REFUND_DEPOSIT_AUDIT);
		appointment.setStatus(ConstantAppStatus.REFUND_DEPOSIT_AUDIT);
		int n = appointmentPushMapper.updateByPrimaryKey(push);
		int m = appointmentMapper.updateByPrimaryKey(appointment);
		// 插入退款申请表
		RefundApplyAudit apply = new RefundApplyAudit();
		apply.setApplyAmount(Double.valueOf(push.getMoney()));
		apply.setRefundType(1);
		apply.setBusinessId(pushId);
		apply.setAppointId(appointment.getId());
		apply.setApplyDate(new Date());
		apply.setApplyReason(applyReason);
		apply.setApplyUserId(null);
		apply.setStatus(0);
		refundApplyAuditMapper.insert(apply);
		if (n > 0 && m > 0) {
			return true;
		} else {
			throw new RuntimeException("USER_DEFINED_ERROR#1004#未更新到数据，申请失败");
		}
	}

	@Override
	public AppointmentPush queryDepositStatus(Map<String, Object> map) {
		Integer pushId = Integer.valueOf((String) map.get("pushId"));
		AppointmentPush push = appointmentPushMapper.selectByPrimaryKey(pushId);
		return push;
	}

	@Override
	public Boolean queryOtherPayProjectStatus(int appointmentpushId) {
		// TODO Auto-generated method stub
		Boolean ret = false;
		AppointmentPush ap = this.findById(appointmentpushId);
		if (ap == null)
			return false;

		List<AppointmentPush> other = this.selectByProperty("aid", ap.getAid(),
				"aid", true);
		if (other.size() <= 0)
			return false;
		for (AppointmentPush o : other) {
			if (o.getId() == appointmentpushId)
				continue;
			if (o.getStatus() == ConstantAppStatus.PUSH_YI_FU_KUAN) {
				ret = true;
			}
		}
		return ret;
	}

	@Override
	public int queryToPayDeposit(int appointmentpushId) {
		int result = 0;
		result = paymentRecordsv.qyrDepositSatus(appointmentpushId);
		if (this.queryOtherPayProjectStatus(appointmentpushId) == true
				&& result == 0){
			result = -1;// 别人支付了
		}
		AppointmentPush ap = this.findById(appointmentpushId);
		if(ap.getRandom()!=null){
			if(ap.getRandom()!=0){
				result = -1;//被取消或者被结束
			}
		}
		return result;
	}

	@Override
	public int queryToPayProject(int appointmentpushId) {
		int result = 0;
		result = paymentRecordsv.qyrJianLiSatus(appointmentpushId);
		if (this.queryOtherPayProjectStatus(appointmentpushId) == true
				&& result == 0){
			result = -1;// 别人支付了
		}
		AppointmentPush ap = this.findById(appointmentpushId);
		if(ap.getRandom()!=null){
			if(ap.getRandom()!=0){
				result = -1;//被取消或者被结束
			}
		}
		return result;
	}

	@Override
	public VIAppointmentPush queryAppPushbn(Integer appointmentPushId) {
		return appointmentPushMapper.queryAppPushbn(appointmentPushId);
	}

}
