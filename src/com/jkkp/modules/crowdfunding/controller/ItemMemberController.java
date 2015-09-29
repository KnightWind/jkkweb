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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.ItemMemberMapper;
import com.jkkp.modules.crowdfunding.service.IitemMemberService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;

@RequestMapping("/material/itemMember")
@Controller
public class ItemMemberController extends BaseController {

	
	@Autowired
	private IitemMemberService itemMemberService;
	@Autowired
	private ItemMemberMapper itemMemberMapper;
	
	
	/**
	 * 用户参与众筹活动列表
	 * @param request
	 * @param url 跳转地址
	 */
	@AccessMenu
	@RequestMapping("/participation")
	public String participation(HttpServletRequest request,HttpServletResponse response,Integer id,String url){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spId", 2);
		}
		params.put("itemId", id);
		Pagination.setPageParams(request, itemMemberMapper,"findPage", "countPage");
		request.setAttribute("pagination", itemMemberService.paginationCustom(params));
		return "materials/activity/" + url;
	}
	
	
	/**
	 * 用户参与众筹活动列表分页
	 */
	@ResponseBody
	@RequestMapping(value = "/participationPage.do")
	public Object participationPage(HttpServletRequest request,HttpServletResponse response) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spId", 2);
		}
		Pagination.setPageParams(request, itemMemberMapper,"findPage", "countPage");
		return new ResponsePagination(itemMemberService.paginationCustom(params));
	}
	
	
}
