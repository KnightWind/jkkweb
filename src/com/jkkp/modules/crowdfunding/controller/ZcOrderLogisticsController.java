package com.jkkp.modules.crowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.service.IZcOrderService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;

/**
 * 众筹订单并发货
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/materials/logistics")
public class ZcOrderLogisticsController extends BaseController {


	@Autowired
	private IZcOrderService zcOrderService;
	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map=new HashMap<String, Object>();
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, activtyOrderMapper,"selectAllSupplierZCOrder", "selectAllSupplierZCOrderCount");
		request.setAttribute("pagination",zcOrderService.paginationCustom(map));
		return "/materials/order/orderLogistics";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map=new HashMap<String, Object>();
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, activtyOrderMapper,"selectAllSupplierZCOrder", "selectAllSupplierZCOrderCount");
		return new ResponsePagination(zcOrderService.paginationCustom(map));
	}
	
	@ResponseBody
	@RequestMapping(value="/updateZcOrderExpressToOne.do")
	public Object updateZcOrderExpressToOne(Integer orderId){
		try {
			zcOrderService.updateZcOrderExpressToOne(orderId);
			return new ResponseObject(true,"发货成功");
		} catch (Exception e) {
			logger.error("众筹订单店面发货出错");
			return new ResponseObject(false,"发货出错");
		}
	}
	
}
