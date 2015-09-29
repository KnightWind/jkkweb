package com.jkkp.modules.design.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.design.mapper.DesignMapper;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.modules.design.service.IDesignImageService;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.design.service.impl.DesignServiceImpl;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.service.IAgreementService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.pc.common.constants.AjaxHelper;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;


/**
 * 梁怡立
 * 
 * @author Administrator 2015-05-08
 */
@Controller
@RequestMapping(value = "/goods/design")
public class DesignController extends BaseController{
	@Autowired
	private IDesignCateService designCateService;
	@Autowired
	private DesignServiceImpl designServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private DesignMapper designMapper;
	@Autowired
	private IDesignService designService;
	@Autowired
	private IDesignImageService designImageService;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private AttachmentServiceImpl attachmentServiceImpl;
	@Autowired
	private IAppointmentPushService appointmentPushService;
	@Autowired
	private  IAgreementService agreementService;
	
	
	/**
	 * 设置封面
	 * @param request
	 * @param aid 附件表id
	 * @param did 设计方案id
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/deletePhoto.do")
	public void deletePhoto(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false)Integer id) throws IOException{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(CheckedUtil.isNotEmpty(id)){
			attachmentService.deleteById(id);
			paramMap.put("code", AjaxHelper.SUCCESS_CODE);
			paramMap.put("msg", "删除成功！");
			AjaxHelper.objectToJson(response, paramMap);
		}else{
			paramMap.put("code", AjaxHelper.FAIL_CODE);
			paramMap.put("msg", "删除失败！");
		}
	}
	
	/**
	 * 设置封面
	 * @param request
	 * @param aid 附件表id
	 * @param did 设计方案id
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/setCover")
	public void setCover(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false)Integer aid,
			@RequestParam(required=false)Integer did) throws IOException{
		if(CheckedUtil.isNotEmpty(aid) && CheckedUtil.isNotEmpty(did)){
			Design design = designService.findById(did);
			design.setPid(aid);
			designService.update(design);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", AjaxHelper.SUCCESS_CODE);
			paramMap.put("msg", "设置成功！");
			AjaxHelper.objectToJson(response, paramMap);
		}
	}
	
	
	
	/**
	 * 查看设计方案
	 */
	@AccessMenu
	@RequestMapping("/view")
	public String view(HttpServletRequest request,@RequestParam(required=false)Integer id,@RequestParam(required=false)Integer mid,
			@RequestParam(required=false)Integer pid,@RequestParam(required=false)String status){
		Design design=designServiceImpl.findById(id);
		if(design != null){
			Supplier su = supplierService.findById(design.getSpId());
			Appointment app = appointmentService.findById(design.getAid());
			
			if(design.getHuxing() != null){
				String huxing = designCateService.findById(design.getHuxing()).getCateName();
				request.setAttribute("huxing", huxing);
			}
			if(design.getKongjian() != null){
				String kongjian = designCateService.findById(design.getKongjian()).getCateName();
				request.setAttribute("kongjian", kongjian);
			}
			if(design.getFengge() != null){
				String fengge = designCateService.findById(design.getFengge()).getCateName();
				request.setAttribute("fengge", fengge);
			}
			Agreement agr = null;
			if(design.getAid() != null){
				AppointmentPush apush = appointmentPushService.findPushBySpIdAndAid(design.getSpId(),design.getAid());
				if(apush != null)
					agr = agreementService.selectByPushId(apush.getId());
			}
			//获取效果图和现场照片
			getDesignImage(request,id,agr == null ? 0 : agr.getId());
			request.setAttribute("agr", agr);
			request.setAttribute("d", design);
			request.setAttribute("status", status);
			request.setAttribute("pid", pid);
			request.setAttribute("mid", mid);
			request.setAttribute("reviewTime", app.getReviewTimeHandle());
			request.setAttribute("agr", agr == null ? new Agreement() : agr);
			if(app != null && app.getUser() != null)
				request.setAttribute("user", app.getUser());
			if(su != null && su.getSpName() != null)
				request.setAttribute("spname",su.getSpName());
			if(design.getHuxing() != null){
				request.setAttribute("hname", designCateService.findById(design.getHuxing()).getCateName());
			}
			if(design.getKongjian() != null){
				request.setAttribute("kname", designCateService.findById(design.getKongjian()).getCateName());
			}
			if(design.getFengge() != null){
				request.setAttribute("fname", designCateService.findById(design.getFengge()).getCateName());
			}
		}
		
		return "/supplier/design_view";
	}
	
	//web后台审核方案
	@RequestMapping("/examine")
	public String examine(HttpServletRequest request,@RequestParam(required=false)Integer flag,
			@RequestParam(required=false)Integer id,
			@RequestParam(required=false)Integer mid,
			@RequestParam(required=false)Integer pid,
			@RequestParam(required=false)Integer status){
		Design de = designService.findById(id);
		if(de != null){
			de.setStatus(new Byte(flag.toString()));
			designService.update(de);
		}
		return "redirect:/goods/design/designList.xhtml?mid="+mid+"&pid="+pid+"&status="+status;
	}
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", request.getParameter("status"));
		Pagination.setSearchParams(params);
		request.setAttribute("lst", areaDomainService.finAll());
		Pagination.setIsConvert();
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("pagination",designServiceImpl.pagination());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/goods/design_list";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(designServiceImpl);
	}
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public ResponseObject add(HttpServletRequest request) {
		try {
			Design design=new Design();
			designServiceImpl.saveOrUpdate(design);
			return new ResponseObject(true, "保存成功!");
		} catch (Exception e) {			
			logger.error("保存出现异常", e);
			return new ResponseObject("保存失败!");
		}finally{
			
		}
	}
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject remove(HttpServletRequest request, @RequestParam Integer id) {
		try {
			designServiceImpl.remove(id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {			
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		}finally{
			
		}
	}
	
	//商家后台
	@RequestMapping(value = "/oper.do")
	public String operate(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if(id != null && id > 0){
			designServiceImpl.deleteDesignById(id);
		}
		return "redirect:/goods/design/select.xhtml";
	}
	
	//管理后台
	@RequestMapping(value = "/deleteOneDesign.do")
	public String deleteOneDesign(HttpServletRequest request,String status) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if(id != null && id > 0){
			designServiceImpl.deleteDesignById(id);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return "redirect:/goods/design/designList.xhtml" + params + "&status=" + status;
	}
	
	/*
	@ResponseBody
	@RequestMapping(value = "/oper.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "1".equals(request.getParameter("isopen"));
			return new ResponseObject(designServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
		finally{
			
		}
	}*/
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/lx")
	public String lst(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		request.setAttribute("lst", areaDomainService.finAll());
		Pagination.setIsConvert();
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("pagination",designServiceImpl.pagination());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("did",CommonUtil.stringToInteger(request.getParameter("did")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("city", request.getParameter("city"));
		return "/goods/design";
	}
	
	
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/select")
	public String select(HttpServletRequest request ){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("spId",su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, designMapper, "findDesignBySpId","findDesignBySpIdCount");
		request.setAttribute("pagination",designServiceImpl.paginationCustom(params));
		return "/supplier/supplierde";
	}
	
	//-------------web后台------------
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/designList")
	public String designList(HttpServletRequest request,@RequestParam(required=false) String status){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",status == null ? 0 : status);
		Pagination.setPageParams(request, designMapper, "findDesignBySpId","findDesignBySpIdCount");
		request.setAttribute("pagination",designServiceImpl.paginationCustom(params));
		request.setAttribute("status", status == null ? 0 : status);
		return "/supplier/design_list";
	}
	//--------------///web--------------
	
	@ResponseBody
	@RequestMapping("/designListPage.do")
	public Object designListPage(HttpServletRequest request){
		Pagination.setPageParams(request, designMapper, "findDesignBySpId","findDesignBySpIdCount");
		return new ResponsePagination(designServiceImpl.paginationCustom());
	}
	
	@ResponseBody
	@RequestMapping("/paginationDesignPage.do")
	public Object paginationDesignPage(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("spId",su.getSpId());
		Pagination.setPageParams(request, designMapper, "findDesignBySpId","findDesignBySpIdCount");
		return new ResponsePagination(designServiceImpl.paginationCustom(params));
	}
	
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request,@RequestParam(required=false) Integer id,
			@RequestParam(required=false) Integer pid){
		Map<String, List<DesignCate>> map = designCateService.findCateList();
		request.setAttribute("id", id);
		request.setAttribute("pid", pid);
		request.setAttribute("huxing", map.get("huxing"));
		request.setAttribute("kongjian", map.get("kongjian"));
		request.setAttribute("fengge", map.get("fengge"));
		
		return "/supplier/supplierjia";
	}
	@RequestMapping(value = "/details")
	public String details(HttpServletRequest request,@RequestParam(required=false) Integer id,
			@RequestParam String huxing,@RequestParam String kongjian,@RequestParam String fengge,
			@RequestParam String hname,@RequestParam String kname,@RequestParam String fname,
			@RequestParam(required=false) Integer pid){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		if(id != null){
			Design de = designServiceImpl.findById(id);
			//获取效果图和现场照片
			Agreement agr = null;
			if(de.getAid() != null){
				AppointmentPush apush = appointmentPushService.findPushBySpIdAndAid(de.getSpId(),de.getAid());
				if(apush != null)
					agr = agreementService.selectByPushId(apush.getId());
			}
			getDesignImage(request,id,agr == null ? 0 : agr.getId());
			request.setAttribute("de", de);
			request.setAttribute("agr", agr);
		}
		
		//设计师列表
		if(su != null){
			SupplierCompanyStaff scs = new SupplierCompanyStaff();
			scs.setSpId(su.getSpId());
			scs.setSid(1);
			List<SupplierCompanyStaff> staffs = supplierCompanyStaffService.select(scs);
			request.setAttribute("staffs", staffs);
		}
		
		request.setAttribute("huxing", huxing);
		request.setAttribute("kongjian", kongjian);
		request.setAttribute("fengge", fengge);
		request.setAttribute("hname", hname);
		request.setAttribute("kname", kname);
		request.setAttribute("fname", fname);		
		request.setAttribute("pid", pid);
		return "/supplier/supplierttt";
	}
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Integer id=CommonUtil.stringToInteger(request.getParameter("id"));
		Design design=designServiceImpl.findById(id);
		Agreement agr = null;
		if(design.getAid() != null){
			AppointmentPush apush = appointmentPushService.findPushBySpIdAndAid(design.getSpId(),design.getAid());
			if(apush != null)
				agr = agreementService.selectByPushId(apush.getId());
		}
		//获取效果图和现场照片
		getDesignImage(request,id,agr == null ? 0 : agr.getId());
		request.setAttribute("agr", agr);
		
		//设计师列表
		if(su != null){
			SupplierCompanyStaff scs = new SupplierCompanyStaff();
			scs.setSpId(su.getSpId());
			scs.setSid(1);
			List<SupplierCompanyStaff> staffs = supplierCompanyStaffService.select(scs);
			request.setAttribute("staffs", staffs);
		}
		
		request.setAttribute("de", design);
		request.setAttribute("huxing", design.getHuxing());
		request.setAttribute("kongjian", design.getKongjian());
		request.setAttribute("fengge", design.getFengge());
		if(design.getHuxing() != null){
			if(designCateService.findById(design.getHuxing()) == null)
				request.setAttribute("hname", "无");
			else{
				request.setAttribute("hname", designCateService.findById(design.getHuxing()).getCateName());
			}
		}
		if(design.getKongjian()!= null){
			if(designCateService.findById(design.getKongjian()) == null)
				request.setAttribute("kname","无");
			else{
				request.setAttribute("kname", designCateService.findById(design.getKongjian()).getCateName());
			}
		}
		if(design.getFengge() != null){
			if(designCateService.findById(design.getFengge()) == null){
				request.setAttribute("fname", "无");
			}else{
				request.setAttribute("fname", designCateService.findById(design.getFengge()).getCateName());
			}
		}
		return "/supplier/supplierttt";
	}
	
	/**
	 * 编辑效果图
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,Design design,Integer []imgId,Integer pid,String hremark){
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		designService.saveOrUpdate(su,design,imgId,pid,hremark,request);
		return "redirect:/goods/design/select.xhtml";
		
	}
	
	
	/**
	 * 获取设计图和现场照片
	 * @param agrId 
	 * @return 
	 */
	public void getDesignImage(HttpServletRequest request,Integer designId, Integer agrId){
		List<VAttachment> xianChangList = attachmentService.findAttachment(designId, AttachmentConstant.LOCALE_TYPE);
		List<VAttachment> xiaoGuoList = attachmentService.findAttachment(designId, AttachmentConstant.DESIGN_TYPE);
		List<VAttachment> hetongList = attachmentService.findAttachment(agrId, AttachmentConstant.AGREEMENT_TYPE);
		request.setAttribute("xianChangList", xianChangList);
		request.setAttribute("xiaoGuoList", xiaoGuoList);
		request.setAttribute("hetongList", hetongList);
	}
	
	
	/**
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request,Design design,@RequestParam String bn){
		designServiceImpl.saveOrUpdate(design);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bn",CommonUtil.stringToInteger(bn));
		Pagination.setSearchParams(params);
		Pagination.setIsConvert();
		request.setAttribute("pagination",designServiceImpl.pagination());
		return "/supplier/supplierde";
	}**/
}
