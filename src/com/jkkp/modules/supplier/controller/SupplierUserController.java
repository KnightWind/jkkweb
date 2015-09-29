package com.jkkp.modules.supplier.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.ISupplierUserService;
import com.jkkp.modules.supplier.service.impl.SupplierUserServiceImpl;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/supplier/in")
public class SupplierUserController extends BaseController {
	@Autowired
	private SupplierUserServiceImpl supplierUserServiceImpl;
	@Autowired
	private ISupplierUserService supplierUserService;
	@Autowired
	private SupplierUserMapper supplierUserMapper;
	
	
	
	/**
	 * 检出用户名是否存在
	 */
	@ResponseBody
	@RequestMapping("/checkUserName.do")
	public Object checkUserName(String name){
		SupplierUser su = new SupplierUser();
		su.setUsername(name);
		List<SupplierUser> list = supplierUserService.select(su);
		if(list.size() > 0){
			return new ResponseObject(false, "帐户名已存在，推荐您使用手机号码作为登录账号！");
		}
		return new ResponseObject(true, "可创建！");
	}
	
	/**
	 * 检出用户名是否存在
	 * 检出手机号码是否绑定
	 */
	@ResponseBody
	@RequestMapping("/checkInfos.do")
	public Object checkInfos(@RequestParam String name,@RequestParam String mobile,@RequestParam Integer id){
		long size = supplierUserService.checkUserName(name,id);
		if(size > 0){
			return new ResponseObject(false, "帐户名已存在，推荐您使用手机号码作为登录账号！");
		}
		//true->存在    false->不存在
		Boolean rel=supplierUserService.checkSupplierUserMobile(mobile,id);
		if(rel){
			return new ResponseObject(false, "该手机号已绑定，请更改");
		}
		return new ResponseObject(true, "可创建");
	}
	
	
	/**
	 * 删除商家子账号
	 */
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public Object deleteUser(@RequestParam Integer id){
		if(id > 0){
			supplierUserService.deleteById(id);
			return new ResponseObject(true, "删除成功！");
		}
		return new ResponseObject(false, "删除失败！");
	}
	
	
	/**
	 * 装修公司子账号管理列表----start------------------------>>
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/supplierUserIndex")
	public String supplierUserIndex(HttpServletRequest request)  {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", su.getSpId());
		request.setAttribute("sid",su.getSpId());
		Pagination.setPageParams(request, supplierUserMapper,"findPage", "countPage");
		request.setAttribute("pagination",supplierUserService.paginationCustom(params));
		return "/supplier/user/supplier_user_list";
	}
	@ResponseBody
	@RequestMapping("/supplierUserpagination.do")
	public Object supplierUserpagination(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid",su.getSpId());
		Pagination.setPageParams(request, supplierUserMapper,"findPage", "countPage");
		return new ResponsePagination(supplierUserService.paginationCustom(params));
	}
	
	/**
	 * 修改账号信息
	 */
	@AccessMenu
	@RequestMapping(value = "/supplierUserUpdate")
	public String supplierUserUpdate(HttpServletRequest request,@RequestParam(required=false) Integer id) {
		if(id != null){
			SupplierUser supplierUser=supplierUserService.findById(id);
			request.setAttribute("view",supplierUser);
		}
		return "/supplier/user/supplier_user_edit";
	}
	
	/**
	 * 保存账号信息
	 */
	@AccessMenu
	@RequestMapping(value = "/updateSupplierUser")
	public String updateSupplierUser(HttpServletRequest request,SupplierUser supplierUser) {
		
		if(supplierUser != null){
			long size = supplierUserService.checkUserName(supplierUser.getUsername(),supplierUser.getId());
			if(size > 0){
				request.setAttribute("view",supplierUser);
				request.setAttribute("msg","账号已存在，推荐您使用手机号码作为登录账号！");
				return "/supplier/user/supplier_user_edit";
			}
			
			VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
			try {
				if(su != null){
					supplierUser.setSpId(su.getSpId());
					supplierUser.setUserpwd(CommonUtil.md5(supplierUser.getSpCode()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			supplierUser.setCreateTime(new Date());
			supplierUserService.saveUpdate(supplierUser);
		}
		return "redirect:supplierUserIndex.xhtml"; 
	}
	
	//装修公司子账号管理列表---end------------------------------->>
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/ins")
	public String index(HttpServletRequest request)  {
		int id= CommonUtil.stringToInteger(request.getParameter("id"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", id);
		Pagination.setSearchParams(params);
		request.setAttribute("mid",request.getParameter("mid"));
		request.setAttribute("pid",request.getParameter("pid"));
		request.setAttribute("bid",id);
		Pagination.setPageParams(request, supplierUserMapper,"findPage", "countPage");
		request.setAttribute("pagination",supplierUserService.paginationCustom(params));
		return "/supplier/supplieruser_list";
	}
	@ResponseBody
	@RequestMapping("/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", CommonUtil.stringToInteger(request.getParameter("sid")));
		Pagination.setPageParams(request, supplierUserMapper,"findPage", "countPage");
		return new ResponsePagination(supplierUserService.paginationCustom());
	}
	
	
	@AccessMenu
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request) {
		if(request.getParameter("id")!=null &&request.getParameter("id")!=""){
			int id= CommonUtil.stringToInteger(request.getParameter("id"));
			SupplierUser supplierUser=supplierUserServiceImpl.findById(id);
			request.setAttribute("view",supplierUser);
		}
		request.setAttribute("mid",request.getParameter("mid"));
		request.setAttribute("pid",request.getParameter("pid"));
		request.setAttribute("bid",request.getParameter("bid"));
		return "/supplier/supplieruser_edit";
	}
	@AccessMenu
	@RequestMapping(value = "/up")
	public String remove(HttpServletRequest request,SupplierUser supplierUser) {
			try {
				long size = supplierUserService.checkUserName(supplierUser.getUsername(),supplierUser.getId());
				if(size > 0){
					request.setAttribute("view",supplierUser);
					request.setAttribute("msg","账号已存在，推荐您使用手机号码作为登录账号！");
					return "/supplier/supplieruser_edit";
				}
				supplierUser.setCreateTime(new Date());
				supplierUserServiceImpl.saveUpdate(supplierUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Integer mid=CommonUtil.stringToInteger(request.getParameter("mid"));
			Integer pid=CommonUtil.stringToInteger(request.getParameter("pid"));
			Integer id=CommonUtil.stringToInteger(request.getParameter("bid"));
			return "redirect:ins.xhtml?mid="+mid+"&pid="+pid+"&id="+id+""; 
	}
	
	
	
	
	
}
