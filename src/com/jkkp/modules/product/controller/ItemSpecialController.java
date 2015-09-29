package com.jkkp.modules.product.controller;

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
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.model.ItemSpecial;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.product.service.IItemSpecialService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/item/special")
public class ItemSpecialController extends BaseController {

	@Autowired
	private IItemSpecialService itemSpecialService;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private IItemService itemService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("pagination", itemSpecialService.pagination());
		return "/goods/itemSpecial_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(itemSpecialService);
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id != null && id > 0) {
			ItemSpecial entity = itemSpecialService.findById(id);
			Item item = itemService.findById(entity.getItemId());
			request.setAttribute("view", entity);
			request.setAttribute("item", item);
			request.setAttribute("lessAmount", item.getPrice() - entity.getDiscount());
		}
		return "/goods/itemSpecial_edit";
	}
	
	@ResponseBody
	@RequestMapping("/save.do")
	public Object save(HttpServletRequest request, ItemSpecial special) {
		try {
			itemSpecialService.saveOrUpdate(special);
			return new ResponseObject(special);
		}catch(Exception e) {
			logger.error("保存失败", e);
			return new ResponseObject("保存失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("/offline.do")
	public Object offline(@RequestParam Integer id, HttpServletRequest request) {
		try {
			itemSpecialService.offline(id);
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
			itemSpecialService.online(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("上架品牌出现异常", e);
			return new ResponseObject("上架品牌出现异常");
		}
	}
}
