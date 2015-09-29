package com.jkkp.appapi.common.control.activity;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAcceptanceService;
import com.jkkp.appapi.common.service.interfaces.ISaleActivityService;
import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.common.BaseController;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.service.IActivityGiftService;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.service.IActivityVoucherNumService;
import com.jkkp.modules.sale_theme.service.IActivityVoucherService;
import com.jkkp.modules.sale_theme.view.VActivityGift;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;
import com.jkkp.modules.sale_theme.view.VActivityProduct;
import com.jkkp.modules.sale_theme.view.VActivityTheme;
import com.jkkp.modules.sale_theme.view.VActivityVoucher;
import com.jkkp.modules.sale_theme.view.VMyVouchers;
import com.jkkp.modules.sale_theme.view.VUserActivityList;
import com.jkkp.modules.sale_theme.vo.LotteryResult;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.IAdminService;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.MathUtils;

@Controller
@RequestMapping("/")
public class SaleActivityController extends BaseController {

	@Autowired
	private ISaleActivityService saleActivityService;
	@Autowired
	private IActivityCardDealsService activityCardDealsService;
	/*
	 * @Autowired private IActivityGiftLogService activityGiftLogService;
	 */
	@Autowired
	private IActivityGiftService activityGiftService;
	@Autowired
	private IActivityJionSignService activityJionSignService;
	@Autowired
	private IActivityVoucherNumService activityVoucherNumService;
	@Autowired
	IActivityCardService activityCardService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IMemberService memberService;
	
	@Autowired public CommonJsonMap commonJsonMap;
	@Autowired
	private IActivityVoucherService activityVoucherService;
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired private  IAcceptanceService iAcceptanceService;
	private static List<String> list = new ArrayList<String>();
	private List<String> smsList = Collections.synchronizedList(list);
	private static Thread task = null;
	
	
	/**
	 * 微信支付微信活动卡
	 */
	@ResponseBody
	@RequestMapping("weixin_activity_card.do")
	public Object wxPayActivityCard(@RequestParam String json) {
		try {
			System.out.println(String.format("<<<    weixin_activity_card json:%s   >>>", json));
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String phone = data.get("phone");
			String openid = data.get("openid");
			Integer cardId = CommonUtil.stringToInteger(data.get("cardId"));
			Integer giftId = CommonUtil.stringToInteger(data.get("giftId"));
			String decorateReq = data.get("decorateReq");
			ActivityCardDeals acd = new ActivityCardDeals(null, cardId, phone, decorateReq, PaymentRecord.PAY_TYPE_WEIXIN, false, giftId, new Date());
			acd.setOpenid(openid);
			PaymentRecord record = paymentRecordService.saveDepositByActivity(cardId, 1, PaymentRecord.PAY_TYPE_WEIXIN,acd);
			if(acd != null && record != null) {				
				return new ApiResponse<ActivityCardDeals>(acd);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("创建微信待支付发生异常", e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("getActivityCardDeals.do")
	public Object activityCardDeals(@RequestParam String json) {
		Map<String, String> data = CommonUtil.jsonToMap(json);
		String serialCode = data.get("serialCode");
		ActivityCardDeals acd = new ActivityCardDeals(serialCode);
		List<ActivityCardDeals> list = activityCardDealsService.select(acd);
		if(list != null && !list.isEmpty()) {
			acd = list.get(0);
		}
		return new ApiResponse<ActivityCardDeals>(acd);
	}

	//
	@ResponseBody
	@RequestMapping("saveGift.do")
	public Object saveCardDeals(@RequestParam String json) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("code", String.valueOf(0));
			resultMap.put("msg", "成功赠送礼包！");
			String phone = (String) jsonObject.get("phone");
			Integer giftId =(Integer) jsonObject.get("giftId");
			String serialCode = (String) jsonObject.get("serialCode");
			ActivityGiftLog ag = new ActivityGiftLog();
			ag.setCreateTime(new Date());
			ag.setGiftId(giftId);
			ag.setUpdateTime(new Date());
			ag.setPhone(phone);
			ag.setUsed(false);

			ActivityGift gift = activityGiftService.findById(giftId);
			ActivityCard card = activityCardService.findById(gift.getCardId());

			ActivityCardDeals acd = new ActivityCardDeals();
			acd.setPhone(phone);
			acd.setCardId(card.getId());
			acd.setSerialCode(serialCode);
			List<ActivityCardDeals> list = activityCardDealsService.select(acd);
			if(list != null && !list.isEmpty()) {
				acd = list.get(0);
			}
			if(acd != null) {
				acd.setPayStatus(1);
				saleActivityService.saveCardAndGift(acd, ag);
				return new ApiResponse<Map<String, String>>(resultMap);				
			} else {
				return new ApiResponse<String>(false, "支付礼包回调出错");
			}
		} catch (Exception e) {
			logger.error("获取礼包出错", e);
			return new ApiResponse<String>(false, "获取礼包出错");
		}
	}

	// 根据id获取礼包
	@ResponseBody
	@RequestMapping("ckIsBuyCard.do")
	public Object ckIsBuyCard(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("code", String.valueOf(0));
			resultMap.put("msg", "可以购买此卡！");
			String phone = data.get("phone");
			Integer cardId = CommonUtil.stringToInteger(data.get("cardId"));
			long count = activityCardDealsService.select(phone, cardId, 1);
			if (count > 0) {
				resultMap.put("code", String.valueOf(1));
				resultMap.put("msg", "您已购买此卡，无需重复购买！");
			}
			return new ApiResponse<Map<String, String>>(resultMap);
		} catch (Exception e) {
			logger.error("获取礼包出错", e);
			return new ApiResponse<String>(false, "获取礼包出错");
		}
	}

	// 根据id获取礼包
	@ResponseBody
	@RequestMapping("getActicityGiftById.do")
	public Object getActicityGiftById(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer id = CommonUtil.stringToInteger(data.get("id"));
			ActivityGift bean = saleActivityService.getActicityGiftById(id);
			return new ApiResponse<ActivityGift>(bean);
		} catch (Exception e) {
			logger.error("获取礼包出错", e);
			return new ApiResponse<String>(false, "获取礼包出错");
		}
	}

	// 活动主题
	@ResponseBody
	@RequestMapping("acticityThemeList.do")
	public Object activityThemeList() {
		try {
			VActivityTheme bean = saleActivityService.selectActivityThemeWX();
			return new ApiResponse<VActivityTheme>(bean);
		} catch (Exception e) {
			logger.error("获取微信引流活动主题报错", e);
			return new ApiResponse<String>(false, "获取活动出错");
		}
	}

	// 根据代金券Id查找对象
	@ResponseBody
	@RequestMapping("getActivityVoucherById.do")
	public Object getActivityVoucherById(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer id = CommonUtil.stringToInteger(data.get("id"));
			VActivityVoucher card = saleActivityService
					.getActivityVoucherById(id);
			return new ApiResponse<VActivityVoucher>(card);
		} catch (Exception e) {
			logger.error("获取活动商品详情", e);
			return new ApiResponse<String>(false, "查询活动商品详情出错");
		}
	}

	// 根据活动Id查找对象
	@ResponseBody
	@RequestMapping("getActivityThemeById.do")
	public Object getActivityById(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String activityId = data.get("id");
			if (StringUtils.isNotBlank(activityId)) {
				Integer id = CommonUtil.stringToInteger(activityId);
				VActivityTheme bean = saleActivityService
						.getActivityThemeById(id);
				return new ApiResponse<VActivityTheme>(bean);
			}
			return null;
		} catch (Exception e) {
			logger.error("获取活动商品详情", e);
			return new ApiResponse<String>(false, "查询活动商品详情出错");
		}
	}

	// 活动商品详情
	@ResponseBody
	@RequestMapping("activityProductDetail.do")
	public Object activityProductDetail(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer id = CommonUtil.stringToInteger(data.get("id"));
			String phoneNo = data.get("phone");
			VActivityProduct bean = saleActivityService
					.selectOneActivityProductDetail(id, phoneNo);
			return new ApiResponse<VActivityProduct>(bean);
		} catch (Exception e) {
			logger.error("获取活动商品详情出错", e);
			return new ApiResponse<String>(false, "查询活动商品详情出错");
		}
	}

	/**
	 * 微信报名用户添加至加\家可可平台会员
	 * @param phone
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public void addPhoneToMember(String phone) {
		activityJionSignService.addPhoneToMember(phone);
	}
	
	@ResponseBody
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/getvoucher.do")
	public Object getvoucher(HttpServletRequest request,
			@RequestParam String json) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			Integer activityId = jsonObject.getInt("activityId");
			String phone = jsonObject.getString("phone");
			String name = jsonObject.getString("name");
			String community = jsonObject.getString("community");
			Integer voucherId = jsonObject.getInt("voucherId");

			ActivityJionSign aj = new ActivityJionSign();
			aj.setPhone(phone);
			aj.setActivityId(activityId);
			List<ActivityJionSign> list2 = activityJionSignService.select(aj);
			if (list2.size() == 0) {
				// 报名操作
				ActivityJionSign ajs = new ActivityJionSign();
				ajs.setActivityId(activityId);
				ajs.setName(name);
				ajs.setSex(0);
				ajs.setPhone(phone);
				ajs.setCommunity(community);
				ajs.setJoinTime(new Date());
				ajs.setDecoration(0);
				ajs.setSpId(0);
				ajs.setVip(0);

				// 分配给一个客服跟进-------->
				int rid = Integer.valueOf(Sysconfig.CONFIG_PARAM
						.get(ConfigConstant.SERVICER_RID));
				Admin admin = new Admin();
				admin.setRid(rid);
				List<Admin> list = adminService.select(admin);
				if (!list.isEmpty()) {
					int[] rids = new int[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Admin ad = list.get(i);
						rids[i] = ad.getId();
					}
					MathUtils.bubbleSort(rids);
					Integer adminId = saleActivityService.getLastAdminId();
					if (adminId != null && adminId > 0) {
						for (int i = 0; i < rids.length; i++) {
							if (adminId == rids[rids.length - 1]) {
								ajs.setAdminId(rids[0]);
								break;
							}
							if (rids[i] == adminId) {
								ajs.setAdminId(rids[i + 1]);
								break;
							}
						}
					} else {
						ajs.setAdminId(rids[0]);
					}
				}

				activityJionSignService.save(ajs);
				activityJionSignService.addPhoneToMember(ajs.getPhone());
			}

			ActivityVoucherNum avn = new ActivityVoucherNum();
			avn.setPhone(phone);
			avn.setVoucherId(voucherId);
			List<ActivityVoucherNum> avns = activityVoucherNumService
					.select(avn);

			// 如果已领取
			if (!avns.isEmpty()) {
				return new ApiResponse<Integer>(1);
			}

			// 报了名
			if (list2.size() > 0) {
				ActivityJionSign ajs = list2.get(0);
				// 不是vip 先判断是否有领取券，假如有领取，则提示升级vip
				if (ajs.getVip() == 0) {
					ActivityVoucherNum av = new ActivityVoucherNum();
					avn.setPhone(phone);
					List<ActivityVoucherNum> avnList = activityVoucherNumService
							.select(av);
					if (avnList.size() > 0) {
						// 已有领取，则提示升级vip
						return new ApiResponse<Integer>(3);
					}
				}
			}
			avn.setHistoryNum(1);
			avn.setUpdateTime(new Date());
			avn.setNum(1);
			activityVoucherNumService.save(avn);
			ActivityVoucher av = activityVoucherService.findById(voucherId);
			SendMsgUtil.send(phone, "您已领取【" + av.getName()+ "】现金券（使用细则以网站内容为准）,9月5日举办的2015家居建材采购大会天津站(西青区知景道198号,社会山国际会议中心三层)");
			SendMsgUtil.send(phone, "现场可凭手机号到指定商家使用！有疑问请拨打4001118108【家可可】");
			return new ApiResponse<Integer>(0);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<Integer>(2);
		}
	}

	// 活动报名
	@ResponseBody
	@RequestMapping(value = "joinActivityTheme.do")
	public Object joinActivityTheme(HttpServletRequest request,
			@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);

			Integer activityId = jsonObject.getInt("activityId");
			String phone = jsonObject.getString("phone");
			String name = jsonObject.getString("name");
			Integer sex = jsonObject.getInt("sex");
			String community = jsonObject.getString("community");
			Integer decoration = jsonObject.getInt("decoration");
			// 方法其它标识参数
			String from = jsonObject.getString("from");
			String fromPhone = jsonObject.getString("fromPhone");
			Integer voucherId = jsonObject.getInt("voucherId");
			Integer activityId2 = jsonObject.getInt("activityId2");

			// 报名操作 --实体
			ActivityJionSign ajs = new ActivityJionSign();
			ajs.setActivityId(activityId);
			ajs.setName(name);
			ajs.setSex(sex);
			ajs.setPhone(phone);
			ajs.setCommunity(community);
			ajs.setJoinTime(new Date());
			ajs.setDecoration(decoration);
			ajs.setSpId(0);
			// 设置用户初始身份
			ajs.setVip(0);

			// 分配给一个客服跟进-------->
			int rid = Integer.valueOf(Sysconfig.CONFIG_PARAM
					.get(ConfigConstant.SERVICER_RID));
			Admin admin = new Admin();
			admin.setRid(rid);
			List<Admin> list = adminService.select(admin);
			if (!list.isEmpty()) {
				int[] rids = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					Admin ad = list.get(i);
					if (CheckedUtil.isNotEmpty(ad.getRid())) {
						rids[i] = ad.getId();
					}
				}
				MathUtils.bubbleSort(rids);
				Integer adminId = saleActivityService.getLastAdminId();
				if (adminId != null && adminId > 0) {
					for (int i = 0; i < rids.length; i++) {
						if (adminId == rids[rids.length - 1]) {
							ajs.setAdminId(rids[0]);
							break;
						}
						if (rids[i] == adminId) {
							ajs.setAdminId(rids[i + 1]);
							break;
						}
					}
				} else {
					ajs.setAdminId(rids[0]);
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map = saleActivityService.signIn(ajs, from, fromPhone, voucherId,activityId2);
			activityJionSignService.addPhoneToMember(ajs.getPhone());
			return new ApiResponse<Map<String, Object>>(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微信引流活动报名出错", e);
			return new ApiResponse<String>(false, "报名出错");
		}
	}

	// 根据手机号码、活动ID查找判断是否报名了活动，如果已经报名，则返回活动记录
	@ResponseBody
	@RequestMapping(value = "isJoinActivityTheme.do")
	public Object isJoinActivityTheme(HttpServletRequest request,
			@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			Integer activityId = jsonObject.getInt("id");
			String phone = jsonObject.getString("phone");
			ActivityJionSign ajs = saleActivityService
					.selectActivityJoinSignIsExits(activityId, phone);
			// System.out
			// .println("ajs=isJoinActivityTheme.do = " + ajs.getClass());
			return new ApiResponse<ActivityJionSign>(ajs);
		} catch (Exception e) {
			logger.error("微信引流活动报名出错", e);
			return new ApiResponse<String>(false, "报名出错");
		}
	}

	// 活动签到
	@ResponseBody
	@RequestMapping("signActivityTheme.do")
	public Object signActivityTheme(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String phone = data.get("phone");
			Integer activityId = Integer.valueOf(data.get("activityId"));
			Map<String, Object> map = new HashMap<String, Object>();
			map = saleActivityService.signActivityTheme(phone, activityId);
			activityJionSignService.addPhoneToMember(phone);
			return new ApiResponse<Map<String, Object>>(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 活动详情-未报名
	@ResponseBody
	@RequestMapping("getActivityThemeDetail.do")
	public Object getActivityThemeDetail(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String id = "0";
			if (data.containsKey("id"))
				id = data.get("id");
			Map<String, Object> rel = saleActivityService.detial(Integer
					.valueOf(id));
			if (data.get("phone") != null) {
				// 嵌入:是否为会员
				// List<ActivityCardDeals> acds =
				// saleActivityService.checkCard(data.get("phone"));
				List<ActivityJionSign> list = activityJionSignService
						.selectByProperty("phone", data.get("phone"));
				if (!list.isEmpty()) {
					ActivityJionSign bean = list.get(0);
					rel.put("role", bean.getVip());
				} else {
					rel.put("role", 2);
				}
			}
			return new ApiResponse<Map<String, Object>>(rel);
		} catch (Exception e) {
			logger.error("获取活动信息失败", e);
			return new ApiResponse<String>(false, "获取失败");
		}
	}

	// 活动详情-已报名
	@ResponseBody
	@RequestMapping("getActivityThemeDetailJoined.do")
	public Object getActivityThemeDetailJoined(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String id = data.get("id");
			Map<String, Object> rel = saleActivityService.detialJoined(Integer
					.valueOf(id));
			return new ApiResponse<Map<String, Object>>(rel);
		} catch (Exception e) {
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 活动详情-已报名
	@ResponseBody
	@RequestMapping("findMyActivities.do")
	public Object findMyActivities(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String phone = data.get("phone");
			String pageNo = data.get("pageNo");
			String pageSize = data.get("pageSize");
			int pn = 0;
			int pz = 20;
			if (pageSize != null && !pageSize.equals("")) {
				pz = Integer.valueOf(pageSize);
				if (pz <= 0)
					pz = 20;
			}
			if (pageNo != null && !pageNo.equals("")) {
				pn = Integer.valueOf(pageNo);
				if (pn <= 0) {
					pn = 1;
				} else {
					pn = (Integer.valueOf(pageNo) - 1) * pz;
				}
			}
			if (pageNo != null && !pageNo.equals("")) {
				pn = Integer.valueOf(pageNo);
				if (pn <= 0) {
					pn = 1;
				} else {
					pn = (Integer.valueOf(pageNo) - 1) * pz;
				}
			}
			return new ApiResponse<List<VUserActivityList>>(
					saleActivityService.UserActivityList(phone, pn, pz));

		} catch (Exception e) {
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 促销活动的所有代金券列表
	@ResponseBody
	@RequestMapping("findActivityVouchers.do")
	public Object findActivityVouchers(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			int saleActivityThemeId = Integer.parseInt(data.get("id"));
			Integer pageNo = Integer.parseInt(data.get("pageNo"));
			int pageSize = Integer.parseInt(data.get("pageSize"));
			List<VActivityVoucher> result = saleActivityService
					.findActivityVouchers(saleActivityThemeId, pageNo, pageSize);
			return new ApiResponse<List<VActivityVoucher>>(result);
		} catch (Exception e) {
			logger.error("获取活动的所有代金券列表", e);
			return new ApiResponse<String>(false, "获取活动的所有代金券列表");
		}
	}

	// 活动详情-已报名
	@ResponseBody
	@RequestMapping("findMyVouchers.do")
	public Object findMyVouchers(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String phone = data.get("phone");
			String pageNo = data.get("pageNo");
			String pageSize = data.get("pageSize");
			int pn = 1;
			int pz = 20;
			if (pageSize != null && !pageSize.equals("")) {
				pz = Integer.valueOf(pageSize);
				if (pz <= 0)
					pz = 20;
			}
			if (pageNo != null && !pageNo.equals("")) {
				pn = Integer.valueOf(pageNo);
				if (pn <= 0) {
					pn = 1;
				} else {
					pn = (Integer.valueOf(pageNo) - 1) * pz;
				}
			}
			if (pageNo != null && !pageNo.equals("")) {
				pn = Integer.valueOf(pageNo);
				if (pn <= 0) {
					pn = 1;
				} else {
					pn = (Integer.valueOf(pageNo) - 1) * pz;
				}
			}
			return new ApiResponse<List<VMyVouchers>>(
					saleActivityService.findMyVouchers(phone, pn, pz));
		} catch (Exception e) {
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 促销活动主题相关的奖项列表
	@ResponseBody
	@RequestMapping("findAwardItems.do")
	public Object findAwardItems(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer saleActivityThemeId = Integer.parseInt(data.get("id"));
			return new ApiResponse<List<Awards>>(
					saleActivityService.findAwards(saleActivityThemeId));
		} catch (Exception e) {
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 促销活动主题抽奖
	@ResponseBody
	@RequestMapping("getLuckyAward.do")
	public Object getLuckyAward(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer saleActivityThemeId = Integer.parseInt(data.get("id"));
			String phone = data.get("phone");
			LotteryResult result = saleActivityService.getLuckyAward(
					saleActivityThemeId, phone);
			if (result != null && result.isStatus()
					&& result.getAwards() != null) {
				Awards aw = result.getAwards();
				String content = "恭喜您获得"+ aw.getRank()+ "等奖("+ aw.getPrizeName()+ ")，9月5日举办的2015家居建材采购大会天津站（西青区知景道198号,社会山国际会议中心三层）活动现场可凭手机号领取奖品！有疑问请拨打4001118108【家可可】";
				getSmsList().add(phone + "_" + content);
				// 启动一个后台线程
				startSendSmsTask();
			}
			return new ApiResponse<LotteryResult>(result);

		} catch (Exception e) {
			logger.error("抽奖失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 促销活动主题参与抽奖活动中奖人及其奖品列表
	@ResponseBody
	@RequestMapping("getLotteryChance.do")
	public Object getLotteryChance(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer saleActivityThemeId = Integer.parseInt(data.get("id"));
			String phone = data.get("phone");
			return new ApiResponse<Integer>(
					saleActivityService.getLotteryChance(saleActivityThemeId,
							phone));
		} catch (Exception e) {
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 促销活动主题参与抽奖活动中奖人及其奖品列表
	@ResponseBody
	@RequestMapping("findLuckyPeopleAwards.do")
	public Object findLuckyPeopleAwards(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			Integer saleActivityThemeId = Integer.parseInt(data.get("id"));
			String phone = (String) data.get("phone");
			Integer pageNo = Integer.parseInt(data.get("pageNo"));
			Integer pageSize = Integer.parseInt(data.get("pageSize"));
			return new ApiResponse<List<VActivityLotteryLog>>(
					saleActivityService.findLuckyPeopleAwards(
							saleActivityThemeId, phone, true, null, pageNo,
							pageSize));
		} catch (Exception e) {
			logger.error("活动签到失败", e);
			return new ApiResponse<String>(false, "签到出错");
		}
	}

	// 促销活动主题, 发送短信内容
	@ResponseBody
	@RequestMapping("sendSms.do")
	public Object sendSms(@RequestParam String json) {
		try {
			Map<String, String> data = CommonUtil.jsonToMap(json);
			String phone = data.get("phone");
			String content = data.get("content");
			return new ApiResponse<Boolean>(saleActivityService.sendMsm(phone,
					content));
		} catch (Exception e) {
			logger.error("促销活动主题, 发送短信内容", e);
			return new ApiResponse<String>(false, "发送短信内容出错");
		}
	}

	// 我的活动礼包
	@ResponseBody
	@RequestMapping("findMyGiftPkgs.do")
	public Object findMyGiftPkgs(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", jsonObject.getString("phone"));

			List<VActivityGift> bean = saleActivityService
					.selectMyActivityGiftLogs(map);
			return new ApiResponse<List<VActivityGift>>(bean);
		} catch (Exception e) {
			logger.error("获取我的礼包列表失败", e);
			return new ApiResponse<String>(false, "获取出错");
		}
	}

	// 保存推荐号码
	@ResponseBody
	@RequestMapping("recommend.do")
	public Object recommend(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			String fromPhone = jsonObject.getString("fromPhone");
			String toPhone = jsonObject.getString("toPhone");
			Integer activityID = jsonObject.getInt("activityID");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("code", saleActivityService.recommend(activityID,
					fromPhone, toPhone));
			return new ApiResponse((Map<String, Object>) ret);
		} catch (Exception e) {
			logger.error("获取我的礼包列表失败", e);
			return new ApiResponse<String>(false, "获取出错");
		}
	}

	// 查询用户推荐多少个用户
	@ResponseBody
	@RequestMapping("orderRecommend.do")
	public Object orderRecommend(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			String phone = jsonObject.getString("phone");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("num", saleActivityService.recommendNum(phone));
			return new ApiResponse((Map<String, Object>) ret);
		} catch (Exception e) {
			logger.error("获取我的礼包列表失败", e);
			return new ApiResponse<String>(false, "获取出错");
		}
	}

	// 保存分享记录接口
	@ResponseBody
	@RequestMapping("share.do")
	public Object share(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			String phone = jsonObject.getString("phone");
			Integer type = jsonObject.getInt("type");
			Integer activityID = jsonObject.getInt("activityId");
			String title = (String) jsonObject.get("title");
			String url = (String) jsonObject.get("url");
			Map<String, Object> ret = new HashMap<String, Object>();
			Boolean b = saleActivityService.share(activityID, phone, type,
					title, url);
			ret.put("ret", b);
			return new ApiResponse<Map<String, Object>>(ret);
		} catch (Exception e) {
			logger.error("获取我的礼包列表失败", e);
			return new ApiResponse<String>(false, "获取出错");
		}
	}

	// 领取现金券
	@ResponseBody
	@RequestMapping("lingQuan.do")
	public Object lingQuan(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			String phone = jsonObject.getString("phone");
			Integer voucherId = jsonObject.getInt("voucherId");
			Map<String, Object> map = saleActivityService.insertOneVoucher(
					phone, voucherId);
			return new ApiResponse<Map<String, Object>>(map);
		} catch (Exception e) {
			logger.error("微信领取现金券出错", e);
			return new ApiResponse<String>(false, "领取出错");
		}
	}

	@ResponseBody
	@RequestMapping("sendjiakekeVipMsg.do")
	public Object sendjiakekeVipMsg(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			String phone = jsonObject.getString("phone");
			Map<String, Object> map = saleActivityService.tuijian(phone);
			return new ApiResponse<Map<String, Object>>(map);
		} catch (Exception e) {
			logger.error("检查推荐出错！", e);
			return new ApiResponse<String>(false, "检查推荐出错！");
		}
	}

	@ResponseBody
	@RequestMapping("checkLoginStatus.do")
	public Object checkLoginStatus(@RequestParam String json) {
		try {
			// 获取界面的参数
			JSONObject jsonObject = JSONObject.fromObject(json);
			String phone = jsonObject.getString("phone");
			ActivityJionSign ajs = saleActivityService.checkLoginStatus(phone);
			return new ApiResponse<ActivityJionSign>(ajs);
		} catch (Exception e) {
			logger.error("检查推荐出错！", e);
			return new ApiResponse<String>(false, "检查推荐出错！");
		}
	}

	private void startSendSmsTask() {
		if (task == null) {
			task = new Thread(new Runnable() {
				public void run() {
					while (true) {
						List<String> list = getSmsList();
						if (!list.isEmpty()) {
							Iterator<String> iter = list.iterator();
							while (iter.hasNext()) {
								String[] items = iter.next().split("_");
								iter.remove();
								try {
									String phone = items[0];
									String content = items[1];
									saleActivityService.sendMsm(phone, content);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
			task.start();
		}
	}

	private synchronized List<String> getSmsList() {
		return smsList;
	}
	
	@ResponseBody @RequestMapping("/engineering_count.do")
	public Object sjs_update(HttpServletRequest request) {
		Map<String, Object> map = null;;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		VJlGcd vJlGcd=new VJlGcd();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer gcdId=CommonUtil.stringToInteger(map.get("gcdId").toString());
			Integer stageId=CommonUtil.stringToInteger(map.get("stageId").toString());
			vJlGcd=iAcceptanceService.querycount(gcdId, stageId);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(vJlGcd, mapBusi);
		}		
	}
}
