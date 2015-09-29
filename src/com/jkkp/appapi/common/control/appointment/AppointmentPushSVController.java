package com.jkkp.appapi.common.control.appointment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VAppointmentPushV1;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.appapi.putapp.PushApp;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class AppointmentPushSVController extends BaseController {

	@Autowired
	IAppointmentPushSV appointmentPushSV;
	@Autowired
	IAppointmentPushService appointmentPushModelSV;
	@Autowired
	IAppointmentSV appointmentSV;
	@Autowired
	ISupplierSV supplierSV;
	@Autowired
	ISuppMessagePushSV suppMessagePushSV;
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	IBaseinf baseinfsv;
	@Autowired
	IAppointmentMemberSV appointmentmembersv;

	@ResponseBody
	@RequestMapping("/supplier_grab_appoint.do")
	public Object qrySupplierCollectUpdate(HttpServletRequest request) {

		AppointmentPush appointmentPush = null;

		Map<String, Object> data = new HashMap<String, Object>();

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

			data = appointmentSV.doTranscRespondAppointment(map, request);

		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().contains("USERERR")) {
			} else {
				e.printStackTrace();
			}
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/collsupplier_grab_appoint.do")
	public Object collsupplier_grab_appoint(HttpServletRequest request) {

		AppointmentPush appointmentPush = null;

		Map<String, Object> data = new HashMap<String, Object>();

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

			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			// 收藏商家 答应
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YU_YUE_LIANG_FANG);
			appointmentPushSV.update(appointmentPush);
			Appointment a = appointmentSV.findById(appointmentPush.getAid());
			a.setStatus(ConstantAppStatus.DAI_LIANG_FANG);
			appointmentSV.update(a);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/qry_supplier_grab_status.do")
	public Object qrySupplierGrabStatues(HttpServletRequest request) {

		List<VIAppointmentPush> appointmentPushs = null;

		Map<String, Object> data = new HashMap<String, Object>();

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		int grabNum = 0;
		int pushNum = 0;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			pushNum = appointmentPushSV.queryAppPush(map);
			grabNum = appointmentPushSV.queryAppPushlGrab(map);
			// 会员收藏
			int appointmentId = Integer.valueOf((String) map
					.get("appointmentId"));
			List<AppointmentMember> amm = appointmentmembersv.selectByProperty(
					"aid", appointmentId);
			if (amm.size() > 0) {
				map.put("mid", amm.get(0).getMid());
			} else {
				map.put("mid", "");
			}
			appointmentPushs = appointmentPushSV.queryAppPushlByAppid(map);
			if (appointmentPushs.size() > 0) {// 增加头像
				for (VIAppointmentPush viAppointmentPush : appointmentPushs) {
					viAppointmentPush.setHeadimg(baseinfsv.getHeadimg(
							Integer.valueOf(viAppointmentPush.getSpplierId()),
							AttachmentConstant.SUPPLIER_COMPANY_TYPE));
				}
			}
			data.put("pushNum", pushNum);
			data.put("grabNum", grabNum);
			data.put("data", appointmentPushs);
			int type = 1;
			if (map.containsKey("type"))
				type = Integer.valueOf(map.get("type").toString());
			if (type == 2) {// 预约看工地就不会显示数量
				data.put("pushNum", 0);
				data.put("grabNum", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/qry_app_push_list.do")
	public Object qryAppPushDetail(HttpServletRequest request) {

		List<VIAppointmentPush> appointmentPushs = null;
		Map<String, Object> map = null;
		Map<String, Object> pagination = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage = "";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage = (String) map.get("curpage");
			pagination.put("currentPage", currentPage);

			map = PaginationInterceptor.pagination(map);
			appointmentPushs = appointmentPushSV.queryAppPushDetail(map);
			// 查询下一个页面数否有数据，如无则返回hastnest为false
			map = PaginationInterceptor.nextPagination(map);
			if (appointmentPushSV.queryAppPushDetail(map).size() > 0) {
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
			return commonJsonMap.autoMap(
					JSONArray.fromObject(appointmentPushs), mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/update_app_push_status.do")
	// 修改商家推送表状态
	public Object updateAppPushStatus(HttpServletRequest request) {

		AppointmentPush appointmentPush = null;

		Map<String, Object> data = new HashMap<String, Object>();

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

			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			Integer status = Integer.valueOf((String) map
					.get("appointmentPushStatus"));
			appointmentPush.setStatus(status.byteValue());
			appointmentPushSV.update(appointmentPush);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/supp_cancel_app.do")
	public Object SuppCancelApp(HttpServletRequest request) {

		AppointmentPush appointmentPush = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage = "";
		String reason = "";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String status = "0";
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			reason = (String) map.get("map");
			status = (String) map.get("status");
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);

			if (appointmentPush.getStatus() == ConstantAppStatus.PUSH_YI_FU_DINGJIN
					&& appointmentPush.getStatus() != ConstantAppStatus.PUSH_YI_FU_KUAN
					&& appointmentPush.getStatus() != ConstantAppStatus.PUSH_YI_QIAN_YUE
					&& appointmentPush.getStatus() != ConstantAppStatus.PUSH_REFUND_DEPOSIT_AUDIT) {
				mapBusi.put("doneCode", "0020");
				mapBusi.put("mess", "订单支付状态中，不能取消");
				return -1;
			}

			//appointmentPush.setStatus(new Byte(status));

			// 如果B端取消提交了原因，需保存
			String pushReason = null;
			if (map.containsKey("pushReason"))
				pushReason = (String) map.get("pushReason");
			if (pushReason != null && !pushReason.equals(""))
				appointmentPush.setReason(pushReason);
			appointmentPush.setRandom(1);
			appointmentPushSV.update(appointmentPush);

			// 修改用户预约单状态为取消
			/*
			 * Byte s=Byte.valueOf(status);
			 * if(s==(Byte)ConstantAppStatus.PUSH_CANSLE){ Appointment
			 * app=appointmentSV.findById(appointmentPush.getAid());
			 * if(app!=null){ app.setStatus(ConstantAppStatus.CLOSE);
			 * appointmentSV.update(app); } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/supp_lier_app.do")
	public Object SuppCancel(HttpServletRequest request) {

		AppointmentPush appointmentPush = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage = "";
		String reason = "";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			reason = (String) map.get("map");
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YU_YUE_LIANG_FANG);

			// 如果B端取消提交了原因，需保存
			String pushReason = null;
			if (map.containsKey("pushReason"))
				pushReason = (String) map.get("pushReason");
			if (pushReason != null && !pushReason.equals(""))
				appointmentPush.setReason(pushReason);
			appointmentPush.setRandom(1);
			appointmentPushSV.update(appointmentPush);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/jiakebao_list.do")
	public Object qryAppPush(HttpServletRequest request) {
		List<VIAppointmentPush> appointmentPushs = null;
		Map<String, Object> map = null;
		Map<String, Object> pagination = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		List<VAppointmentPushV1> list = null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id = CommonUtil.stringToInteger((String) map.get("id"));
			list = appointmentPushSV.fin(id);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/appointmentpush_time_update.do")
	// 修改商家量房时间Appointmentpush推送表状态，传入id,修改量房时间
	public Object appointmentpush_time_update(HttpServletRequest request) {
		Appointment appointment = null;
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
			// 修改商家推送表状态
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			Date amountTime = StringAndDate.StringToDate((String) map
					.get("appointmentPushamountTime"));// 商家量房时间
			appointmentPush.setAmountTime(amountTime);
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YU_YUE_LIANG_FANG);
			appointmentPushSV.update(appointmentPush);
			// 修改预约表状态
			Appointment app = appointmentSV.findById(appointmentPush.getAid());
			app.setStatus(ConstantAppStatus.DAI_LIANG_FANG);
			// 暂时屏蔽发送短信接口
			String smsContent = Sysconfig.CONFIG_PARAM
					.get(ConfigConstant.SMS_SEND_CONTENT_CHANGEROOMTIME);// xx装修公司修改了量房时间为
			appointment = appointmentSV.findById(appointmentPush.getAid());
			String mobile = appointment.getMobile();
			SendMsgUtil.send(mobile,
					smsContent + (String) map.get("appointmentPushamountTime"));
			// 缺少在线用户推送表
			// 判断商家是否在线，如果在线则推送给B端商户
			// SuppMessagePush suppMessagePush =
			// suppMessagePushSV.selectByProperty("spId", spId);
			// PushApp.pushApp(suppMessagePush.getCid());
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(appointmentPush, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/b_amount_time_update.do")
	// 修改商家量房时间Appointmentpush推送表状态，传入id,修改量房时间
	public Object b_amount_time_update(HttpServletRequest request) {
		Appointment appointment = null;
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
			// 修改商家推送表状态
			Date amountTime = StringAndDate.StringToDate((String) map
					.get("appointmentPushamountTime"));// 商家量房时间
			int pushid = Integer.valueOf(map.get("appointmentPushId")
					.toString());// 商家量房时间
			AppointmentPush apppush = appointmentPushSV.findById(pushid);
			if (apppush.getStatus().equals(ConstantAppStatus.PUSH_CANSLE)
					|| apppush.getStatus().equals(
							ConstantAppStatus.PUSH_JIE_SHU_QIANGDAN)
					|| apppush.getRandom() == 1 || apppush.getRandom() == 2) {
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "已结束不能修改");
				return -1;
			}
			if (appointmentPushSV.updatetimeB(pushid, amountTime) == false) {
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "超出修改次数");
				return -1;
			}

			// 发送短信接口
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			String smsContent = new String(
					Sysconfig.CONFIG_PARAM
							.get(ConfigConstant.SMS_SEND_CONTENT_CHANGEROOMTIME));// xx装修公司修改了量房时间为

			smsContent = smsContent.replace("xxxx", baseinfsv.getName(
					appointmentPush.getSpId(),
					AttachmentConstant.SUPPLIER_COMPANY_TYPE));
			appointment = appointmentSV.findById(appointmentPush.getAid());
			String mobile = appointment.getMobile();
			String txt = "yyyy-MM-dd HH:mm:ss";
			DateFormat df = new SimpleDateFormat(txt);
			try {
				Date today = df.parse((String) map.get("appointmentPushamountTime"));
				String tx = "yyyy-MM-dd HH:mm";
				SimpleDateFormat ft = new SimpleDateFormat(tx);
				String DateTime= ft.format(today);
				SendMsgUtil.send(mobile,
						smsContent + DateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 
			// 缺少在线用户推送表
			// 判断用户是否在线，如果在线则推送
			String smstitle = Sysconfig.CONFIG_PARAM
					.get(ConfigConstant.SMS_SEND_TITLE_CHANGEROOMTIME);// xx装修公司修改了量房时间为

			 List<AppointmentMember> appointmentMember=appointmentmembersv.selectByProperty("aid", appointmentPush.getAid());
			List<SuppMessagePush> suppMessagePush = suppMessagePushSV
					.selectByUserIdAndType(appointmentMember.get(0).getMid(), 20);
			for (SuppMessagePush s : suppMessagePush) {
				String txt1 = "yyyy-MM-dd HH:mm:ss";
				DateFormat df1 = new SimpleDateFormat(txt1);
				try {
					Date today = df1.parse((String) map.get("appointmentPushamountTime"));
					String tx = "yyyy-MM-dd HH:mm";
					SimpleDateFormat fmt = new SimpleDateFormat(tx);
					String DateTime2 = fmt.format(today);
					PushApp.pushApp(s,s.getCid(),smstitle,smsContent+DateTime2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(appointmentPush, mapBusi);
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/c_amount_time_update.do")
	// 修改商家量房时间Appointmentpush推送表状态，传入id,修改量房时间
	public Object c_amount_time_update(HttpServletRequest request) {
		Appointment appointment = null;
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
			// 修改商家推送表状态
			Date amountTime = StringAndDate.StringToDate((String) map
					.get("appointmentPushamountTime"));// 商家量房时间
			int pushid = Integer.valueOf(map.get("appointmentPushId")
					.toString());// 商家量房时间
			if (appointmentPushSV.updatetimeC(pushid, amountTime) == false) {
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "超出修改次数");
				return -1;
			}
			// 发送短信接口
			// 商家不一定有手机号码
			String smsContent = new String(
					Sysconfig.CONFIG_PARAM
							.get(ConfigConstant.SMS_SEND_CONTENT_CHANGEROOMTIME_USER));// xx装修公司修改了量房时间为
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			appointment = appointmentSV.findById(appointmentPush.getAid());
			if (appointment != null) {
				if (appointment.getUser() != null)
					smsContent = smsContent.replace("xxxx",
							appointment.getUser());
				else {
					smsContent = smsContent.replace("xxxx", "");
				}
			}
			// String mobile=appointment.getMobile();
			// SendMsgUtil.send(mobile,smsContent+(String)map.get("appointmentPushamountTime"));
			// 缺少在线用户推送表
			// 判断商家是否在线，如果在线则推送给B端商户
			String smstitle = Sysconfig.CONFIG_PARAM
					.get(ConfigConstant.SMS_SEND_TITLE_CHANGEROOMTIME);// xx装修公司修改了量房时间为
			Supplier supplier=supplierSV.findById(appointmentPush.getSpId());
			List<SuppMessagePush> suppMessagePush = suppMessagePushSV
					.selectByUserIdAndType(appointmentPush.getSpId(), supplier.getType());
			for (SuppMessagePush s : suppMessagePush) {
				PushApp.pushApp(s,
						s.getCid(),
						smstitle,
						smsContent
								+ (String) map.get("appointmentPushamountTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(appointmentPush, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/supplier_count_add.do")
	public Object SuppCancelAdd(HttpServletRequest request) {

		AppointmentPush appointmentPush = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage = "";
		String reason = "";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			reason = (String) map.get("map");
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);

			appointmentPush.setStatus(ConstantAppStatus.PUSH_YU_YUE_LIANG_FANG);
			// 如果B端取消提交了原因，需保存
			String pushReason = null;
			if (map.containsKey("pushReason"))
				pushReason = (String) map.get("pushReason");
			if (pushReason != null && !pushReason.equals(""))
				appointmentPush.setReason(pushReason);
			appointmentPush.setRandom(1);
			appointmentPush
					.setSupplierCount(appointmentPush.getSupplierCount() + 1);
			appointmentPushSV.update(appointmentPush);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}

	

	/**
	 * 查询push单相关支付状态
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query_pay_status.do")
	public Object queryDepositStatus(HttpServletRequest request) {
		AppointmentPush appointmentPush = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map dataMap = new HashMap();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			AppointmentPush push = appointmentPushSV.queryDepositStatus(map);
			if (push == null) {
				mapBusi.put("doneCode", "1006");
				mapBusi.put("mess", "没有查到任何数据");
				return -1;
			}
			dataMap.put("pushId", push.getId());
			dataMap.put("aId", push.getAid());
			dataMap.put("depositPayStatus", push.getDepositPayStatus());
			dataMap.put("depositPayTime", push.getDepositPayTime());
			dataMap.put("depositRefundTime", push.getDepositRefundTime());
			dataMap.put("projectPayStatus", push.getProjectPayStatus());
			dataMap.put("projectPayTime", push.getProjectPayTime());
			dataMap.put("projectRefundTime", push.getProjectRefundTime());
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
			return commonJsonMap.autoMap(dataMap, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/qry_app_push_detail_qry.do")
	public Object qryAppPushDetailbn(HttpServletRequest request) {
		VIAppointmentPush appointmentPushs = null;
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
			Integer appointmentPushId = CommonUtil.stringToInteger(map.get(
					"appointmentPushId").toString());
			appointmentPushs = appointmentPushSV
					.queryAppPushbn(appointmentPushId);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(
					JSONArray.fromObject(appointmentPushs), mapBusi);
		}
	}

	/**
	 * 查询push单相关支付状态
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/depositPayProject.do")
	public Object depositPayProject(HttpServletRequest request) {
		AppointmentPush appointmentPush = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map dataMap = new HashMap();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			int pushid = Integer.valueOf(map.get("appointmentPushId")
					.toString());// 商家推送单ID
			dataMap.put("result",
					appointmentPushModelSV.depositPayProject(pushid));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(dataMap, mapBusi);
		}
	}

}
