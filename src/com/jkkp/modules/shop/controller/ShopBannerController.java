package com.jkkp.modules.shop.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.design.model.AdBanner;
import com.jkkp.modules.design.service.impl.AdBannerServiceImpl;
import com.jkkp.modules.shop.model.ShopBanner;
import com.jkkp.modules.shop.service.impl.ShopBannerServiceImpl;
import com.jkkp.utils.CommonUtil;
@Controller
@RequestMapping(value = "/shop")
public class ShopBannerController  extends BaseController{
	@Autowired
	private ShopBannerServiceImpl shopBannerServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private AdBannerServiceImpl adBannerServiceImpl;
	@ResponseBody
	@RequestMapping(value = "/operate.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "0".equals(request.getParameter("isopen"));
			return new ResponseObject(shopBannerServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
		finally{
			
		}
	}
	@AccessMenu
	@RequestMapping(value = "/op")
	public String ope(HttpServletRequest request) {
		if(request.getParameter("id")!=null&&request.getParameter("id")!=""){
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		ShopBanner shopBanner=shopBannerServiceImpl.findById(id);
		request.setAttribute("view",shopBanner);
		}
		return "/shop/shopbanner_edit";
	}
	@AccessMenu
	@RequestMapping(value = "/ad")
	public String operg(HttpServletRequest request,ShopBanner bShopBanner) {
		ShopBanner shopBanner=shopBannerServiceImpl.findById(bShopBanner.getId());
		shopBanner.setPlace(bShopBanner.getPlace());
		shopBanner.setTitle(bShopBanner.getTitle());
		shopBanner.setSeq(bShopBanner.getSeq());
		shopBanner.setStatus(bShopBanner.getStatus());
		shopBannerServiceImpl.update(shopBanner);
		return "/shop/shopbanner_list";
	}
}
