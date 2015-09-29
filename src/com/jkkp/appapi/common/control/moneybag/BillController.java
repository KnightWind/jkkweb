package com.jkkp.appapi.common.control.moneybag;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAgreementImgService;
import com.jkkp.appapi.common.service.interfaces.IAgreementSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.appapi.modules.mapper.VIEngneerings;
import com.jkkp.appapi.modules.mapper.VPaymentRecordApi;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAgreementImg;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignImage;
import com.jkkp.modules.member.model.MoneyBag;
import com.jkkp.modules.member.service.IMoneyBagService;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.model.AgreementImg;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.JsonUtil;

@Controller
@RequestMapping("/")
public class BillController extends BaseController {
	@Autowired
	IEngineeringsService engservice;
	@Autowired
	IAgreementSV aservice;
	// 装入自动组装返回报文格式
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	private IMoneyBagService moneyBagService;
	@Autowired
	IPaymentRecordService paymentsv;
	@Autowired
	IBaseinf ib;
	@Autowired
	ISupplierSV supp;
	@Autowired
	IAppointmentPushSV ap;

	@ResponseBody
	@RequestMapping("/qry_BillByUid.do")
	public Object qry_BillByUid(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<VPaymentRecordApi> prPaymentRecord = null;
		Map<String, Object> pagination = new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			/*
			 * public static final int TYPE_DEPOSIT = 1; // 订金 public static
			 * final int TYPE_WALLET = 2; // 充值 public static final int
			 * TYPE_JIANLI = 3; // 监理款
			 */
			map = commonJsonMap.setRequestMap(request);
			// int memberId=Integer.valueOf((String) map.get("uid"));
			String currentPage = "0";
			if (map.containsKey("curpage"))
				currentPage = (String) map.get("curpage");

			pagination.put("currentPage", currentPage);
			map = PaginationInterceptor.pagination(map);

			prPaymentRecord = paymentsv.qryBillByUid(map);

			map = PaginationInterceptor.nextPagination(map);
			if (paymentsv.qryBillByUid(map).size() > 0) {
				pagination.put("hasnext", true);
			} else {
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(prPaymentRecord, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/qry_OneBill.do")
	public Object qry_OneBill(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		VPaymentRecordApi prPaymentRecord = null;
		Map<String, Object> pagination = new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String serialCode = (String) map.get("serialCode");
			prPaymentRecord = paymentsv.paymentDetial(serialCode);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(prPaymentRecord, mapBusi);
		}
	}

}