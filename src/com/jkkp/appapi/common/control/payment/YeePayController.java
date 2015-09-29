package com.jkkp.appapi.common.control.payment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.control.common.IdCreator;
import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.client.yeepay.api.ZGTService;
import com.jkkp.client.yeepay.constant.YeePayCode;
import com.jkkp.client.yeepay.service.YeePayService;
import com.jkkp.client.yeepay.service.YeePayUtil;
import com.jkkp.common.BaseController;
import com.jkkp.modules.member.model.MemberBankCard;
import com.jkkp.modules.member.service.IMemberBankCardService;
import com.jkkp.modules.member.service.IMgatheringService;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class YeePayController extends BaseController {

	private YeePayService yeePayService = new YeePayService();
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired
	private IMgatheringService mgatheringService;
	@Autowired
	private IMemberBankCardService memberBankCardService;
	
	/**
	 * 支付订金
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_deposit.do")
	public Object deposit(@RequestParam String json) {
		try {
			System.out.println("<<<    yeepay_deposit.do    >>>");
			System.out.println(String.format("<<<    json=%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer pushId = CommonUtil.stringToInteger(data.get("appointmentId")); // 推送单id
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveDeposit(pushId, source, PaymentRecord.PAY_TYPE_YEEPAY);
			return this.createResponseObject(record, data.get("returnUrl"));
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
	}

	/**
	 * 钱包充值
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_wallet.do")
	public Object wallet(@RequestParam String json) {
		try {
			System.out.println("<<<    yeepay_wallet.do    >>>");
			System.out.println(String.format("<<<    json=%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer memberId = CommonUtil.stringToInteger(data.get("memberId"));
			if (memberId == null) {
				return new ApiResponse<String>(false, "memberId参数不能为空");
			}
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveWallet(memberId, source,
					CommonUtil.stringToFloat(data.get("amount")), PaymentRecord.PAY_TYPE_YEEPAY);
			return this.createResponseObject(record, data.get("returnUrl"));
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
	}

	/**
	 * 支付监管款
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_project.do")
	public Object project(@RequestParam String json) {
		try {
			System.out.println("<<<    yeepay_project.do    >>>");
			System.out.println(String.format("<<<    json=%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer appointmentPushId = CommonUtil.stringToInteger(data.get("projectId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveProject(appointmentPushId, source,
					PaymentRecord.PAY_TYPE_YEEPAY);
			if (record == null) { // 没有支付记录，说明不需要支付
				return new ApiResponse<String>(false, "监管款小于等于0，不需要支付");
			}
			return this.createResponseObject(record, data.get("returnUrl"));
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
	}

	private Object createResponseObject(PaymentRecord record, String returnUrl) {
		Map<String, String> result = yeePayService.payment(record, returnUrl);
		String returnCode = result.get("code");
		if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
			return new ApiResponse<String>(result.get("payurl"));
		} else {
			return new ApiResponse<String>(false, YeePayCode.getErrorMessage(returnCode));
		}
	}

	/**
	 * 易宝通知接口
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/yeepay_notify.do")
	public void notify(HttpServletRequest request) {
		try {
			System.out.println(String.format("<<<    yeepay_notify.do data:%s    >>>", request.getParameter("data")));
			Map<String, String> result = ZGTService.getCallbackData(request.getParameter("data"));
			String orderNo = result.get("requestid"); // 商户订单号
			String trade_no = result.get("externalid"); // 易宝交易流水号
			String trade_status = result.get("code"); // 返回码
			PaymentRecord record = paymentRecordService.updateStatus(orderNo, trade_no, trade_status);

			if (YeePayUtil.INVOKE_SUCCESS.equals(trade_status)) {
				paymentRecordService.saveSuccess(record);
				mgatheringService.saveRecord(record);
			}
		} catch (Exception e) {
			logger.error("易宝支付通知接口发生异常", e);
		}
	}

	@ResponseBody
	@RequestMapping("/yeepay_verify.do")
	public Object verify(@RequestParam String json) {
		Map<String, String> data = CommonUtil.jsonToMap(json);
		Integer appointmentPushId = CommonUtil.stringToInteger(data.get("appointmentPushId"));
		Integer type = CommonUtil.stringToInteger(data.get("type"));
		return new ApiResponse<Map<String, String>>(paymentRecordService.validateResult(appointmentPushId, type));
	}

	/**
	 * 支付订金
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_app_deposit.do")
	public Object appDeposit(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    yeepay_app_deposit.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer pushId = CommonUtil.stringToInteger(data.get("appointmentId")); // 推送单id
			int source = CommonUtil.stringToInteger(data.get("type"));
			Integer cardId = CommonUtil.stringToInteger(data.get("cardid"));
			PaymentRecord record = paymentRecordService.saveDeposit(pushId, source, PaymentRecord.PAY_TYPE_YEEPAY);
			return this.createResponseObject(record, cardId, data.get("returnUrl"));
		} catch (Exception e) {
			logger.error("创建APP支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建APP支付链接发生异常");
		}
	}

	/**
	 * 钱包充值
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_app_wallet.do")
	public Object appWallet(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    yeepay_app_wallet.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer memberId = CommonUtil.stringToInteger(data.get("memberId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			Integer cardId = CommonUtil.stringToInteger(data.get("cardid"));
			PaymentRecord record = paymentRecordService.saveWallet(memberId, source,
					CommonUtil.stringToFloat(data.get("amount")), PaymentRecord.PAY_TYPE_YEEPAY);
			return this.createResponseObject(record, cardId, data.get("returnUrl"));
		} catch (Exception e) {
			logger.error("创建APP支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建APP支付链接发生异常");
		}
	}

	/**
	 * 支付监管款
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_app_project.do")
	public Object appProject(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    yeepay_app_project.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer appointmentPushId = CommonUtil.stringToInteger(data.get("projectId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			Integer cardId = CommonUtil.stringToInteger(data.get("cardid"));
			PaymentRecord record = paymentRecordService.saveProject(appointmentPushId, source,
					PaymentRecord.PAY_TYPE_YEEPAY);
			if (record == null) { // 没有支付记录，说明不需要支付
				return new ApiResponse<String>(false, "监管款小于等于0，不需要支付");
			}
			return this.createResponseObject(record, cardId, data.get("returnUrl"));
		} catch (Exception e) {
			logger.error("创建APP支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建APP支付链接发生异常");
		}
	}
	
	private Object createResponseObject(PaymentRecord record, Integer cardId, String returnUrl) {
		MemberBankCard bankcard = memberBankCardService.findById(cardId);
		if (bankcard == null) {
			return new ApiResponse<String>(false, "无法查询到该银行卡：" + cardId);
		}
		Map<String, String> result = yeePayService.payment(record, bankcard, returnUrl);
		String returnCode = result.get("code");
		if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
			return new ApiResponse<String>(result.get("payurl"));
		} else {
			return new ApiResponse<String>(false, YeePayCode.getErrorMessage(returnCode));
		}
	}
	
	
	
	/**
	 * 易宝支付 退定金
	 * appointmentPushId
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yeepay_app_refund_deposit.do")
	public Object appRefundDeposit(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    yeepay_app_refund_deposit.do json:%s    >>>", json));
			Map<String, String> paramMap = CommonUtil.jsonToMap(json);
			PaymentRecord record = paymentRecordService.dealDepositRefundRecord(paramMap);
			Map<String, String> result = yeePayService.refund(IdCreator.getSeqId(), record.getSerialCode(), Double.valueOf(record.getAmount()), null, "退定金");
			String returnCode = result.get("code");
			String msg = YeePayCode.getErrorMessage(returnCode);
			//同步处理更新业务数据状态
			paymentRecordService.updateRefundSuccess(record, result);
			if (YeePayUtil.INVOKE_SUCCESS.equals(returnCode)) {
				return new ApiResponse<String>(true, msg);
			} else {
				return new ApiResponse<String>(false, msg);
			}
		} catch (Exception e) {
			logger.error("退定金款发生异常", e);
			return new ApiResponse<String>(false, "退定金款发生异常");
		}
	}

	
	
	
	
	
	
	
	
	
}
