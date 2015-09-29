package com.jkkp.modules.product.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.design.service.IBrandService;

import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.model.ItemFreeRecommand;


import com.jkkp.modules.product.service.impl.ItemFreeRecommandServiceImpl;
import com.jkkp.modules.product.service.impl.ItemServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;


@Controller
@RequestMapping(value = "/shop/item")
public class ItemFreeRecommandController extends BaseController {
	@Autowired
	private ItemFreeRecommandServiceImpl itemFreeRecommandServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private ItemServiceImpl itemServiceImpl;
	@Autowired
	private IBrandService brandService;
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/shop/itemfreerecommand";
	}
	@AccessMenu
	@RequestMapping(value = "/cha")
	public String in(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("index",itemFreeRecommandServiceImpl.index(request.getParameter("city")));
		request.setAttribute("yugao",itemFreeRecommandServiceImpl.yugao(request.getParameter("city")));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("city", request.getParameter("city"));
		return "/shop/itemfreerecommand_list";
	}
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject remove(HttpServletRequest request, @RequestParam Integer id) {
		try {
			itemFreeRecommandServiceImpl.deleteById(id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		} finally {

		}
	}
	@ResponseBody
	@RequestMapping("/save")
	public String  save(HttpServletRequest request,String[] itid,String[] yu,String[] id,String[] itemId) {
		Integer mid=CommonUtil.stringToInteger(request.getParameter("mid"));
		Integer pid=CommonUtil.stringToInteger(request.getParameter("pid"));
		 return "redirect:index.xhtml?mid="+mid+"&pid="+pid+"";
	}
	@AccessMenu
	@ResponseBody
	@RequestMapping(value = "/so.do")
	public Object searchItem(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name");
		List<Item> list = itemServiceImpl.name("%"+name+"%");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	@AccessMenu
	@RequestMapping(value = "/il")
	public String lits(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/goods/ifr";
	}
	@AccessMenu
	@RequestMapping(value = "/ifr")
	public String ift(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("list",itemFreeRecommandServiceImpl.list(request.getParameter("city")));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("city",request.getParameter("city"));
		return "/goods/ifr_list";
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/xuanzhe")
	public String delete(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		Pagination.setIsConvert();
		request.setAttribute("paginationq", brandService.findPagination(null));
		request.setAttribute("pagination", itemServiceImpl.pagination());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("city",request.getParameter("city"));
		request.setAttribute("did",CommonUtil.stringToInteger(request.getParameter("did")));
		return "/goods/xuanze_list";
	}
	@AccessMenu
	@RequestMapping(value = "/mm")
	public String ifkt(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("oyg",1);
		Pagination.setSearchParams(params);
		Integer itemId=CommonUtil.stringToInteger(request.getParameter("id"));
		Integer id=CommonUtil.stringToInteger(request.getParameter("did"));
		ItemFreeRecommand itemFreeRecommand=itemFreeRecommandServiceImpl.findById(id);
		itemFreeRecommand.setItemId(itemId);
		itemFreeRecommandServiceImpl.update(itemFreeRecommand);
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("list",itemFreeRecommandServiceImpl.list(request.getParameter("city")));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("city",request.getParameter("city"));
		return "/goods/ifr_list";
	}
	@AccessMenu
	@ResponseBody
	@RequestMapping(value = "/soitem.do")
	public Object search(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name");
		List<Item> list = itemServiceImpl.fin("%"+name+"%");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	@AccessMenu
	@ResponseBody
	@RequestMapping(value = "/mjm")
	public Object save (@RequestParam String itemIds,@RequestParam String city,@RequestParam String it,@RequestParam String id) {
		Integer[] itemIdList = CommonUtil.stringToIntegerArray(itemIds);
		Integer[] itId= CommonUtil.stringToIntegerArray(it);
		Integer[] iId = CommonUtil.stringToIntegerArray(id);
		itemFreeRecommandServiceImpl.dele(city);
		itemFreeRecommandServiceImpl.saveOrUpdate(itemIdList, city, itId, iId);	
		return new ResponseObject(true);
	}
}
