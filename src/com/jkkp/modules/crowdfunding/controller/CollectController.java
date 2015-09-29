package com.jkkp.modules.crowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.crowdfunding.mapper.QrCodeMapper;
import com.jkkp.modules.crowdfunding.service.IQrCodeService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.QRcodeUtils;

/**
 * 收银管理-生成二维码
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/material/collect")
public class CollectController extends BaseController {

	@Autowired
	private IQrCodeService qrCodeService;
	@Autowired
	private QrCodeMapper qrCodeMapper;
	@Autowired
	private AttachmentServiceImpl impl;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String, Object>();
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, qrCodeMapper,"selectAllQrCode", "selectAllQrCodeCount");
		request.setAttribute("pagination",qrCodeService.paginationCustom(map));
		return "/materials/finance/collect";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map=new HashMap<String, Object>();
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		map.put("spId", su.getSpId());
		Pagination.setPageParams(request, qrCodeMapper,"selectAllQrCode", "selectAllQrCodeCount");
		return new ResponsePagination(qrCodeService.paginationCustom(map));
	}
	
	//获取二维码字符串
	@ResponseBody
	@RequestMapping("createCode.do")
	public Object createCode(Double fee,Integer type,HttpServletRequest request,HttpServletResponse response){
		try {
			VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
			
			if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
			String code = qrCodeService.saveOneAndReturnCode(su.getSpId(), fee, type);
			String path = request.getServletContext().getRealPath(AttachmentConstant.QRCODE_PATH);
			String savePath = path + "/" + su.getSpId() + ".jpg";
			String fileName = su.getSpId() + ".jpg";
			QRcodeUtils.createQrcodeImages(140, 140, code, savePath, "jpg", false);
			String filePath = AttachmentConstant.QRCODE_PATH + "/" + fileName;
			return new ResponseObject(true, filePath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("建材后台创建二维码出错");
			return new ResponseObject(false, "生成失败");
		}
	}
	
	//收银管理更新商品信息
	@ResponseBody
	@RequestMapping("updateProductInfo.do")
	public Object updateProductInfo(HttpServletRequest request,String productInfo,Integer id){
		try {
			qrCodeService.updateProductInfo(productInfo, id);
			return new ResponseObject(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("收银管理更新商品信息出错");
			return new ResponseObject(false);
		}
	}
	
	//收银管理更新地址
	@ResponseBody
	@RequestMapping("updateAddress.do")
	public Object updateAddress(HttpServletRequest request,String address,Integer id){
		try {
			qrCodeService.updateAddress(address, id);
			return new ResponseObject(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("收银管理更新地址出错");
			return new ResponseObject(false);
		}
	}
	
	//收银管理更新备注
	@ResponseBody
	@RequestMapping("updateRemark.do")
	public Object updateRemark(HttpServletRequest request,String remark,Integer id){
		try {
			qrCodeService.updateRemark(remark, id);
			return new ResponseObject(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("收银管理更新备注出错");
			return new ResponseObject(false);
		}
	}
	
}
