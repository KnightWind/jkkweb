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
import com.jkkp.modules.basedata.service.ICaseCollectService;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.VJtopic;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Label;

@Controller
@RequestMapping()
public class CaseCollectSVController extends BaseController{
	
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private ICaseCollectService iCaseCollectService;	
	@ResponseBody
	@RequestMapping("/add_case_collect.do")
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
			Integer cid=Integer.valueOf((String) map.get("cid"));
			Integer  uid= Integer.valueOf((String) map.get("uid"));
			CaseCollect caseCollect=new CaseCollect();
			caseCollect.setType("zy");
		    caseCollect.setCaseId(cid);
		    caseCollect.setTypeId(uid);
		    iCaseCollectService.save(caseCollect);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
		
	}
	

}
