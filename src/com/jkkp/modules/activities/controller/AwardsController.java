package com.jkkp.modules.activities.controller;

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
import com.jkkp.modules.activities.mapper.AwardsMapper;
import com.jkkp.modules.activities.service.IActivitiesService;
import com.jkkp.modules.activities.service.IAwardsService;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;

@RequestMapping("/awards")
@Controller
public class AwardsController extends BaseController {

	@Autowired
	private IAwardsService awardsService;
	@Autowired
	private AwardsMapper awardsMapper;
	@Autowired
	private IActivitiesService activitiesService;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,Integer id,Integer mid,Integer pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, awardsMapper,"findPage", "countPage");
		request.setAttribute("pagination",awardsService.paginationCustom(params));
		request.setAttribute("act",activitiesService.findById(id));
		setAttribute(request);
		return "/activities/awards_list";
	}

	@ResponseBody
	@RequestMapping(value = "/indexpage.do")
	public Object indexpage(HttpServletRequest request) {
		Pagination.setPageParams(request, awardsMapper,"findPage", "countPage");
		return new ResponsePagination(awardsService.paginationCustom());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public Object remove(HttpServletRequest request,Integer id) {
		try {
			if(CheckedUtil.isNotEmpty(id)){
				awardsService.deleteById(id);
				return new ResponsePagination(true, "删除成功！");
			}
			return new ResponsePagination(false, "奖项不存在！");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponsePagination(false, "系统出错，请联系管理员！");
		}
		
	}
	
	
	/**
	 * 设置后台页面标签参数
	 * @param request
	 */
	public void setAttribute(HttpServletRequest request){
		request.setAttribute("mid", request.getParameter("mid"));
		request.setAttribute("pid", request.getParameter("pid"));
	}
	
	
}
