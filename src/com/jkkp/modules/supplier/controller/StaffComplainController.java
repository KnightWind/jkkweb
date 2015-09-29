package com.jkkp.modules.supplier.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.supplier.mapper.StaffComplainDetailsMapper;
import com.jkkp.modules.supplier.mapper.StaffComplainMapper;
import com.jkkp.modules.supplier.model.StaffComplainDetails;
import com.jkkp.modules.supplier.service.IStaffComplainService;
import com.jkkp.modules.supplier.service.IStaffComplaintsDetailsService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/staffComplain")
public class StaffComplainController extends BaseController {

	@Autowired
	private StaffComplainMapper staffComplainMapper;

	@Autowired
	private IStaffComplainService staffComplainService;

	@Autowired
	private StaffComplainDetailsMapper staffComplaintsDetailsMapper;

	@Autowired
	private IStaffComplaintsDetailsService staffComplaintsDetailsService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type",
				CommonUtil.stringToInteger(request.getParameter("type")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, staffComplainMapper,
				"selectAllStaffcomplains", "selectAllStaffcomplainCount");
		request.setAttribute("pagination",
				staffComplainService.paginationCustom());
		request.setAttribute("type",
				CommonUtil.stringToInteger(request.getParameter("type")));
		return "/supplier/staffComplain_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, staffComplainMapper,
				"selectAllStaffcomplains", "selectAllStaffcomplainCount");
		return new ResponsePagination(staffComplainService.paginationCustom());
	}

	// 关闭
	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object close(int id) {
		try {
			staffComplainService.close(id);
			return new ResponseObject(true, "关闭成功！");
		} catch (Exception e) {
			logger.error("关闭设计师投诉出错", e);
			return new ResponseObject(false, "关闭失败！");
		} finally {

		}
	}

	// 详细信息
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		// 获取投诉单id 跟踪单cid
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		// 获取投诉
		request.setAttribute("complaint", staffComplainService.detail(id));
		// 跟进列表
		Pagination.setPageParams(request, staffComplaintsDetailsMapper,
				"selectAllDetailsByCid", "selectAllDetailsCount");
		request.setAttribute("pagination",
				staffComplaintsDetailsService.paginationCustom());
		return "/supplier/staffComplain_detail";
	}

	//新增一跟进内容
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save(StaffComplainDetails details) {
		try {
			if (details.getContent().isEmpty()) {
				return new ResponseObject(false, "请输入跟进内容");
			}
			staffComplaintsDetailsService.saveOne(details);
			return new ResponseObject(true, "跟进成功！");
		} catch (Exception e) {
			logger.error("设计师投诉跟进出错", e);
			return new ResponseObject(false, "跟进失败！");
		}

	}
}
