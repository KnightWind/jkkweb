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
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherMapper;
import com.jkkp.modules.sale_theme.service.IActivityVoucherService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;

/**
 * 建材商现金券核销页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/materials/activityVoucher")
public class ActivityVoucherJCController extends BaseController {

	@Autowired
	private IActivityVoucherService activityVoucherService;
	@Autowired
	private ActivityVoucherMapper activityVoucherMapper;
	@Autowired
	private AttachmentServiceImpl impl;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map=new HashMap<String, Object>();
        VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
        if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, activityVoucherMapper,"selectSupplierVoucher", "selectSupplierVoucherCount");
		
		request.setAttribute("pagination",activityVoucherService.paginationCustom(map));
		request.setAttribute("basePath", impl.getAccessPath());
		return "/materials/order/activityVoucher";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map=new HashMap<String, Object>();
        VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
        if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, activityVoucherMapper,"selectSupplierVoucher", "selectSupplierVoucherCount");
		return new ResponsePagination(activityVoucherService.paginationCustom(map));
	}
	
	@ResponseBody
	@RequestMapping(value="/useActivityVoucher.do")
	public Object useActivityVoucher(Integer id){
		try {
			activityVoucherService.useActivityVoucher(id);
			return new ResponseObject(true, "核销成功");
		} catch (Exception e) {
		    logger.error("建材商核销现金券出错");
		    return new ResponseObject(false, "核销失败");
		}
	}
}
