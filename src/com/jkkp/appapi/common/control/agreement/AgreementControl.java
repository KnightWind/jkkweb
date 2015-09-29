package com.jkkp.appapi.common.control.agreement;

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

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAgreementImgService;
import com.jkkp.appapi.common.service.interfaces.IAgreementSV;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VAgreementDetail;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.appapi.modules.mapper.VHeTong;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.appapi.modules.mapper.VIEngneerings;
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
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.model.AgreementImg;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.JsonUtil;

@Controller @RequestMapping("/")
public class AgreementControl extends BaseController {
	@Autowired private IAgreementSV aservice;
	//装入自动组装返回报文格式
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private IAttachmentService attachmentService;
	
	//入参 合同id
	//传出  一个合同所有图片，
	@ResponseBody @RequestMapping("/agreement_query_detail.do")
	public Object agreement_query_detail(HttpServletRequest request) {
		Map<String, Object> ret=new HashMap<String, Object>();
		VHeTong	vHeTong=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request)){				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id=Integer.valueOf((String) map.get("id"));
             vHeTong=aservice.queryhetong(id);        
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(vHeTong,mapBusi);
		}
	}
	
	//入参 合同id
	//合同明细
	@ResponseBody @RequestMapping("/agreement_detail.do")
	public Object agreement_detail(HttpServletRequest request) {
		Map<String, Object> ret=new HashMap<String, Object>();
		VAgreementDetail	detail=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request)){				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id=Integer.valueOf((String) map.get("id"));
			detail=aservice.detail(id);        
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(detail,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/agreement_add.do")
	public Object agreement_add(HttpServletRequest request) {	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String fileName="";
		Integer id=null;
		Map rmap = null;
		List<Object> ret=new ArrayList<Object>();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			rmap = aservice.doTranscAddAgreement(request, map, mapBusi, ret);			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(rmap.get("id"),mapBusi);
		}
	}
    
	
	@ResponseBody
	@RequestMapping("/agreement_del.do")
	public Object agreement_del(HttpServletRequest request){	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		ArrayList<String> fileUrl=null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String fileName="";
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);			
			int agreement_id=Integer.valueOf((String) map.get("agreement_id"));
			aservice.deleteById(agreement_id);
			List<Attachment> alist = attachmentService.findByMainId(agreement_id, AttachmentConstant.AGREEMENT_TYPE);
			for (Attachment att : alist) {
				attachmentService.delete(att);
				}			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( null,mapBusi);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/agreement_del_img.do")
	public Object designImgeDel(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		ArrayList<String> urls = null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String url;
		Map dataMap = null;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			urls = (ArrayList<String>) JsonUtil.jsonToList(map.get("urls")
					.toString(), String.class);
			String agreementId =(String)map.get("agreementId");
			
			String fileBuffer = "";
			Map pmap = new HashMap();
			//mainid
			pmap.put("mainid", agreementId);
			pmap.put("filetype", AttachmentConstant.AGREEMENT_TYPE);
			dataMap = attachmentService.doTransacDelImge(urls, pmap);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(dataMap, mapBusi);
		}
	}
	
	
}