/**
 * 
 */
package com.jkkp.appapi.common.control.refund;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.refund.mapper.RefundApplyAuditMapper;
import com.jkkp.modules.refund.service.IRefundApplyAuditService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class RefundApplySVController extends BaseController{
	@Autowired
	IAppointmentPushSV appointmentPushSV;
	@Autowired
	IAppointmentPushService appointmentPushModelSV;
	@Autowired
	IAppointmentSV appointmentSV;
	@Autowired
	ISuppMessagePushSV suppMessagePushSV;
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	IBaseinf baseinfsv;
	@Autowired
	IAppointmentMemberSV appointmentmembersv;
	@Autowired	
	IRefundApplyAuditService refundApplyAuditService;
	@Autowired
	RefundApplyAuditMapper refundApplyAuditMapper;
	
	
	@ResponseBody
	@RequestMapping("/apply_for_refund_deposit.do")
	public Object applyForRefundDeposit(HttpServletRequest request) {
		AppointmentPush appointmentPush = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			boolean result = appointmentPushSV.applyForRefundDeposit(map);
			if (!result) {
				mapBusi.put("doneCode", "1006");
				mapBusi.put("mess", "异常，没有更新到任何行");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage() != null && e.getMessage().contains("#")) {
				mapBusi.put("doneCode", e.getMessage().split("#")[1]);
				mapBusi.put("mess", e.getMessage().split("#")[2]);
			} else {
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());
			}

		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}
}
