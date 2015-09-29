package com.jkkp.appapi.common.control.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.IDesignSV;
import com.jkkp.appapi.common.service.interfaces.ILabelSV;
import com.jkkp.appapi.common.service.interfaces.ISgtopicSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.service.interfaces.ISystemRegulationSVService;
import com.jkkp.appapi.common.service.interfaces.VIStaffSV;
import com.jkkp.appapi.modules.mapper.VISgtopic;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.modules.system.model.SystemRegulation;
import com.jkkp.utils.CommonUtil;
@Controller
@RequestMapping("/")
public class SgtopicSVController extends BaseController {
	@Autowired IBaseinf baseinfsv;
	@Autowired
	ILabelSV iLabelSV;
	@Autowired 
	ISgtopicSV iSgtopicSV;
	@Autowired
	VIStaffSV viStaffSV;
	@Autowired
	IDesignSV iDesignSV;
	@Autowired
	ISupplierSV ISupplierSV;
	@Autowired 
	ISupplierCollectSV supplierCollectSV;
	@Autowired
	ISystemRegulationSVService iSystemRegulationSVService;
	private IAttachmentService attachmentService;
	@Autowired
	CommonJsonMap commonJsonMap;
	@ResponseBody
	@RequestMapping("/sjs_list.do")
	public Object qrySupplierCollectCount(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		VISgtopic viSgtopic=null;
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
			Integer sid =CommonUtil.stringToInteger((String)map.get("sid"));
			Integer uid =CommonUtil.stringToInteger((String)map.get("uid"));
			viSgtopic=iSgtopicSV.query(sid, uid);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(viSgtopic,mapBusi);
		}

	}
	@ResponseBody
	@RequestMapping("/design_zuoping.do")
	public Object qryAppPushDetail(HttpServletRequest request) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		List<Object> list2=new ArrayList<Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			Integer  mainid=CommonUtil.stringToInteger((String)map.get("sid"));
			map= PaginationInterceptor.pagination(map);	
			List<VDesign> list4=iDesignSV.pagin(map);
			if(list4!=null){
				for (VDesign vDesign : list4) {
					list.add(vDesign.getMingcheng());			
				}
			}
			List<Attachment> attachment=attachmentService.findByMainId(mainid,AttachmentConstant.DESIGN_TYPE);
			if(attachment!=null){
				for (Attachment attachment2 : attachment) {
					list2.add(attachment2.getFilepath());
				}
			}
			//查询下一个页面数否有数据，如无则返回hastnest为false
			
			map= PaginationInterceptor.nextPagination(map);
			if(iDesignSV.pagin(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			data.put("design",list);
			data.put("img",list2);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}
	}
	@ResponseBody
	@RequestMapping("/sjs_xiangqing.do")
	public Object qreySupplierCollectCount(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		VIStaff viStaff=null;
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
			Integer sid =CommonUtil.stringToInteger((String)map.get("sid"));
			viStaff=viStaffSV.getById(sid);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(viStaff,mapBusi);
		}
		
	}
	@ResponseBody
	@RequestMapping("/dingjin_xiyi.do")
	public Object qreySupplierCollect(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		String content=null;
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
			SystemRegulation systemRegulation=iSystemRegulationSVService.fin();
			content=systemRegulation.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(content,mapBusi);
		}
		
	}
	/**
	 * * 可选参数：
	 * jlAppointmentId 监理预约单id： 查询结果排除该排除该监理预约单已选的监理
	 * gcdId  工程单id： 查询结果排除该工程单已选的监理
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/jl_list.do")
	public Object appPushDetail(HttpServletRequest request) {
		List<VSupplierBnjn> list = new ArrayList<VSupplierBnjn>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		String name="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			name=(String) map.get("name");
			pagination.put("currentPage", currentPage);
			pagination.put("name",name);
			map= PaginationInterceptor.pagination(map);
			
			list=ISupplierSV.zhonghe(map);
			if(list.size()>0){
				for (VSupplierBnjn vSupplierBnjn : list) {
					vSupplierBnjn.setHeadurl(baseinfsv.getHeadimg(Integer.valueOf(vSupplierBnjn.getId()), AttachmentConstant.SUPPLIER_COMPANY_TYPE));
				}
			}
			//查询下一个页面数否有数据，如无则返回hastnest为false
			if(ISupplierSV.zhonghe(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode","9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list,mapBusi);
		}
	}
	/**
	 * * 可选参数：
	 * jlAppointmentId 监理预约单id 查询结果排除该排除该监理预约单已选的监理
	 * gcdId  工程单id 查询结果排除该工程单已选的监理
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/jl_xingji_list.do")
	public Object appPush(HttpServletRequest request) {
		List<VSupplierBnjn> list = new ArrayList<VSupplierBnjn>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			list=ISupplierSV.xingji(map);
			if(list.size()>0){
				for (VSupplierBnjn vSupplierBnjn : list) {
					vSupplierBnjn.setHeadurl(baseinfsv.getHeadimg(Integer.valueOf(vSupplierBnjn.getId()), AttachmentConstant.SUPPLIER_COMPANY_TYPE));
				}
			}
			//查询下一个页面数否有数据，如无则返回hastnest为false
			if(ISupplierSV.xingji(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode","9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list,mapBusi);
		}
	}
	
	
	/**
	 * * 可选参数：
	 * jlAppointmentId 监理预约单id 查询结果排除该排除该监理预约单已选的监理
	 * gcdId  工程单id 查询结果排除该工程单已选的监理
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/jl_shoucan.do")//查询监理信息
	public Object shouCanDetail(HttpServletRequest request) {
		List<VSupplierBnjn> list = new ArrayList<VSupplierBnjn>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		Integer uid=0;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			uid=CommonUtil.stringToInteger((String) map.get("uid"));
			pagination.put("currentPage", currentPage);
			pagination.put("uid",uid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=supplierCollectSV.queryShouCan(map);
			if(list.size()>0){
				for (VSupplierBnjn vSupplierBnjn : list) {
					vSupplierBnjn.setHeadurl(baseinfsv.getHeadimg(Integer.valueOf(vSupplierBnjn.getId()), AttachmentConstant.SUPPLIER_COMPANY_TYPE));
				}
			}
			map= PaginationInterceptor.nextPagination(map);
			if(supplierCollectSV.queryShouCan(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}
	}
	
}
