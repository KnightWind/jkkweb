package com.jkkp.modules.crowdfunding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.ZcjsMapper;
import com.jkkp.modules.crowdfunding.service.IZcJsService;
import com.jkkp.utils.Pagination;

/**
 * 众凑结算
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/adminZcjs")
public class AdminZcjsController extends BaseController {

	@Autowired
	private IZcJsService zcJsService;
	@Autowired
	private ZcjsMapper zcjsMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, zcjsMapper,"selectAllZcjs", "selectAllZcjsCount");
		request.setAttribute("pagination", zcJsService.paginationCustom());
		return "/materials/admin/zcjs_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, zcjsMapper,"selectAllZcjs", "selectAllZcjsCount");
		return new ResponsePagination(zcJsService.paginationCustom());
	}
}
