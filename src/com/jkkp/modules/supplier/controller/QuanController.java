package com.jkkp.modules.supplier.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.supplier.mapper.QuanMapper;
import com.jkkp.modules.supplier.service.IQuanService;
import com.jkkp.modules.supplier.service.impl.QuanServiceImpl;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/supplier/quan")
public class QuanController extends BaseController {
	@Autowired
	private QuanServiceImpl quanServiceImpl;

	@Autowired
	private IAreaDomainService areaDomainService;

	@Autowired
	private IQuanService quanService;

	@Autowired
	private QuanMapper quanMapper;

	// 数据请求错误 20150615 黄宇健更改数据访问
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping(value = "/index")
	// public String list(HttpServletRequest request) {
	// Pagination.setIsConvert();
	// request.setAttribute("lst", areaDomainService.finAll());
	// request.setAttribute("pagination",quanServiceImpl.pagination());
	// return "/supplier/quan_list";
	// }
	// @ResponseBody
	// @AccessPagination(custom = true, async =
	// AccessPagination.ASYNC.ASYNC_YES)
	// @RequestMapping("/pagination.do")
	// public void pagination(HttpServletRequest request) {
	// Pagination.setIsConvert();
	// Pagination.setContext(quanServiceImpl);
	// }

	//待金券列表    黄宇健
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Pagination.setPageParams(request, quanMapper, "daiJinQuanList",
				"daiJinQuanCount");
		request.setAttribute("pagination", quanService.paginationCustom());
		request.setAttribute("lst", areaDomainService.finAll());
		return "/supplier/quan_list";
	}
	
	//待金券列表   黄宇健
	@ResponseBody
	@RequestMapping("/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, quanMapper, "daiJinQuanList",
				"daiJinQuanCount");
		return new ResponsePagination(quanService.paginationCustom());
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject remove(HttpServletRequest request,
			@RequestParam Integer id) {
		try {
			quanServiceImpl.deleteById(id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		} finally {

		}
	}

	@ResponseBody
	@RequestMapping(value = "/oper.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "0".equals(request.getParameter("isopen"));
			return new ResponseObject(quanServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		} finally {

		}
	}

	@ResponseBody
	@RequestMapping(value = "/push.do")
	public Object push(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			quanServiceImpl.push(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/logout.do")
	public Object logout(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			quanServiceImpl.logout(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
	}

}
