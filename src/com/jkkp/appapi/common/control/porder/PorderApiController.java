package com.jkkp.appapi.common.control.porder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IPorderListSV;
import com.jkkp.appapi.common.service.interfaces.IPorderTypeSV;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VIAppointmentMember;
import com.jkkp.appapi.modules.mapper.VPorderType;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.service.impl.AppointmentServiceImpl;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.product.model.PorderList;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/")
public class PorderApiController  extends BaseController {
	
	@Autowired
	private ITopicSV itopService;
	@Autowired
	private AppointmentServiceImpl appointmentServiceImpl;
	@Autowired 
	private IPorderTypeSV pTypeservice;
	@Autowired 
	private IPorderListSV plypeservice;
	
	@Autowired 
	CommonJsonMap commonJsonMap;

	
	@ResponseBody
	@RequestMapping("/PordetType_type.do")
	public Object PordetType_type(HttpServletRequest request) {

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		List<PorderType> ptlist = null;
		PorderType pt=new PorderType();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			//String currentPage = (String) map.get("curpage");		
			//ptlist=pTypeservice.findPagination(pt,1,3);
			ptlist=pTypeservice.selectByProperty("status", "1");
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(ptlist,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/PordetList_add.do")
	public Object PordetList_add(HttpServletRequest request) {

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		List<PorderType> ptlist = null;
		PorderType pt=new PorderType();
		PorderList pl=new PorderList();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String type_id = (String) map.get("type_id");	
			String prod_nanme = (String) map.get("prod_nanme");
			String total_price = (String) map.get("total_price");
			String num = (String) map.get("num");
			String tid = (String) map.get("tid");
			
			String spec=null;
			String buy_addr=null;
			String pf_id=null;
			String offline_addr=null;
			String remark=null;
			String create_user=null;
			String mx_desc=null;
			String passFlag=null;
			if(map.containsKey("spec"))
				spec=(String)map.get("spec");
			if(map.containsKey("buy_addr"))
				buy_addr=(String)map.get("buy_addr");
			if(map.containsKey("pf_id"))
				pf_id=(String)map.get("pf_id");
			if(map.containsKey("remark"))
				remark=(String)map.get("remark");
			if(map.containsKey("create_user"))
				create_user=(String)map.get("create_user");
			if(map.containsKey("mx_desc"))
				mx_desc=(String)map.get("mx_desc");
			if(map.containsKey("passFlag"))
				passFlag=(String)map.get("passFlag");
			
			
			pl.setBuyAddr(buy_addr);
			pl.setMxDesc(mx_desc);
			pl.setCreateUser(create_user);
			pl.setOfflineAddr(offline_addr);
			pl.setPassFlag(Integer.valueOf(passFlag));
			pl.setProdNanme(prod_nanme);
			pl.setRemark(remark);
			pl.setSpec(spec);
			pl.setCreateTime(StringAndDate.getTime());
			if(tid!=null)
			pl.setTid(Integer.valueOf( tid));
			if(total_price!=null)
			pl.setTotalPrice(Float.valueOf(total_price));
			if(type_id!=null)
			pl.setTypeId(Integer.valueOf(type_id));
			if(num!=null)
			pl.setNum(Integer.valueOf( num));
			if(pf_id!=null)
			pl.setPfId(Integer.valueOf(pf_id));

			
			plypeservice.saveSelective(pl);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(ptlist,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/PordetList_del.do")
	public Object PordetList_del(HttpServletRequest request) {

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		List<PorderType> ptlist = null;
		PorderList pList=null;
		PorderType pt=new PorderType();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String porderlist_id = (String) map.get("porderlist_id");	
			pList=plypeservice.findById(Integer.valueOf(porderlist_id));
			plypeservice.delete(pList);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(ptlist,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/PordetList_update.do")
	public Object PordetList_update(HttpServletRequest request) {

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		List<PorderType> ptlist = null;
		PorderType pt=new PorderType();
		PorderList pl=null;
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String type_id = (String) map.get("type_id");	
			String prod_nanme = (String) map.get("prod_nanme");
			String total_price = (String) map.get("total_price");
			String num = (String) map.get("num");
			String tid = (String) map.get("tid");
			String porderlist_id = (String) map.get("porderlist_id");	
			String spec=null;
			String buy_addr=null;
			String pf_id=null;
			String online_addr=null;
			String offline_addr=null;
			String remark=null;
			String create_user=null;
			String mx_desc=null;
			String passFlag=null;
			if(map.containsKey("spec"))
				spec=(String)map.get("spec");
			if(map.containsKey("buy_addr"))
				buy_addr=(String)map.get("buy_addr");
			if(map.containsKey("pf_id"))
				pf_id=(String)map.get("pf_id");
			if(map.containsKey("offline_addr"))
				offline_addr=(String)map.get("offline_addr");
			if(map.containsKey("remark"))
				remark=(String)map.get("remark");
			if(map.containsKey("create_user"))
				create_user=(String)map.get("create_user");
			if(map.containsKey("mx_desc"))
				mx_desc=(String)map.get("mx_desc");
			if(map.containsKey("passFlag"))
				passFlag=(String)map.get("passFlag");
			
			
			pl=plypeservice.findById(Integer.valueOf(porderlist_id));
			
			pl.setBuyAddr(buy_addr);
			pl.setMxDesc(mx_desc);
			pl.setCreateUser(create_user);
			pl.setOfflineAddr(offline_addr);
			pl.setPassFlag(Integer.valueOf(passFlag));
			pl.setProdNanme(prod_nanme);
			pl.setRemark(remark);
			pl.setSpec(spec);
			if(tid!=null)
			pl.setTid(Integer.valueOf( tid));
			if(total_price!=null)
			pl.setTotalPrice(Float.valueOf(total_price));
			if(type_id!=null)
			pl.setTypeId(Integer.valueOf(type_id));
			if(num!=null)
			pl.setNum(Integer.valueOf( num));
			if(online_addr!=null)
			pl.setPfId(Integer.valueOf(pf_id));
			
			plypeservice.update(pl);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(ptlist,mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/PordetList_query.do")
	public Object PordetList_query(HttpServletRequest request) {

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		List<PorderList> pllist = null;
		PorderList pList=null;
		PorderType pt=new PorderType();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String topic_id = (String) map.get("topic_id");	
			pllist=plypeservice.selectByProperty("tid", Integer.valueOf(topic_id));
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(pllist,mapBusi);
		}
	}
}