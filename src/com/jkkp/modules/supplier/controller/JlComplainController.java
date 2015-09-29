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
import com.jkkp.modules.supplier.mapper.JlComplainMapper;
import com.jkkp.modules.supplier.mapper.JlComplainDetailsMapper;
import com.jkkp.modules.supplier.model.JlComplainDetails;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.IJlComplainService;
import com.jkkp.modules.supplier.service.IJlComplainDetailsService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/jlComplain")
public class JlComplainController extends BaseController {
	@Autowired
	private JlComplainMapper jlComplainMapper;
	@Autowired
	private IJlComplainService jlComplainService;
	@Autowired
	private IJlComplainDetailsService jlComplaintsDetailsService;
	@Autowired
	private JlComplainDetailsMapper jlComplaintsDetailsMapper;
	@Autowired
	private ISupplierService supplierService;

	//管理后台   --投诉
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type",
				CommonUtil.stringToInteger(request.getParameter("type")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jlComplainMapper,
				"selectAllAboutJLComplain", "selectAllAboutJLCCount");
		request.setAttribute("pagination", jlComplainService.paginationCustom());
		request.setAttribute("type",
				CommonUtil.stringToInteger(request.getParameter("type")));
		return "/supplier/jlComplain_list";
	}

	//监理后台    --投诉
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/indexJL")
	public String indexJL(HttpServletRequest request) {
		SupplierUser su = (SupplierUser) request.getSession()
				.getAttribute("su");
		if (su != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("jlid", su.getSpId());
			Pagination.setPageParams(request, jlComplainMapper,
					"selectAllAboutJLComplain", "selectAllAboutJLCCount");
			request.setAttribute("pagination",
					jlComplainService.paginationCustom(params));
			request.setAttribute("jlid", su.getSpId());
		}
		return "/supplier/jlComplainJL_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, jlComplainMapper,
				"selectAllAboutJLComplain", "selectAllAboutJLCCount");
		return new ResponsePagination(jlComplainService.paginationCustom());
	}

	// 关闭
	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object close(int id) {
		try {
			jlComplainService.close(id);
			return new ResponseObject(true, "关闭成功！");
		} catch (Exception e) {
			logger.error("关闭商家投诉出错", e);
			return new ResponseObject(false, "关闭失败！");
		} finally {

		}
	}

	// 详细信息   --管理后台
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		// 获取投诉单id 跟踪单cid
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		// 获取投诉
		request.setAttribute("complaint", jlComplainService.complainDetail(id));

		// 获取投诉跟踪列表
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("cid", id);
		// Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jlComplaintsDetailsMapper,
				"selectAllDetailsByCid", "selectAllDetailsCount");
		request.setAttribute("pagination",
				jlComplaintsDetailsService.paginationCustom());
		return "/supplier/jlComplain_detail";
	}

	
	   // 详细信息   --监理后台
		@AccessMenu
		@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
		@RequestMapping(value = "/detailJL")
		public String detailJL(HttpServletRequest request) {
			// 获取投诉单id 跟踪单cid
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			// 获取投诉
			request.setAttribute("complaint", jlComplainService.complainDetail(id));

			Pagination.setPageParams(request, jlComplaintsDetailsMapper,
					"selectAllDetailsByCid", "selectAllDetailsCount");
			request.setAttribute("pagination",
					jlComplaintsDetailsService.paginationCustom());
			return "/supplier/jlComplainJL_detail";
		}
	
	//web后台跟进
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save(JlComplainDetails details,HttpServletRequest request) {
		try {
			if (details.getContent().isEmpty()) {
				return new ResponseObject(false, "请输入跟进内容");
			}
			//获取后台登录管理员
			details.setUserName(request.getRemoteUser());
			jlComplaintsDetailsService.saveOne(details);
			return new ResponseObject(true, "跟进成功！");
		} catch (Exception e) {
			logger.error("监理投诉跟进出错", e);
			return new ResponseObject(false, "跟进失败！");
		}

	}
	
	//监理后台回复投诉
	@ResponseBody
	@RequestMapping(value = "/saveJL.do")
	public Object saveJL(JlComplainDetails details,HttpServletRequest request) {
		try {
			if (details.getContent().isEmpty()) {
				return new ResponseObject(false, "请输入跟进内容");
			}
			SupplierUser su=(SupplierUser)request.getSession().getAttribute("su");
			if(su==null){
				return new ResponseObject(false, "请先登录");
			}
			details.setUserId(su.getId());
			//获取supplier信息
			Supplier supplier=supplierService.findById(su.getSpId());
			details.setUserName(supplier.getSpName());
			jlComplaintsDetailsService.saveOneJL(details);
			return new ResponseObject(true, "回复成功！");
		} catch (Exception e) {
			logger.error("监理回复投诉出错", e);
			return new ResponseObject(false, "回复失败！");
		}

	}
}
