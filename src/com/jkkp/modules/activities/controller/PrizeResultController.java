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
import com.jkkp.modules.activities.mapper.PrizeResultMapper;
import com.jkkp.modules.activities.model.PrizeResult;
import com.jkkp.modules.activities.service.IPrizeResultService;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;

@RequestMapping("/prizeResult")
@Controller
public class PrizeResultController extends BaseController {

	@Autowired
	private IPrizeResultService prizeResultService;
	@Autowired
	private PrizeResultMapper prizeResultMapper;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/changeStatus.do")
	public Object changeStatus(HttpServletRequest request,Integer id) {
		try {
			if(CheckedUtil.isNotEmpty(id)){
				PrizeResult pr = prizeResultService.findById(id);
				pr.setIsUnclaimed(1);
				prizeResultService.update(pr);
				return new ResponsePagination(true, "修改成功！");
			}
			return new ResponsePagination(false, "修改失败！");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponsePagination(false, "系统出错，请联系管理员！");
		}
		
	}
	
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,Integer mid,Integer pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, prizeResultMapper,"findPage", "countPage");
		request.setAttribute("pagination",prizeResultService.paginationCustom());
		setAttribute(request);
		return "/activities/prizeResult_list";
	}

	@ResponseBody
	@RequestMapping(value = "/indexpage.do")
	public Object indexpage(HttpServletRequest request) {
		Pagination.setPageParams(request, prizeResultMapper,"findPage", "countPage");
		return new ResponsePagination(prizeResultService.paginationCustom());
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
