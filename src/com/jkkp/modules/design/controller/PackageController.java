package com.jkkp.modules.design.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.interceptor.AccessPagination.ASYNC;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.design.model.Package;
import com.jkkp.modules.design.service.IPackageService;
import com.jkkp.modules.product.model.Item;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.JsonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/package")
public class PackageController extends BaseController {

	@Autowired
	private IPackageService packageService;
	@Autowired
	private IAreaDomainService areaDomainService;
	
	@AccessMenu
	@AccessPagination(custom = true, async = ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("pagination", packageService.pagination());
		return "/design/package_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(packageService);
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id != null && id > 0) {
			request.setAttribute("view", packageService.findById(id));
		}
		return "/design/package_edit";
	}
	
	@ResponseBody
	@RequestMapping("/offline.do")
	public Object offline(@RequestParam Integer id, HttpServletRequest request) {
		try {
			packageService.offline(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("下架套餐出现异常", e);
			return new ResponseObject("下架套餐出现异常");
		}
	}

	@ResponseBody
	@RequestMapping("/online.do")
	public Object online(@RequestParam Integer id, HttpServletRequest request) {
		try {
			packageService.online(id);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("上架套餐出现异常", e);
			return new ResponseObject("上架套餐出现异常");
		}
	}
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public ResponseObject add(HttpServletRequest request,Package package1) {
		try {
			
			packageService.saveOrUpdate(package1);
			return new ResponseObject(true, "保存成功!");
		} catch (Exception e) {
			logger.error("保存出现异常", e);
			return new ResponseObject("保存失败!");
		} finally {

		}
	}
}
