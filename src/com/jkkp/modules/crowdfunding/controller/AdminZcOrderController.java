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
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.service.IZcOrderService;
import com.jkkp.utils.Pagination;
/**
 * 众凑订单列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("adminZcOrder")
public class AdminZcOrderController extends BaseController {
	
	@Autowired
	private IZcOrderService zcOrderService;
	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activtyOrderMapper,"selectAllSupplierZCOrder", "selectAllSupplierZCOrderCount");
		request.setAttribute("pagination",zcOrderService.paginationCustom());
		return "/materials/admin/zcOrder";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activtyOrderMapper,"selectAllSupplierZCOrder", "selectAllSupplierZCOrderCount");
		return new ResponsePagination(zcOrderService.paginationCustom());
	}
}
