package com.jkkp.modules.sale_theme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.sale_theme.mapper.ActivityCardMapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.service.impl.ActivityCardService;
import com.jkkp.modules.sale_theme.service.impl.ActivityThemeService;
import com.jkkp.modules.sale_theme.view.VActivityCard;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

/**
 * 活动卡
 * @author fangyang
 *
 */
@Controller
@RequestMapping("/activityCard")
public class ActivityCardController extends BaseController {
	
	@Autowired
	private ActivityCardService   acService;
	@Autowired
	private ActivityCardMapper    activityCardMapper;
	@Autowired
	private ActivityThemeService  aThemeService;
	
	/**
	 * 编辑页面
	 * @param request
	 * @param id
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request,@RequestParam(required=false)Integer id){
		if(CheckedUtil.isNotEmpty(id)){
			VActivityCard card = acService.selectActivityCardById(id);
			request.setAttribute("card", card);
		}
		return "/saleActivity/activityCard_edit";
	}
	
	/**
	 * 保存
	 * @param request
	 * @param id
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,ActivityCard card){
		acService.savaOrUpdate(card);
		String urlParams = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return "redirect:/activityCard/index.xhtml" + urlParams;
	}
	
	
	/**
	 * 后台活动卡管理列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityCardMapper,"selectActivityCard", "selectActivityCardCount");
		List<ActivityTheme> list = aThemeService.select(null);
		for (ActivityTheme at : list) {
			at.setTitle(CheckedUtil.splitString(at.getTitle(), 10));
		}
		request.setAttribute("pagination", acService.paginationCustom());
		request.setAttribute("list", list);
		return "/saleActivity/activityCard_list";
	}

	/**
	 * 后台活动卡管理列表 分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityCardMapper,"selectActivityCard", "selectActivityCardCount");
		return new ResponsePagination(acService.paginationCustom());
	}
	
}
