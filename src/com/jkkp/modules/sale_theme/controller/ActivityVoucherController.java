package com.jkkp.modules.sale_theme.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherMapper;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.service.IActivityVoucherService;
import com.jkkp.modules.sale_theme.service.impl.ActivityThemeService;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Escape;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@RequestMapping("activityVoucher")
@Controller
public class ActivityVoucherController extends BaseController {

	@Autowired
	private IActivityVoucherService activityVoucherService;
	@Autowired
	private ActivityThemeService  aThemeService;
	@Autowired
	private ActivityVoucherMapper activityVoucherMapper;
	@Autowired
	private IAttachmentService attachmentService;
	
	/**
	 * 转跳到现金券编辑页面
	 * @param request
	 * @param id
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("edit")
	public String edit(HttpServletRequest request,@RequestParam(required=false)Integer id) {
		if(CheckedUtil.isNotEmpty(id)){
			ActivityVoucher voucher = activityVoucherService.findById(id);
			request.setAttribute("voucher", voucher);
		}
		List<ActivityTheme> list = aThemeService.select(null);
		request.setAttribute("list", list);
		return "saleActivity/voucher_edit";
	}
	
	/**
	 * 添加现金券
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,ActivityVoucher voucher) {
		voucher.setDescr(Escape.escape(voucher.getDescr()));
		voucher.setUpdateTime(new Date());
		voucher.setCreateTime(new Date());
		voucher.setStatus(true);
		voucher.setAdminId(String.valueOf(1));
		Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
		if (attachment != null) {
			int mainid = voucher.getId();
			Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, mainid,AttachmentConstant.WX_ACTIVITY_VOUCHER);
		}
		activityVoucherService.save(voucher);
		String param = RequestParamUtils.joinRedirectParams(request,new String[]{"pid","mid"});
		return "redirect:/activityVoucher/index.xhtml" + param;
	}
	
	
	/**
	 * 后台现金券管理列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityVoucherMapper,"selectActivityVoucher", "selectActivityVoucherCount");
		List<ActivityTheme> list = aThemeService.select(null);
		for (ActivityTheme at : list) {
			at.setTitle(CheckedUtil.splitString(at.getTitle(), 10));
		}
		request.setAttribute("pagination", activityVoucherService.paginationCustom());
		request.setAttribute("list", list);
		return "/saleActivity/activityVoucher_list";
	}

	/**
	 * 后台活动卡管理列表 分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityVoucherMapper,"selectActivityVoucher", "selectActivityVoucherCount");
		return new ResponsePagination(activityVoucherService.paginationCustom());
	}
	
	
}
