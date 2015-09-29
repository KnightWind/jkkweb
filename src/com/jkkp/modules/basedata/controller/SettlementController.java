package com.jkkp.modules.basedata.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.SettlementMapper;
import com.jkkp.modules.basedata.model.Settlement;
import com.jkkp.modules.basedata.service.ISettlementService;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;


@RequestMapping("/admin/settlement")
@Controller
public class SettlementController extends BaseController {
	
	private final static String REDIRECT_INDEX_URL = "redirect:settlementByPhone.xhtml";
	
	@Autowired
	private ISettlementService settlementService;
	@Autowired
	private SettlementMapper settlementMapper;
	
	
	
	@AccessMenu
	@RequestMapping("/settlementById")
	public String settlementById(HttpServletRequest request,Integer id,Integer op){
		if(id != null){
			Settlement settlement = settlementService.findById(id);
			settlement.setStatus(op);
			settlementService.update(settlement);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid","phone","role"});
		return REDIRECT_INDEX_URL + params;
	}
	
	
	
	/**
	 * 后台推荐用户支出列表
	 * @param request
	 * @param phone
	 * @return
	 */
	@AccessMenu
	@RequestMapping("/settlementByPhone")
	public String index(HttpServletRequest request,String phone,String role){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("phone", phone);
		Pagination.setPageParams(request, settlementMapper,"settlementByPhoneList","settlementByPhoneListCount");
		request.setAttribute("pagination",settlementService.paginationCustom(map));
		request.setAttribute("role", role);
		request.setAttribute("phone", phone);
		return "/basedata/settlement_byphone";
	}
	
	/**
	 * 后台推荐用户支出列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/settlementPage.do")
	public Object pagination(HttpServletRequest request,String phone,String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("phone", phone);
		Pagination.setPageParams(request, settlementMapper,"settlementByPhoneList","settlementByPhoneListCount");
		return new ResponsePagination(settlementService.paginationCustom(map));
	}
	
	
}
