package com.jkkp.modules.sale_theme.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.sale_theme.mapper.ActivityLotteryLogMapper;
import com.jkkp.modules.sale_theme.model.ActivityLotteryLog;
import com.jkkp.modules.sale_theme.service.IActivityLotteryLogService;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@Controller
@RequestMapping("wxActivityLotteryLog")
public class ActivityLotteryLogController extends BaseController {

	private final static String REDIRECT_URL = "redirect:/wxActivityLotteryLog/takeAward.xhtml";
	
	@Autowired
	private ActivityLotteryLogMapper activityLotteryLogMapper;
	@Autowired
	private IActivityLotteryLogService activityLotteryLogService;
	
	
	
	
	@RequestMapping("/take")
	public String take(HttpServletRequest request,Integer id){
		if(id != null){
			ActivityLotteryLog log = activityLotteryLogService.findById(id);
			log.setReceive(true);
			log.setReceiveTime(new Date());
			activityLotteryLogService.update(log);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return  REDIRECT_URL + params;
	}
	
	
	/**
	 * 抽奖奖品兑换列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/takeAward")
	public String takeGift(HttpServletRequest request,String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", status == null ? 0 : status);
		Pagination.setPageParams(request, activityLotteryLogMapper,"selectAllActivityLotteryLog", "selectAllActivityLotteryLogCount");
		request.setAttribute("pagination",activityLotteryLogService.paginationCustom(paramMap));
		request.setAttribute("status",status == null ? 0 : status);
		return "/saleActivity/take_awrd";
	}
	
	/**
	 * 抽奖奖品列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/takeAwardPagination.do")
	public Object takeGiftPagination(HttpServletRequest request,String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", status == null ? 0 : status);
		Pagination.setPageParams(request, activityLotteryLogMapper,"selectAllActivityLotteryLog", "selectAllActivityLotteryLogCount");
		return new ResponsePagination(activityLotteryLogService.paginationCustom(paramMap));
	}
	
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityLotteryLogMapper,"selectAllActivityLotteryLog", "selectAllActivityLotteryLogCount");
		request.setAttribute("pagination",activityLotteryLogService.paginationCustom());
		return "/saleActivity/activityLotteryLog_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityLotteryLogMapper,"selectAllActivityLotteryLog", "selectAllActivityLotteryLogCount");
		return new ResponsePagination(activityLotteryLogService.paginationCustom());
	}
}
