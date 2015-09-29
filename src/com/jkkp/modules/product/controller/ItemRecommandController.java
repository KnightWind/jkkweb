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

import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.service.impl.ItemRecommandServiceImpl;
import com.jkkp.modules.product.service.impl.ItemServiceImpl;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/shop/em")
public class ItemRecommandController {
	@Autowired
	private ItemRecommandServiceImpl itemRecommandServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private ItemServiceImpl itemServiceImpl;

	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		return "/shop/itemrecommand";
	}

	@AccessMenu
	@RequestMapping(value = "/cha")
	public String in(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("index", itemRecommandServiceImpl.index(request.getParameter("city")));
		request.setAttribute("yugao", itemRecommandServiceImpl.yugao(request.getParameter("city")));
		request.setAttribute("city", request.getParameter("city"));
		return "/shop/itemrecommand_list";
	}

	@ResponseBody
	@RequestMapping(value = "/so.do")
	public Object searchItem(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		List<Item> list = itemServiceImpl.name("%" + name + "%");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	@AccessMenu
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save (@RequestParam String itemIds,@RequestParam String city,@RequestParam String it,@RequestParam String id) {
		Integer[] itemIdList = CommonUtil.stringToIntegerArray(itemIds);
		Integer[] itId= CommonUtil.stringToIntegerArray(it);
		Integer[] iId = CommonUtil.stringToIntegerArray(id);
		itemRecommandServiceImpl.dele(city);
		itemRecommandServiceImpl.saveOrUpdate(itemIdList, city, itId, iId);	
		return new ResponseObject(true);
	}
}
