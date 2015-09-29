package com.jkkp.modules.crowdfunding.controller;

import java.util.ArrayList;
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
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.crowdfunding.model.ItemCategoryInfo;
import com.jkkp.modules.crowdfunding.service.IItemCategoryInfoService;
import com.jkkp.modules.crowdfunding.view.VItemCategoryInfo;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;

@Controller
@RequestMapping(value = "adminZCCategory")
public class AdminZCCategoryController extends BaseController {

	@Autowired
	private IItemCategoryService itemCategoryService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IItemCategoryInfoService itemCategoryInfoService;

	@AccessMenu
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) {
		//设置分类的6个序号
		List<Integer> nums=new ArrayList<Integer>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(5);
		nums.add(6);
		
        //根分类列表
		List<ItemCategory> icList = itemCategoryService.getAllParentItemCategory();
		request.setAttribute("icList", icList);
		
		//已设置的众筹分类列表
		List<VItemCategoryInfo> categoryList = itemCategoryInfoService.getAllItemCategoryInfo();
		request.setAttribute("itemCategory", categoryList);
		
		//可设置的分类序号
		if(categoryList.size()>0){
			for (VItemCategoryInfo temp : categoryList) {
				nums.remove(temp.getSeq());
			}
		}
		
		//把可设置的序号放到页面
		
		request.setAttribute("useCategory", nums);
		return "materials/admin/zcCategory";
	}
	
	@RequestMapping(value="saveOneCate.do",method=RequestMethod.POST)
	public String saveOne(HttpServletRequest request,@RequestParam Integer categoryId,String title,@RequestParam Integer seq){
		//response.setCharacterEncoding("UTF-8");
		try {
			MultipartRequest multiPart=(MultipartRequest)request;
			//Map<String, MultipartFile> map=multiPart.getFileMap();
			//String fileName=map.keySet().iterator().next();
			//MultipartFile file=map.get(fileName);
			
			//保存对象
			ItemCategoryInfo cateBean=new ItemCategoryInfo();
			cateBean.setCategoryId(categoryId);
			cateBean.setTitle(title);
			cateBean.setSeq(seq);
			itemCategoryInfoService.saveOne(cateBean);
			
			//保存分类的图片
			Attachment attachment=attachmentService.uploadOne(multiPart);
			
			//保存一附件记录
			if(attachment!=null){
				int mainid = cateBean.getId();
				attachmentService.saveAttachment(attachment, mainid, AttachmentConstant.ACTIVITY_HOME);
			}
			return "redirect:/adminZCCategory/index.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新建众凑商品分类出错");
			return "redirect:/adminZCCategory/index.xhtml";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="deleteOne.do")
	public Object deleteOne(Integer id){
		try {
			if(id!=null){
				attachmentService.deleteOneAttachment(Long.valueOf(id.toString()), AttachmentConstant.ACTIVITY_HOME);
				itemCategoryInfoService.deleteOneZCItemCategory(id);
				return new ResponseObject(true, "删除成功");
			}
			return new ResponseObject(false, "删除出错");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除一众凑分类出错");
			return new ResponseObject(false, "删除出错");
		}
	}
}
