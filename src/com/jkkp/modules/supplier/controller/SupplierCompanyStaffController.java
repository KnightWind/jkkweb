package com.jkkp.modules.supplier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.interceptor.AvoidDuplicateSubmission;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.service.ISupplierPositionService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@RequestMapping("/supplierSaff")
@Controller
public class SupplierCompanyStaffController extends BaseController {
	
	@Autowired
	private ISupplierCompanyStaffService 	supplierCompanyStaffService;
	@Autowired
	private SupplierCompanyStaffMapper 		supplierCompanyStaffMapper;
	@Autowired
	private ISupplierPositionService 		supplierPositionService;
	@Autowired
	private IAttachmentService 				attachmentService;

	/**
	 * 编辑装修公司员工信息 
	 */
	@AccessMenu
	@AvoidDuplicateSubmission(saveToken = true)
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,HttpSession session,@RequestParam(value="id",required=false) Integer id,
			@RequestParam String returnUrl,@RequestParam(required=false) Integer sid){
		if(id != null){
			SupplierCompanyStaff staff = supplierCompanyStaffService.findById(id);
			List<VAttachment> list = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_STAFF_TYPE);
			request.setAttribute("staff", staff);
			request.setAttribute("photo", list.size() == 0 ? "" : list.get(0).getDownloadPath());
		}
		if(sid != null){
			request.setAttribute("sid",sid);
		}
		//request.setAttribute("spostionList", supplierPositionService.select(null));
		return "/supplier/staff/"+returnUrl;
	}
	
	/**
	 * 管理员后台删除员工
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public String removeStaff(HttpServletRequest request,@RequestParam Integer sid){
		if(sid > 0){
			Attachment att = new Attachment();
			att.setMainid(sid);
			att.setFiletype(AttachmentConstant.SUPPLIER_STAFF_TYPE);
			List<Attachment> staffList = attachmentService.select(att);
			supplierCompanyStaffService.deleteStaffById(sid,staffList);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String []{"id","mid","pid","returnUrl"});
		return "redirect:/supplierSaff/index.xhtml" + params;
	}
	
	
	/**
	 * 修改装修公司员工
	 */
	@RequestMapping(value="/updateSupplierCompanyStaff",method=RequestMethod.POST)
	public String updateSupplierCompanyStaff(SupplierCompanyStaff entity,HttpServletRequest request,
			@RequestParam(value="userImg",required=false) MultipartFile userImg,@RequestParam String returnUrl){
		
		SupplierCompanyStaff oldEntity = supplierCompanyStaffService.findById(entity.getId());
		Attachment att = new Attachment();
		att.setMainid(entity.getId());
		att.setFiletype(AttachmentConstant.SUPPLIER_STAFF_TYPE);
		List<Attachment> staffImageList = attachmentService.select(att);
		if(userImg.getSize() > 0){
			if(staffImageList.size() >= 0){
				for (Attachment attachment : staffImageList) {
					attachmentService.deleteById(attachment.getId());
				}
			}
			Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
			if (attachment != null) {
				int mainid = entity.getId();
				Admin admin = new Admin();
				attachmentService.saveAttachment(attachment, admin, mainid, AttachmentConstant.SUPPLIER_STAFF_TYPE);
			}
		}
		if(entity.getSid() == 2){
			entity.setSjsSuggest(null);
		}
		entity.setAvatar(null);
		entity.setCreateTime(oldEntity.getCreateTime());
		entity.setSpId(oldEntity.getSpId());
		supplierCompanyStaffService.update(entity);
		Integer mid =  CommonUtil.stringToInteger(request.getParameter("mid"));
		Integer pid =  CommonUtil.stringToInteger(request.getParameter("pid"));
		return "redirect:/supplierSaff/index.xhtml?id="+oldEntity.getSpId()+"&returnUrl="+returnUrl+"&mid="+mid+"&pid="+pid;
	}
	
	
	/**
	 * 删除员工
	 */
	@ResponseBody
	@RequestMapping(value="/removeSupplierCompanyStaff")
	public Object removeSupplierCompanyStaff(@RequestParam(value="id") Integer id){
		if(id > 0){
			supplierCompanyStaffService.deleteById(id);
			Attachment att = new Attachment();
			att.setMainid(id);
			att.setFiletype(AttachmentConstant.SUPPLIER_STAFF_TYPE);
			List<Attachment> staffImageList = attachmentService.select(att);
			for (Attachment attachment : staffImageList) {
				HttpFileTools.deleteFile(attachment.getFilepath());
			}
		}
		return new ResponseObject(true,"删除成功！");
	}
	
	
	/**
	 * 添加商家员工 
	 */
	@AvoidDuplicateSubmission(removeToken = true)
	@RequestMapping(value="/saveSupplierCompanyStaff",method=RequestMethod.POST)
	public String saveSupplierCompanyStaff(SupplierCompanyStaff entity,HttpServletRequest request,HttpSession session,
		Integer spid,String returnUrl,String formUUID){
		final VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		if(spid == null && su != null){
			spid = su.getSpId();
		}
		entity.setSpId(spid);
		entity.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
		supplierCompanyStaffService.save(entity);
		Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
		if (attachment != null) {
			int mainid = entity.getId();
			Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, mainid, AttachmentConstant.SUPPLIER_STAFF_TYPE);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String[]{"spid","returnUrl","mid","pid","mid"});
		params = params.replace("spid", "id");
		return "redirect:/supplierSaff/index.xhtml" + params;
	}
	
	/**
	 * 员工列表
	 * @param returnUrl 跳转路径
	 * @param id 员工Id
	 * @param sid 
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,String returnUrl,Integer id,Integer sid) {
		final VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		//如果su为空,说明后台获取数据,否则是商家获取数据
		if(su == null){
			params.put("id", id);
			request.setAttribute("id",id);
		}else{
			params.put("id", su.getSpId());
			request.setAttribute("id",su.getSpId());
		}
		if(sid != null){
			params.put("sid", sid);
		}
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"selectSaffListBySpId", "findSaffListCountBySpId");
		request.setAttribute("pagination",supplierCompanyStaffService.paginationCustom(params));
		request.setAttribute("spostionList",supplierPositionService.select(null));
		return "/supplier/staff/"+returnUrl;
	}

	@ResponseBody  
	@RequestMapping(value = "/pagination.do")
	public Object appointmentPage(HttpServletRequest request) {
		final VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"selectSaffListBySpId", "findSaffListCountBySpId");
		return new ResponsePagination(supplierCompanyStaffService.paginationCustom(params));
	}
	
	
	/**
	 * 员工后台管理分页
	 *
	 */
	@ResponseBody  
	@RequestMapping(value = "/adminPagination.do")
	public Object appointmentPage(HttpServletRequest request,
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="sid",required=false) Integer sid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		if(sid != null){
			params.put("sid", sid);
		}
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"selectSaffListBySpId", "findSaffListCountBySpId");
		return new ResponsePagination(supplierCompanyStaffService.paginationCustom(params));
	}
	
	
}
