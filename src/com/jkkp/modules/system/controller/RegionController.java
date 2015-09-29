package com.jkkp.modules.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.system.service.IRegionService;

@RequestMapping("/region")
@Controller
public class RegionController extends BaseController {
	
	private final static int FILE_CODE = 1;
	private final static int SUCC_CODE = 0;
	
	@Autowired
	private IRegionService regionService;
	
	@Autowired
	private com.jkkp.pc.main.service.IRegionService  service;
	@ResponseBody
	@RequestMapping(value = "/getList.do")
	public Object getList(HttpServletRequest request,HttpServletResponse response,Integer parentId) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", SUCC_CODE);
		Region re = new Region();
		re.setParentid(parentId);
		re.setStatus(1);
		try {
			List<Region> list=regionService.select(re);
			map.put("list", list);
		} catch (Exception e) {
			map.put("code", FILE_CODE);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getNetxData.do")
	public Object getNetxData(HttpServletRequest request,HttpServletResponse response,Integer parentId) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", SUCC_CODE);
		Region re = new Region();
		re.setParentid(parentId);
		try {
			List<Region> list=regionService.select(re);
			map.put("list", list);
		} catch (Exception e) {
			map.put("code", FILE_CODE);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/childrenRegion.do")
	public Object childrenRegion(Integer pid){
		List<Region> list = service.getChileRegions(pid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
}
