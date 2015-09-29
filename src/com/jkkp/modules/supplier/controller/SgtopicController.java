package com.jkkp.modules.supplier.controller;

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
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.supplier.mapper.SgtopicMapper;
import com.jkkp.modules.supplier.service.ISgtopicReplyService;
import com.jkkp.modules.supplier.service.ISgtopicService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/sgtopic")
public class SgtopicController extends BaseController {
	@Autowired
	private SgtopicMapper sgtopicMapper;
	@Autowired
	private ISgtopicService sgtopicService;
	@Autowired
	private ISgtopicReplyService sgtopicReplyService;
	@Autowired
	private IAreaDomainService areaDomainService;

	// 设计师日记@20150704黄宇健
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String businessTopic(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, sgtopicMapper, "selectAllSgtopic",
				"selectAllSgtopicCount");
		request.setAttribute("pagination", sgtopicService.paginationCustom());
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("lst", areaDomainService.finAll());
		return "/supplier/sgTopic_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, sgtopicMapper, "selectAllSgtopic",
				"selectAllSgtopicCount");
		return new ResponsePagination(sgtopicService.paginationCustom());
	}

	//设计师日记详情
	@AccessMenu
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			request.setAttribute("topic", sgtopicService.sgtopicDetail(id));
			int storey = request.getParameter("storey") != null ? CommonUtil
					.stringToInteger(request.getParameter("storey")) : 1;
			// 日记评论
			int cid = request.getParameter("cid") != null ? CommonUtil
					.stringToInteger(request.getParameter("cid")) : id;
			if (cid == id && storey == 1) {
				request.setAttribute("topicComment",
						sgtopicReplyService.selectAllParentComment(cid));
			} else {
				request.setAttribute("topicComment",
						sgtopicReplyService.selectAllChildComment(cid));
			}
			request.setAttribute("storey", storey);
		}

		return "/supplier/sgTopic_detail";
	}

	@ResponseBody
	@RequestMapping(value = "/pass.do")
	public Object pass(HttpServletRequest request) {
		try {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			sgtopicService.pass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("设计师日记审核出错");
			return new ResponseObject(false, "审核失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/noPass.do")
	public Object noPass(HttpServletRequest request) {
		try {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			sgtopicService.noPass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("设计师日记审核出错");
			return new ResponseObject(false, "审核失败");
		}
	}
	
	// 日记评论审核不通过
		@ResponseBody
		@RequestMapping(value = "/commentNoPass.do")
		public Object commentNoPass(HttpServletRequest requeset, int id) {
			try {
				sgtopicReplyService.noPass(id);
				return new ResponseObject(true, "审核成功");
			} catch (Exception e) {
				logger.error("审核设计师日记评论出错", e);
				return new ResponseObject(false, "审核失败");
			} finally {

			}
		}

		// 日记评论审核通过
		@ResponseBody
		@RequestMapping(value = "/commentPass.do")
		public Object commentPass(HttpServletRequest requeset, int id) {
			try {
				sgtopicReplyService.pass(id);
				return new ResponseObject(true, "审核成功");
			} catch (Exception e) {
				logger.error("审核设计师日记评论出错", e);
				return new ResponseObject(false, "审核失败");
			} finally {

			}
		}
}
