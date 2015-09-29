package com.jkkp.modules.supplier.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.supplier.service.ISupplierPayService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/supplierPay")
public class supplierPayController extends BaseController {

	@Autowired
	private ISupplierPayService supplierPayService;

	@Autowired
	private IAreaDomainService areaDomainService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("pagination", supplierPayService.pagination());
		request.setAttribute("areaDomain", areaDomainService.finAll());
		return "/supplier/supplierPay";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(supplierPayService);
	}

	@ResponseBody
	@RequestMapping(value = "/recharge.do")
	public Object recharge(int id) {
		try {
			int rel = supplierPayService.recharge(id);
			if (rel == 1) {
				return new ResponseObject(true, "充值成功！");
			} else {
				return new ResponseObject("剩余次数为0,充值失败！");
			}
		} catch (Exception e) {
			logger.error("商户充值出现异常！", e);
			return new ResponseObject("充值失败！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/manyRecharge.do")
	public Object manyRecharge(String ids) {
		try {
			int rel = supplierPayService.manyRecharge(ids);
			if (rel == 1) {
				return new ResponseObject(true, "批充值成功！");
			} else {
				return new ResponseObject(false, "批充值失败！");
			}
		} catch (Exception e) {
			logger.error("商户批充值出现异常", e);
			return new ResponseObject("批充值失败！");
		}
	}
}
