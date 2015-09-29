package com.jkkp.modules.order.controller;

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
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.order.mapper.OrderQuanMapper;
import com.jkkp.modules.order.service.IOrderQuanService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/orderQuan")
public class OrderQuanController extends BaseController {

	@Autowired
	private IOrderQuanService orderQuanService;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private OrderQuanMapper orderQuanMapper;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String orderIdList(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		request.setAttribute("pagination", orderQuanService.pagination());
		request.setAttribute("areaDomain", areaDomainService.finAll());
		return "/order/orderQuan_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(orderQuanService);
	}

	// 券退款列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/refund")
	public String quanRefund(HttpServletRequest request) {
		Pagination.setPageParams(request, orderQuanMapper,
				"selectAllQuanRefund", "resultCount");
		request.setAttribute("pagination", orderQuanService.paginationCustom());
		request.setAttribute("areaDomain", areaDomainService.finAll());
		return "/quan/quan_refund";
	}

	// 券退款列表
	@ResponseBody
	@RequestMapping(value = "/refundPage.do")
	public Object refundPage(HttpServletRequest request) {
		Pagination.setPageParams(request, orderQuanMapper,
				"selectAllQuanRefund", "resultCount");
		return new ResponsePagination(orderQuanService.paginationCustom());
	}
}
