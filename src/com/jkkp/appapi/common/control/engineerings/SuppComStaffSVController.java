package com.jkkp.appapi.common.control.engineerings;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.ISuppComStaffSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;



@Controller
@RequestMapping()
public class SuppComStaffSVController extends BaseController {

	@Autowired CommonJsonMap commonJsonMap;
	@Autowired ISuppComStaffSV iSuppComStaffSV;
	@Autowired
	private IAttachmentService attachmentService;
	
	@ResponseBody
	@RequestMapping("/addDesignStaff.do")
	public Object addDesignStaff(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
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
			//保存文件
			Attachment  attachment = attachmentService.uploadOne((MultipartRequest) request);
			if(attachment!=null){
				String staffName=(String)map.get("staffName");
				String staffJob=(String)map.get("staffJob");
				String staffIntroduce=(String)map.get("staffIntroduce");
				String staffTheory=(String)map.get("staffTheory");
				Integer suppId=Integer.valueOf((String)map.get("suppId"));
				staff.setJob(staffJob);
				staff.setSid(1);
				staff.setSjsSuggest(staffTheory);
				staff.setName(staffName);
				staff.setGzSuggest(staffIntroduce);
				staff.setSpId(suppId);
				iSuppComStaffSV.save(staff);
			     Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, staff.getId(), AttachmentConstant.SUPPLIER_STAFF_TYPE);
			}else {
				Admin admin = new Admin();
				String staffName=(String)map.get("staffName");
				String staffJob=(String)map.get("staffJob");
				String staffIntroduce=(String)map.get("staffIntroduce");
				String staffTheory=(String)map.get("staffTheory");
				
				Integer suppId=Integer.valueOf((String)map.get("suppId"));
				staff.setJob(staffJob);
				staff.setSjsSuggest(staffTheory);
				staff.setName(staffName);
				staff.setGzSuggest(staffIntroduce);
				staff.setSid(1);
				staff.setSpId(suppId);
				iSuppComStaffSV.save(staff);
				Attachment  attachment1=new Attachment();
				attachment1.setCreateTime(new Date());
				attachment1.setFilepath("/jtxweb/images/notperson.png");
				attachment1.setFilename("notperson.png");
				attachmentService.saveAttachment(attachment1, admin, staff.getId(), AttachmentConstant.SUPPLIER_STAFF_TYPE);
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
	@RequestMapping("/delDesignStaff.do")
	public Object delDesignStaff(HttpServletRequest request) throws Exception {
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
			Integer designStaffId=CommonUtil.stringToInteger(map.get("designStaffId").toString());
			if(designStaffId<=0){
				mapBusi.put("mess", "入参designStaffId不能为空！");
				mapBusi.put("doneCode", "9999");
			}			
			iSuppComStaffSV.deleteById(designStaffId);			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/queryDesignStaffBySpId.do")
	public Object queryDesignStaffBySpId(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<SupplierCompanyStaff> staffs=null;
 
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String designStaffId=(String)map.get("designStaffId");

			if(designStaffId==null){
				mapBusi.put("mess", "入参designStaffId不能为空！");
				mapBusi.put("doneCode", "9999");
				return -1;
			}
			Integer id=Integer.valueOf(designStaffId);
			staffs=iSuppComStaffSV.queryDesignStaffBySpId(map);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(staffs, mapBusi);
		}
		
	}
}
