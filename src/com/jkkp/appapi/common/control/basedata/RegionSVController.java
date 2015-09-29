package com.jkkp.appapi.common.control.basedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IRegionSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.model.Region;

@Controller
public class RegionSVController extends BaseController{
	
	//装入自动组装返回报文格式
	@Autowired 
	CommonJsonMap commonJsonMap;
	@Autowired
	IRegionSV iRegionSV;
	
	
	
	@ResponseBody
	@RequestMapping("/getBaseRegion.do")
	public Object getBaseRegion(HttpServletRequest request) {	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<Region> regions=new ArrayList<Region>();
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			List<Region> retregion = null;
			if(map.get("status") != null) {
				retregion=iRegionSV.selectByProperty(new String[]{"level", "status"}, new Object[]{1,map.get("status")});
			} else {
				retregion=iRegionSV.selectByProperty("level", 1);
			}
			
			if(retregion.size()>0){
				for (Region region : retregion) {
					if(region.getRegionid()>43)
					regions.add(region);
				}
				for (Region region : retregion) {
					if(region.getRegionid()<=43)
					regions.add(region);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( regions,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/getNexteRegion.do")
	public Object getNexteRegion(HttpServletRequest request) {	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<Region> regions=null;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String parentid=map.get("parentid").toString();

		    regions=iRegionSV.findByParentid(map);

			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( regions,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/getRegionId.do")
	public Object getRegionId(HttpServletRequest request) {	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<Region> regions=null;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String regionname=map.get("regionname").toString();

		    regions=iRegionSV.selectByProperty("regionname", regionname);
		    if(!regions.isEmpty()){
		    	return commonJsonMap.autoMap( regions.get(0),mapBusi);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(regions,mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/getRegionByCityName.do")
	public Object getRegionByCityName(HttpServletRequest request) {	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Region region=null;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			region=iRegionSV.findRegionByName(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(region,mapBusi);
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/getOpenRegionCity.do")
	public Object getOpenRegionCity(HttpServletRequest request) {	
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
			//map = commonJsonMap.setRequestMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(iRegionSV.findOpenCity(),mapBusi);
		}
	}
}
