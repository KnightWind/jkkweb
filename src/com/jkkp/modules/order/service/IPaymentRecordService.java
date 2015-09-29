package com.jkkp.modules.order.service;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VPaymentRecordApi;
import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.JlService;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.view.VPaymentRecord;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;

public interface IPaymentRecordService extends IService<PaymentRecord, VPaymentRecord, Integer> {

	/**
	 * 保存支付订金记录
	 * 
	 * @param appointmentPushId
	 *            推送单id
	 * @param source
	 *            支付来源，1：微信，2：Android，3：IOS
	 * @param type
	 *            支付方式，1：支付宝，2：易宝, 3 微信支付
	 * @return
	 */
	public PaymentRecord saveDeposit(Integer appointmentPushId, Integer source, Integer type);
	
	/**
	 * 保存支付订金记录
	 * 
	 * @param cardId
	 *            推送单id
	 * @param source
	 *            支付来源，1：微信，2：Android，3：IOS
	 * @param type
	 *            支付方式，1：支付宝，2：易宝, 3 微信支付
	 * @return
	 */
	public PaymentRecord saveDepositByActivity(Integer cardId, Integer source, Integer type,ActivityCardDeals acd);

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
	public PaymentRecord saveWallet(Integer memberId, Integer source, Float amount, Integer type);

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
	public PaymentRecord saveProject(Integer appointmentPushId, Integer source, Integer type);

	PaymentRecord findBySerialCode(String code);

	public PaymentRecord updateStatus(String orderNo, String trade_no, String trade_status);

	public void saveSuccess(PaymentRecord record);

	/**
	 * 查询业务数据
	 * 
	 * @param dataa
	 * @return
	 */
	public Map<String, String> findResult(Map<String, String> data);

	public Map<String, String> validateResult(Integer appointmentPushId, Integer type);
	
	/**
	 * @param uid
	 * @param curpage
	 * @param pageSize
	 * @return VPaymentRecordApi
	 */
	public List<VPaymentRecordApi> qryBillByUid(Map<String, Object> map);
	/**
	 * 查询支付定金状态
	 * @param appointmentpushId 商家推送ID
	 * @return int 0可以支付 1 等待支付结果 2 已经支付
	 */
	public int qyrDepositSatus(int pushid);
	public Float qyrDepositValue(int pushid);
	/**
	 * 查询支付监管款状态
	 * @param appointmentpushId 商家推送ID
	 * @return int  0可以支付 1 等待支付结果 2 已经支付
	 */
	public int qyrJianLiSatus(int pushid);
	
	/**
	 * 支付成功后微信活动卡数据
	 * @param data
	 * @return
	 */
	public Map<String, String> findActiveCardResult(Map<String, String> data);

	/**
	 * 定金退款日志处理
	 * @param pushId
	 * @return
	 */
	public PaymentRecord dealDepositRefundRecord(Map paramMap);
	/**
	 * 工程款退款日志处理
	 * @param pushId
	 * @return
	 */
	public PaymentRecord dealProjectRefundRecord(Map paramMap);
	/**
	 * 更新退款后业务相关数据
	 * @param record
	 */
	public void updateRefundSuccess(PaymentRecord record,Map<String, String> result);
	
	/**
	 * 
	 * @param appointmentPushId
	 * @param source
	 * @param type
	 * @return
	 */
	public PaymentRecord saveBuildingActivityOrder(Map<String,Object> map);
	
	/**
	 * 
	 * @param appointmentPushId
	 * @param source
	 * @param type
	 * @return
	 */
	public PaymentRecord paymentByQrCode(Map<String,Object> map);
	
	/**
	 * 
	 * @param appointmentPushId
	 * @param source
	 * @param type
	 * @return
	 */
	public VPaymentRecordApi paymentDetial(String serialCode);

	/**
	 * 根据pushid查询该push单
	 * @param businessId
	 */
	public VPaymentRecordApi queryDepositPayRecordByPushId(Integer businessId);
	
	
	public  PaymentRecord insertRefundRecord(Integer pushId,
			PaymentRecord payRecord) ;
	
	/**
	 * 支付宝退款 后业务状态处理
	 * @param record
	 * @param result
	 */
	public void updateAliRefundSuccess(PaymentRecord record,
			String result);
	
	
	/**
	 * 保存支付购买监理服务记录
	 * 
	 * @param jlService 购买监理服务
	 * @param source 支付来源，1：H5，2：Android，3：IOS
	 * @param payType 付途径 1：微信端支付 2：Android支付 3：IOS支付 
	 * @param payway 支付类型 1：支付宝 2：易宝 3：支付宝
	 * @return
	 */
	public PaymentRecord saveJlService(JlService jlService, Integer source, int payType, int payway);
	
	/**
	 * 保存信息到流水表
	 * @param paymentRecord
	 * @return paymentRecord 
	 */
	public PaymentRecord savePaymentRecord(Map<String,Object> map);
}
