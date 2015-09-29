package com.jkkp.modules.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.control.common.IdCreator;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.service.interfaces.ISaleActivityService;
import com.jkkp.appapi.modules.mapper.VPaymentRecordApi;
import com.jkkp.client.yeepay.service.YeePayService;
import com.jkkp.client.yeepay.service.YeePayUtil;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.appointment.service.IJlAppointmentService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.model.ItemMember;
import com.jkkp.modules.crowdfunding.model.JlService;
import com.jkkp.modules.member.service.IMoneyBagService;
import com.jkkp.modules.order.mapper.PaymentRecordMapper;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.order.view.VPaymentRecord;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.service.IRefundApplyAuditService;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.model.ActivityRecommend;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.service.IActivityGiftLogService;
import com.jkkp.modules.sale_theme.service.IActivityGiftService;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.service.IActivityRecommendService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.modules.sale_theme.view.VActivityCardDeals;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;
import com.service.IRecommendMobileService;

@Service("paymentRecordService")
public class PaymentRecordServiceImpl extends
		ServiceSupport<PaymentRecord, VPaymentRecord, Integer> implements
		IPaymentRecordService {
	private YeePayService yeePayService = new YeePayService();
	@Autowired
	private IEngineeringsService engineeringsService;
	@Autowired
	private PaymentRecordMapper paymentRecordMapper;
	@Autowired
	private IMoneyBagService moneyBagService;
	@Autowired
	private IAppointmentPushService appointmentPushService;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IJlAppointmentService jlAppointmentService;
	@Autowired
	private IActivityCardService activityCardService;
	@Autowired
	private IActivityCardDealsService activityCardDealsService;
	@Autowired
	private IActivityThemeService activityThemeService;
	@Autowired
	private IActivityGiftLogService activityGiftLogService;
	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	@Autowired
	private IActivityGiftService activityGiftService;
	@Autowired
	private ISaleActivityService saleActivityService;
	@Autowired
	private IActivityRecommendService activityRecommendService;
	@Autowired
	private IActivityJionSignService activityJionSignService;
	@Autowired
	private IBaseinf ib;
	@Autowired
	private IAppointmentPushSV ap;
	@Autowired
	private RefundApplyAuditMapper refundApplyAuditMapper;
	@Autowired	
	IRefundApplyAuditService refundApplyAuditService;
	@Autowired
	private IRecommendMobileService recommendMobileService;
	
	@Override
	protected Mapper<PaymentRecord> getMapper() {
		return paymentRecordMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private PaymentRecord saveRecord(Integer businessId, Integer type,
			float amount, int payType, String title, int payway) {
		PaymentRecord record = new PaymentRecord();
		record.setSerialCode(DateUtil.format(new Date(), "yyyyMMddHHmmss") + getRandomStr(6));
		record.setBusinessId(businessId);
		record.setType(type);
		record.setCreateDate(new Date());
		record.setAmount(amount);
		record.setPayType(payType);
		record.setPayway(payway);
		record.setTitle(title);
		record.setLogType(PaymentRecord.LOG_TYPE_PAY);
		this.save(record);
		return record;
	}
	
	@Override
	public PaymentRecord saveJlService(JlService jlService, Integer source, int payType, int payway) {
		return saveRecord(jlService.getId(),PaymentRecord.TYPE_JL_SERVICE,jlService.getCprice(), payType,"购买监理服务" + jlService.getCtype(),payway);
	}

	/**
	 * 保存记录并且添加一条购物卡明细
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private PaymentRecord saveRecord(Integer businessId, Integer type,
			float amount, int payType, String title, int payway,
			ActivityCardDeals acd) {
		acd.setPayStatus(0);
		acd.setFee(amount);
		acd.setSerialCode(DateUtil.format(new Date(), "yyyyMMddHHmmss")
				+ getRandomStr(6)); // 订单号码,最高支持每秒1万个订单号
		activityCardDealsService.save(acd);
		PaymentRecord record = new PaymentRecord();
		record.setSerialCode(acd.getSerialCode());
		record.setBusinessId(acd.getId());
		record.setType(type);
		record.setCreateDate(new Date());
		record.setAmount(amount);
		record.setPayType(payType);
		record.setPayway(payway);
		record.setTitle(title);
		record.setLogType(PaymentRecord.LOG_TYPE_PAY);
		this.save(record);
		return record;
	}

	/**
	 * 保存支付订金记录
	 * 
	 * @param appointmentPushId
	 *            推送单id
	 * @param source
	 *            支付来源，1：微信，2：Android，3：IOS
	 * @param type
	 *            支付方式，1：支付宝，2：易宝
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PaymentRecord saveDeposit(Integer appointmentPushId, Integer source,
			Integer type) {
		AppointmentPush appointmentPush = appointmentPushService
				.findById(appointmentPushId);
		//add 20150831  已支付不允许再支付
		if(appointmentPush!=null&&appointmentPush.getProjectPayStatus()!=null){
			if(appointmentPush.getProjectPayStatus()==1){
			System.out.print("\r\n Project is Pay \r\n");
			return null;
			}
		}
		//end 
		
		Appointment appointment = appointmentService.findById(appointmentPush
				.getAid());
		String subject = "支付" + appointment.getCommunity() + "订金";
		float amount = CommonUtil.isNull(appointmentPush.getMoney(), 0f); // 读取定金
		return this.saveRecord(appointmentPushId, PaymentRecord.TYPE_DEPOSIT,
				amount, source, subject, type);
	}

	/**
	 * 保存支付活动卡记录
	 * 
	 * @param cardId
	 *            活动卡id
	 * @param source
	 *            支付来源，1：微信，2：Android，3：IOS
	 * @param type
	 *            支付方式，1：支付宝，2：易宝
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PaymentRecord saveDepositByActivity(Integer cardId, Integer source,
			Integer type, ActivityCardDeals acd) {
		ActivityCard card = activityCardService.findById(cardId);
		ActivityTheme aTheme = activityThemeService.findById(card
				.getActivityId());
		String subject = "支付" + aTheme.getTitle() + "活动卡款";
		float amount = CommonUtil.isNull(card.getPrice(), 0f); // 活动卡金额
		return this.saveRecord(cardId, PaymentRecord.TYPE_ACTIVITYCARD, amount,
				source, subject, type, acd);
	}

	/**
	 * 保存充值记录
	 * 
	 * @param memberId
	 *            会员id
	 * @param source
	 *            支付来源，1：微信，2：Android，3：IOS
	 * @param amount
	 *            充值金额
	 * @param type
	 *            支付方式，1：支付宝，2：易宝
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PaymentRecord saveWallet(Integer memberId, Integer source,
			Float amount, Integer type) {
		String subject = "钱包充值";
		return this.saveRecord(memberId, PaymentRecord.TYPE_WALLET, amount,
				source, subject, type);
	}

	/**
	 * 保存支付监管款记录
	 * 
	 * @param appointmentPushId
	 *            推送单id
	 * @param source
	 *            支付来源，1：微信，2：Android，3：IOS
	 * @param type
	 *            支付方式，1：支付宝，2：易宝
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PaymentRecord saveProject(Integer appointmentPushId, Integer source,
			Integer type) {
		AppointmentPush appointmentPush = appointmentPushService
				.findById(appointmentPushId);
		//add 20150831  已支付不允许再支付
		if(appointmentPush!=null&&appointmentPush.getProjectPayStatus()!=null){
			if(appointmentPush.getProjectPayStatus()==1){
			System.out.print("\r\n Project is Pay \r\n");
			return null;
			}
		}
		//end
		
		float amount = appointmentPushService
				.calculatePayAmount(appointmentPush);
		if (amount <= 0) { // 支付金额小于等于0，表示不需要支付
			appointmentPushService.saveToMoneyBag(appointmentPush, -amount); // 多出来的钱
			return null;
		}
		String subject = "支付监管款";
		return this.saveRecord(appointmentPushId, PaymentRecord.TYPE_JIANLI,
				amount, source, subject, type);
	}

	@Override
	public PaymentRecord findBySerialCode(String code) {
		List<PaymentRecord> recordList = this.selectByProperty("serialCode",
				code);
		return recordList != null && !recordList.isEmpty() ? recordList.get(0)
				: null;
	}

	public PaymentRecord findByTradeNo(String tradeNo) {
		List<PaymentRecord> recordList = this.selectByProperty("tradeNo",
				tradeNo);
		return recordList != null && !recordList.isEmpty() ? recordList.get(0)
				: null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PaymentRecord updateStatus(String orderNo, String trade_no,
			String trade_status) {
		PaymentRecord record = this.findBySerialCode(orderNo);
		record.setTradeNo(trade_no);
		record.setStatus(trade_status);
		record.setNotifyTime(new Date());// add 20150815
		this.update(record);
		return record;
	}

	/**
	 * 支付成功后更新相关业务数据
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSuccess(PaymentRecord record) {
		if (record.getType() == PaymentRecord.TYPE_WALLET) { // 充值
			moneyBagService.saveMoneyBag(record);
		} else if (record.getType() == PaymentRecord.TYPE_DEPOSIT) { // 订金,更新推送单状态为已付定金
			AppointmentPush appointmentPush = appointmentPushService
					.findById(record.getBusinessId()); // 推送单
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_FU_DINGJIN);
			appointmentPush.setDepositPayStatus(1);// 定金支付状态：已付款
			appointmentPush.setDepositPayTime(record.getCreateDate());// 定金支付时间
			appointmentPush.setDepositPayRecId(record.getRecordId());// 支付记录id
			appointmentPushService.update(appointmentPush);
		} else if (record.getType() == PaymentRecord.TYPE_JIANLI) { // 支付监理款，更新推送单状态为已付款，同时生成工程单
			AppointmentPush appointmentPush = appointmentPushService
					.findById(record.getBusinessId()); // 推送单
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_FU_KUAN);
			appointmentPush.setProjectPayStatus(1);
			appointmentPush.setProjectPayTime(record.getCreateDate());
			appointmentPush.setProjectPayRecId(record.getRecordId());
			appointmentPushService.update(appointmentPush);
			engineeringsService.addEngineerings(record.getBusinessId());
		} else if (record.getType() == PaymentRecord.TYPE_ACTIVITYCARD) {// 支付微信引流活动卡
			ActivityCardDeals acd = activityCardDealsService.findById(record.getBusinessId());
			ActivityGiftLog ag = new ActivityGiftLog();
			ag.setCreateTime(new Date());
			ag.setGiftId(acd.getGiftId());
			ag.setUpdateTime(new Date());
			ag.setPhone(acd.getPhone());
			ag.setUsed(false);
			acd.setPayStatus(1);
			saleActivityService.saveCardAndGift(acd, ag);
			saveActivityRecommend(acd.getPhone());
		} else if (record.getType() == PaymentRecord.TYPE_ACTIVITY_DJ) {
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", record.getBusinessId());
			actiivityOrder.setId(record.getBusinessId());
			actiivityOrder.setMoneyState(1);
			actiivityOrder.setCreateDate(new Date());
			activtyOrderMapper.updateOrderStatus(actiivityOrder);
			insertJoinMember(map);
		} else if (record.getType() == PaymentRecord.TYPE_ACTIVITY_WK) {
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", record.getBusinessId());
			actiivityOrder.setId(record.getBusinessId());
			actiivityOrder.setMoneyState(2);
			actiivityOrder.setExpressState(0);
			actiivityOrder.setCreateDate(new Date());
			activtyOrderMapper.updateOrderStatus(actiivityOrder);
			
			insertJoinMember(map);
		} else if (record.getType() == PaymentRecord.TYPE_ACTIVITY_DJWK) {
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", record.getBusinessId());
			
			actiivityOrder.setId(record.getBusinessId());
			actiivityOrder.setMoneyState(2);
			actiivityOrder.setExpressState(0);
			actiivityOrder.setCreateDate(new Date());
			activtyOrderMapper.updateOrderStatus(actiivityOrder);
			insertJoinMember(map);
		} else if (record.getType() == PaymentRecord.TYPE_QR_DJ_CODE) {
			Map<String, Object> map = new HashMap<String, Object>();
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			
			map.put("id", record.getBusinessId());
			actiivityOrder.setId(record.getBusinessId());
			actiivityOrder.setMoneyState(1);
			actiivityOrder.setUseState(0);
			actiivityOrder.setCreateDate(new Date());
			activtyOrderMapper.updateOrderStatus(actiivityOrder);
			insertJoinMember(map);
		} else if (record.getType() == PaymentRecord.TYPE_QR_WK_CODE) {
			Map<String, Object> map = new HashMap<String, Object>();
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			
			map.put("id", record.getBusinessId());
			actiivityOrder = activtyOrderMapper.getAcitvityOrderByParam(map);
			actiivityOrder.setId(record.getBusinessId());
			actiivityOrder.setMoneyState(2);
			actiivityOrder.setExpressState(0);
			actiivityOrder.setCreateDate(new Date());
			activtyOrderMapper.updateOrderStatus(actiivityOrder);
			insertJoinMember(map);
		}
	}

	private void insertJoinMember(Map<String, Object> map) {
		Map<String, Object> resultMember;
		ItemMember itemMember = new ItemMember();
		Map<String, Object> resultMap = activtyOrderMapper.getOrderInfo(map);
		
		resultMember = activtyOrderMapper.getAcitivityJoinMember(map);
		if(resultMember==null){
			itemMember.setItemId(Integer.parseInt(resultMap.get("item_id").toString()));
			itemMember.setMemberId(Integer.parseInt(resultMap.get("member_id").toString()));
			activtyOrderMapper.insertActItemMember(itemMember);
		}
	}
	
	

	/**
	 * 更新微信引流购卡后业务数据
	 * 
	 * @param phone
	 */
	public void saveActivityRecommend(String phone) {
		ActivityRecommend arBean = new ActivityRecommend();
		arBean.setToPhone(phone);
		arBean.setStatus(0);
		List<ActivityRecommend> arList = activityRecommendService
				.select(arBean);
		if (arList.size() > 0) {
			ActivityRecommend ar = arList.get(0);
			ar.setStatus(1);
			ar.setSuccessTime(new Date());
			// 更新推送表状态
			activityRecommendService.update(ar);

			// 判断推送者是否已经推送足5人，如果足够5人，买卡处理
			ActivityRecommend fromPhoneBean = new ActivityRecommend();
			fromPhoneBean.setFromPhone(ar.getFromPhone());
			fromPhoneBean.setStatus(1);
			List<ActivityRecommend> fromPhoneCount = activityRecommendService
					.select(fromPhoneBean);
			if (fromPhoneCount.size() == 2) {
				String toPhone = fromPhoneCount.get(0).getFromPhone();
				ActivityJionSign ajs = new ActivityJionSign();
				ajs.setPhone(toPhone);
				List<ActivityJionSign> list = activityJionSignService
						.select(ajs);
				if (list.size() > 0) {
					ActivityJionSign sign = list.get(0);
					sign.setVip(1);
					// 更新成为vip
					activityJionSignService.update(sign);
				}
				String content = Sysconfig.CONFIG_PARAM
						.get(ConfigConstant.SMS_JIAKEKE_VIP_SUCC);
				SendMsgUtil.send(toPhone, content);
			}
		}
	}

	/**
	 * 查询业务数据
	 * 
	 * @param data
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Map<String, String> findResult(Map<String, String> data) {
		Map<String, String> result = new HashMap<String, String>();
		PaymentRecord record = this.findBySerialCode(data.get("out_trade_no"));
		if (record != null) {
			if (record.getType() == PaymentRecord.TYPE_JIANLI) { // 支付监理款
				AppointmentPush appointmentPush = appointmentPushService
						.findById(record.getBusinessId()); // 推送单
				appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_FU_KUAN);
				appointmentPushService.update(appointmentPush);
				result.putAll(this.findProjectSheet(record));
			}
		}
		return result;
	}

	/**
	 * 查詢工程單
	 * 
	 * @param record
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private Map<String, String> findProjectSheet(PaymentRecord record) {
		Integer engineerId;
		List<Engineerings> engineerList = engineeringsService.selectByProperty(
				"appPushId", record.getBusinessId()); // 查询工程单
		if (engineerList != null && !engineerList.isEmpty()) {
			engineerId = engineerList.get(0).getId();
		} else {
			Engineerings engineerings = engineeringsService
					.addEngineerings(record.getBusinessId());
			engineerId = engineerings.getId();
		}
		List<Jlappointment> jlappointments = jlAppointmentService
				.selectByProperty("gcdId", engineerId);
		Map<String, String> result = new HashMap<String, String>();
		result.put("jlAppointmentId",
				String.valueOf(jlappointments.get(0).getId()));
		result.put("projectId", String.valueOf(engineerId));
		return result;
	}

	@Override
	public Map<String, String> validateResult(Integer appointmentPushId,
			Integer type) {
		PaymentRecord paymentRecord = null;
		if (type != 3
				|| (paymentRecord = this.findByBusinessAndType(
						appointmentPushId, type, PaymentRecord.PAY_TYPE_YEEPAY)) == null) {
			return null;
		}
		if (!StringUtils.isNotEmpty(paymentRecord.getStatus())) {
			try {
				Thread.sleep(1000);
				paymentRecord = this.findByBusinessAndType(appointmentPushId,
						type, PaymentRecord.PAY_TYPE_YEEPAY);
				return this.findPaymentResult(paymentRecord);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return this.findPaymentResult(paymentRecord);
		}
	}

	private Map<String, String> findPaymentResult(PaymentRecord paymentRecord) {
		if (!YeePayUtil.INVOKE_SUCCESS.equals(paymentRecord.getStatus())) {
			return null;
		}
		return this.findProjectSheet(paymentRecord);
	}

	PaymentRecord findByBusinessAndType(Integer businessId, Integer type,
			Integer payway) {
		List<PaymentRecord> paymentList = this.selectByProperty(new String[] {
				"businessId", "type", "payway" }, new Object[] { businessId,
				type, payway });
		return paymentList != null && !paymentList.isEmpty() ? paymentList
				.get(0) : null;
	}

	@Override
	public List<VPaymentRecordApi> qryBillByUid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<VPaymentRecordApi> vprlist = paymentRecordMapper.qryBillByUid(map);
		if (vprlist.size() > 0) {
			for (VPaymentRecordApi vPaymentRecordApi : vprlist) {
				vPaymentRecordApi = paymentRecordMakeInf(vPaymentRecordApi);
			}
		}
		return vprlist;
	}

	@Override
	public int qyrDepositSatus(int pushid) {
		// TODO Auto-generated method stub
		List<PaymentRecord> paydeposit = this.selectByProperty(new String[] {
				"businessId", "type" }, new Object[] { pushid, 1 });
		for (PaymentRecord v : paydeposit) {
			if (v.getStatus() != null) {
				if (v.getStatus().contains("TRADE_FINISHED")
						|| v.getStatus().contains("TRADE_SUCCESS")
						|| v.getStatus().contains("1"))
					return 2;// 支付定金成功
			}
		}
		for (PaymentRecord v : paydeposit) {
			if (v.getTradeNo() != null) {
				return 1;// 等待中
			}
		}
		return 0;
	}

	@Override
	public int qyrJianLiSatus(int pushid) {
		// TODO Auto-generated method stub
		List<PaymentRecord> payjianli = this.selectByProperty(new String[] {
				"businessId", "type" }, new Object[] { pushid, 3 });
		for (PaymentRecord v : payjianli) {
			if (v.getStatus() != null) {
				if (v.getStatus().contains("TRADE_FINISHED")
						|| v.getStatus().contains("TRADE_SUCCESS")
						|| v.getStatus().contains("1"))
					return 2;// 支付监理成功
			}
		}
		for (PaymentRecord v : payjianli) {
			if (v.getTradeNo() != null) {
				return 1;// 等待中
			}
		}
		return 0;
	}

	@Override
	public Float qyrDepositValue(int pushid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<PaymentRecord> paydeposit = this.selectByProperty(new String[] {
				"businessId", "type" }, new Object[] { pushid, 1 });
		for (PaymentRecord v : paydeposit) {
			if (v.getStatus() != null) {
				if (v.getStatus().contains("TRADE_FINISHED")
						|| v.getStatus().contains("TRADE_SUCCESS")
						|| v.getStatus().contains("1"))
					return v.getAmount();// 支付定金成功
			}
		}
		return (float) 0.0;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Map<String, String> findActiveCardResult(Map<String, String> data) {
		Map<String, String> result = new HashMap<String, String>();
		PaymentRecord record = this.findBySerialCode(data.get("out_trade_no"));
		if (record != null) {
			VActivityCardDeals vacd = activityCardDealsService.findById(
					record.getBusinessId(),
					AttachmentConstant.WX_ACTIVITY_VOUCHER);
			result.put("cardName", vacd.getCardName());
			result.put("phone", vacd.getPhone());
			result.put("paymenType", String.valueOf(vacd.getPaymenType()));
			result.put("giftName", vacd.getGiftName());
			result.put("cardId", String.valueOf(vacd.getId()));
			result.put("giftId", String.valueOf(vacd.getGiftId()));
		}
		return result;
	}

	/**
	 * 定金退款 appointmentPushId reason退款理由 title退款日志标题
	 */
	@Override
	public PaymentRecord dealDepositRefundRecord(Map paramMap) {
		Integer pushId = (Integer)paramMap.get("appointmentPushId");
		String reason = (String) paramMap.get("reason");// 退款理由
		AppointmentPush appointmentPush = appointmentPushService
				.findById(pushId);
		Appointment appointment = appointmentService.findById(appointmentPush
				.getAid());
		PaymentRecord payRecord = null;
		// 找到支付的那一条记录 获取相关信息
		payRecord = this.findById(appointmentPush.getDepositPayRecId());
		if (payRecord == null) {
			return null;
		}
		payRecord.setTitle("定金退款");// 重置标题
		return insertRefundRecord(pushId, payRecord);
	}

	/**
	 * 工程款退款 插入日志 appointmentPushId reason退款理由 title退款日志标题
	 */
	@Override
	public PaymentRecord dealProjectRefundRecord(Map paramMap) {
		Integer pushId = CommonUtil.stringToInteger((String) paramMap
				.get("appointmentPushId"));
		String reason = (String) paramMap.get("reason");// 退款理由
		AppointmentPush appointmentPush = appointmentPushService
				.findById(pushId);
		Appointment appointment = appointmentService.findById(appointmentPush
				.getAid());
		PaymentRecord payRecord = null;
		// 找到支付的那一条记录 获取相关信息
		payRecord = this.findById(appointmentPush.getProjectPayRecId());
		if (payRecord == null) {
			return null;
		}
		payRecord.setTitle("工程款退款");// 重置标题
		// if(payRecord==null){
		// //后续 将以push表中recordid来查, 这段代码将作废
		// List<PaymentRecord> paydeposit=this.selectByProperty(new
		// String[]{"businessId","type"},new
		// Object[]{pushId,PaymentRecord.TYPE_DEPOSIT});
		// for (PaymentRecord v : paydeposit) {
		// //在某种异常的情况下，可能有两条成功支付的记录，后续需要考虑这种异常情况
		// if(v.getStatus()!=null){
		// if(v.getStatus().contains("TRADE_FINISHED")||v.getStatus().contains("TRADE_SUCCESS")||v.getStatus().contains("1"))
		// payRecord = v;//返回支付成功的那一条
		// }
		// }
		// }
		return insertRefundRecord(pushId, payRecord);
	}

	/**
	 * 易宝
	 * notify
	 * 退款成功后执行 更新退款后业务相关数据
	 * 
	 * @param record
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateRefundSuccess(PaymentRecord record,
			Map<String, String> result) {
		String returnCode = result.get("code");
		String refundexternalid = result.get("refundexternalid");
		if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
			AppointmentPush appointmentPush = appointmentPushService
					.findById(record.getBusinessId()); // 推送单
			// 推送单的状态应该更新到哪个状态？
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_LIANG_FANG);// 跳到已量房 可以重新发起支付定金
			appointmentPush.setDepositPayStatus(2);// 定金支付状态：已退款
			appointmentPush.setDepositRefundTime(record.getCreateDate());// 定金退款时间
			appointmentPush.setDepositRefundRecId(record.getRecordId());// 退款记录id
			appointmentPushService.update(appointmentPush);

			Appointment appointment = appointmentService
					.findById(appointmentPush.getAid()); // 需求单
			// 预约单的状态应该更新到哪个状态？
			appointment.setStatus(ConstantAppStatus.DAI_QIAN_YUE);// 回退到待签约状态
			appointmentService.update(appointment);
		}
		record.setBatchNo(refundexternalid);
		record.setStatus(returnCode);
		// 更新退款日志信息
		this.update(record);

	}

	
	/**
	 * 支付宝
	 * notify
	 * 退款成功后执行 更新退款后业务相关数据
	 * 
	 * @param record
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAliRefundSuccess(PaymentRecord record,
			String result) {
		//更新业务状态
		if ("SUCCESS".equals(result)) {
			AppointmentPush appointmentPush = appointmentPushService
					.findById(record.getBusinessId()); // 推送单
			// 推送单的状态应该更新到哪个状态？
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_LIANG_FANG);// 跳到已量房 可以重新发起支付定金
			appointmentPush.setDepositPayStatus(2);// 定金支付状态：已退款
			appointmentPush.setDepositRefundTime(record.getCreateDate());// 定金退款时间
			appointmentPush.setDepositRefundRecId(record.getRecordId());// 退款记录id
			appointmentPushService.update(appointmentPush);

			Appointment appointment = appointmentService
					.findById(appointmentPush.getAid()); // 需求单
			// 预约单的状态应该更新到哪个状态？
			appointment.setStatus(ConstantAppStatus.DAI_QIAN_YUE);// 回退到待签约状态
			appointmentService.update(appointment);
			
			//更新退款申请单的状态
			//退款完成
			List<RefundApplyAudit> applyList = refundApplyAuditService.selectByProperty("refund_record_id", record.getRecordId());
			if(applyList!=null&&applyList.size()>0){
				applyList.get(0).setStatus(3);
				refundApplyAuditService.update(applyList.get(0));
			}
		}
		record.setNotifyTime(new Date());
		record.setStatus(result);
		// 更新退款日志信息
		this.update(record);

	}
	/**
	 * 插入退款记录
	 * 
	 * @param pushId
	 * @param payRecord
	 * @return
	 */
	public  PaymentRecord insertRefundRecord(Integer pushId,
			PaymentRecord payRecord) {

		if (payRecord == null) {
			return null;
		}
		// 插入一条退款日志记录
		PaymentRecord refundRecord = new PaymentRecord();
		refundRecord.setSerialCode(payRecord.getSerialCode());// 原来的商户订单号
		refundRecord.setAmount(payRecord.getAmount()); // 原来的订单金额
		refundRecord.setBusinessId(payRecord.getBusinessId());// pushid
		refundRecord.setType(payRecord.getType());// 原来的款项类型
		refundRecord.setCreateDate(new Date());
		refundRecord.setPayType(payRecord.getPayType());// 原来的支付途径 1：微信端支付
														// 2：Android支付 3：IOS支付
		refundRecord.setTradeNo(payRecord.getTradeNo());
		refundRecord.setLogType(PaymentRecord.LOG_TYPE_REFUND);// 日志类型
		refundRecord.setPayway(payRecord.getPayway());// 原来的支付方式 支付宝or易宝
		refundRecord.setTitle(payRecord.getTitle());
		//支付宝
		if(refundRecord.getPayway()!=null&&refundRecord.getPayway()==1){
			refundRecord.setBatchNo(IdCreator.getSeqId());
		}else if(refundRecord.getPayway()!=null&&refundRecord.getPayway()==2){
			refundRecord.setRequestid(IdCreator.getSeqId());
		}
		
		paymentRecordMapper.insert(refundRecord);

		return refundRecord;
	}

	/**
	 * 根据tradeNo跟tradeStatus判断是否支付成功
	 * 
	 * @param tradeNo
	 * @param tradeStatus
	 * @return
	 */
	public PaymentRecord findByTradeNoAndStatus(String tradeNo,
			String tradeStatus) {
		PaymentRecord pay = new PaymentRecord();
		pay.setStatus(tradeStatus);
		pay.setTradeNo(tradeNo);
		List<PaymentRecord> recordList = this.select(pay);
		return recordList != null && !recordList.isEmpty() ? recordList.get(0)
				: null;
	}

	@Override
	public PaymentRecord saveBuildingActivityOrder(Map<String, Object> map) {
		float amount = 0;
		String subject = "";
		int recordType = 0;
		int source = CommonUtil.stringToInteger((String) map.get("source"));
		int paymentModel = CommonUtil.stringToInteger((String) map.get("paymentModel"));
		String type = (String) map.get("type"); //1  定金 2 尾款或定金和尾款
		float redFee = Float.valueOf(map.get("redFee").toString());
		float discountfee = 0; 

		ActiivityOrder actiivityOrder = activtyOrderMapper.getAcitvityOrderByParam(map);
		Map<String, Object> activityMap = activtyOrderMapper.getActivityOrder(map);
		int num = (Integer) activityMap.get("item_num"); // 商品数量
		double activityPrice = CommonUtil.stringToDoubles(String.valueOf(activityMap.get("activityPrice"))); // 当前价格，即活动众筹价格
		double deposit = actiivityOrder.getDeposit(); // 定金
        int moneyState = actiivityOrder.getMoneyState();
        if(map.get("mobile")!=null){
        	boolean isdiscount = recommendMobileService.isvalidate(String.valueOf(map.get("mobile")));
        	if(isdiscount){
        		discountfee =Math.round((Float.valueOf(activityMap.get("lowPrice").toString()))*(float) 0.02*100)/100F ;
        	}
        }
		
		if ("1".equals(type)) {
			amount = CommonUtil.isNull((float) deposit- redFee, 0f); // 读取定金
			recordType = PaymentRecord.TYPE_ACTIVITY_DJ;
			subject = "支付" + amount + "订金";
		} else if ("2".equals(type)) {
			if(moneyState == 1){
				amount = CommonUtil.isNull((float) (activityPrice * num - deposit - redFee - discountfee),0f); // 读取定金
				subject = "支付" + amount + "尾款";
				
				ActiivityOrder actOrder = new ActiivityOrder();
				actOrder.setId(actiivityOrder.getId());
				actOrder.setRetainage(activityPrice * num - deposit);
				actOrder.setOrderPrice(activityPrice * num);
				activtyOrderMapper.updateOrderStatus(actOrder);
			}else{
				amount = CommonUtil.isNull((float) (activityPrice * num - redFee -discountfee),0f); // 读取定金
				subject = "支付" + amount + "定金和尾款"; 
			}
			recordType = PaymentRecord.TYPE_ACTIVITY_WK;
		}
		return this.saveRecord(actiivityOrder, recordType, amount, source,subject, paymentModel, redFee);
	}

	public String getRandomStr(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append((int) (10 * Math.random()));
		}
		return sb.toString();
	}

	@Override
	public PaymentRecord paymentByQrCode(Map<String, Object> map) {
		if (map.get("orderCode") == null) {
			return null;
		}
		float amount = 0;
		String subject = "";
		int recordType = 0;
		int source = CommonUtil.stringToInteger((String) map.get("source"));//1：微信，2：Android，3：IOS
		int type = CommonUtil.stringToInteger((String) map.get("type")); //1定金2全款
		int payway = Integer.valueOf(String.valueOf(map.get("pay_type"))) ; //1支付宝 2易宝
		float redFee = Float.valueOf(map.get("redFee").toString()); //红包金额

		ActiivityOrder actiivityOrder = activtyOrderMapper.getAcitvityOrderByParam(map);
		double deposit = actiivityOrder.getDeposit(); // 待付款定金或全款
		System.out.println("deposit");
		if (actiivityOrder.getMoneyState() == null || actiivityOrder.getMoneyState() == 0) {
			String deposit_id = (String) map.get("deposit_id");
			if (StringUtils.isNotBlank(deposit_id)) {
				ActiivityOrder actOrder = new ActiivityOrder();
				actOrder.setOrderCode(deposit_id);
				actOrder.setUseState(0);
				List<ActiivityOrder> actOrderlist = activtyOrderMapper.select(actOrder);
				if (actOrderlist != null && !actOrderlist.isEmpty()) {
					actOrder = actOrderlist.get(0);
					ActiivityOrder containDepost = new ActiivityOrder();
					containDepost.setDepositId(deposit_id);
					List<ActiivityOrder> containList = activtyOrderMapper.select(containDepost);
					if (containList == null || containList.isEmpty()) { // 没有订单持有此定金ID
						double paied = actOrder.getDeposit(); // 已支付定金
						deposit -= paied;// 待付款
						actOrder.setUseState(1);
						activtyOrderMapper.updateByPrimaryKey(actOrder);
						
						if(deposit <= 0) { //使用定金支付完成
							actiivityOrder.setMoneyState(1);
							actiivityOrder.setPaymentModel("10"); // 定金付款
							activtyOrderMapper.updateByPrimaryKey(actiivityOrder);
						}
					}
				}
			}
			if (deposit > 0) { // 待付款大于0，需要发起在线支付请求
				actiivityOrder.setDeposit(Double.valueOf(new java.text.DecimalFormat("#.00").format(deposit)));
				actiivityOrder.setDepositId(deposit_id);
				if (type == 1) {
					subject = "支付" + (actiivityOrder.getDeposit() - redFee) + "订金";
					amount = CommonUtil.isNull((float) deposit, 0f); // 读取定金
					recordType = PaymentRecord.TYPE_QR_DJ_CODE;
				} else if (type == 2) {
					subject = "支付" + (actiivityOrder.getDeposit() - redFee) + "全款";
					amount = CommonUtil.isNull((float) deposit, 0f); // 读取全款
					recordType = PaymentRecord.TYPE_QR_WK_CODE;
				}
				return this.saveRecord(actiivityOrder, recordType, amount,source, subject, payway, redFee);
			}
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private PaymentRecord saveRecord(ActiivityOrder actiivityOrder,
			Integer type, Float amount, Integer payType, String title, Integer payway,Float redFee) {
		if(amount <=0) {
			throw new RuntimeException("交易金额必须大于0！！actiivityOrder="+actiivityOrder);
		}
		PaymentRecord record = new PaymentRecord();
		record.setBusinessId(actiivityOrder.getId());
		record.setType(type);
		record.setCreateDate(new Date());
		record.setAmount(amount);
		record.setPayType(payType);
		record.setPayway(payway);
		record.setTitle(title);
		record.setSerialCode(DateUtil.format(new Date(), "yyyyMMddHHmmss") + getRandomStr(6));
		record.setLogType(PaymentRecord.LOG_TYPE_PAY);
		record.setDicountType(1);
		record.setDicountFee(redFee);
		this.save(record);
		return record;
	}

	public VPaymentRecordApi paymentRecordMakeInf(VPaymentRecordApi v) {
		String head = "";
		String name = "";
		if (v.getType() == 1 || v.getType() == 3) {
			AppointmentPush ap1 = ap.findById(v.getBusinessId());
			head = ib.getHeadimg(ap1.getSpId(),
					AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			name = ib.getName(ap1.getSpId(),
					AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		}
		if (v.getType() == 2) {
			head = ib.getHeadimg(v.getBusinessId(),
					AttachmentConstant.MEMBER_TYPE);
			name = ib
					.getName(v.getBusinessId(), AttachmentConstant.MEMBER_TYPE);
			v.setImgurl(head);
		}
		v.setDetialimgString(head);
		v.setPaynameLongString(name);

		if (v.getStatus() == null) {
			v.setPayresultString("交易失败");
			v.setPaymentresult(0);
		} else if (v.getStatus().contains("TRADE_FINISHED")
				|| v.getStatus().contains("TRADE_SUCCESS")
				|| v.getStatus().contains("1")) {
			v.setPayresultString("交易成功");
			v.setPaymentresult(1);
		} else if (v.getStatus().contains("WAIT_BUYER_PAY")) {
			v.setPayresultString("等待支付");
			v.setPaymentresult(2);
		} else if (v.getStatus().contains("TRADE_CLOSED")) {
			v.setPayresultString("交易关闭");
			v.setPaymentresult(3);
		}
		return v;
	}

	@Override
	public VPaymentRecordApi paymentDetial(String serialCode) {
		List<PaymentRecord> pr = this.selectByProperty("serialCode",
				serialCode, "serialCode", true);
		if (pr.size() <= 0)
			return null;
		VPaymentRecordApi vpr = new VPaymentRecordApi();
		try {
			BaseTools.CopyBean(pr.get(0), vpr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("属性复制失败:" + e.getMessage());
		}
		// TODO Auto-generated method stub

		return paymentRecordMakeInf(vpr);
	}

	@Override
	public VPaymentRecordApi queryDepositPayRecordByPushId(Integer pushId) {
		return paymentRecordMapper.queryDepositPayRecordByPushId(pushId);
	}

	@Override
	public PaymentRecord savePaymentRecord(Map<String,Object> map) {
		boolean finalPayment = Boolean.valueOf(map.get("finalPayment").toString());
		int moneyState = Integer.valueOf(map.get("moneyState").toString());
		String title = "";
		
		PaymentRecord record = new PaymentRecord();
		record.setBusinessId(Integer.valueOf(map.get("orderId").toString()));
		if(finalPayment){
			record.setType(PaymentRecord.TYPE_QR_WK_CODE);
			title = "扫码支付全款";
		}else{
			if(moneyState == 0){
				record.setType(PaymentRecord.TYPE_QR_DJ_CODE);
				title = "扫码支付定金";
			}
			if(moneyState == 1){
				record.setType(PaymentRecord.TYPE_QR_WK_CODE);
				title = "扫码支付全款";
			}
		}
		record.setCreateDate(new Date());
		record.setAmount((float) 0.0);
		record.setPayType(4);
		record.setPayway(3);
		record.setTitle(title);
		record.setSerialCode(DateUtil.format(new Date(), "yyyyMMddHHmmss") + getRandomStr(6));
		record.setLogType(PaymentRecord.LOG_TYPE_PAY);
		record.setDicountType(1); //扫码支付优惠
		record.setDicountFee(Float.valueOf(map.get("redFee").toString()));
		this.save(record);
		return record;
	}
}
