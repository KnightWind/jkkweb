package com.jkkp.appapi.common.control.design;

import java.util.ArrayList;
import java.util.Date;
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
import com.jkkp.appapi.common.service.interfaces.IDesignCollectSV;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.design.model.DesignCollect;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.utils.CommonUtil;
//用户收藏方案
@Controller
@RequestMapping("/")
public class DesignCollectController extends BaseController{
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired IDesignCollectSV dscSV;
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/designcollect_list.do")
	public Object designcollect_list(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";
		List<VIDesign> sclist=null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
     		currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		sclist=dscSV.designcollectlist(map);
     		for(VIDesign onesc:sclist){//设置商家头像
     			List<String> url=attachmentService.findForDownload(onesc.getDesignerid(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
     			if(!url.isEmpty())
     				onesc.setHeadurl(url.get(0));
     		}
			map= PaginationInterceptor.nextPagination(map);
			if(dscSV.designcollectlist(map).size()>0){
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
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/designcollect_add.do")
	public Object designcollect_add(HttpServletRequest request) {
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
			Integer uid=CommonUtil.stringToInteger((String) map.get("Uid"));
			Integer spid=CommonUtil.stringToInteger((String) map.get("Spid"));
			Map<String,Object> map1=new HashMap<String, Object>();
     		map1.put("Uid", uid);
 			map1.put("Spid", spid);
			List<DesignCollect> sclist=dscSV.queryDesignByUidSpid(map1);
			if(sclist.isEmpty()){
				DesignCollect supplierCollect=new DesignCollect();
				supplierCollect.setCreatetime(new Date());
				supplierCollect.setDesignid(spid);
				supplierCollect.setUserid(uid);
				dscSV.save(supplierCollect);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(null,mapBusi); 
		}
	}
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/designcollect_del.do")
	public Object designcollect_del(HttpServletRequest request) {
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
			Integer uid=CommonUtil.stringToInteger((String) map.get("Uid"));
     		String spid_list=(String) map.get("Spid");//分割符，
     		String[] sp_list=null;
     		sp_list=spid_list.split(",");
     		Map<String,Object> map1=new HashMap<String, Object>();
     		for (int i = 0; i < sp_list.length; i++) {
         		map1.put("Uid", Integer.valueOf(uid));
     			map1.put("Spid", Integer.valueOf(sp_list[i]));
     			List<DesignCollect> sclist=dscSV.queryDesignByUidSpid(map1);
     			if(!sclist.isEmpty()){
	     			dscSV.delete(sclist.get(0));
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
}
