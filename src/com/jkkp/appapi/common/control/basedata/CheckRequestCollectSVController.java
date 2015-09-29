package com.jkkp.appapi.common.control.basedata;

import java.util.Date;
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
import com.jkkp.appapi.common.service.interfaces.ILabelSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.CaseCollect;
import com.jkkp.modules.basedata.model.CheckRequest;
import com.jkkp.modules.basedata.service.ICaseCollectService;
import com.jkkp.modules.basedata.service.ICheckRequestService;
import com.jkkp.modules.basedata.service.IEngineeringsService;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.VJtopic;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Label;

@Controller
@RequestMapping()
public class CheckRequestCollectSVController extends BaseController{
	
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private ICheckRequestService iCheckRequestService;
	@Autowired private IEngineeringsService engineeringsv;
	@ResponseBody
	@RequestMapping("/add_check_request.do")
	public Object addLabel(HttpServletRequest request) throws Exception {
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
			Integer gcdid=Integer.valueOf((String) map.get("gcdid"));
			Integer  spid= Integer.valueOf((String) map.get("spid"));
			Integer  stageId= Integer.valueOf((String) map.get("stageId"));
			Integer  fristTime=1;
			if(map.containsKey("fristTime"))
				fristTime= Integer.valueOf((String) map.get("fristTime"));
			
			CheckRequest checkRequest=new CheckRequest();
			Date date=new Date();
			checkRequest.setCreateTime(date);
			checkRequest.setCreateUser(spid);
			checkRequest.setGcdId(gcdid);
			checkRequest.setStageId(stageId);
			if(fristTime==1){
				//第一次验收就修改工程单阶段
				engineeringsv.updateZXStage(gcdid, stageId);
				checkRequest.setRequestType(1);//首次验收
			}else {
				checkRequest.setRequestType(2);//整改验收
			}
			iCheckRequestService.save(checkRequest);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
		
	}
	
    
	@ResponseBody
	@RequestMapping("/check_time.do")
	public Object checkcre(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		 CheckRequest checkRequest=new CheckRequest();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);		
			Integer engineerId=Integer.valueOf((String) map.get("engineerId"));	
			Integer  stageId= Integer.valueOf((String) map.get("stageId"));
		    checkRequest=iCheckRequestService.queryCreate(engineerId, stageId);		
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(checkRequest, mapBusi);
		}
		
	}
}
