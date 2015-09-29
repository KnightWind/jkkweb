package com.jkkp.appapi.common.control.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.common.BaseController;
import com.jkkp.common.CommonResult;
import com.jkkp.modules.member.model.MemberBankCard;
import com.jkkp.modules.member.service.IMemberBankCardService;

@Controller
@RequestMapping(value = "/")
public class BankCardBindController extends BaseController {

	@Autowired
	private IMemberBankCardService memberBankCardService;

	/**
	 * 查询银行卡
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("card_band_list.do")
	public Object bankCardList(@RequestParam Integer memberId) {
		try {
			List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
			List<MemberBankCard> cardList = memberBankCardService.findByMemberId(memberId);
			for (MemberBankCard item : cardList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", item.getId());
				map.put("cardname", item.getBankname());
				datalist.add(map);
			}
			return new ApiResponse<List<Map<String, Object>>>(datalist);
		} catch (Exception e) {
			logger.error("查询银行卡发生异常", e);
			return new ApiResponse<String>(false, "查询银行卡发生异常");
		}
	}

	/**
	 * 绑定银行卡
	 * 
	 * @param memberId
	 *            会员id
	 * @param bankname
	 *            开户银行
	 * @param ownername
	 *            持卡人姓名
	 * @param cardnum
	 *            银行卡号
	 * @param idcard
	 *            身份证号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("card_band_save.do")
	public Object saveBankCard(@RequestParam Integer memberId, @RequestParam String bankname,
			@RequestParam String ownername, @RequestParam String cardnum, @RequestParam String idcard) {
		try {
			MemberBankCard entity = new MemberBankCard();
			entity.setMemberId(memberId);
			entity.setBankname(bankname);
			entity.setOwnername(ownername);
			entity.setCardnum(cardnum);
			entity.setIdcard(idcard);
			CommonResult<String> res = memberBankCardService.saveBindCard(entity);
			if (res.isSuccess()) {
				return new ApiResponse<String>(true);
			} else {
				return new ApiResponse<String>(false, res.getResult());
			}
		} catch (Exception e) {
			logger.error("绑定银行卡发生异常", e);
			return new ApiResponse<String>(false, "绑定银行卡发生异常");
		}
	}

	/**
	 * 解绑银行卡
	 * 
	 * @param memberId
	 *            会员id
	 * @param cardid
	 *            银行卡id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("card_band_cancel.do")
	public Object cancelBankCard(@RequestParam Integer memberId, @RequestParam Integer cardid) {
		try {
			memberBankCardService.removeBankCard(memberId, cardid);
			return new ApiResponse<String>(true);
		} catch (Exception e) {
			logger.error("解绑银行卡发生异常", e);
			return new ApiResponse<String>(false, "解绑银行卡发生异常");
		}
	}
}
