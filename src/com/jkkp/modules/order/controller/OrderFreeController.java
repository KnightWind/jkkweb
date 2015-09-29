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
import com.jkkp.modules.order.mapper.OrderFreeMapper;
import com.jkkp.modules.order.service.IOrderFreeService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/orderFree")
public class OrderFreeController extends BaseController {

	@Autowired
	private IOrderFreeService orderFreeService;
	@Autowired
	private OrderFreeMapper orderFreeMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request){
		request.setAttribute("pagination",orderFreeService.pagination());
		return "/order/orderFree_list";
	}
	
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(orderFreeService);
	}

	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/free_list")
	public String freeList(HttpServletRequest request){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, orderFreeMapper, "findOrderFreeBySpId","findOrderFreeBySpIdCount");
		request.setAttribute("pagination", orderFreeService.paginationCustom(params));
		return "/order/free_list";
	}
	
	@ResponseBody
	@RequestMapping("/paginationFreeList.do")
	public Object paginationFreeList(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, orderFreeMapper, "findOrderFreeBySpId","findOrderFreeBySpIdCount");
		return new ResponsePagination(orderFreeService.paginationCustom(params));
	}
}
