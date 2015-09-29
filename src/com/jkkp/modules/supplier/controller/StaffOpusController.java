package com.jkkp.modules.supplier.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.supplier.mapper.StaffOpusMapper;
import com.jkkp.modules.supplier.model.StaffOpus;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.IStaffOpusService;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.view.VStaffOpus;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.Pagination;

@RequestMapping("/staffopu")
@Controller
public class StaffOpusController {
	
	@Autowired
	private IStaffOpusService staffOpusService;
	@Autowired
	private StaffOpusMapper staffOpusMapper;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private ISupplierCompanyStaffService companyStaffService;
	
	/**
	 * 查看作品详情
	 */
	@AccessMenu
	@RequestMapping("/opusView")
	public String view(HttpServletRequest request,@RequestParam(required=false) Integer id){
		if(id != null){
			VStaffOpus opus = staffOpusService.getVStaffOpusById(id);
			request.setAttribute("opus", opus);
			List<VAttachment> attachments = attachmentService.findAttachment(opus.getId(), AttachmentConstant.STAFF_OPUS_TYPE);
			request.setAttribute("atts", attachments);
			
		}
		request.setAttribute("pid", request.getParameter("pid"));
		request.setAttribute("mid", request.getParameter("mid"));
		return "/design/opus_view";
	}
	
	/**
	 * 审核
	 */
	@RequestMapping("/examine")
	public String examineXhtml(HttpServletRequest request,Integer id,Integer status,String url){
		StaffOpus opus = staffOpusService.findById(id);
		opus.setStatus(status);
		staffOpusService.update(opus);
		String mid = request.getParameter("mid");
		String pid = request.getParameter("pid");
		request.getAttribute("mid");
		return "redirect:index.xhtml?url="+url+"&mid="+mid+"&pid="+pid;
	}
	
	/**
	 * 审核
	 */
	@ResponseBody
	@RequestMapping("/examine.do")
	public Object examineDo(HttpServletRequest request,Integer id,Integer status){
		
		try {
			StaffOpus opus = staffOpusService.findById(id);
			opus.setStatus(status);
			staffOpusService.update(opus);
			return new ResponseObject(true, status == 1 ? "审核已通过！" : "审核未通过！");
		} catch (Exception e) {
			return new ResponseObject(false, "系统错误，请联系管理员！");
		}
		
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/remove.do")
	public Object remove(HttpServletRequest request,@RequestParam Integer id){
		if(id != null){
			staffOpusService.deleteById(id);
			return new ResponseObject(true, "删除成功！");
		}
		return new ResponseObject(false, "系统出错,请联系管理员 ！");
	}
	
	
	/**
	 * 保存作品
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOpus(HttpServletRequest request,@RequestParam(required=false) Integer []imgId){
		Integer staffId = Integer.valueOf(request.getParameter("staffId"));
		String strId =  request.getParameter("id");
		String title = request.getParameter("title");
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		StaffOpus opus = new StaffOpus(strId.equals("") ? 0 : Integer.valueOf(strId), su.getSpId(), staffId, new Date(), 0, title);
		staffOpusService.savaOrUpdate(su,opus,imgId,request);
		return "redirect:index.xhtml?url=opus_supplier_list";
	}
	
	/**
	 * 设计师上传作品
	 */
	@RequestMapping("/uploadOpus")
	public String uploadOpus(HttpServletRequest request ,@RequestParam(required=false) Integer id){
		if(id != null){
			VStaffOpus opus = staffOpusService.getVStaffOpusById(id);
			List<VAttachment> list = attachmentService.findAttachment(id, AttachmentConstant.STAFF_OPUS_TYPE);
			request.setAttribute("list", list);
			request.setAttribute("opus", opus);
		}
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		SupplierCompanyStaff scf = new SupplierCompanyStaff();
		scf.setSpId(su.getSpId());
		scf.setSid(1);
		List<SupplierCompanyStaff> staffList = companyStaffService.select(scf);
		request.setAttribute("staffList", staffList);
		return "/design/upload_opus";
	}
	
	
	/**
	 * 设计师作品列表
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public Object designOpusList(HttpServletRequest request,@RequestParam(required=false) String title,
			@RequestParam(required=false) String status,@RequestParam String url){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spid",su.getSpId());
		}
		params.put("title", title);
		params.put("status", status == null ? String.valueOf(0) : status);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, staffOpusMapper,"findPage", "countPage");
		request.setAttribute("pagination",staffOpusService.paginationCustom(params));
		request.setAttribute("mid", request.getAttribute("mid"));
		request.setAttribute("pid", request.getAttribute("pid"));
		request.setAttribute("status", status);
		return "/design/"+url;
	}
	
	/**
	 * 上下分页
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,@RequestParam(required=false) String status) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spid",su.getSpId());
		}
		params.put("status", status);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, staffOpusMapper,"findPage", "countPage");
		return new ResponsePagination(staffOpusService.paginationCustom(params));
	}
	
}
