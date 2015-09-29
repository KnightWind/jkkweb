package com.jkkp.appapi.common.control.payment;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.appapi.common.service.interfaces.IZcCrowdfundingService;
import com.jkkp.common.BaseController;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class WeixinPayController extends BaseController {
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired
	private IZcCrowdfundingService zcCrowdfundingService;
	
	/**
	 * 微信支付通知接口
	 * @param out_trade_no 商户订单号
	 * @param transaction_id 微信交易号
	 * @param trade_status 交易状态
	 * @param randStr 随机字符串
	 * @param sign 签名
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("weixin_notify.do")
	@ResponseBody
	public Object notifyPay(@RequestParam String json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String out_trade_no = data.get("out_trade_no"); 
			String transaction_id = data.get("transaction_id"); 
			String trade_status = data.get("trade_status"); 
			String randStr = data.get("randStr"); 
			String sign = data.get("sign"); 
			System.out.println("weixin_notify.do>>orderNo=" + out_trade_no + ",transaction_id=" + transaction_id + ",trade_status=" + trade_status + ",randStr=" + randStr + ",sign=" + sign);
			//自定义实现的简单的签名算法
			String code = CommonUtil.md5(StringUtils.reverse((out_trade_no + transaction_id + trade_status + randStr).toUpperCase()));
			if(code.equals(sign)) {
				PaymentRecord record = paymentRecordService.updateStatus(out_trade_no, transaction_id, trade_status);
				paymentRecordService.saveSuccess(record);
				if(record.getType() == PaymentRecord.TYPE_JL_SERVICE) {
					Map<String, Object> result = zcCrowdfundingService.updateInfo(record.getBusinessId());
					int status = (int) result.get("status");
					if(status == 0) {
						return new ApiResponse<String>("error");
					}
				}
				return new ApiResponse<String>("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "weixin_notify.do error";
	}
}
