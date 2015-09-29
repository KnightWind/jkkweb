package com.jkkp.modules.basedata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.BankMapper;
import com.jkkp.modules.basedata.model.Bank;
import com.jkkp.modules.basedata.service.IBankService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/admin/bank")
public class BankController extends BaseController {

	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private IBankService iBankService;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, bankMapper,"selectAllBank", "selectAllBankCount");
		request.setAttribute("pagination",iBankService.paginationCustom());
		return "/basedata/bank_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, bankMapper,"selectAllBank", "selectAllBankCount");
		return new ResponsePagination(iBankService.paginationCustom());
	}
	
	@ResponseBody
	@RequestMapping("/saveOne.do")
	public Object saveOne(HttpServletRequest request){
		try {
			Integer id=CommonUtil.stringToInteger(request.getParameter("id"));
			String name=request.getParameter("name");
			String abbreviation=request.getParameter("abbreviation");
			Bank bank=new Bank(id, name, abbreviation);
			iBankService.saveOrUpdate(bank);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(false, "保存出错");
		}
	}
}
