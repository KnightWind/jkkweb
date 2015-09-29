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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.product.mapper.PorderTypeMapper;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.modules.product.service.IPorderTypeService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/porderType")
public class PorderTypeController extends BaseController {

	@Autowired
	private IPorderTypeService porderTypeService;
	@Autowired
	private PorderTypeMapper porderTypeMapper;

	// 购物清单分类列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, porderTypeMapper,
				"selectAllPorderType", "selectPorderTypeCount");
		request.setAttribute("pagination", porderTypeService.paginationCustom());
		return "/product/porderType_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, porderTypeMapper,
				"selectAllPorderType", "selectPorderTypeCount");
		return new ResponsePagination(porderTypeService.paginationCustom());
	}

	// 隐藏清单分类
	@ResponseBody
	@RequestMapping(value = "/hide.do")
	public Object hide(int id) {
		try {
			porderTypeService.hide(id);
			return new ResponseObject(true, "隐藏成功！");
		} catch (Exception e) {
			logger.error("购物清单隐藏出错", e);
			return new ResponseObject(false, "隐藏失败！");
		} finally {

		}
	}

	// 显示清单分类
	@ResponseBody
	@RequestMapping(value = "/show.do")
	public Object show(int id) {
		try {
			porderTypeService.show(id);
			return new ResponseObject(true, "隐藏成功！");
		} catch (Exception e) {
			logger.error("购物清单隐藏出错", e);
			return new ResponseObject(false, "隐藏失败！");
		} finally {

		}
	}

	// 保存购物清单分类
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save(PorderType pt) {
		try {
			if (pt.getTypeName() == null || pt.getTypeName().trim() == "") {
				return new ResponseObject(false, "请输入分类名称！");
			}
			porderTypeService.saveOrUpdate(pt);
			return new ResponseObject(true, "操作成功！");
		} catch (Exception e) {
			logger.error("保存购物清单分类出错", e);
			return new ResponseObject(false, "操作失败！");
		} finally {

		}
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
