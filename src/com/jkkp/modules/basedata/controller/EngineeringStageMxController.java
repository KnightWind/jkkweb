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
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.EngineeringStageMxMapper;
import com.jkkp.modules.basedata.model.EngineeringStageMx;
import com.jkkp.modules.basedata.service.IEngineeringStageMxService;
import com.jkkp.modules.basedata.service.IEngineeringStageService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/engineeringStageMx")
public class EngineeringStageMxController extends BaseController {

	@Autowired
	private IEngineeringStageMxService engineeringStageMxService;

	@Autowired
	private EngineeringStageMxMapper engineeringStageMxMapper;

	@Autowired
	private IEngineeringStageService engineeringStageService;

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/save.do") public Object save(EngineeringStageMx
	 * eng) { try { if(eng.getMxDesc()==""){ return new ResponseObject(false,
	 * "请输入节点描述！"); } engineeringStageMxService.saveOrUpdate(eng); return new
	 * ResponseObject(true, "操作成功！"); } catch (Exception e) {
	 * logger.error("保存节点出错", e); return new ResponseObject(false, "操作失败！"); }
	 * finally {
	 * 
	 * } }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/hide.do") public Object hide(int id) { try {
	 * engineeringStageMxService.hide(id); return new ResponseObject(true,
	 * "隐藏成功！"); } catch (Exception e) { logger.error("验收标准隐藏出错", e); return new
	 * ResponseObject(false, "隐藏失败！"); } finally {
	 * 
	 * } }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/show.do") public Object show(int id) { try {
	 * engineeringStageMxService.show(id); return new ResponseObject(true,
	 * "显示成功！"); } catch (Exception e) { logger.error("验收标准隐藏出错", e); return new
	 * ResponseObject(false, "显示失败！"); } finally {
	 * 
	 * } }
	 */

	// 验收节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMxMapper,
				"selectAllEngineeringStageMx",
				"selectAllEngineeringStageMxCount");
		Map<String, Object> params = new HashMap<String, Object>();
		if (request.getParameter("ppid") != null) {
			int ppid = CommonUtil.stringToInteger(request.getParameter("ppid"));
			params.put("ppid", ppid);
			request.setAttribute("ppid", ppid);
		}
		request.setAttribute("pagination",
				engineeringStageMxService.paginationCustom(params));
		return "/basedata/engineeringStageMx_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, engineeringStageMxMapper,
				"selectAllEngineeringStageMx",
				"selectAllEngineeringStageMxCount");
		return new ResponsePagination(
				engineeringStageMxService.paginationCustom());
	}

	// 进入编辑验收节点
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			request.setAttribute("engineeringStageMx",
					engineeringStageMxService.findById(id));
		}
		request.setAttribute("oneStage",
				engineeringStageService.selectAllParentStage());
		return "/basedata/engineeringStageMx_edit";
	}

	//修改验收标准
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/updateOne")
	public String updateOne(HttpServletRequest request) {
		Integer id=CommonUtil.stringToInteger(request.getParameter("id"));
		if(id!=null){
			EngineeringStageMx mx=engineeringStageMxService.findById(id);
			request.setAttribute("engineeringStageMx", mx);
		}
		return "/basedata/engineeringStageMx_update";
	}
	
	// 删除一验收节点
	@ResponseBody
	@RequestMapping(value = "/deleteOne.do")
	public Object deleteOne(Integer id) {
		try {
			if (id != null) {
				engineeringStageMxService.deleteOne(id);
				return new ResponseObject(true, "删除成功");
			}
			return new ResponseObject(false, "删除失败");
		} catch (Exception e) {
			logger.error("删除验收节点失败");
			return new ResponseObject(false, "删除失败");
		}
	}

	// 添加一验收标准
	@ResponseBody
	@RequestMapping(value = "/saveOne.do")
	public Object saveOne(EngineeringStageMx stage, HttpServletRequest request) {
		try {
			if (stage.getId() == null && stage.getPid() == null) {
				return new ResponseObject(false, "请选择父节点");
			}
			if (stage.getMxDesc().isEmpty()) {
				return new ResponseObject(false, "请输入验收节点");
			}
			engineeringStageMxService.saveOrUpdate(stage, request);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存验收节点出错");
			return new ResponseObject(false, "保存失败");
		}
	}

}
