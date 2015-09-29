package com.jkkp.modules.crowdfunding.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.pc.common.constants.AjaxHelper;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/material/account")
public class AccountController extends BaseController {
 
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private ISupplierService supplierService;
	
	/**
	 * 保存信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		HashMap<String, Object> map = new HashMap<>();
		
		try {
		String spName = request.getParameter("spName");
		String address = request.getParameter("address");
		String pointx = request.getParameter("longitude");
		String pointy = request.getParameter("latitude");
		Integer level=CommonUtil.stringToInteger(request.getParameter("level"));
		Float gainRate=CommonUtil.stringToFloat(request.getParameter("gainRate"));
		Supplier supplier = supplierService.findById(su.getSpId());
		supplier.setSpName(spName);
		supplier.setAddress(address);
		supplier.setPointx(pointx);
		supplier.setPointy(pointy);
		supplier.setLevel(level);
		supplier.setGainRate(gainRate);
		//修改数据后，商家处于待审核状态
		supplier.setProxyStatus(0);
		supplierService.update(supplier);
		AjaxHelper.setStatusAndMsg(map, "保存成功！", AjaxHelper.SUCCESS_CODE);
		} catch (Exception e) {
			AjaxHelper.setStatusAndMsg(map, "保存失败！", AjaxHelper.FAIL_CODE);
			throw new RuntimeException(e);
		}
		return map;
	}
	
	
	/**
	 * 上传商家Logo图片
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
	public Object uploadLogo(HttpServletRequest request, HttpServletResponse response)throws IOException {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
	    
		int spId = su.getSpId();
		Attachment att = new Attachment();
		att.setMainid(spId);
		att.setFiletype(AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		List<Attachment> logoList = attachmentService.select(att);
		if (logoList != null && logoList.size() > 0) {
			for (Attachment attachment : logoList) {
				attachmentService.delete(attachment);
			}
		}
		Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
		if (attachment != null) {
			int mainid = spId;
			Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, mainid,AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			return new ResponseObject(true, "Logo上传成功 ！");
		}
		return new ResponseObject(true, "Logo上传失败 ！");
	}
	
	/**
	 * 建材商首页
	 * @param request
	 * @return
	 */
	@RequestMapping("zc_index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		//VSupplierUser bean=new VSupplierUser();
		//bean.setSpId(182);
		//request.getSession().setAttribute("su", bean);
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		int spId = su.getSpId();
		Supplier supplier = supplierService.findById(spId);
		supplier.setGainRate(supplier.getGainRate()*100);
		List<VAttachment> list = attachmentService.findAttachment(spId, AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		request.setAttribute("sp", supplier);
		if(!list.isEmpty()){
			request.setAttribute("logo", list.get(0).getDownloadPath());
		}
		return "/materials/account/businessInfo";
	}
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/material/account/zc_index.xhtml";
	}
	
}
