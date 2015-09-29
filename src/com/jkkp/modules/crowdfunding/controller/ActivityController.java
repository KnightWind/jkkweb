package com.jkkp.modules.crowdfunding.controller;

import java.io.IOException;
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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.CrowdActitvityMapper;
import com.jkkp.modules.crowdfunding.mapper.CrowdItemMapper;
import com.jkkp.modules.crowdfunding.service.ICrowdActitvityService;
import com.jkkp.modules.crowdfunding.service.ICrowdItemService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/material/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ICrowdActitvityService crowdActitvityService;
	@Autowired
	private CrowdActitvityMapper crowdActitvityMapper;
	@Autowired
	private ICrowdItemService crowdItemService;
	@Autowired
	private CrowdItemMapper crowdItemMapper;
	 
	
	
	
	/**
	 * 商家参与的众筹活动列表
	 * @param request
	 * @param url 跳转地址
	 * @return
	 * @throws IOException 
	 */
	@AccessMenu
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response,String url) throws IOException{
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		if(url.contains("zc")){
			if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		}
		String status = request.getParameter("status");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status == null ? -2 : status);
		if(su != null){
			params.put("spId", su.getSpId());
		}
		Pagination.setPageParams(request, crowdItemMapper,"findPage", "countPage");
		request.setAttribute("pagination", crowdItemService.paginationCustom(params));
		request.setAttribute("url", url);
		request.setAttribute("status", status);
		return "materials/activity/" + url;
	}
	
	
	/**
	 * 商家参与的众筹活动列表上下分页
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,HttpServletResponse response,String url) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		if(url.contains("zc")){
			if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		}
		String status = request.getParameter("status");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status == null ? -2 : Integer.valueOf(status));
		if(su != null){
			params.put("spId", su.getSpId());
		}
		Pagination.setPageParams(request, crowdItemMapper,"findPage", "countPage");
		return new ResponsePagination(crowdActitvityService.paginationCustom(params));
	}
	
	@RequestMapping(value = "/zcActivityIndex")
	public String activityList(){
		return "materials/activity/zc_activity_index";
		
	}
	
	@AccessMenu
	@RequestMapping(value = "/adActivityIndex")
	public String adminActivityList(){
		return "materials/activity/ad_activity_index";
		
	}
	
}
