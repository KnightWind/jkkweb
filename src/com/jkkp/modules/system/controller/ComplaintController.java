package com.jkkp.modules.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.system.model.ComplainDetails;
import com.jkkp.modules.system.service.IComplainDetailsService;
import com.jkkp.modules.system.service.IComplainService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/complaint")
public class ComplaintController extends BaseController {

	@Autowired
	private IComplainService complaintsService;
	@Autowired
	private IComplainDetailsService complaintsDetailsService;

	// 获取投诉列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("pagination", complaintsService.pagination());
		return "/complain/complain_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(complaintsService);
	}

	@AccessMenu
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, int id) {
		request.setAttribute("complaint", complaintsService.complaintDetail(id));
		List<ComplainDetails> list = complaintsDetailsService
				.complaintDetailList(id);
		request.setAttribute("complaintsDetails", list);
		return "/complain/complain_detail";
	}

	@ResponseBody
	@RequestMapping("/save.do")
	public Object save(ComplainDetails com) {
		try {
			complaintsDetailsService.saveOrUpdate(com);		
			//更新时间
			if(com.getCid()>0){
				complaintsService.updateTime(com.getCid());	
			}			
			return new ResponseObject("跟进成功");
		} catch (Exception e) {			
			logger.error("保存投诉跟进失败");
			return new ResponseObject("保存投诉跟进失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping("/close.do")
	public Object close(int id){
		try {
			complaintsService.closeOneComplaint(id);
			return new ResponseObject("关闭成功");
		} catch (Exception e) {
			logger.error("关闭投诉失败");
			return new ResponseObject("关闭失败！");
		}
	}
}
