package com.jkkp.modules.supplier.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.supplier.mapper.StaffCommentMapper;
import com.jkkp.modules.supplier.model.StaffComment;
import com.jkkp.modules.supplier.service.IStaffCommentService;
import com.jkkp.utils.Pagination;

@RequestMapping("/staffComment")
@Controller
public class StaffCommentController extends BaseController {

	@Autowired
	private IStaffCommentService staffCommentService;
	@Autowired
	private StaffCommentMapper staffCommentMapper;
	
	
	/**
	 *管理员异步审核评论操作
	 */
	@ResponseBody
	@RequestMapping("/operationApproval.do")
	public Object operationApproval(@RequestParam(value="flag") boolean flag,@RequestParam(value="id") Integer id){
		try {
			StaffComment sc = staffCommentService.findById(id);
			if(flag){
				sc.setStatus(1);
				sc.setCloseTime(null);
			}else{
				sc.setStatus(0);
				sc.setCloseTime(new Date());
			}
			sc.setCheckTime(new Date());
			staffCommentService.update(sc);
			return new ResponseObject(true,"操作成功 !");
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject(false,"操作失败 !");
		}
		
	}
	
	
	
	/**
	 * 管理员后台查看指定设计师的评论
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/designReplyList")
	public String designReplyList(HttpServletRequest request,
			@RequestParam(required=false) String returnUrl,
			@RequestParam Integer id,@RequestParam(value="state",required=false) Integer state,
			@RequestParam(value="status",required=false) Integer status){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", id);
		params.put("state", state);
		Pagination.setPageParams(request, staffCommentMapper,"findPage", "countPage");
		request.setAttribute("pagination",staffCommentService.paginationCustom(params));
		request.setAttribute("mid",request.getParameter("mid"));
		request.setAttribute("pid",request.getParameter("pid"));
		request.setAttribute("staffId", id);
		request.setAttribute("status", status);
		request.setAttribute("state", state == null ? -1 : state);
		return "/supplier/staff/"+returnUrl;
	}
	
	/**
	 * 管理员后台查看指定设计师的评论分页
	 * @return
	 */
	@ResponseBody  
	@RequestMapping(value = "/designReplyPagination.do")
	public Object designReplyPagination(HttpServletRequest request,@RequestParam Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", id);
		Pagination.setPageParams(request, staffCommentMapper,"findPage", "countPage");
		return new ResponsePagination(staffCommentService.paginationCustom(params));
	}
	
	
}
