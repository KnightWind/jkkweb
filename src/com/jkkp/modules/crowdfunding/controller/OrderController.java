package com.jkkp.modules.crowdfunding.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.CrowdOrderMapper;
import com.jkkp.modules.crowdfunding.model.CrowdExpress;
import com.jkkp.modules.crowdfunding.model.CrowdOrder;
import com.jkkp.modules.crowdfunding.service.ICrowdExpressService;
import com.jkkp.modules.crowdfunding.service.ICrowdOrderService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;

@SuppressWarnings("finally")
@RequestMapping("/material/order")
@Controller
public class OrderController extends BaseController {

	
	@Autowired
	private CrowdOrderMapper crowdOrderMapper;
	@Autowired
	private ICrowdOrderService crowdOrderService;
	@Autowired
	private ICrowdExpressService crowdExpressService;
	
	
	/**
	 * 设置订单备注信息
	 */
	@ResponseBody
	@RequestMapping("/setRemark.do")
	public Object setRemark(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false)Integer id,@RequestParam(required=false)String remark){
		Map<String, Object> resulMap = new HashMap<String, Object>();
		try {
			if(remark == null) resulMap.put("code", 1);
			else{
				CrowdOrder order = crowdOrderService.findById(id);
				order.setRemark(remark);
				crowdOrderService.update(order);
				resulMap.put("code", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resulMap.put("code", 1);
		}finally{
			return resulMap;
		}
		
	}
	
	
	/**
	 * 设置订单收货地址
	 * @param id
	 * @param address
	 */
	@ResponseBody
	@RequestMapping("/setAddress.do")
	public Object setAddress(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false)Integer id,@RequestParam(required=false)String address){
		Map<String, Object> resulMap = new HashMap<String, Object>();
		try {
			if(id == null || address == null) resulMap.put("code", 1);
			else{
				CrowdOrder order = crowdOrderService.findById(id);
				if(CheckedUtil.isNotEmpty(order.getExpressId())){
					Integer ddddInteger=order.getExpressId();
					CrowdExpress express = crowdExpressService.findById(ddddInteger);
					express.setAddress(address);
					crowdExpressService.update(express);
				}else{
					CrowdExpress express = new CrowdExpress(address,new Date());
					crowdExpressService.save(express);
					order.setExpressId(express.getId());
					crowdOrderService.update(order);
				}
				resulMap.put("code", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resulMap.put("code", 1);
		}finally{
			return resulMap;
		}
	}
	
	
	/**
	 * 用户定单列表
	 * @param request
	 */
	@AccessMenu
	@RequestMapping("/participation")
	public String participation(HttpServletRequest request,HttpServletResponse response,Integer itemId){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spId", su.getSpId());
		}
		params.put("itemId", itemId);
		Pagination.setPageParams(request, crowdOrderMapper,"findPage", "countPage");
		request.setAttribute("pagination", crowdOrderService.paginationCustom(params));
		return "materials/activity/zc_partici_list";
	}
	
	
	/**
	 * 用户定单列表分页
	 */
	@ResponseBody
	@RequestMapping(value = "/participationPage.do")
	public Object participationPage(HttpServletRequest request,HttpServletResponse response) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spId", su.getSpId());
		}
		Pagination.setPageParams(request, crowdOrderMapper,"findPage", "countPage");
		return new ResponsePagination(crowdOrderService.paginationCustom(params));
	}
	
	
}
