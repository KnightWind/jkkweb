package com.jkkp.appapi.common.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.appapi.putapp.PushApp;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Controller
@RequestMapping("/aa")
public class TestSVController extends BaseController{
	
	
	//装入自动组装返回报文格式
	@Autowired 
	CommonJsonMap commonJsonMap;
	@Autowired IAppointmentSV appsv;
	
	@Autowired public ISuppMessagePushSV suppMessagePushSV;
	@ResponseBody
	@RequestMapping("/fileUpload.do")
	public Object queryDetail(HttpServletRequest request) {
		List<VAppointment> appointments=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
//			//如果判断异常，则退出不做业务处理
//			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
//			{				
//				return -1;
//			}
//			map = commonJsonMap.setRequestMap(request);
			HttpFileTools.uploadMulti((MultipartRequest) request);
			System.out.println(request.getContextPath());
			data.put("result", 1);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap( appointments,mapBusi);
		}
	}
	
	@RequestMapping("/list4json.do")//查询
	public void list4json(HttpServletRequest request,HttpServletResponse response,
	@RequestParam(defaultValue="20")Integer pageSize,
	@RequestParam(defaultValue="1")Integer pageNo) throws Exception {
		
		System.out.println("ddd");
	}
	
	@ResponseBody
	@RequestMapping("/testmess.do")
	public Object testmess(HttpServletRequest request) throws Exception {
		String spId=request.getParameter("spid");
	List<SuppMessagePush>  suppMessagePushs=null;
	suppMessagePushs = suppMessagePushSV.selectByProperty("spId", spId);
	if(suppMessagePushs.size()>0){
		//推送到B端，让其显示新增的预约信息
		for(int j=0;j<suppMessagePushs.size();j++)
			PushApp.pushApp(suppMessagePushs.get(j));
	}
	return suppMessagePushs;
	}
	
	@ResponseBody
	@RequestMapping("/testmesscid.do")
	public Object testmesscid(HttpServletRequest request) throws Exception {
		String spId=request.getParameter("cid");
	List<SuppMessagePush>  suppMessagePushs=null;
	suppMessagePushs = suppMessagePushSV.selectByProperty("cid", spId);
	if(suppMessagePushs.size()>0){
			PushApp.pushApp(suppMessagePushs.get(0));
	}
	return suppMessagePushs;
	}
	
	@ResponseBody
	@RequestMapping("/testcap.do")
	public Object testcap(HttpServletRequest request) throws Exception {
		String spId=request.getParameter("aid");
		String version=request.getParameter("ver");
		Appointment appointment=null;
		appointment=appsv.findById(Integer.valueOf(spId));
		if(appointment==null){
			return "no data";
		}
		appointment.setVersion(Integer.valueOf(version));
		
		appointment.setGrabCount(appointment.getGrabCount()+1);
		if(appsv.updateCAP(appointment)==true){
			return "count="+appointment.getGrabCount()+"version="+appointment.getVersion();
		}
		return "you to late";
			
	}
	
}
