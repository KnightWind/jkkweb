package com.jkkp.modules.order.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.order.service.IOrderIdService;
import com.jkkp.modules.order.service.IOrderPackageItemService;
import com.jkkp.modules.order.view.VOrderId;
import com.jkkp.modules.order.view.VOrderPackageItem;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;


@Controller
@RequestMapping("/orderId")
public class OrderIdController extends BaseController {
	@Autowired
	private IOrderIdService orderIdService;
	@Autowired
	private IAreaDomainService areaDomainService;
	
	@Autowired
	private IOrderPackageItemService orderPackageItemService;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String orderIdList(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		request.setAttribute("pagination", orderIdService.pagination());
		
		request.setAttribute("areaDomain", areaDomainService.finAll());
		return "/order/orderId_list";
	}
	
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(orderIdService);
	}
	
	@AccessMenu
	@RequestMapping("/detail")
	public String orderIdDetail(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		VOrderId o=orderIdService.findOneById(id);
		List<VOrderPackageItem> list=orderPackageItemService.orderItemDetail(o.getId());
		request.setAttribute("orderId", o);
		request.setAttribute("itemList", list);
		return "/order/orderId_detail";
	}
}
