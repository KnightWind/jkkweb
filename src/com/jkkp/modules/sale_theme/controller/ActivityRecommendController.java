package com.jkkp.modules.sale_theme.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.sale_theme.mapper.ActivityRecommendMapper;
import com.jkkp.modules.sale_theme.service.IActivityRecommendService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("wxActivityRecommend")
public class ActivityRecommendController extends BaseController {

    @Autowired
    private IActivityRecommendService activityRecommendService;
    @Autowired
    private ActivityRecommendMapper activityRecommendMapper;
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityRecommendMapper,"selectAllActivityRecommend", "selectAllActivityRecommendCount");
		request.setAttribute("pagination",activityRecommendService.paginationCustom());
		return "/saleActivity/activityRecommend_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityRecommendMapper,"selectAllActivityRecommend", "selectAllActivityRecommendCount");
		return new ResponsePagination(activityRecommendService.paginationCustom());
	}
}
