package com.jkkp.appapi.common.control.supplier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.If;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class SupplierCollectController extends BaseController{
	
	@Autowired ISupplierCollectSV supplierCollectSV;
	@Autowired IAppointmentService appointmentService;
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	private IAttachmentService attachmentService;
	
	@ResponseBody
	@RequestMapping("/supplier_collect_count.do")//查询用户所有收藏的商家
	public Object qrySupplierCollectCount(HttpServletRequest request) {
		Integer count = null;

		Map<String, Object> data = new HashMap<String, Object>();

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

			count = supplierCollectSV.querySupCollByUid(map);
			Integer appCount = appointmentService.countAll();
			data.put("memeberCollectSupplierCount", count);
			data.put("appointmentCount", appCount);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	@ResponseBody
	@RequestMapping("/supplier_collect_add.do")//用户增加收藏商家
	public Object supplier_collect_add(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
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
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			Integer spid=CommonUtil.stringToInteger((String) map.get("spid"));
			Map<String,Object> map1=new HashMap<String, Object>();
     		map1.put("Uid", Integer.valueOf(uid));
 			map1.put("Spid", Integer.valueOf(spid));
			List<SupplierCollect> sclist=supplierCollectSV.querySupCollByUidSpid(map1);
			if(sclist.isEmpty()){
				SupplierCollect supplierCollect=new SupplierCollect();
				supplierCollect.setCreateTime(new Date());
				supplierCollect.setUid(uid);
				supplierCollect.setSpId(spid);
				supplierCollectSV.save(supplierCollect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
          
	}
	
	@ResponseBody
	@RequestMapping("/supplier_collect_query.do")//用户查询是否收藏商家
	public Object supplier_collect_query(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<SupplierCollect> sclist=null;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			Integer spid=CommonUtil.stringToInteger((String) map.get("spid"));
			Map<String,Object> map1=new HashMap<String, Object>();
     		map1.put("Uid", Integer.valueOf(uid));
 			map1.put("Spid", Integer.valueOf(spid));
			sclist=supplierCollectSV.querySupCollByUidSpid(map1);
			if(sclist.isEmpty()){
				data.put("collect", false);
			}
			else {
				data.put("collect", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
          
	}
	
	@ResponseBody
	@RequestMapping("/supplier_collect_del.do")//用户增加收藏商家
	public Object supplier_collect_del(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
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
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
     		String spid_list=(String) map.get("spid_list");//分割符，
     		String[] sp_list=null;
     		sp_list=spid_list.split(",");
     		Map<String,Object> map1=new HashMap<String, Object>();
     		for (int i = 0; i < sp_list.length; i++) {
         		map1.put("Uid", Integer.valueOf(uid));
     			map1.put("Spid", Integer.valueOf(sp_list[i]));
     			List<SupplierCollect> sclist=supplierCollectSV.querySupCollByUidSpid(map1);
     			if(!sclist.isEmpty()){
     			for(int j = 0; j < sclist.size(); j++){
     				SupplierCollect sc=sclist.get(j);
     				supplierCollectSV.delete(sc);
     			}
     			}
     			map1.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
          
	}
	
	
	@ResponseBody
	@RequestMapping("/supplier_collect_list.do")//用户查询商家收藏列表
	public Object supplier_collect_list(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";
		List<VSupplierBnjn> sclist=null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));

     		currentPage=(String) map.get("curpage");
			uid=CommonUtil.stringToInteger((String) map.get("uid"));
			pagination.put("currentPage", currentPage);
			pagination.put("uid",uid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		sclist=supplierCollectSV.querySupplierListDetial(map);
     		for(VSupplierBnjn onesc:sclist){//设置商家头像
     			List<String> url=attachmentService.findForDownload(onesc.getId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
     			if(!url.isEmpty())
     				onesc.setHeadurl(url.get(0));
     		}
			map= PaginationInterceptor.nextPagination(map);
			if(supplierCollectSV.querySupplierListDetial(map).size()>0){
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
			return commonJsonMap.autoMap(sclist,mapBusi);
		}
          
	}
}
