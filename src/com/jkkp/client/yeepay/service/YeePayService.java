package com.jkkp.client.yeepay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jkkp.client.yeepay.api.ZGTService;
import com.jkkp.client.yeepay.dto.YeePaySupplier;
import com.jkkp.modules.member.model.MemberBankCard;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;

public class YeePayService {

	public Map<String, String> registerAccount(String requestId, Supplier supplier) {
		Map<String, String> registerParams = new HashMap<String, String>();
		registerParams.put("bankcity", YeePayUtil.getBankCity(supplier.getBankCity()));
		registerParams.put("bankprovince", YeePayUtil.getBankProvince(supplier.getBankProvince()));
		registerParams.put("bankaccounttype", supplier.getBankAccountType() == 1 ? "PublicCash" : "PrivateCash"); // 对公/对私
		registerParams.put("accountname", supplier.getBankAuthor());
		registerParams.put("bankname", YeePayUtil.getBankName(supplier.getBankFullName()));
		registerParams.put("bankaccountnumber", supplier.getBankAccount());
		registerParams.put("riskreserveday", Sysconfig.CONFIG_PARAM.get("")); // 结算周期
																				// TODO
		registerParams.put("minsettleamount", Sysconfig.CONFIG_PARAM.get("")); // 起结金额
																				// TODO
		registerParams.put("customertype", "ENTERPRISE"); // 个人/企业
		registerParams.put("businesslicence", supplier.getBusinessCode());
		registerParams.put("idcard", "");
		registerParams.put("legalperson", supplier.getLegalPerson());
		registerParams.put("linkman", supplier.getContactUser());
		registerParams.put("bindmobile", supplier.getBindMobile());
		registerParams.put("signedname", supplier.getSpName());
		registerParams.put("requestid", requestId);
		return ZGTService.registerAccount(registerParams);
	}

	private Map<String, String> createPaymentRequest(PaymentRecord record, String returnUrl) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("requestid", record.getSerialCode());
		params.put("amount", String.valueOf(record.getAmount()));
		params.put("assure", YeePayUtil.ASSURE_NO);
		params.put("productname", record.getTitle());
		params.put("callbackurl", YeePayUtil.CALLBACK_URL);
		params.put("webcallbackurl", returnUrl);
		params.put("payproducttype", "ONEKEY");
		return params;
	}
	
	public Map<String, String> payment(PaymentRecord record, String returnUrl) {
		Map<String, String> params = createPaymentRequest(record, returnUrl);
		return ZGTService.paymentRequest(params);
	}

	public Map<String, String> payment(PaymentRecord record, MemberBankCard bankcard, String returnUrl) {
		Map<String, String> params = createPaymentRequest(record, returnUrl);
		params.put("cardname", bankcard.getOwnername());
		params.put("idcard", bankcard.getIdcard());
		params.put("bankcardnum", bankcard.getCardnum());
		return ZGTService.paymentRequest(params);
	}

	public Map<String, String> queryOrder(String requestId) {
		return ZGTService.paymentQuery(requestId);
	}

	public Map<String, String> transferToSupplier(String requestId, String ledgerno, Double amount) {
		Map<String, String> transferParams = new HashMap<String, String>();
		transferParams.put("requestid", requestId);
		transferParams.put("ledgerno", ledgerno);
		transferParams.put("amount", CommonUtil.doubleToString(amount));
		return ZGTService.transfer(transferParams);
	}

	public Map<String, String> transferToPlatform(String requestId, String ledgerno, Double amount) {
		Map<String, String> transferParams = new HashMap<String, String>();
		transferParams.put("requestid", requestId);
		transferParams.put("sourceledgerno", ledgerno);
		transferParams.put("amount", CommonUtil.doubleToString(amount));
		return ZGTService.transfer(transferParams);
	}

	public Map<String, String> transferQuery(String requestId) {
		return ZGTService.transferQuery(requestId);
	}

	public Map<String, String> divide(String requestId, String orderCode, List<YeePaySupplier> supplierList) {
		String divideInfo = YeePayUtil.getDivideInfo(supplierList);
		if (!StringUtils.isNotEmpty(divideInfo)) {
			throw new RuntimeException("分账信息不能为空");
		}
		Map<String, String> divideParams = new HashMap<String, String>();
		divideParams.put("requestid", requestId);
		divideParams.put("orderrequestid", orderCode);
		divideParams.put("divideinfo", divideInfo);
		return ZGTService.divide(divideParams);
	}

	public Map<String, String> divideQuery(String orderrequestid, String dividerequestid, String ledgerno) {
		Map<String, String> divideQueryParams = new HashMap<String, String>();
		divideQueryParams.put("orderrequestid", orderrequestid);
		divideQueryParams.put("orderrequestid", dividerequestid);
		divideQueryParams.put("orderrequestid", ledgerno);
		return ZGTService.divideQuery(divideQueryParams);
	}

	public Map<String, String> refund(String requestId, String orderCode, Double amount,
			List<YeePaySupplier> supplierList, String memo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("requestid", requestId);
		params.put("orderrequestid", orderCode);
		params.put("amount", CommonUtil.doubleToString(amount));
		params.put("confirm", "1");
		params.put("memo", memo);
		params.put("divideinfo", YeePayUtil.getDivideInfo(supplierList));
		return ZGTService.refund(params);
	}

	public Map<String, String> refundQuery(String requestId, String orderCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("refundrequestid", requestId);
		params.put("orderrequestid", orderCode);
		return ZGTService.refundQuery(params);
	}

	public Map<String, String> assure(String orderCode) {
		return ZGTService.confirm(orderCode);
	}

	public Map<String, String> queryBalance(List<YeePaySupplier> supplierList) {
		return ZGTService.balanceQuery(YeePayUtil.getSubAccount(supplierList));
	}

}
