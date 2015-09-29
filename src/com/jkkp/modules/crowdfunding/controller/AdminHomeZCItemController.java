package com.jkkp.modules.crowdfunding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.crowdfunding.mapper.ActivityItemMapper;
import com.jkkp.modules.crowdfunding.service.IZCItemService;

@Controller
@RequestMapping(value="adminHomeZCItem")
public class AdminHomeZCItemController extends BaseController {

	@Autowired
	private ActivityItemMapper activityItemMapper;
	@Autowired
	private IZCItemService zcitemService;
	@Autowired
	private IAttachmentService attachmentService;
	
	@AccessMenu
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("itemList",zcitemService.selectAllHomeZCItem());
		return "/materials/admin/zcHomeItem";
	}

	@ResponseBody
	@RequestMapping(value="deleteOne.do")
	public Object deleteOne(@RequestParam Long id){
		try {
			if(id!=null){
			    Long itemId=zcitemService.selectItemId(id);
				zcitemService.updateOneItemFlagFalse(itemId);
				//zcitemService.deleteOneZCItem(id);
				//删除附件
				attachmentService.deleteOneAttachment(id, AttachmentConstant.ACTIVITY_ZC_ITEM);
				return new ResponseObject(true, "移除成功");
			}
			return new ResponseObject(false, "移除失败");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("移除众凑商品出错");
			return new ResponseObject(false, "移除失败");
		}
	}
}
