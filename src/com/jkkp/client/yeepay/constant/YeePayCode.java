package com.jkkp.client.yeepay.constant;

import java.util.HashMap;
import java.util.Map;

public class YeePayCode {

	@SuppressWarnings("serial")
	public static final Map<Integer, String> CODE_MAP = new HashMap<Integer, String>() {
		{
			put(1, "成功");
			put(101000, "订单已结算确认");
			put(101003, "订单已结算确认错误");
			put(101005, "订单结算确认失败");
			put(110001, "订单不存在");
			put(110002, "订单号重复");
			put(110003, "订单信息错误");
			put(110004, "订单状态错误");
			put(110005, "完成订单失败");
			put(110006, "订单支付失败");
			put(110007, "订单通知失败");
			put(111004, "订单业务类型不为担保订单");
			put(116004, "会员余额不足");
			put(116005, "会员支付失败");
			put(120001, "退款请求不存在");
			put(120002, "重复退款请求");
			put(120004, "退款请求状态错误");
			put(120005, "退款失败 ");
			put(122005, "更新退款记彔失败");
			put(122004, "更新退款汇总失败");
			put(130001, "分账记彔不存在");
			put(130003, "创建分账请求报错");
			put(130004, "分账记彔状态错误");
			put(130006, "分账请求参数为空报错");
			put(130007, "分账请求为空");
			put(130009, "分账金额超限");
			put(131001, "分账信息不存在");
			put(131003, "分账信息错误");
			put(132005, "更新分账记彔失败");
			put(132003, "更新分账信息失败");
			put(141005, "创建退款分账明细失败");
			put(141007, "更新退款分账请求失败");
			put(142007, "重复退款分账请求");
			put(150004, "算账记彔状态错误");
			put(150007, "创建算账请求报错");
			put(152005, "更新分账记彔失败");
			put(160002, "批次号重复");
			put(160003, "更新商户入账记录错误");
			put(160004, "商户入账状态错误");
			put(160005, "入账失败 ");
			put(160008, "转账余额不足");
			put(162005, "更新转账失败");
			put(173001, "代理商不存在");
			put(173004, "代理商冻结");
			put(174001, "商户不存在");
			put(174004, "商户冻结 ");
			put(175001, "分账方不存在");
			put(175002, "分账方重复");
			put(175004, "分账方冻结");
			put(176001, "会员不存在");
			put(176002, "会员重复注册");
			put(176003, "会员状态不合法");
			put(900001, "记彔不存在");
			put(900003, "参数错误 ");
			put(900006, "参数非空 ");
			put(900007, "非法请求 ");
			put(901001, "支付链接创建失败");
			put(999999, "系统异常 ");
		}
	};

	public static String getErrorMessage(String returnCode) {
		return CODE_MAP.get(Integer.valueOf(returnCode));
	}
}
