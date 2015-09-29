/**
 * 
 */
package com.jkkp.modules.refund.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.client.alipay.config.AlipayConfig;
import com.jkkp.client.alipay.util.AlipayNotify;
import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.service.IRefundApplyAuditService;
import com.jkkp.modules.refund.service.IRefundService;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestUtils;
import com.jkkp.utils.ResponseUtils;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/refund")
public class RefundApplyAuditController extends BaseController{
	@Autowired	
	IRefundApplyAuditService refundApplyAuditService;
	@Autowired
	RefundApplyAuditMapper refundApplyAuditMapper;
	
	@Resource(name = "alipayRefundServiceImpl")
	private IRefundService alipayRefundService;
	
	@Resource(name = "yeePayRefundServiceImpl")
	private IRefundService yeepayRefundService;
	@Autowired
	private IPaymentRecordService paymentRecordService;
	
	
	//退款审核
	@AccessMenu
	@RequestMapping(value = "/index.xhtml")
	public String refund_audit_list(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", request.getParameter("status"));
		Pagination.setPageParams(request, refundApplyAuditMapper,"selectRefundApplyList", "selectRefundApplyCount");
		request.setAttribute("pagination",refundApplyAuditService.paginationCustom(params));
		request.setAttribute("status", request.getParameter("status"));
		return "/refund/refund_audit_list";
	}
	
	//退款审核-分页
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, refundApplyAuditMapper,"selectRefundApplyList", "selectRefundApplyCount");
		return new ResponsePagination(refundApplyAuditService.paginationCustom());
	}
	
	//详细信息
	@AccessMenu
	@RequestMapping(value = "/refundDetail.xhtml")
	public String refundDetail(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		request.setAttribute("detail", refundApplyAuditService.detailInfo(id));
		return "/refund/refund_audit_detail";
	}
	
	@ResponseBody
	@RequestMapping(value="refundAuditing.do")
	public Object refundAuditing(Integer id,Integer status,String mobile){
		try {
			//审核   包含发短信
			Boolean flag = refundApplyAuditService.refundAuditing(id, status,mobile);
			if(flag){
			   return new ResponseObject(true, "审核成功");
			}
			return new ResponseObject(false, "审核失败");
		} catch (Exception e) {
			return new ResponseObject(false, "审核失败");
		}
	}
	
	
	/**
	 * 审核退款单
	 * @param request
	 * @param session
	 * @param baoming
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/refund_audit.do")
	public Object refund_audit(HttpServletRequest request,Integer id,Integer status,String remark){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SupplierUser su = (SupplierUser) request.getSession()
				.getAttribute("su");
		try {
			if (su != null) {
				RefundApplyAudit refundApplyAudit = new RefundApplyAudit();
				refundApplyAudit.setId(id);
				refundApplyAudit.setStatus(status);
				refundApplyAudit.setAuditDate(new Date());
				refundApplyAudit.setAuditRemark(remark);
				refundApplyAudit.setAuditUserId(su.getId());
				refundApplyAudit.setAuditUserName(su.getUsername());
				refundApplyAuditService.updateByPrimaryKeySelective(refundApplyAudit);
				resultMap.put("resultCode", "0");
				resultMap.put("resultInfo", "审批操作成功");
			}else{
				resultMap.put("resultCode", "noLogin");
				resultMap.put("resultInfo", "未登录或者登录超时");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultCode", "exception");
			resultMap.put("resultInfo", "系统异常");
		}
		return resultMap;
	}

	/**
	 * 退款操作
	 * @param id:退款申请单id
	 * 
	 * @return
	 */
	@RequestMapping("/refund.do")
	public Object refund(HttpServletRequest request,Integer id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SupplierUser su = (SupplierUser) request.getSession()
				.getAttribute("su");
		try {
			if (su != null) {
				resultMap = (Map)refundApplyAuditService.refund(request,id);
			}else{
				resultMap.put("resultCode", "noLogin");
				resultMap.put("resultInfo", "未登录或者登录超时");
			}
			if(resultMap.get("payway")==null){
				resultMap.put("payway", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultCode", "exception");
			resultMap.put("resultInfo", "系统异常");
		}
		request.setAttribute("resultMap", resultMap);
		return "/refund/refund_api";
	}
	
	/**
	 * 审核退款单
	 * @param id:退款申请单id
	 * 
	 * @return
	 */
	@RequestMapping("/refund_alipay_api.do")
	public Object refundAlipay(HttpServletRequest request,Integer id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SupplierUser su = (SupplierUser) request.getSession()
				.getAttribute("su");
		try {
			if (su != null) {
				resultMap = (Map)refundApplyAuditService.refundAlipay(request,id);
			}else{
				resultMap.put("resultCode", "noLogin");
				resultMap.put("resultInfo", "未登录或者登录超时");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultCode", "exception");
			resultMap.put("resultInfo", "系统异常");
		}
		request.setAttribute("resultMap", resultMap);
		return "/refund/refund_alipay_api";
	}
	
	
	
	/**
	 * 支付宝通知接口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/alipay_app_refund_notify.do")
	public void notifyAppPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.notifyServer(request, response, AlipayConfig.sign_type_RSA);
	}
	
	public void notifyServer(HttpServletRequest request, HttpServletResponse response, String signType) throws IOException {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = RequestUtils.createRequest(request);

			String result;
			if (AlipayNotify.verify(params, signType)) {// 验证成功
				alipayRefundService.refundNotity(request);
				result = "success";
			} else {// 验证失败
				result = "fail";
			}
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.write(response, "fail");
		}
	}
	
	/**
	 * 支付宝通知接口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/yeepay_refund_notify.do")
	public void notifyYeePay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.notifyYeepayServer(request, response, AlipayConfig.sign_type_RSA);
	}
	
	public void notifyYeepayServer(HttpServletRequest request, HttpServletResponse response, String signType) throws IOException {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = RequestUtils.createRequest(request);

			String result;
			if (AlipayNotify.verify(params, signType)) {// 验证成功
				yeepayRefundService.refundNotity(request);
				result = "success";
			} else {// 验证失败
				result = "fail";
			}
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.write(response, "fail");
		}
	}
	
	
	
}
