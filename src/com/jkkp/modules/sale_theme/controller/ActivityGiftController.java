package com.jkkp.modules.sale_theme.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.sale_theme.mapper.ActivityGiftLogMapper;
import com.jkkp.modules.sale_theme.mapper.ActivityGiftMapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.service.IActivityGiftLogService;
import com.jkkp.modules.sale_theme.service.IActivityGiftService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@RequestMapping("/activityGift")
@Controller
public class ActivityGiftController extends BaseController {
	
	private static final String REDIRECT_URL = "redirect:/activityGift/index.xhtml";
	private static final String REDIRECT_TAKE_GIFT_URL = "redirect:/activityGift/takeGift.xhtml";
	
	
	@Autowired
	private IActivityGiftService activityGiftService;
	@Autowired
	private ActivityGiftMapper activityGiftMapper;
	@Autowired
	private IActivityThemeService aThemeService;
	@Autowired
	private IActivityCardService activityCardService;
	@Autowired
	private IActivityGiftLogService activityGiftLogService;
	@Autowired
	private ActivityGiftLogMapper activityGiftLogMapper;
	
	
	
	
	
	@RequestMapping("/take")
	public String take(HttpServletRequest request,Integer id){
		if(id != null){
			ActivityGiftLog log = activityGiftLogService.findById(id);
			log.setUsed(true);
			log.setUpdateTime(new Date());
			activityGiftLogService.update(log);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return  REDIRECT_TAKE_GIFT_URL + params;
	}
	
	
	
	/**
	 * 后台活动礼包领取列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("takeGift")
	public String takeGift(HttpServletRequest request,String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", status == null ? 0 : status);
		Pagination.setPageParams(request, activityGiftLogMapper,"takeGift", "takeGiftCount");
		request.setAttribute("pagination", activityGiftLogService.paginationCustom(paramMap));
		request.setAttribute("status",status == null ? 0 : status);
		return "/saleActivity/take_gift";
	}

	/**
	 * 后台活动礼包领取列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/takeGiftPagination.do")
	public Object takeGiftPagination(HttpServletRequest request,String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", status == null ? String.valueOf(0) : status);
		Pagination.setPageParams(request, activityGiftLogMapper,"takeGift", "takeGiftCount");
		return new ResponsePagination(activityGiftLogService.paginationCustom(paramMap));
	}
	
	
	/**
	 * 保存
	 * @param request
	 * @param id
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,ActivityGift gift){
		activityGiftService.savaOrUpdate(gift);
		String urlParams = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return REDIRECT_URL + urlParams;
	}
	
	
	/**
	 * 后台活动礼包管理列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityGiftMapper,"selectActivityGift", "selectActivityGiftCount");
		request.setAttribute("pagination", activityGiftService.paginationCustom());
		List<ActivityTheme> aThemelist = aThemeService.select(null);
		for (ActivityTheme at : aThemelist) {
			at.setTitle(CheckedUtil.splitString(at.getTitle(), 10));
		}
		List<ActivityCard> aCardlist = activityCardService.select(null);
		request.setAttribute("aThemelist", aThemelist);
		request.setAttribute("aCardlist", aCardlist);
		return "/saleActivity/activityGift_list";
	}

	/**
	 * 后台活动礼包管理列表 分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityGiftMapper,"selectActivityGift", "selectActivityGiftCount");
		return new ResponsePagination(activityGiftService.paginationCustom());
	}
}
