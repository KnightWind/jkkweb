package com.jkkp.modules.supplier.controller;

import java.util.Date;
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
import com.jkkp.modules.supplier.mapper.SupplierCommentMapper;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierComment;
import com.jkkp.modules.supplier.service.ISupplierCommentService;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@RequestMapping("supplierComment/manager")
@Controller
public class SupplierCommentController extends BaseController {

	@Autowired
	private ISupplierCommentService supplierCommentService;
	@Autowired
	private SupplierCommentMapper supplierCommentMapper;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private SupplierCompanyStaffMapper supplierCompanyStaffMapper;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	
	
	
	
	/**
	 *后台端 装修公司设计师列表
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/designIndex")
	public String mylist(HttpServletRequest request,
			@RequestParam(value="sid",required=false) Integer sid) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(sid != null){
			params.put("sid", sid);
			request.setAttribute("sid",sid);
		}
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"selectStaffDesgins", "selectStaffDesginsCount");
		request.setAttribute("pagination",supplierCompanyStaffService.paginationCustom(params));
		return "/supplier/staff/staff_design_list";
	}
	/**
	 * 后台端设计师列表分页
	 */
	@ResponseBody  
	@RequestMapping(value = "/designPagination.do")
	public Object appointmentPage(HttpServletRequest request,
			@RequestParam(value="sid",required=false) Integer sid) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(sid != null){
			params.put("sid", sid);
		}
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"selectStaffDesgins", "selectStaffDesginsCount");
		return new ResponsePagination(supplierCompanyStaffService.paginationCustom(params));
	}
	
	
	/**
	 *异步审核操作
	 */
	@ResponseBody
	@RequestMapping("/operationApproval.do")
	public Object operationApproval(@RequestParam(value="flag") boolean flag,@RequestParam(value="id") Integer id){
		try {
			SupplierComment sc = supplierCommentService.findById(id);
			if(flag){
				sc.setStatus(1);
				sc.setCloseTime(null);
			}else{
				sc.setStatus(0);
				sc.setCloseTime(new Date());
			}
			sc.setCheckTime(new Date());
			supplierCommentService.update(sc);
			return new ResponseObject(true,"操作成功 !");
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject(false,"操作失败 !");
		}
		
	}
	
	/**
	 * 查看
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/view")
	public String view(HttpServletRequest request,@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="state",required=false) Integer state,@RequestParam(value="status",required=false) Integer status){
		if(id != null){
			Supplier supplier = supplierService.findById(id);
			request.setAttribute("supplier", supplier);
		}
		
		//加载商家评论列表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", id);
		params.put("state", state);
		Pagination.setPageParams(request, supplierCommentMapper, "findPage", "countPage");
		request.setAttribute("pagination", supplierCommentService.paginationCustom(params));
		request.setAttribute("sId", id);
		request.setAttribute("status", status);
		request.setAttribute("state", state);
		setRequestAttribute(request);
		return "/supplier/supplierReplyManager_view";
	}
	
	
	/**
	 * 商家列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			@RequestParam(value="typeId",required=false) Integer typeId) {
		setRequestAttribute(request);
		request.setAttribute("list", areaDomainService.finAll());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("typeId", typeId);
		request.setAttribute("typeId", typeId);
		Pagination.setPageParams(request, supplierMapper,"supplierCommentList", "supplierCommentListCount");
		request.setAttribute("pagination",supplierService.paginationCustom(params));
		
		return "/supplier/supplierComment_list";
	}
	
	/**
	 * 商家列表上下分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,@RequestParam(value="typeId",required=false) Integer typeId,
			@RequestParam(value="state",required=false) Integer state ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("typeId", typeId);
		params.put("state", state);
		Pagination.setPageParams(request, supplierMapper,"supplierCommentList", "supplierCommentListCount");
		return new ResponsePagination(supplierService.paginationCustom(params));
	}
	
	/**
	 * 商家评论列表上下分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/commentPagination.do")
	public Object commentPagination(HttpServletRequest request,@RequestParam("sId") Integer sId,
			@RequestParam(value="state",required=false) Integer state) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", sId);
		params.put("state", state);
		Pagination.setPageParams(request, supplierCommentMapper, "findPage", "countPage");
		return new ResponsePagination(supplierService.paginationCustom(params));
	}
	
	/**
	 * 设置导航菜单参数
	 */
	public void setRequestAttribute(HttpServletRequest request){
		request.setAttribute("mid", CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid", CommonUtil.stringToInteger(request.getParameter("pid")));
	}
	
}
