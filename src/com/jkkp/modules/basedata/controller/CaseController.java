package com.jkkp.modules.basedata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.CaseMapper;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.service.ICaseService;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.Pagination;


@Controller
@RequestMapping("/case")
public class CaseController extends Case {

	@Autowired
	private ICaseService 				 caseService;
	@Autowired
	private CaseMapper 					 caseMapper;
	@Autowired
	private IDesignCateService 			 designCateService;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	
	
	@RequestMapping("/save")
	public String saveCase(HttpServletRequest request,Design design, Case cases,@RequestParam(value="imgId",required=false) Integer []imgId){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		caseService.save(su,imgId,design,cases, request);
		return "redirect:/case/index.xhtml?returnUrl=case_supplier_list";
	}
	
	
	/**
	 * 上传案例页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/upload")
	public String uploadCase(HttpServletRequest request,@RequestParam(required=false) Integer op){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, List<DesignCate>> map = designCateService.findCateList();
		
		//设计师列表
		if(su != null){
			SupplierCompanyStaff scs = new SupplierCompanyStaff();
			scs.setSpId(su.getSpId());
			scs.setSid(1);
			List<SupplierCompanyStaff> staffs = supplierCompanyStaffService.select(scs);
			request.setAttribute("staffs", staffs);
		}
		
		request.setAttribute("huxing", map.get("huxing"));
		request.setAttribute("kongjian", map.get("kongjian"));
		request.setAttribute("fengge", map.get("fengge"));
		request.setAttribute("title", op == 0 ? "上传案例" : "上传方案");
		return "/basedata/case_upload";
	}
	
	
	/**
	 * 删除案例
	 */
	@ResponseBody
	@RequestMapping("/deleteCase.do")
	public Object deleteCase(HttpServletRequest request,@RequestParam(required=false) Integer id){
		if(id > 0){
			caseService.deleteById(id);
			return new ResponseObject(true, "删除成功！");
		}
		return new ResponseObject(false, "系统出错,请联系管理员 ！");
	}
	
	/**
	 *  商家案例列表
	 * @param returnUrl 跳转页面
	 */ 
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,@RequestParam(required=false) String returnUrl) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("sId",su.getSpId());
		}
		Pagination.setPageParams(request, caseMapper,"findPage", "countPage");
		request.setAttribute("pagination", caseService.paginationCustom(params));
		return "/basedata/"+returnUrl;
	}

	
	/**
	 * 上下分页
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("sId",su.getSpId());
		}
		Pagination.setPageParams(request, caseMapper,"findPage", "countPage");
		return new ResponsePagination(caseService.paginationCustom(params));
	}
	
}
