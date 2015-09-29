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
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.VJtopic;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Label;

@Controller
@RequestMapping()
public class LabelSVController extends BaseController{
	
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private ILabelSV iLableSV;
	
	//类型:1商家2监理3设计师4工长5业主日记6监理日记7商家日记8会员
	
	
	@ResponseBody
	@RequestMapping("/addLabel.do")
	public Object addLabel(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Label label=null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		SupplierCompanyStaff staff=new SupplierCompanyStaff();

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			
			String labelNames=(String) map.get("labelName");
			Integer labelType=Integer.valueOf((String) map.get("labelType"));
			Integer  createName = Integer.valueOf((String) map.get("createName"));
			
			if(labelNames==null||labelType==null||createName==null){
				mapBusi.put("mess", "必填入参不能为空！");
				mapBusi.put("doneCode", "9999");
				return -1;
			}
			
			Date createTime=new Date();

			for(String labelName:labelNames.split(",")){
				label=new Label();
				label.setLname(labelName);
				label.setCreateName(createName);
				label.setType(labelType);
				label.setStatus(1);
				label.setCreateTime(createTime);
				iLableSV.save(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
		
	}
	@ResponseBody
	@RequestMapping("/delLabelByid.do")
	public Object delLabelByid(HttpServletRequest request) throws Exception {
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
			String labelId=(String)map.get("labelId");
			if(labelId.isEmpty()||labelId==null||labelId==""){
				mapBusi.put("mess", "入参labelId不能为空！");
				mapBusi.put("doneCode", "9999");
			}
			for(String id:labelId.split(","))
				iLableSV.deleteById(Integer.valueOf(id));
 
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/queryLabelById.do")
	//例如根据商家id查出对应标签组
	public Object queryLabelById(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<Label> labels=null;
 
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String createName=(String)map.get("createName");
			String labelType=(String)map.get("labelType");

			if(createName==null||labelType==null){
				mapBusi.put("mess", "入参createName,labelType不能为空！");
				mapBusi.put("doneCode", "9999");
				return -1;
			}


			labels=iLableSV.queryLabelById(map);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(labels, mapBusi);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/queryAllLabelById.do")
	//例如根据商家id查出对应标签组
	public Object queryAllLabelById(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<Label> labels=null;
 
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String createName=(String)map.get("createName");
			String labelType=(String)map.get("labelType");

			if(createName==null||labelType==null){
				mapBusi.put("mess", "入参createName,labelType不能为空！");
				mapBusi.put("doneCode", "9999");
				return -1;
			}


			labels=iLableSV.queryAllLabelById(map);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(labels, mapBusi);
		}
		
	}
	

}
