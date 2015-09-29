package com.jkkp.modules.crowdfunding.controller;

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
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;

/**
 * 认证信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/material/authentication")
public class AuthenticationController extends BaseController {

	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private ISupplierService supplierService;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		//获取身份证号码与营业执照编码
		Supplier supplier = supplierService.findById(su.getSpId());
		request.setAttribute("supplier", supplier);
		
		//身份证正面
		List<String> frontList = attachmentService.findForDownload(su.getSpId(), AttachmentConstant.SUPPLIER_IDCARD_FRONT );
		
		if(frontList.size()>0){
			request.setAttribute("fontImg", frontList.get(0));
			
			//身份证反面
			List<String> negativeList = attachmentService.findForDownload(su.getSpId(), AttachmentConstant.SUPPLIER_IDCARD_NEGATIVE );
			if(negativeList.size()>0){
				request.setAttribute("negativeImg", negativeList.get(0));
			}
			//营业执照
			List<String> licenceList = attachmentService.findForDownload(su.getSpId(), AttachmentConstant.SUPPLIER_BUSINESS_LICENCE );
			if(licenceList.size()>0){
				request.setAttribute("licenceImg", licenceList.get(0));
			}
			return "/materials/account/authentication_info";
		}
		return "/materials/account/authentication";
	}
	
	
	@RequestMapping("add")
	public String add(){
		return "/materials/account/authentication";
	}
	
	
	@RequestMapping(value="saveOneInfo.do",method=RequestMethod.POST)
	public String saveOne(HttpServletRequest request,HttpServletResponse response,@RequestParam String idCard,@RequestParam String businessCode){
		try {
			if(idCard.isEmpty()){
				return "redirect:/material/authentication/index.xhtml";
			}
			if(businessCode.isEmpty()){
				return "redirect:/material/authentication/index.xhtml";
			}
			
			MultipartRequest multiPart=(MultipartRequest)request;
			//保存身份证正面   身份证反面    营业执照附件
			 List<Attachment> relList=attachmentService.uploadMulti(multiPart);
			//写死商户   编号182
			//Integer spId=182;
			 VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
			 
			 if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
			 
			 Integer spId=su.getSpId();
			//依次是    身份证正面   身份证反面    营业执照
			Map<Integer, Integer> attachmentConstantMap=new HashMap<Integer, Integer>();
			attachmentConstantMap.put(0,AttachmentConstant.SUPPLIER_IDCARD_FRONT );
			attachmentConstantMap.put(1,AttachmentConstant.SUPPLIER_IDCARD_NEGATIVE );
			attachmentConstantMap.put(2,AttachmentConstant.SUPPLIER_BUSINESS_LICENCE );
			
			//删除附件记录
			attachmentService.deleteOneAttachment((long)spId, AttachmentConstant.SUPPLIER_IDCARD_FRONT );
			attachmentService.deleteOneAttachment((long)spId, AttachmentConstant.SUPPLIER_IDCARD_NEGATIVE );
			attachmentService.deleteOneAttachment((long)spId, AttachmentConstant.SUPPLIER_BUSINESS_LICENCE );
			
			//保存新记录
			for (int i = 0; i < relList.size(); i++) {
				attachmentService.saveAttachment(relList.get(i), spId,attachmentConstantMap.get(i));
			}
			
			supplierService.updatebusinessCodeAndIdCard(businessCode, idCard, spId);
			return "redirect:/material/authentication/index.xhtml";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存建材商身份证，营业执照出错");
			return "redirect:/material/authentication/index.xhtml";
		}
	}
}
