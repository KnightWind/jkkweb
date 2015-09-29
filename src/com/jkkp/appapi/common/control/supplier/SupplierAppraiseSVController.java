package com.jkkp.appapi.common.control.supplier;

import java.util.*;

import javax.servlet.http.*;

import net.sf.json.*;
import org.apache.commons.lang.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.supplier.mapper.*;
import com.jkkp.modules.supplier.model.StaffAppraise;
import com.jkkp.modules.supplier.model.SupplierAppraise;
import com.jkkp.modules.supplier.service.IStaffAppraiseService;
import com.jkkp.modules.supplier.service.ISupplierAppraiseService;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.*;

@Controller
public class SupplierAppraiseSVController extends BaseController{
	@Autowired private ITopicSV itopService;
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private IAttachmentService attachmentService;
	@Autowired private ISupplierAppraiseService iSupplierAppraiseService;
	@Autowired private IStaffAppraiseService iStaffAppraiseService;
	@ResponseBody @RequestMapping("/a.do")
	public Object queryDetail(HttpServletRequest request) {
		VISMember visMember=new VISMember();
		Map<String, Object> map = null;
		List<VAttachment> imgurlpathList=null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
			//保存文件
			List<Attachment> attachment = attachmentService.uploadMulti((MultipartRequest) request);
			if(attachment==null){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess","保存文件失败");
				return -1;
			}
			Admin admin =new Admin();
			attachmentService.saveAttachment(attachment,admin,uid,AttachmentConstant.AGREEMENT_TYPE);
			imgurlpathList=attachmentService.findAttachment(uid,AttachmentConstant.AGREEMENT_TYPE);
			visMember=itopService.getById(uid);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(visMember, mapBusi);
		}
	}
	@ResponseBody @RequestMapping("/pingjia_add_supplier.do")
	public Object saveSupplierAppraise(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			SupplierAppraise supplierAppraise=new SupplierAppraise();
			 map = commonJsonMap.setRequestMap(request);
	         float syScore=Float.valueOf((String)map.get("sy")); 
	         float sgScore=Float.valueOf((String)map.get("sg"));	        
	         float fwScore=Float.valueOf((String)map.get("fw"));
	         float money=Float.valueOf((String)map.get("money"));
	        Integer spid=CommonUtil.stringToInteger((String)map.get("spid"));
	        Integer gcdid=CommonUtil.stringToInteger((String)map.get("gcdid"));
	        Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
	        String explain=(String)map.get("explain");
	        supplierAppraise.setCreateTime(new Date());
	        supplierAppraise.setSpId(spid);
	        supplierAppraise.setSyScore(syScore);
	        supplierAppraise.setSgScore(sgScore);
	        supplierAppraise.setFwScore(fwScore);
	        supplierAppraise.setGcdId(gcdid);
	        supplierAppraise.setMoney(money);
	        supplierAppraise.setExplain(explain);
	        supplierAppraise.setUid(uid);
	        iSupplierAppraiseService.save(supplierAppraise);	        
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}
	
	@ResponseBody @RequestMapping("pingjia_add_jl.do")
	public Object saveJlAppraise(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			SupplierAppraise supplierAppraise=new SupplierAppraise();
			 map = commonJsonMap.setRequestMap(request);
			 float syScore=Float.valueOf((String)map.get("sy"));
	         float zyScore=Float.valueOf((String)map.get("zy")); 	        
	         float fwScore=Float.valueOf((String)map.get("fw"));
	         float money=Float.valueOf((String)map.get("money"));
	        Integer spid=CommonUtil.stringToInteger((String)map.get("spid"));
	        Integer gcdid=CommonUtil.stringToInteger((String)map.get("gcdid"));
	        Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
	        String explain=(String)map.get("explain");
	        supplierAppraise.setCreateTime(new Date());
	        supplierAppraise.setSpId(spid);
	        supplierAppraise.setZyScore(zyScore);
	        supplierAppraise.setSyScore(syScore);
	        supplierAppraise.setFwScore(fwScore);
	        supplierAppraise.setGcdId(gcdid);
	        supplierAppraise.setMoney(money);
	        supplierAppraise.setExplain(explain);
	        supplierAppraise.setUid(uid);
	        iSupplierAppraiseService.save(supplierAppraise);        
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}
	
	@ResponseBody @RequestMapping("/pingjia_add_sjs.do")
	public Object saveSjsAppraise(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request); 
			StaffAppraise staffAppraise=new StaffAppraise();
			staffAppraise.setCreateTime(new Date());
			  float fw=Float.valueOf((String)map.get("fw"));
			  float sj=Float.valueOf((String)map.get("sj"));
			  float sy=Float.valueOf((String)map.get("sy"));
			  String explain=(String)map.get("explain");
			  Integer sid=CommonUtil.stringToInteger((String)map.get("sid"));
		     Integer gcdid=CommonUtil.stringToInteger((String)map.get("gcdid"));
	         float money=Float.valueOf((String)map.get("money"));
	         staffAppraise.setFwScore(fw);
	         staffAppraise.setGcdId(gcdid);
	         staffAppraise.setSid(sid);
	         staffAppraise.setSyScore(sy);
	         staffAppraise.setSjScore(sj);
	         staffAppraise.setExplain(explain);
             staffAppraise.setMoney(money);
	        iStaffAppraiseService.save(staffAppraise);	        
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
}
