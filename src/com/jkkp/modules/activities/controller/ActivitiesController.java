package com.jkkp.modules.activities.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.activities.mapper.ActivitiesMapper;
import com.jkkp.modules.activities.model.Activities;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.service.IActivitiesService;
import com.jkkp.modules.activities.service.IAwardsService;
import com.jkkp.modules.activities.view.VActivities;
import com.jkkp.modules.sale_theme.service.IActivityLotteryLogService;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;
import com.jkkp.modules.sale_theme.vo.LotteryResult;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;

@RequestMapping("/activities")
@Controller
public class ActivitiesController extends BaseController {
	
	@Autowired
	private IActivitiesService activitiesService;
	@Autowired
	private ActivitiesMapper activitiesMapper;
	@Autowired
	private IAwardsService awardsService;
	@Autowired
	private IActivityLotteryLogService iallService;
	
	@ResponseBody
	@RequestMapping("/findAwardItems")
	public String findAwardItems(Integer saleThemeId) {
		List<Awards> awardList = activitiesService.findAwards(saleThemeId);
		if(awardList != null) {
			//清空无用字段的值
			for(Awards ad : awardList) {
				ad.setId(null);
				ad.setActiveId(null);
				ad.setCreatTime(null);
				ad.setProbability(null);
			}
		}
		String json = JSONObject.toJSONString(awardList);
		System.out.println("json=" + json);
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/getLuckyAward")
	public String getLuckyAward(Integer saleThemeId, String phone) {
		LotteryResult result = activitiesService.getLuckyAward(saleThemeId, phone);
		if(result != null) {
			Awards ad = result.getAwards();
			if(ad != null) {
				ad.setId(null);
				ad.setActiveId(null);
				ad.setCreatTime(null);
				ad.setProbability(null);				
			}
		}
		String json = JSONObject.toJSONString(result);
		System.out.println("lottery result =" + json);
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/findLuckyPeople")
	public String findLukyPeople(Integer saleThemeId, Boolean win, Boolean receive, Integer pageNo, Integer pageSize) {
		if(pageNo == null || pageNo < 1) {
			pageNo = 1;
		}
		if(pageSize == null || pageSize < 1) {
			pageSize = 20;
		}
		List<VActivityLotteryLog> list = iallService.findLuckyPeopleAwards(saleThemeId, null, win, receive, pageNo, pageSize);
		String json = JSONObject.toJSONString(list);
		System.out.println("lottery result =" + json);
		return "/activities/lucky_people";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public Object remove(HttpServletRequest request,Integer id) {
		try {
			if(CheckedUtil.isNotEmpty(id)){
				activitiesService.deleteById(id);
				return new ResponsePagination(true, "删除成功！");
			}
			return new ResponsePagination(false, "奖项不存在！");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponsePagination(false, "系统出错，请联系管理员！");
		}
		
	}
	
	@RequestMapping(value = "/savAwards",method=RequestMethod.POST)
	public String savAwards(HttpServletRequest request,Integer mid,Integer pid){
		String id = request.getParameter("id");
		Integer rank = Integer.parseInt(request.getParameter("rank"));
		String prizeName = request.getParameter("prizeName");
		String prizeNum = request.getParameter("prizeNum");
		String probability = request.getParameter("probability");
		String activeId = request.getParameter("activeId");
		Awards awards = new Awards(id == null || id.equals("") ? null : Integer.valueOf(id), rank, prizeName, 
		Integer.valueOf(prizeNum), Float.valueOf(probability),
		new Date(), activeId == null ? null : Integer.valueOf(activeId));
		activitiesService.saveOrUpdate(awards);
		return "redirect:/awards/index.xhtml?mid="+mid+"&pid="+pid+"&id="+activeId;
	}
	
	@AccessMenu
	@RequestMapping(value = "/addAwards")
	public String editAwards(HttpServletRequest request,@RequestParam(required=false)Integer id,Integer mid,Integer pid){
		if(id != null){
			VActivities act = activitiesService.findByActId(id);
			request.setAttribute("act", act);
			List<Awards> asas = awardsService.selectByProperty("activeId", id);
			request.setAttribute("asas", asas);
		}
		setAttribute(request);
		return "/activities/awards_edit";
	}
	
	
	
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,Activities activities,Integer mid,Integer pid,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date starDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate){
		activities.setStartTime(starDate);
		activities.setEndTime(endDate);
		
		activitiesService.saveOrUpdate(activities);
		return "redirect:index.xhtml?mid="+mid+"&pid="+pid;
	}
	
	
	@AccessMenu
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request,@RequestParam(required=false)Integer id,Integer mid,Integer pid){
		if(id != null){
			VActivities act = activitiesService.findByActId(id);
			request.setAttribute("act", act);
		}
		setAttribute(request);
		return "/activities/activities_edit";
	}
	
	@AccessMenu
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,@RequestParam(required=false)Integer id){
		if(id != null){
			VActivities act = activitiesService.findByActId(id);
			List<Awards> asas = awardsService.selectByProperty("activeId", id);
			request.setAttribute("asas", asas);
			request.setAttribute("act", act);
		}
		setAttribute(request);
		return "/activities/activities_view";
	}
	
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,Integer mid,Integer pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, activitiesMapper,"findPage", "countPage");
		request.setAttribute("pagination",activitiesService.paginationCustom());
		setAttribute(request);
		return "/activities/activities_list";
	}

	@ResponseBody
	@RequestMapping(value = "/indexpage.do")
	public Object indexpage(HttpServletRequest request) {
		Pagination.setPageParams(request, activitiesMapper,"findPage", "countPage");
		return new ResponsePagination(activitiesService.paginationCustom());
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
