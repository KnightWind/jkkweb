package com.jkkp.modules.member.controller;

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
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.service.ITopicCommentService;
import com.jkkp.modules.member.service.ITopicService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/topic")
public class TopicController extends BaseController {
	@Autowired
	private TopicMapper topicMapper;

	@Autowired
	private ITopicService topicService;

	@Autowired
	private IAreaDomainService areaDomainService;

	@Autowired
	private ITopicCommentService topicCommentService;

	// 业主日记
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(map);
		Pagination.setPageParams(request, topicMapper, "selectAllMemberTopic",
				"selectAllMemberTopicCount");
		request.setAttribute("pagination", topicService.paginationCustom());
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("lst", areaDomainService.finAll());
		return "/member/topic_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, topicMapper, "selectAllMemberTopic",
				"selectAllMemberTopicCount");
		return new ResponsePagination(topicService.paginationCustom());
	}

	// 加载日记信息，与日记评论信息
	// id:日记id
	// cid:查看关于（日记评论cid==id或者子评论cid!=id）信息
	// storey:1:标识指向拿取日记评论     2:页面传参，标识指向子评论
	@AccessMenu
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		request.setAttribute("topic", topicService.detail(id));
		int storey = request.getParameter("storey") != null ? CommonUtil
				.stringToInteger(request.getParameter("storey")) : 1;
		// 日记评论
		int cid = request.getParameter("cid") != null ? CommonUtil
				.stringToInteger(request.getParameter("cid")) : id;
		if (cid == id && storey == 1) {
			request.setAttribute("topicComment",
					topicCommentService.selectAllParentComment(cid));
		} else {
			request.setAttribute("topicComment",
					topicCommentService.selectAllChildComment(cid));
		}
		request.setAttribute("storey", storey);
		return "/member/topic_detail";
	}

	// 日记审核不通过
	@ResponseBody
	@RequestMapping(value = "/noPass.do")
	public Object noPass(HttpServletRequest requeset, int id) {
		try {
			topicService.noPass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("审核业主日记出错", e);
			return new ResponseObject(false, "审核失败");
		} finally {

		}
	}

	// 日记审核通过
	@ResponseBody
	@RequestMapping(value = "/pass.do")
	public Object pass(HttpServletRequest requeset, int id) {
		try {
			topicService.pass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("审核业主日记出错", e);
			return new ResponseObject(false, "审核失败");
		} finally {

		}
	}

	// 日记评论审核不通过
	@ResponseBody
	@RequestMapping(value = "/commentNoPass.do")
	public Object commentNoPass(HttpServletRequest requeset, int id) {
		try {
			topicCommentService.noPass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("审核业主日记评论出错", e);
			return new ResponseObject(false, "审核失败");
		} finally {

		}
	}

	// 日记评论审核通过
	@ResponseBody
	@RequestMapping(value = "/commentPass.do")
	public Object commentPass(HttpServletRequest requeset, int id) {
		try {
			topicCommentService.pass(id);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			logger.error("审核业主日记评论出错", e);
			return new ResponseObject(false, "审核失败");
		} finally {

		}
	}

}
