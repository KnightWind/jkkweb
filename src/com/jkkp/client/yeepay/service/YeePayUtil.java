package com.jkkp.client.yeepay.service;

import java.util.ArrayList;
import java.util.List;

import com.jkkp.client.yeepay.dto.YeePaySupplier;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;

public class YeePayUtil {
	
	public static final String ASSURE_YES = "1"; // 需要担保
	public static final String ASSURE_NO = "0"; // 不需要担保
	public static String CALLBACK_URL = Sysconfig.CONFIG_PARAM.get(ConfigConstant.YEEPAY_CALLBACK_URL);
	public static final int PAY_TYPE_WEBBANK = 1; // 1:网银支付
	public static final int PAY_TYPE_PHONE = 2; // 2:手机端一键支付
	public static final int PAY_TYPE_DIRECT = 3; // 3:无卡直连支付

	public static final String INVOKE_SUCCESS = "1"; // 成功

	/**
	 * 取支付的城市编码
	 * 
	 * @param cityId
	 * @return
	 */
	public static String getBankCity(int cityId) {
		return ""; // TODO
	}

	/**
	 * 取支付的省编码
	 * 
	 * @param cityId
	 * @return
	 */
	public static String getBankProvince(int province) {
		return ""; // TODO
	}

	/**
	 * 取支付的银行名称
	 * 
	 * @param cityId
	 * @return
	 */
	public static String getBankName(String bankName) {
		return ""; // TODO
	}

	/**
	 * 支付类型名称
	 * 
	 * @param paytype
	 * @return
	 */
	public static String getPaymentType(int paytype) {
		if (paytype == PAY_TYPE_WEBBANK) {
			return "SALES"; // 网银支付
		} else if (paytype == PAY_TYPE_PHONE) {
			return "ONEKEY"; // 手机端一键支付
		} else if (paytype == PAY_TYPE_DIRECT) {
			return "DIRECT"; // 无卡直连支付
		}
		return null;
	}

	public static String getDivideInfo(List<YeePaySupplier> supplierList) {
		if (supplierList == null || supplierList.isEmpty()) {
			return "";
		}
		List<String> divideList = new ArrayList<String>();
		for (YeePaySupplier item : supplierList) {
			divideList.add(item.getSubAccountCode() + ":" + item.getDivideRate());
		}
		return CommonUtil.join(divideList, "|");
	}

	public static String getSubAccount(List<YeePaySupplier> supplierList) {
		if (supplierList == null || supplierList.isEmpty()) {
			return "";
		}
		List<String> divideList = new ArrayList<String>();
		for (YeePaySupplier item : supplierList) {
			divideList.add(item.getSubAccountCode());
		}
		return CommonUtil.join(divideList, ",");
	}
}
