package com.jkkp.modules.product.controller;

import java.io.IOException;
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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.product.mapper.PorderListMapper;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.modules.product.service.IPorderListService;
import com.jkkp.modules.product.service.IPorderTypeService;
import com.jkkp.modules.product.view.VPorderList;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/porderList")
public class PorderListController extends BaseController {

	@Autowired
	private IPorderListService porderListService;
	@Autowired
	private PorderListMapper porderListMapper;
	@Autowired
	private IPorderTypeService porderTypeService;
	/**
	 * 查看购物清单
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/view")
	public String view(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			VPorderList porder = porderListService.findPordeerById(id);
			request.setAttribute("porder", porder);
		}
		return "/product/product_view";
	}


	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, porderListMapper, "findPage",
				"pageCount");
		request.setAttribute("pagination", porderListService.paginationCustom());
		List<PorderType> list=porderTypeService.select(null);
		request.setAttribute("porderTypeList", list);
		return "/product/porderList_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, porderListMapper, "findPage",
				"pageCount");
		return new ResponsePagination(porderListService.paginationCustom());
	}
	
	// 获取购物清单分类列表
		@ResponseBody
		@RequestMapping(value = "/selectAllType.do")
		public Object selectAllType(HttpServletRequest request,HttpServletResponse response) throws IOException {
			List<PorderType> list=porderTypeService.select(null);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("porderTypeList", list);
			return map;
		}

}
