package com.jkkp.modules.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IEventService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.impl.MemberServiceImpl;
import com.jkkp.modules.order.service.IOrderFreeService;
import com.jkkp.modules.order.service.IOrderPackageService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/members")
public class MemberController extends BaseController {

	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private IOrderFreeService orderFreeService;
	@Autowired
	private IEventService eventService;
	@Autowired
	private IOrderPackageService orderPackageService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String memberIndex(HttpServletRequest request) {
		request.setAttribute("pagination", memberService.pagination());
		return "/member/member_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(memberService);
	}

	@AccessMenu
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request) {
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id > 0) {
			Member member = memberService.findById(id);
			request.setAttribute("member", member);
			request.setAttribute("orderFree",
					orderFreeService.selectUserOrderFree(id));
			request.setAttribute("events",
					eventService.selectUserEvent(member.getMobile()));
			request.setAttribute("orderPackage",
					orderPackageService.selectAllUserOrderPackage(id));
			request.setAttribute("orderPackageItem",
					orderPackageService.selectAllUserOrderPackageItem(id));
		}
		return "/member/member_detail";
	}
	
	@ResponseBody
	@RequestMapping(value="deleteOne.do")
	public Object deleteOne(Integer id){
		try {
			memberService.deleteOne(id);
			return new ResponseObject(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(false, "删除出错");
		}
	}
}
