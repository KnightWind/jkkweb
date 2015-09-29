package com.jkkp.modules.basedata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.ISettlementService;
import com.jkkp.modules.basedata.view.VExpenditure;
import com.jkkp.modules.order.mapper.PaymentRecordMapper;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.utils.Pagination;


@SuppressWarnings("unchecked")
@RequestMapping("/admin/expenditure")
@Controller
public class ExpenditureController extends BaseController {
	
	@Autowired
	private IPaymentRecordService paymentRecordService;
	@Autowired
	private PaymentRecordMapper paymentRecordMapper;
	@Autowired
	private ISettlementService settlementService;
	
	
	
	/**
	 * 后台支出列表
	 * @param request
	 * @param response
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true,async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/memberExpenIndex")
	public String memberExpenIndex(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, paymentRecordMapper,"memberExpenList","memberExpenListCount");
		Pagination<?> data = (Pagination<PaymentRecord>) paymentRecordService.paginationCustom(map);
		setData(data);
		request.setAttribute("list",data);
		return "/basedata/memberexp_list";
	}


	
	
	/**
	 * 后台支出列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/memberExpenIndexPage.do")
	public Object memberExpenIndexPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, paymentRecordMapper,"memberExpenList","memberExpenListCount");
		return new ResponsePagination(paymentRecordService.paginationCustom(map));
	}
	
	
	
	/**
	 * 后台支出列表
	 * @param request
	 * @param response
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true,async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, paymentRecordMapper,"expenditureList","expenditureListCount");
		Pagination<?> data = (Pagination<PaymentRecord>) paymentRecordService.paginationCustom(map);
		setData(data);
		request.setAttribute("pagination",data);
		return "/basedata/expenditure_list";
	}
	
	
	/**
	 * 后台支出列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object sendRedPackageListPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, paymentRecordMapper,"expenditureList","expenditureListCount");
		Pagination<?> data = (Pagination<PaymentRecord>) paymentRecordService.paginationCustom(map);
		setData(data);
		return new ResponsePagination(data);
	}
	
	private List<VExpenditure> setData(Pagination<?> data) {
		List<VExpenditure> list = (List<VExpenditure>) data.getDataList();
		for (VExpenditure exp : list) {
			Double unCalePushMoney = settlementService.findUnCalePushMoney(exp.getRphone());
			exp.setUnCalePushMoney(unCalePushMoney);
		}
		return list;
	}
	
}
