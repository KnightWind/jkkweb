package com.jkkp.modules.supplier.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.supplier.mapper.QuanMapper;
import com.jkkp.modules.supplier.service.IQuanService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/quanCard")
public class QuanCardController extends BaseController {

	@Autowired
	private IQuanService quanService;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private QuanMapper quanMapper;

	// 代金券管理
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping("/index")
	// public String index(HttpServletRequest request) {
	// request.setAttribute("lst", areaDomainService.finAll());
	// request.setAttribute("pagination", quanService.pagination());
	// return "/quan/quan_list";
	// }
	
	// @ResponseBody
	// @AccessPagination(custom = true, async =
	// AccessPagination.ASYNC.ASYNC_YES)
	// @RequestMapping("/pagination.do")
	// public void pagination(HttpServletRequest request) {
	// Pagination.setIsConvert();
	// Pagination.setContext(quanService);
	// }

	// 代金券管理 原数据请求错误 20150616 黄宇健更改数据访问
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Pagination.setPageParams(request, quanMapper, "daiJinQuanList",
				"daiJinQuanCount");
		request.setAttribute("pagination", quanService.paginationCustom());
		request.setAttribute("lst", areaDomainService.finAll());
		return "/quan/quan_list";
	}

	// 代金券管理 黄宇健
	@ResponseBody
	@RequestMapping("/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, quanMapper, "daiJinQuanList",
				"daiJinQuanCount");
		return new ResponsePagination(quanService.paginationCustom());
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		request.setAttribute("city", request.getParameter("city"));
		request.setAttribute("num", request.getParameter("num"));
		request.setAttribute("view", quanService.findById(id));

		return "/quan/quan_edit";
	}

	@AccessMenu
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		return "/quan/quan_add";
	}
}
