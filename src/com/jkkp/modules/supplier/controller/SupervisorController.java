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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.IEngineeringStageMxInstService;
import com.jkkp.modules.supplier.mapper.SupervisorMapper;
import com.jkkp.modules.supplier.service.ISupervisorService;
import com.jkkp.modules.supplier.view.VSupervisorWeb;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController extends BaseController {

	@Autowired
	private SupervisorMapper supervisorMapper;
	@Autowired
	private ISupervisorService supervisorService;
	@Autowired
	private IEngineeringStageMxInstService engineeringStageMxInstService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", request.getParameter("word"));
		Pagination.setPageParams(request, supervisorMapper,
				"selectAllSupervisors", "selectAllSupervisorsCount");
		request.setAttribute("pagination", supervisorService.paginationCustom());
		request.setAttribute("word", request.getParameter("word"));
		return "/jlReport/supervisors_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, supervisorMapper,
				"selectAllSupervisors", "selectAllSupervisorsCount");
		return new ResponsePagination(supervisorService.paginationCustom());
	}

	@AccessMenu
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, int id) {
		VSupervisorWeb supervisor = supervisorService
				.selectSupervisorDetail(id);
		request.setAttribute("supervisor", supervisor);
		if (supervisor != null) {
			request.setAttribute("mxInst", engineeringStageMxInstService
					.selectEngineeringStageMxInsts(supervisor.getId()));
		}
		return "/jlReport/supervisors_detail";
	}
}
