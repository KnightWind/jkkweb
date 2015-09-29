package com.jkkp.modules.basedata.controller;

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
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IEngineeringsService;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/engineerings")
public class EngineeringsController extends BaseController {
	@Autowired
	private EngineeringsMapper engineeringsMapper;
	@Autowired
	private IEngineeringsService engineeringsService;

	@Autowired
	private IDesignService designService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);

		Pagination.setPageParams(request, engineeringsMapper,"selectAllEngineerings", "selectEngineeringsCount");
		request.setAttribute("pagination",engineeringsService.paginationCustom());

		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));

		return "/basedata/engineerings_list";
	}

	//商家工程列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/indexSU")
	public String indexSU(HttpServletRequest request) {
         SupplierUser su=(SupplierUser)request.getSession().getAttribute("su");
		if(su!=null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status",CommonUtil.stringToInteger(request.getParameter("status")));
			params.put("spid", su.getSpId());
			Pagination.setPageParams(request, engineeringsMapper,"selectAllEngineerings", "selectEngineeringsCount");
			request.setAttribute("pagination",engineeringsService.paginationCustom(params));
			request.setAttribute("spid", su.getSpId());
			request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		}
		return "/basedata/engineeringsSU_list";
	}
	
	//监理工程列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/indexJL")
	public String indexJL(HttpServletRequest request) {
         SupplierUser su=(SupplierUser)request.getSession().getAttribute("su");
		if(su!=null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status",
					CommonUtil.stringToInteger(request.getParameter("status")));
			params.put("jlid", su.getSpId());
			Pagination.setPageParams(request, engineeringsMapper,
					"selectAllEngineerings", "selectEngineeringsCount");
			request.setAttribute("pagination",
					engineeringsService.paginationCustom(params));
			request.setAttribute("jlid", su.getSpId());
			request.setAttribute("status",
					CommonUtil.stringToInteger(request.getParameter("status")));
		}
		return "/basedata/engineeringsJL_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringsMapper,
				"selectAllEngineerings", "selectEngineeringsCount");
		return new ResponsePagination(engineeringsService.paginationCustom());
	}

	@AccessMenu
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request,String req) {
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		// 工程单详细
		VEngineerings engineering = engineeringsService.engineeringDetail(id);
		request.setAttribute("engineering", engineering);
		return "/basedata/"+req;
	}

	//修改工程单坐标
	@ResponseBody
	@RequestMapping(value = "/updatePoint.do")
	public Object updatePoint(HttpServletRequest request, Engineerings eng) {
		try {
			engineeringsService.updatePoint(eng.getPointx(), eng.getPointy(), eng.getId());
			return new ResponseObject(true, "修改成功");
		} catch (Exception e) {
			logger.error("修改工程单坐标出错");
			return new ResponseObject(false, "修改失败");
		}
	}

}
