package com.jkkp.modules.basedata.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.RegionMapper;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/admin/region")
public class WebRegionController extends BaseController {

	@Autowired
	private IRegionService regionService;
	@Autowired
	private RegionMapper regionMapper;

	
	@AccessMenu
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		String parentid=request.getParameter("parentid")==null?"0":request.getParameter("parentid");
		params.put("parentid", parentid);
		Pagination.setPageParams(request, regionMapper,"selectAllConditionRegion", "selectAllConditionRegionCount");
		request.setAttribute("pagination",regionService.paginationCustom(params));
		
		request.setAttribute("parentid", parentid);
		return "/basedata/region_list";
	}
	
	//服务区域
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, regionMapper,"selectAllConditionRegion", "selectAllConditionRegionCount");
		return new ResponsePagination(regionService.paginationCustom());
	}
	
	
	@ResponseBody
	@RequestMapping(value="/regionPass.do")
	public Object regionPass(Integer id){
		try {
			Boolean flag=regionService.regionPass(id);
			if(flag==true){
				return new ResponseObject(true, "开通成功");
			}
			return new ResponseObject(false, "开通出错");
		} catch (Exception e) {
			logger.error("开通服务区域出错");
			return new ResponseObject(false, "开通出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/regionNoPass.do")
	public Object regionNoPass(Integer id){
		try {
			Boolean flag=regionService.regionNoPass(id);
			if(flag==true){
				return new ResponseObject(true, "关闭成功");
			}
			return new ResponseObject(false, "关闭出错");
		} catch (Exception e) {
			logger.error("关闭服务区域出错");
			return new ResponseObject(false, "关闭出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="updatePoint.do")
	public Object updatePoint(String pointx,String pointy,Integer id){
		try {
			regionService.updatePoint(pointx, pointy, id);
			return new ResponseObject(true, "更新成功");
		} catch (Exception e) {
		     logger.error("更新服务区域坐标出错");
		     return new ResponseObject(false, "更新坐标出错");
		}
	}
	
}