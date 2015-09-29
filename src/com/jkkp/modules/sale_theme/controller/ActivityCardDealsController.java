package com.jkkp.modules.sale_theme.controller;

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
import com.jkkp.modules.sale_theme.mapper.ActivityCardDealsMapper;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("wxActivityCardDeals")
public class ActivityCardDealsController extends BaseController {
   
	
	@Autowired
	private ActivityCardDealsMapper activityCardDealsMapper;
	@Autowired
	private IActivityCardDealsService activityCardDealsService;
	
	
	
	@AccessMenu
	@RequestMapping("/saleIndex")
	public String saleIndex(HttpServletRequest request){
		Pagination.setPageParams(request, activityCardDealsMapper,"selectAllActivityCardDeals", "selectAllActivityCardDealsCount");
		request.setAttribute("pagination",activityCardDealsService.paginationCustom());
		return "/saleActivity/sale_index";
	}
	
	
	
	
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Pagination.setPageParams(request, activityCardDealsMapper,"selectAllActivityCardDeals", "selectAllActivityCardDealsCount");
		request.setAttribute("pagination",activityCardDealsService.paginationCustom(params));
		return "/saleActivity/activityCardDeals_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Pagination.setPageParams(request, activityCardDealsMapper,"selectAllActivityCardDeals", "selectAllActivityCardDealsCount");
		return new ResponsePagination(activityCardDealsService.paginationCustom(params));
	}

}
