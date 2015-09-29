package com.jkkp.appapi.common.control.crowdfunding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IZcCrowdfundingService;
import com.jkkp.common.BaseController;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.mapper.JlServiceMapper;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.model.ActivityItem;
import com.jkkp.modules.crowdfunding.model.JlService;
import com.jkkp.modules.crowdfunding.model.ResultItemList;
import com.jkkp.modules.crowdfunding.service.ICrowdfundingService;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.service.IRefundApplyAuditService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.system.service.IRegionService;
import com.jkkp.utils.CommonUtil;
import com.service.MemberRedPackageService;

/**
 * 众筹活动
 * 
 * @author xiaozhenyu
 *
 */
@Controller
@RequestMapping("/")
public class crowdFundingController extends BaseController {
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	ICrowdfundingService crowdfoundService;
	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	@Autowired
	private IZcCrowdfundingService zcCrowdfundingService;
	@Autowired
	private JlServiceMapper jlServiceMapper;
	@Autowired	
	private IRefundApplyAuditService refundApplyAuditService;
	@Autowired
	private IRegionService regionService;
	@Autowired
	MemberRedPackageService memberRedPackageService;
	
	//保存监理套餐
	@ResponseBody
	@RequestMapping("saveOneJlService.do")
	public Object saveOneJlService(@RequestParam String json){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			Map<String, String> beanMap = CommonUtil.jsonToMap(json);
			
			JlService jls = new JlService();
			jls.setCispay(1);
			List<JlService> list = jlServiceMapper.select(jls);//已经购买套餐的人数
			int size = 0;
			if(list != null) {
				size = list.size();
			}
			
			String ctype=beanMap.get("ctype");
			Float price = 199f;
			if("A".equals(ctype)) {
				if(size < 100) {
					price = 199f;
				} else {
					price = 999f;
				}
			} else if("B".equals(ctype)){
				if(size < 100) {
					price = 399f;
				} else {
					price = 1999f;
				}
			} else {
				throw new RuntimeException("saveOneJlService.do不支付的监理套餐类型：" + ctype);
			}
			
			resultMap=zcCrowdfundingService.saveOneJlService(beanMap.get("phone"), price, ctype,1,1,3);
			return new ApiResponse<Map<String, Object>>(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("type", 2);
			resultMap.put("massage", "保存出错");
			logger.error("保存监理套餐出错");
			return new ApiResponse<Map<String, Object>>(resultMap);
		}
	}
	
	//监理套餐更改支付状态
	@ResponseBody
	@RequestMapping("/updateJlServiceInfo.do")
	public Object updateInfo(@RequestParam String json){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Map<String, String> beanMap = CommonUtil.jsonToMap(json);
		Integer id=CommonUtil.stringToInteger(beanMap.get("id"));
		resultMap = zcCrowdfundingService.updateInfo(id);
		return new ApiResponse<Map<String, Object>>(resultMap);
	}
	
	//根据订单号查找
	@ResponseBody
	@RequestMapping("/getJlServiceByOrderCode.do")
	public Object getJlServiceByOrderCode(@RequestParam String json){
		Map<String, String> beanMap = CommonUtil.jsonToMap(json);
		JlService jls = zcCrowdfundingService.getByOrderCode(beanMap.get("orderCode"));
		return new ApiResponse<JlService>(jls);
	}
	
	//根据手机号查找
	@ResponseBody
	@RequestMapping("/findJlServiceByPhone.do")
	public Object findJlServiceByPhone(@RequestParam String json){
		Map<String, String> beanMap = CommonUtil.jsonToMap(json);
		List<JlService> jls = zcCrowdfundingService.findByPhone(beanMap.get("phone"));
		return new ApiResponse<List<JlService>>(jls);
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getBannerByNum.do")
	public Object getBannerByNum(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		int bannerNo = 0;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			if ("".equals(map.get("bannerNo")) || map.get("bannerNo") == null) {
				bannerNo = 3;
			} else {
				bannerNo = Integer.valueOf((String) map.get("bannerNo"));
			}
			return commonJsonMap.autoMap(crowdfoundService.getBannerByNum(bannerNo), mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getActivityList.do")
	public Object getActivityList(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(crowdfoundService.getActivityList(), mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getCrowdItemType.do")
	public Object getCrowdItemType(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String regionName = null;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			regionName = (String)map.get("regionName");
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(crowdfoundService.getCrowdItemType(regionName), mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getActivityHotItem.do")
	public Object getActivityHotItem(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(crowdfoundService.getActivityHotItem(map), mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/findCrowdItemList.do")
	public Object findCrowdItemList(HttpServletRequest request) {
		List<ResultItemList> actitemlist = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			currentPage = (String) map.get("curpage");
			if (map.get("categoryId") != null && !"".equals(map.get("categoryId"))) {
				pagination.put("categoryId", map.get("categoryId"));
			}
			pagination.put("currentPage", currentPage);

			map = PaginationInterceptor.pagination(map);
			actitemlist = crowdfoundService.findCrowdItemList(map);

			// 查询下一个页面数否有数据，如无则返回hastnest为false
			map = PaginationInterceptor.nextPagination(map);
			if (actitemlist.size() > 0) {
				pagination.put("hasnext", true);
			} else {
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(actitemlist, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getCrowdItemDetail.do")
	public Object getCrowdItemDetail(HttpServletRequest request) {
		ActivityItem activityItem = new ActivityItem();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			int itemId = (int) map.get("itemId");
			activityItem = crowdfoundService.getCrowdItemDetail(itemId);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(activityItem, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/newCrowdOrder.do")
	public Object newCrowdOrder(HttpServletRequest request) {
		ActivityItem activityItem = new ActivityItem();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			mapBusi.put("itemId", CommonUtil.stringToInteger((String) map.get("itemId")));
			activityItem = crowdfoundService.newCrowdOrder(mapBusi);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(activityItem, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getSupplierById.do")
	public Object getSupplierById(HttpServletRequest request) {
		Supplier supplier = new Supplier();
		Map<String, Object> map = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			supplier = crowdfoundService.getSupplierById((Integer) map.get("spId"));
			resultMap.put("id", supplier.getId());
			resultMap.put("spName", supplier.getSpName());
			resultMap.put("address", supplier.getAddress());
			resultMap.put("pointx", supplier.getPointx());
			resultMap.put("pointy", supplier.getPointy());
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(resultMap, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/confirmOrder.do")
	public Object confirmOrder(HttpServletRequest request) {
		Map<String, Object> mp = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			mapBusi.put("itemId", CommonUtil.stringToInteger((String) map.get("itemId")));
			mapBusi.put("memberId", CommonUtil.stringToInteger((String) map.get("memberId")));
			mapBusi.put("orderNum", CommonUtil.stringToInteger((String) map.get("orderNum")));
			mapBusi.put("type", map.get("type")); // '1':定金，'2':尾款
			mp = crowdfoundService.confirmOrder(mapBusi);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(mp, mapBusi);
		}
	}

	/** 网页发起支付请求 */
	@ResponseBody
	@RequestMapping("/payCrowdItem.do")
	public Object payCrowdItem(HttpServletRequest request, @RequestParam String json) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			mapBusi.put("orderCode", map.get("orderCode").toString());
			mapBusi.put("paymentModel", map.get("paymentModel").toString()); // 1 支付宝, 2  易宝,3微信支付
			mapBusi.put("type", map.get("type").toString()); // '1':定金，'2':尾款
			mapBusi.put("source", map.get("source").toString()); // 支付来源，1：微信，2：Android，3：IOS
			mapBusi.put("returnUrl", (String) map.get("returnUrl")); // 支付成功回调页面
			mapBusi.put("redFee", map.get("redFee")); // 支付成功回调页面
			mapBusi.put("mobile", map.get("mobile")); //手机推荐号码
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		}
		Object result = crowdfoundService.payCrowdItem(mapBusi);
		return commonJsonMap.autoMap(result, mapBusi);
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getMyActiivityOrderList.do")
	public Object getMyActiivityOrderList(HttpServletRequest request) {
		Map<String, Object> map = null;
		List<Map<String, Object>> list = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			mapBusi.put("memberId", map.get("memberId"));
			if(map.get("moneyState")!=null){
				if("5".equals(map.get("moneyState").toString())){
					mapBusi.put("mState", String.valueOf(map.get("moneyState")));
				}else{
					mapBusi.put("moneyState", String.valueOf(map.get("moneyState")));
				}
			}if(map.get("expressState")!=null){
				mapBusi.put("expressState", String.valueOf(map.get("expressState")));
			}
			list = crowdfoundService.getMyActiivityOrderList(mapBusi);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getMyActiivityList.do")
	public Object getMyActiivityList(HttpServletRequest request) {
		Map<String, Object> map = null;
		List<Map<String, Object>> list = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			mapBusi.put("memberId", map.get("memberId"));
			list = crowdfoundService.getMyActiivityList(mapBusi);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/updateOrderStatus.do")
	public Object updateOrderStatus(HttpServletRequest request) {
		boolean flag = false;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			ActiivityOrder actiivityOrder = new ActiivityOrder();
			if(map.get("orderId")!= null){
				actiivityOrder.setId(Integer.valueOf(map.get("orderId").toString()));
			}if(map.get("moneyState")!=null){
				actiivityOrder.setMoneyState(Integer.valueOf(map.get("moneyState").toString()));
			}if(map.get("expressState")!=null){
				actiivityOrder.setExpressState(Integer.valueOf(map.get("expressState").toString()));
			}
			flag = crowdfoundService.updateOrderStatus(actiivityOrder);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(flag, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getJoinUserInfo.do")
	public Object getJoinUserInfo(HttpServletRequest request) {
		List<Map<String, Object>> actitemlist = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Integer pageNo = 1;
		Integer pageSize = 20;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			pageNo = (Integer) map.get("pageNo");
			pageSize = (Integer) map.get("pageSize");
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 20;
			}
			if (map.get("activityId") != null) {
				pagination.put("activityId", map.get("activityId"));
			}
			if (map.get("itemId") != null) {
				pagination.put("itemId", map.get("itemId"));
			}
			pagination.put("pageNo", pageNo);
			pagination.put("pageSize", pageSize);

			map = PaginationInterceptor.pagination(map);
			
			map.put("pageNo", pageNo);
			map.put("pageSize", pageSize);
			actitemlist = crowdfoundService.getJoinUserInfo(map);

			// 查询下一个页面数否有数据，如无则返回hastnest为false
			map = PaginationInterceptor.nextPagination(map);
			if (actitemlist.size() > 0) {
				pagination.put("hasnext", true);
			} else {
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(actitemlist, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getPayInfoByQrCode.do")
	public Object getPayInfoByQrCode(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			resultMap = crowdfoundService.getPayInfoByQrCode(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(resultMap, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/paymentByQrCode.do")
	public Object paymentByQrCode(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return crowdfoundService.paymentByQrCode(map);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getBillListById.do")
	public Object getBillListById(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String memberId = (String) map.get("memberId");
			resultMap = crowdfoundService.getBillListById(memberId);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(resultMap, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/refundOrder.do")
	public Object refundOrder(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			crowdfoundService.refundOrder(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(resultMap, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getBillDetailsById.do")
	public Object getBillDetailsById(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String recordId = (String) map.get("recordId");
			resultMap = crowdfoundService.getBillDetailsById(recordId);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(resultMap, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getAcitvityOrderByParam.do")
	public Object getAcitvityOrderByParam(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		ActiivityOrder order = null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id = (Integer) map.get("id");
			Integer ustate = (Integer) map.get("ustate");
			String orderCode = (String) map.get("orderCode");
			if (id != null || ustate != null || StringUtils.isNotBlank(orderCode)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", id);
				params.put("ustate", ustate);
				params.put("orderCode", orderCode);
				order = activtyOrderMapper.getAcitvityOrderByParam(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(order, mapBusi);
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody @RequestMapping("/getCrowdOrderDeail.do")
	public Object getCrowdOrderDeail(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer orderId = Integer.valueOf(map.get("orderId").toString()) ;
			resultMap = crowdfoundService.getCrowdOrderDeail(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(resultMap, mapBusi);
		}
	}
	
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/refundDeposit.do")
	public Object refundDeposit(HttpServletRequest request) {
		boolean flag = false;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			RefundApplyAudit refundApplyAudit = new RefundApplyAudit();
			refundApplyAudit.setApplyUserId(Integer.valueOf(map.get("memberId").toString()));
			refundApplyAudit.setApplyAmount(Double.valueOf(map.get("fee").toString()));
			refundApplyAudit.setBusinessId(Integer.valueOf(map.get("orderId").toString()));
			refundApplyAudit.setRefundType(5);
			refundApplyAudit.setApplyReason("众筹活动退定金");
			refundApplyAudit.setStatus(0);
			flag = crowdfoundService.insertRefundAudit(refundApplyAudit);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(flag, mapBusi);
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getRegionIdList.do")
	public Object getRegionIdList(HttpServletRequest request) {
		boolean flag = false;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			int regionId = Integer.valueOf(map.get("regionId").toString()) ;
			List<Integer> rslist = new ArrayList<Integer>();
			List<Integer> list = regionService.getRegionIdList(regionId,rslist);
			for (int i = 0; i < list.size(); i++) {
				System.out.println("*******i*******=" + list.get(i));
			}
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(flag, mapBusi);
		}
	}
	
}
