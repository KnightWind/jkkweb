package com.jkkp.modules.order.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.order.service.IRefundService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/order/refund")
public class RefundController extends BaseController {

	@Autowired
	private IRefundService refundService;
	@Autowired
	private IAreaDomainService areaDomainService;
	
	//获取商品退款列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String orderIdList(HttpServletRequest request){
		request.setAttribute("pagination",refundService.pagination());
		request.setAttribute("areaDomain", areaDomainService.finAll());
		return "/order/goodRefund_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(refundService);
	}

}
