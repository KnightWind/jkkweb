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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.service.IZcJsService;
import com.jkkp.modules.crowdfunding.service.IZcOrderService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/material/finance")
public class FinanceController extends BaseController {
 
	@Autowired
	private IZcOrderService zcOrderService;
	@Autowired
	private IZcJsService zcJsService;
	
	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("spId", su.getSpId());
		//总收入
		Double inComeTotle=zcOrderService.selectJSTotal(su.getSpId());
		//已结算总数
		Double jsTotle=zcJsService.selectJSTotal(su.getSpId());
		
		inComeTotle=(inComeTotle==null)?0:inComeTotle;
		
		jsTotle=(jsTotle==null)?0:jsTotle;
		
		Double  overplus=inComeTotle-jsTotle;
		
		request.setAttribute("inComeTotle", inComeTotle!=0?inComeTotle:"无");
		request.setAttribute("jsTotle", jsTotle!=0?jsTotle:"无");
		request.setAttribute("overplus", overplus==0?0:overplus);
		
		Pagination.setPageParams(request, activtyOrderMapper,"selectAllSupplierZCOrder", "selectAllSupplierZCOrderCount");
		request.setAttribute("pagination",zcOrderService.paginationCustom(map));
		return "/materials/finance/orderJs";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,HttpServletResponse response) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, activtyOrderMapper,"selectAllSupplierZCOrder", "selectAllSupplierZCOrderCount");
		return new ResponsePagination(zcOrderService.paginationCustom(map));
	}
}
