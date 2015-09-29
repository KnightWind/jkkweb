package com.jkkp.modules.supplier.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.interceptor.AccessPagination.ASYNC;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.service.impl.SupplierCollectServiceImpl;
import com.jkkp.modules.supplier.service.impl.SupplierServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/supplier/cosss")
public class SupplierCollectsController extends BaseController  {
	@Autowired
	private SupplierCollectServiceImpl supplierCollectServiceImpl;
	@Autowired
	private SupplierServiceImpl supplierServiceImpl;
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("pagination",supplierServiceImpl.pagination());
		return "/admin/suppliercollect";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(supplierServiceImpl);
	}
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public ResponseObject remove(HttpServletRequest request,String[] spid,String uid) {
		try {		
			for(int i = 0; i < spid.length; i++) {
				SupplierCollect supplierCollect=new SupplierCollect();
				if(spid[i]!=null &&spid[i].trim().length()>0){
					supplierCollect.setSpId(CommonUtil.stringToInteger(spid[i]));
					supplierCollect.setCreateTime(new Date());
					supplierCollect.setUid(CommonUtil.stringToInteger(uid));
					supplierCollectServiceImpl.saveOrUpdate(supplierCollect);
				}
				
			}
			return new ResponseObject(true, "成功!");
		} catch (Exception e) {
			logger.error("出现异常", e);
			return new ResponseObject("失败!");
		} finally {

		}
	}
}
