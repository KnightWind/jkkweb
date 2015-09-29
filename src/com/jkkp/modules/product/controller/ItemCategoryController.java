package com.jkkp.modules.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/goods/itemCategory")
public class ItemCategoryController extends BaseController {

	@Autowired
	private IItemCategoryService itemCategoryService;

	@AccessMenu
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		Integer parentId = CommonUtil.stringToInteger(request.getParameter("parentId"));
		ItemCategory bean = new ItemCategory();
		bean.setParentId(CommonUtil.isNull(parentId, 0));
		Pagination.setIsConvert();
		request.setAttribute("pagination", itemCategoryService.findPagination(bean, 1, 1000));
		if (bean.getParentId() > 0) {
			ItemCategory entity = itemCategoryService.findById(bean.getParentId());
			request.setAttribute("categoryName", entity.getName());
			request.setAttribute("parentId", entity.getParentId());
			request.setAttribute("id", entity.getId());
		} else {
			request.setAttribute("categoryName", "一级分类");
			request.setAttribute("parentId", -1);
		}
		return "/goods/itemCategory_list";
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id != null && id > 0) {
			ItemCategory item = itemCategoryService.findById(id);
			request.setAttribute("parentName", CommonUtil.isNull(item.getParentId(), 0) == 0 ? "一级分类"
					: itemCategoryService.findNameById(item.getParentId()));
			request.setAttribute("view", item);
			request.setAttribute("parentId", item.getParentId());
		} else {
//			if (itemCategoryService.countSubItem(0) >= 7) {
//				request.setAttribute("errorMessage", true);
//				return this.index(request);
//			}
				
			ItemCategory view = new ItemCategory();
			Integer parentId = CommonUtil.isNull(CommonUtil.stringToInteger(request.getParameter("parentId")), 0);
			if (parentId > 0) {
				request.setAttribute("parentName", itemCategoryService.findNameById(parentId));
			} else {
				request.setAttribute("parentName", "一级分类");
			}
			view.setParentId(parentId);
			request.setAttribute("parentId", parentId);
			request.setAttribute("view", view);
		}
		return "/goods/itemCategory_edit";
	}

	@ResponseBody
	@RequestMapping("/save.do")
	public Object edit(HttpServletRequest request, ItemCategory entity) {
		try {
			itemCategoryService.saveOrUpdate(entity);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("保存数据出现异常", e);
			return new ResponseObject("保存数据出现异常");
		}
	}
}
