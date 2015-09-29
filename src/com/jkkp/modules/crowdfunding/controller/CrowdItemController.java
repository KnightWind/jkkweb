package com.jkkp.modules.crowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.crowdfunding.mapper.CrowdItemMapper;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.crowdfunding.service.ICrowdItemService;
import com.jkkp.modules.crowdfunding.view.VCrowdItem;
import com.jkkp.utils.Escape;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;


@RequestMapping("/material/crowdItem")
@Controller
public class CrowdItemController {

	
	@Autowired
	private CrowdItemMapper crowdItemMapper;
	@Autowired
	private ICrowdItemService crowdItemService;
	
	
	private final static String REDIRECT_ACTIVITY_URL = "redirect:/material/crowdItem/cancelList.xhtml";
	
	/**setStatus() -1未通过审核，0待审核，1审核通过，2.申请取消众筹商品，3，审核取消众筹商品通过，4.审核取消众筹商品未通过
	 * 审核众筹商品页面
	 * @param request
	 * @return
	 */
	@AccessMenu
	@RequestMapping(value="/oper")
	public String oper(HttpServletRequest request,Integer id,Integer op){
		CrowdItem item = crowdItemService.findById(id);
		item.setStatus(op);
		crowdItemService.update(item);
		String param = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return REDIRECT_ACTIVITY_URL + param;
	}
	
	
	
	/**
	 * 审核众筹商品页面
	 * @param request
	 * @return
	 */
	@AccessMenu
	@RequestMapping(value="/view")
	public String view(HttpServletRequest request,Integer id){
		VCrowdItem item = crowdItemService.itemInfo(id);
		if(item.getDetail() != null)
			item.setDetail(Escape.unescape(item.getDetail()));
		request.setAttribute("item", item);
		return "/materials/item/ad_item_view";
	}
	
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/cancelList")
	public String cancelList(HttpServletRequest request,Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status == null) status = 2;
		params.put("status", status);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, crowdItemMapper,"cancelCrowdItemList", "cancelCrowdItemListCount");
		request.setAttribute("pagination", crowdItemService.paginationCustom(params));
		request.setAttribute("status", status);
		return "/materials/item/zcitem_cancel_list";
	}

	@ResponseBody
	@RequestMapping("/cancelListPage.do")
	public Object cancelListPage(HttpServletRequest request,Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status == null) status = 2;
		params.put("status", status);
		Pagination.setPageParams(request, crowdItemMapper,"cancelCrowdItemList", "cancelCrowdItemListCount");
		return new ResponsePagination(crowdItemService.paginationCustom(params));
	}
	
}
