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
import com.jkkp.modules.sale_theme.mapper.ActivityThemeMapper;
import com.jkkp.modules.sale_theme.service.impl.ActivityThemeService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/activityTheme")
public class ActivityThemeController extends BaseController {
	@Autowired
	private ActivityThemeService atService;
	@Autowired
	private ActivityThemeMapper activityThemeMapper;

	// 微信引流活动管理
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityThemeMapper,"selectAllActivityTheme", "selectAllActivityThemeCount");
		request.setAttribute("pagination", atService.paginationCustom());
		return "/saleActivity/activityTheme_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityThemeMapper,"selectAllActivityTheme", "selectAllActivityThemeCount");
		return new ResponsePagination(atService.paginationCustom());
	}

}
