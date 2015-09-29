package com.jkkp.modules.supplier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.supplier.mapper.JtopicReplyMapper;
import com.jkkp.modules.supplier.service.IJtopicReplyService;
import com.jkkp.modules.supplier.view.VJtopicReply;

@Controller
@RequestMapping("/jtopicReply")
public class JtopicReplyController extends BaseController {

	@Autowired
	private IJtopicReplyService jtopicReplyService;
	@Autowired
	private JtopicReplyMapper 	jtopicReplyMapper;

	/**
	 * 删除监理日记评论,删除主评论的时候其子评论也会删除
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteById.do")
	public Object pagination(HttpServletRequest request,@RequestParam(value="id") Integer id) {
		try {
			List<VJtopicReply> list = jtopicReplyService.findReplyByPid(id);
			jtopicReplyService.deleteReply(list,id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		}
	}
	
	/**
	 * 查询子评论
	 */
	@ResponseBody
	@RequestMapping(value = "/findSubReplyByPid.do")
	public Object findSubReplyByPid(HttpServletRequest request,@RequestParam(value="id") Integer id) {
		List<VJtopicReply> list = jtopicReplyService.findReplyByPid(id);
		return list;
	}
}	
