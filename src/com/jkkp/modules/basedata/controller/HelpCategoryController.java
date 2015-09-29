package com.jkkp.modules.basedata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.model.HelpCategory;
import com.jkkp.modules.basedata.service.IHelpCategoryService;
import com.jkkp.modules.basedata.service.IHelpService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/helpCategory")
public class HelpCategoryController extends BaseController {

	@Autowired
	private IHelpCategoryService helpCategoryService;
	@Autowired
	private IHelpService helpService; 

	@AccessMenu
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Integer parentId = CommonUtil.stringToInteger(request
				.getParameter("parentId"));
		HelpCategory helpCategory = new HelpCategory();
		helpCategory.setParentId(CommonUtil.isNull(parentId, 0));
		Pagination.setIsConvert();
		request.setAttribute("pagination",
				helpCategoryService.findPagination(helpCategory, 1, 300));
		if (helpCategory.getParentId() > 0) {
			HelpCategory entity = helpCategoryService.findById(helpCategory
					.getParentId());
			request.setAttribute("helpName", entity.getName());
			request.setAttribute("parentId", entity.getParentId());
		} else {
			request.setAttribute("helpName", "一级目录");
			request.setAttribute("parentId", 1);
		}
		return "/help/help_cata";
	}

	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save(HelpCategory helpCate) {
		try {
			helpCategoryService.saveOrUpdate(helpCate);
			return new ResponseObject(true, "保存目录成功！");
		} catch (Exception e) {
			logger.error("保存目录出错", e);
			return new ResponseObject("保存目录失败！");
		} finally {

		}
	}

	@AccessMenu
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request){
		List<HelpCategory> cateParentList=helpCategoryService.selectAllParents();		
		request.setAttribute("cateParentList",cateParentList);	
		return "/help/helpCate_add";
	}
	
	//获取父类下面的子类列表
	@ResponseBody
	@RequestMapping(value = "/Subclass.do")
	public Object Subclass(HttpServletRequest request){
		int pid=CommonUtil.stringToInteger(request.getParameter("pid"));
		if(pid>0){
			List<HelpCategory> list=helpCategoryService.selectAllSubclass(pid);
			return list;
		}
		return null;
	}
	
	@AccessMenu
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request){
		int id=CommonUtil.stringToInteger(request.getParameter("id"));
		if(id>0){
			request.setAttribute("helpInfo", helpService.selectOneHelpCata(id));
		}
		return "/help/helpCate_edit";
	}
}
