package com.jkkp.modules.crowdfunding.controller;

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
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.crowdfunding.mapper.ActivityItemMapper;
import com.jkkp.modules.crowdfunding.service.IZCItemService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value="adminZCItem")
public class AdminZCItemController extends BaseController {

	@Autowired
	private ActivityItemMapper activityItemMapper;
	@Autowired
	private IZCItemService zcitemService;
	@Autowired
	private IAttachmentService attachmentService;
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Pagination.setPageParams(request, activityItemMapper,"selectAllZCItemInfo", "selectAllZCItemInfoCount");
		request.setAttribute("pagination",zcitemService.paginationCustom());
		return "/materials/admin/zcItem";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityItemMapper,"selectAllZCItemInfo", "selectAllZCItemInfoCount");
		return new ResponsePagination(zcitemService.paginationCustom());
	}
	
	@ResponseBody
	@RequestMapping(value="saveOneImg.do",method=RequestMethod.POST)
	public Object saveOne(HttpServletRequest request,@RequestParam Integer id,@RequestParam Integer itemId){
		try {
			MultipartRequest multiPart=(MultipartRequest)request;
			//保存分类的图片
			Attachment attachment=attachmentService.uploadOne(multiPart);
			
			//保存一附件记录
			if(attachment!=null){
				attachmentService.saveAttachment(attachment, id, AttachmentConstant.ACTIVITY_ZC_ITEM);
			}
			
			activityItemMapper.updateOneItemFlag(itemId);
			return new ResponseObject(true, "设置成功");
		}catch(Exception e){
			logger.error("保存众凑商品出错");
			return new ResponseObject(false, "保存出错");
		}
	}
}
