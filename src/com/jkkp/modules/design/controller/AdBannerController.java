package com.jkkp.modules.design.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.design.model.AdBanner;
import com.jkkp.modules.design.service.impl.AdBannerServiceImpl;
import com.jkkp.modules.supplier.model.StaffOpus;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/shop")
public class AdBannerController extends BaseController {
	@Autowired
	private AdBannerServiceImpl adBannerServiceImpl;
	@Autowired
	private IRegionService regionService;
	@Autowired private IAttachmentService iAttachmentService;
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {		
		request.setAttribute("regionList", regionService.getParentRegions());
		request.setAttribute("regionList", regionService.getParentRegions());
		if(request.getParameter("regionId")!=null&&request.getParameter("regionId")!=""){
			request.setAttribute("ind",adBannerServiceImpl.index(request.getParameter("regionId")));
			request.setAttribute("news",adBannerServiceImpl.news(request.getParameter("regionId")));
			request.setAttribute("quan",adBannerServiceImpl.quan(request.getParameter("regionId")));
			request.setAttribute("design",adBannerServiceImpl.design(request.getParameter("regionId")));
		}else {
			AdBanner index=new AdBanner();
			index.setPlace("index");
			request.setAttribute("ind",adBannerServiceImpl.select(index));
			AdBanner news=new AdBanner();
			news.setPlace("news");
			request.setAttribute("news",adBannerServiceImpl.select(news));
			AdBanner quan=new AdBanner();
			quan.setPlace("quan");
			request.setAttribute("quan",adBannerServiceImpl.select(quan));
			AdBanner design=new AdBanner();
			design.setPlace("design");
			request.setAttribute("design",adBannerServiceImpl.select(design));
		}
		request.setAttribute("mmid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("ppid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/shop/shopbanner";
	}
	@AccessMenu
	@RequestMapping(value = "/list")
	public String query(HttpServletRequest request) {		
		request.setAttribute("regionList", regionService.getParentRegions());
		Integer mmid=CommonUtil.stringToInteger(request.getParameter("mmid"));
		Integer ppid=CommonUtil.stringToInteger(request.getParameter("ppid"));
		String gid=request.getParameter("regionId");
		request.setAttribute("mmid",mmid);
		request.setAttribute("ppid",ppid);
		request.setAttribute("regionId",request.getParameter("regionId"));
		return "redirect:/shop/index.xhtml?mid="+mmid+"&pid="+ppid+"&regionId="+gid;
	}
	
	@AccessMenu
	@RequestMapping(value = "/compile")
	public String opeeview(HttpServletRequest request) {
		if(request.getParameter("id")!=null&&request.getParameter("id")!=""){
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		
		AdBanner adBanner=adBannerServiceImpl.findById(id);
		request.setAttribute("view",adBanner);
		Attachment attachment=new Attachment();
		attachment.setMainid(id);
		attachment.setFiletype(AttachmentConstant.AD_BANNER_TPYE);
		List<Attachment>  list=iAttachmentService.select(attachment);
		String bn=Sysconfig.CONFIG_PARAM.get(ConfigConstant.PHOTO_PREFIX_URL);
		request.setAttribute("imgUrl",bn+list.get(0));
		}
		request.setAttribute("position",CommonUtil.stringToInteger(request.getParameter("position")));
		return "/shop/adbanner_edit";
	}
	
	/**
	 * 修改和保存图
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOpus(HttpServletRequest request,@RequestParam(required=false) Integer []imgId){
//		Integer staffId = Integer.valueOf(request.getParameter("staffId"));
//		String strId =  request.getParameter("id");
//		String title = request.getParameter("title");
//		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
//		StaffOpus opus = new StaffOpus(strId.equals("") ? 0 : Integer.valueOf(strId), su.getSpId(), staffId, new Date(), 0, title);
		
		//staffOpusService.savaOrUpdate(su,opus,imgId,request);
		return "redirect:index.xhtml?url=opus_supplier_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/oper.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "0".equals(request.getParameter("isopen"));
			return new ResponseObject(adBannerServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
		finally{
			
		}
	}
}
