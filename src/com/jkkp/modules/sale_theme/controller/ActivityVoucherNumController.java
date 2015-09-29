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
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherMapper;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherNumMapper;
import com.jkkp.modules.sale_theme.service.IActivityVoucherNumService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("wxActivityVoucherNum")
public class ActivityVoucherNumController extends BaseController {

	@Autowired
	private IActivityVoucherNumService activityVoucherNumService;
	@Autowired
	private ActivityVoucherNumMapper activityVoucherNumMapper;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityVoucherNumMapper,"selectAllActivityVoucherNum","selectAllActivityVoucherNumCount");
		request.setAttribute("pagination",activityVoucherNumService.paginationCustom());
		return "/saleActivity/activityVoucherNum_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityVoucherNumMapper,"selectAllActivityVoucherNum","selectAllActivityVoucherNumCount");
		return new ResponsePagination(activityVoucherNumService.paginationCustom());
	}
}
