package com.jkkp.modules.design.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.design.service.IBrandService;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController {

	@Autowired
	private IBrandService brandService;
	@Autowired
	private IItemCategoryService itemCategoryService;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String orderIdList(HttpServletRequest request){
		request.setAttribute("pagination",brandService.pagination());
	   return "/brand/brand_list";
	}

	
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(brandService);
	}


	@ResponseBody
	@RequestMapping("/offline.do")
	public Object offline(@RequestParam Integer id, HttpServletRequest request) {
		try {
			brandService.offline(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("下架品牌出现异常", e);
			return new ResponseObject("下架品牌出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("/online.do")
	public Object online(@RequestParam Integer id, HttpServletRequest request) {
		try {
			brandService.online(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("上架品牌出现异常", e);
			return new ResponseObject("上架品牌出现异常");
		}
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id != null && id > 0) {
			request.setAttribute("view", brandService.findById(id));
		}
		return "/brand/brand_edit";
	}
	
	
	//分类品牌-添加品牌
	@AccessMenu
	@RequestMapping("/addBrand")
	public String addBrand(HttpServletRequest request){
		ItemCategory ic=new ItemCategory();
		ic.setParentId(0);
		request.setAttribute("itemCategoryList", itemCategoryService.select(ic));
		return "/brand/beank_add";
	}
	
	//分类品牌-获取子类品牌
	@ResponseBody
	@RequestMapping("/brandChild.do")
	public List<String> child(){
		return null;
	}
	
}
