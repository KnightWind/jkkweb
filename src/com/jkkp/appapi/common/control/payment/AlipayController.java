package com.jkkp.appapi.common.control.payment;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.client.alipay.config.AlipayConfig;
import com.jkkp.client.alipay.service.AlipayService;
import com.jkkp.client.alipay.util.AlipayNotify;
import com.jkkp.common.BaseController;
import com.jkkp.modules.member.service.IMgatheringService;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.order.service.impl.PaymentRecordServiceImpl;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.RequestUtils;
import com.jkkp.utils.ResponseUtils;

@Controller
@RequestMapping("/")
public class AlipayController extends BaseController {

	private AlipayService alipayService = new AlipayService();
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired
	private IMgatheringService mgatheringService;
	@Autowired
	private PaymentRecordServiceImpl paymentRecordServiceImpl;

	/**
	 * 支付订金
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/alipay_deposit.do")
	public Object deposit(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_deposit.do json:%s   >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer pushId = CommonUtil.stringToInteger(data.get("appointmentId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveDeposit(pushId, source, PaymentRecord.PAY_TYPE_ALIPAY);
			if(record==null){
				return new ApiResponse<String>(false, "重复支付定金");
			}
			return new ApiResponse<String>(alipayService.createRequestParams(record, data.get("returnUrl")));
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
	}
	
	/**
	 * 支付微信活动卡
	 * 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/alipay_activity_card.do")
	public Object activityCard(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_activity_card json:%s   >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String phone = data.get("phone");
			Integer cardId = CommonUtil.stringToInteger(data.get("cardId"));
			Integer giftId = CommonUtil.stringToInteger(data.get("giftId"));
			String decorateReq = data.get("decorateReq");
			int source = CommonUtil.stringToInteger(data.get("type"));
			ActivityCardDeals acd = new ActivityCardDeals(null, cardId, phone, decorateReq, PaymentRecord.PAY_TYPE_ALIPAY, false, giftId, new Date());
			PaymentRecord record = paymentRecordService.saveDepositByActivity(cardId, source, PaymentRecord.PAY_TYPE_ALIPAY, acd);
			return new ApiResponse<String>(alipayService.createRequestParams(record, data.get("returnUrl")));
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
	@RequestMapping("/alipay_wallet.do")
	public Object wallet(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_wallet.do json:%s   >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer memberId = CommonUtil.stringToInteger(data.get("memberId"));
			if (memberId == null) {
				return new ApiResponse<String>(false, "memberId参数不能为空");
			}
			float amount = CommonUtil.stringToFloat(data.get("amount"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveWallet(memberId, source, amount,
					PaymentRecord.PAY_TYPE_ALIPAY);
			return new ApiResponse<String>(alipayService.createRequestParams(record, data.get("returnUrl")));
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
	@RequestMapping("/alipay_project.do")
	public Object project(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_project.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer pushId = CommonUtil.stringToInteger(data.get("projectId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveProject(pushId, source, PaymentRecord.PAY_TYPE_ALIPAY);
			if (record == null) { // 没有支付记录，说明不需要支付
				return new ApiResponse<String>(false, "监管款小于等于0，不需要支付");
			}
			return new ApiResponse<String>(alipayService.createRequestParams(record, data.get("returnUrl")));
		} catch (Exception e) {
			logger.error("创建支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建支付链接发生异常");
		}
	}

	@ResponseBody
	@RequestMapping("/alipay_verifyResult.do")
	public Object alipay_verifyResult(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_verifyResult.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			data.remove("login_user");
			data.remove("login_flag");
			data.remove("os");
			boolean success = alipayService.verifyResult(data);
			if (success) {
				return new ApiResponse<Map<String, String>>(paymentRecordService.findResult(data));
			}
		} catch (Exception e) {
			logger.error("校验结果发生异常", e);
		}
		return new ApiResponse<String>(false, "校验结果失败");
	}
	
	
	/**
	 * 校验微信活动购卡是否成功
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/alipay_verifyByCardResult.do")
	public Object alipay_verifyByCardResult(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_verifyByCardResult.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			data.remove("login_user");
			data.remove("login_flag");
			data.remove("os");
			PaymentRecord record = paymentRecordServiceImpl.findByTradeNoAndStatus(data.get("trade_no"),data.get("trade_status"));
			if(record != null){
				return new ApiResponse<Map<String, String>>(paymentRecordService.findActiveCardResult(data));
			}
			boolean success = alipayService.verifyResult(data);
			if (success) {
				return new ApiResponse<Map<String, String>>(paymentRecordService.findActiveCardResult(data));
			}
		} catch (Exception e) {
			logger.error("校验结果发生异常", e);
		}
		return new ApiResponse<String>(false, "校验结果失败");
	}

	/**
	 * 支付宝通知接口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/alipay_notify.do")
	public void notifyPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.notifyServer(request, response, AlipayConfig.sign_type_md5);
	}
	
	/**
	 * 支付宝通知接口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/alipay_app_notify.do")
	public void notifyAppPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.notifyServer(request, response, AlipayConfig.sign_type_RSA);
	}
	
	public void notifyServer(HttpServletRequest request, HttpServletResponse response, String signType) throws IOException {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = RequestUtils.createRequest(request);

			String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8"); // 商户订单号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8"); // 支付宝交易号
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");// 交易状态
			PaymentRecord record = paymentRecordService.updateStatus(orderNo, trade_no, trade_status);

			String result;
			if (AlipayNotify.verify(params, signType)) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					paymentRecordService.saveSuccess(record);
				}
				mgatheringService.saveRecord(record);
				result = "success";
			} else {// 验证失败
				result = "fail";
			}
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.write(response, "fail");
		}
	}
	
	/**
	 * app支付定金
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("alipay_app_deposit.do")
	public Object appDepost(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_app_deposit.do json:%s   >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer pushId = CommonUtil.stringToInteger(data.get("appointmentId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveDeposit(pushId, source, PaymentRecord.PAY_TYPE_ALIPAY);
			if(record==null){
				return new ApiResponse<String>(false, "重复支付定金");
			}
			String result = alipayService.createAppParams(record);
			return new ApiResponse<String>(result);
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
	@RequestMapping("/alipay_app_wallet.do")
	public Object appWallet(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_app_wallet.do json:%s   >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer memberId = CommonUtil.stringToInteger(data.get("memberId"));
			float amount = CommonUtil.stringToFloat(data.get("amount"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveWallet(memberId, source, amount,
					PaymentRecord.PAY_TYPE_ALIPAY);
			return new ApiResponse<String>(alipayService.createAppParams(record));
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
	@RequestMapping("/alipay_app_project.do")
	public Object appProject(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    alipay_app_project.do json:%s    >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer pushId = CommonUtil.stringToInteger(data.get("projectId"));
			int source = CommonUtil.stringToInteger(data.get("type"));
			PaymentRecord record = paymentRecordService.saveProject(pushId, source, PaymentRecord.PAY_TYPE_ALIPAY);
			if (record == null) { // 没有支付记录，说明不需要支付
				return new ApiResponse<String>(false, "监管款小于等于0，不需要支付");
			}
			return new ApiResponse<String>(alipayService.createAppParams(record));
		} catch (Exception e) {
			logger.error("创建APP支付链接发生异常", e);
			return new ApiResponse<String>(false, "创建APP支付链接发生异常");
		}
	}
	
	
	
//	/**
//	 * app退款
//	 * @param json
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("alipay_app_refund.do")
//	public Object appRefund(@RequestParam String json) {
//		try {
//			System.out.println(String.format("<<<    alipay_app_refund.do json:%s   >>>", json));
//			Map<String, String> data = CommonUtil.jsonToMap(json);
//			
//			PaymentRecord record = paymentRecordService.queryAndInsertPayRecord(data);
//			if(record==null){
//				return new ApiResponse<String>(false, "没有支付成功记录");
//			}
//			String result = alipayService.createAppRefundParams(record);
//			return new ApiResponse<String>(result);
//		} catch (Exception e) {
//			logger.error("创建APP支付链接发生异常", e);
//			return new ApiResponse<String>(false, "创建APP支付链接发生异常");
//		}
//	}
	//
	/**
	 * 支付宝退款通知接口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
//	@RequestMapping("/alipay_app_refund_notify.do")
//	public void notifyAppRefundPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		this.notifyServer(request, response, AlipayConfig.sign_type_RSA);
//	}
	
//	public void notifyRefundServer(HttpServletRequest request, HttpServletResponse response, String signType) throws IOException {
//		try {
//			// 获取支付宝POST过来反馈信息
//			Map<String, String> params = RequestUtils.createRequest(request);
//
//			String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8"); // 退款批次号
//			String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8"); // 退款成功总数
//			String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8");// 退款结果明细
////			PaymentRecord record = paymentRecordService.updateStatus(orderNo, trade_no, trade_status);
////
////			String result;
////			if (AlipayNotify.verify(params, signType)) {// 验证成功
////				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
////					paymentRecordService.saveSuccess(record);
////				}
////				mgatheringService.saveRecord(record);
////				result = "success";
////			} else {// 验证失败
////				result = "fail";
////			}
////			ResponseUtils.write(response, result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			ResponseUtils.write(response, "fail");
//		}
//	}
}
