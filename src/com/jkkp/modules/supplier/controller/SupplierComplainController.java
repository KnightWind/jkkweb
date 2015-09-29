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
import com.jkkp.modules.supplier.mapper.SupplierComplainMapper;
import com.jkkp.modules.supplier.mapper.SupplierComplainDetailsMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierComplainDetails;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.ISupplierComplainService;
import com.jkkp.modules.supplier.service.ISupplierComplaintsDetailsService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/supplierComplain")
public class SupplierComplainController extends BaseController {

	@Autowired
	private SupplierComplainMapper supplierComplainMapper;
	@Autowired
	private ISupplierComplainService supplierComplainService;
	@Autowired
	private SupplierComplainDetailsMapper supplierComplaintsDetailsMapper;
	@Autowired
	private ISupplierComplaintsDetailsService supplierComplaintsDetailsService;
	@Autowired
	private ISupplierService supplierService;

	//管理后台  --投诉管理
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type",
				CommonUtil.stringToInteger(request.getParameter("type")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierComplainMapper,
				"selectAllAboutSupplierComplain", "selectAllAboutSCCount");
		request.setAttribute("pagination",
				supplierComplainService.paginationCustom());
		request.setAttribute("type",
				CommonUtil.stringToInteger(request.getParameter("type")));
		return "/supplier/supplierComplain_list";
	}

	//商家后台  --投诉
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/indexSU")
	public String indexSU(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		SupplierUser supplierUser=(SupplierUser)request.getSession().getAttribute("su");
		if(supplierUser!=null){
			params.put("sid", supplierUser.getSpId());
			Pagination.setPageParams(request, supplierComplainMapper,
					"selectAllAboutSupplierComplain", "selectAllAboutSCCount");
			request.setAttribute("pagination",
					supplierComplainService.paginationCustom(params));
		}
		return "/supplier/supplierComplainSU_list";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, supplierComplainMapper,
				"selectAllAboutSupplierComplain", "selectAllAboutSCCount");
		return new ResponsePagination(
				supplierComplainService.paginationCustom());
	}

	// 关闭
	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object close(int id) {
		try {
			supplierComplainService.close(id);
			return new ResponseObject(true, "关闭成功！");
		} catch (Exception e) {
			logger.error("关闭商家投诉出错", e);
			return new ResponseObject(false, "关闭失败！");
		} finally {

		}
	}

	// 详细信息   管理后台
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		// 获取投诉单id 跟踪单cid
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		// 获取投诉
		request.setAttribute("complaint",
				supplierComplainService.complainDetail(id));
		// 获取投诉跟踪列表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", id);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierComplaintsDetailsMapper,
				"selectAllDetailsByCid", "selectAllDetailsCount");
		request.setAttribute("pagination",
				supplierComplaintsDetailsService.paginationCustom());
		return "/supplier/supplierComplain_detail";
	}

	//商家后台
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/detailSU")
	public String detailSU(HttpServletRequest request) {
		// 获取投诉单id 跟踪单cid
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		// 获取投诉
		request.setAttribute("complaint",
				supplierComplainService.complainDetail(id));
		// 获取投诉跟踪列表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", id);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierComplaintsDetailsMapper,
				"selectAllDetailsByCid", "selectAllDetailsCount");
		request.setAttribute("pagination",
				supplierComplaintsDetailsService.paginationCustom());
		return "/supplier/supplierComplainSU_detail";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/detailPagination.do")
	public Object detailPagination(HttpServletRequest request) {
		// Map<String, Object> params = new HashMap<String, Object>();
		// int id=CommonUtil.stringToInteger(request.getParameter("status"));

		Pagination.setPageParams(request, supplierComplaintsDetailsMapper,
				"selectAllDetailsByCid", "selectAllDetailsCount");
		return new ResponsePagination(
				supplierComplaintsDetailsService.paginationCustom());
	}

	//web后台
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save(SupplierComplainDetails details) {
		try {
			if (details.getContent().isEmpty()) {
				return new ResponseObject(false, "请输入跟进内容");
			}
			supplierComplaintsDetailsService.saveOne(details);
			return new ResponseObject(true, "跟进成功！");
		} catch (Exception e) {
			logger.error("商家投诉跟进出错", e);
			return new ResponseObject(false, "跟进失败！");
		}

	}
	
	//商家回复投诉
	@ResponseBody
	@RequestMapping(value = "/saveSU.do")
	public Object saveSU(SupplierComplainDetails details,HttpServletRequest request) {
		try {
			if (details.getContent().isEmpty()) {
				return new ResponseObject(false, "请输入跟进内容");
			}
			SupplierUser su=(SupplierUser)request.getSession().getAttribute("su");
			if(su==null){
				return new ResponseObject(false, "请先登录");
			}
			//获取登录者所属的商家
			Supplier supplier=supplierService.findById(su.getSpId());
			details.setUserName(supplier.getSpName());
			details.setUserId(su.getId());
			supplierComplaintsDetailsService.saveOneSU(details);
			return new ResponseObject(true, "跟进成功！");
		} catch (Exception e) {
			logger.error("商家投诉跟进出错", e);
			return new ResponseObject(false, "跟进失败！");
		}

	}
}
