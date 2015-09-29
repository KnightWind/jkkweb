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
import com.jkkp.modules.order.mapper.OrderShipMapper;
import com.jkkp.modules.order.service.IOrderShipService;
import com.jkkp.modules.order.service.impl.OrderShipServiceImpl;
import com.jkkp.modules.order.view.VOrderShip;
import com.jkkp.modules.supplier.mapper.QuanMapper;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/order/ordership")
public class OrderShipController extends BaseController{
	@Autowired
	private OrderShipServiceImpl orderShipServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private OrderShipMapper orderShipMapper;
	@Autowired
	private IOrderShipService orderShipService;
	@Autowired
	private QuanMapper	quanMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", CommonUtil.stringToInteger(request.getParameter("id")));
		Pagination.setSearchParams(params);
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		Pagination.setIsConvert();
		request.setAttribute("pagination",orderShipServiceImpl.pagination());	 
		return "/order/orderShip_list";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(orderShipServiceImpl);
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/list")
	public String lst(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", CommonUtil.stringToInteger(request.getParameter("id")));
		Pagination.setSearchParams(params);
		Pagination.setIsConvert();
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("id")));
		request.setAttribute("sname",request.getParameter("sname"));
		request.setAttribute("tool",request.getParameter("tool"));
		request.setAttribute("fc",request.getParameter("fc"));
		request.setAttribute("jsa",request.getParameter("jsa"));
		request.setAttribute("ga",request.getParameter("ga"));
		Pagination<VOrderShip> list=(Pagination<VOrderShip>) orderShipServiceImpl.pagination();
		if(list!=null){
			request.setAttribute("jj",list.getTotal());
		}
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("pagination",orderShipServiceImpl.pagination());	 
		return "/order/orderShipdele_list";
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/lst")
	public String lt(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ppid", CommonUtil.stringToInteger(request.getParameter("ppid")));
		Pagination.setSearchParams(params);
		Pagination.setIsConvert();
		request.setAttribute("pagination",orderShipServiceImpl.pagination());	 
		return "/order/orderShipde_list";
	}
	
	/**
	 * 商家服务订单结算列表
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value="/service_bill")
	public String serviceBill(HttpServletRequest request){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, orderShipMapper, "selectOrderShipBySupplierId","selectOrderShipCount");
		request.setAttribute("pagination", orderShipServiceImpl.paginationCustom(params));
		request.setAttribute("lst", areaDomainService.finAll());
		//Pagination.setIsConvert();
		//request.setAttribute("pagination",orderShipServiceImpl.pagination());
		return "order/service_bill";
	}
	
	@ResponseBody
	@RequestMapping(value = "/serviceBillPage.do")
	public Object appointmentPage(HttpServletRequest request){	
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, orderShipMapper, "selectOrderShipBySupplierId","selectOrderShipCount");
		return new ResponsePagination(orderShipServiceImpl.paginationCustom(params));
	}
	
}
