package com.jkkp.modules.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.model.AreaDomain;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.design.service.IBrandService;
import com.jkkp.modules.product.mapper.ItemMapper;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.service.impl.ItemServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.JsonUtil;
import com.jkkp.utils.Pagination;


/**
 * 梁怡立
 * 
 * @author Administrator 2015-05-08
 */
@Controller
@RequestMapping(value = "/goods/item")
public class ItemController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ItemServiceImpl itemServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private ItemMapper itemMapper;
	
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",request.getParameter("state"));
		Pagination.setSearchParams(params);
		request.setAttribute("lst", areaDomainService.finAll());
		Pagination.setIsConvert();
		request.setAttribute("state",request.getParameter("state"));
		request.setAttribute("paginationq", brandService.findPagination(null));
		request.setAttribute("pagination", itemServiceImpl.pagination());
		request.setAttribute("mid",request.getParameter("mid"));
		request.setAttribute("pid",request.getParameter("pid"));
		return "/goods/item_list";
	}

	@ResponseBody
	@RequestMapping("/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",request.getParameter("state"));
		Pagination.setPageParams(request, itemMapper, "findPage", "countPage");
		return new ResponsePagination(itemServiceImpl.paginationCustom(params));
	}
	
	
	
	// 根据域名去查对应的省份
	@ResponseBody
	@RequestMapping(value = "/sheng.do")
	public Object finBy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("province");
		List<AreaDomain> list = areaDomainService.finName(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	// 根据域名去查对应的省份
	@ResponseBody
	@RequestMapping(value = "/city.do")
	public Object finByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AreaDomain areaDomain = new AreaDomain();
		areaDomain.setProvinceDomain(request.getParameter("city"));
		List<AreaDomain> list = areaDomainService.select(areaDomain);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/save.do")
	public ResponseObject add(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			Item item = JsonUtil.JsonToBean(data, Item.class);
			boolean isSave = "add".equals(request.getParameter("op"));
			if (isSave) {
				itemServiceImpl.save(item);
			} else {
				itemServiceImpl.update(item);
			}
			return new ResponseObject(true, "保存成功!");
		} catch (Exception e) {
			logger.error("保存出现异常", e);
			return new ResponseObject("保存失败!");
		} finally {

		}
	}

	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject remove(HttpServletRequest request, @RequestParam Integer id) {
		try {
			itemServiceImpl.remove(id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		} finally {

		}
	}

	@ResponseBody
	@RequestMapping(value = "/operate.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "1".equals(request.getParameter("isopen"));
			return new ResponseObject(itemServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		} finally {

		}
	}

	@ResponseBody
	@RequestMapping(value = "/oper.do")
	public Object oper(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "1".equals(request.getParameter("isopen"));
			return new ResponseObject(itemServiceImpl.oper(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		} finally {

		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchItem.do")
	public Object searchItem(HttpServletRequest request, HttpServletResponse response) {
		Item item = new Item();
		item.setSpId(CommonUtil.stringToInteger(request.getParameter("supplierid")));
		item.setTitle(request.getParameter("title"));
		List<Item> list = itemServiceImpl.select(item);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
}
