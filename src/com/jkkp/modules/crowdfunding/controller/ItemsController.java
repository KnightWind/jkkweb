package com.jkkp.modules.crowdfunding.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
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
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.crowdfunding.model.CrowdActitvity;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.crowdfunding.service.ICrowdActitvityService;
import com.jkkp.modules.crowdfunding.service.ICrowdItemService;
import com.jkkp.modules.crowdfunding.view.VCrowdItem;
import com.jkkp.modules.design.service.IBrandService;
import com.jkkp.modules.product.mapper.ItemMapper;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.service.IItemCategoryService;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Escape;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@RequestMapping("/materials/item")
@Controller
public class ItemsController extends BaseController {

	@Autowired
	private IItemService itemService;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private IItemCategoryService itemCategoryService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private AttachmentServiceImpl impl;
	@Autowired
	private ICrowdItemService crowdItemService;
	@Autowired
	private ICrowdActitvityService crowdActitvityService;
	@Autowired
	private IRegionService regionService;
	
	private final static String REDIRECT_ACTIVITY_URL = "redirect:/material/activity/index.xhtml";
	private final static String REDIRECT_ITEM_URL = "redirect:/materials/item/index.xhtml";
	private final static String AD_URL = "ad_activity_list";
	
	
	
	
	
	
	/**
	 * 取消众筹商品，将zc_item status改为2   -1未通过审核，0待审核，1审核通过，2.申请取消众筹商品，3，审核取消众筹商品通过，4.审核取消众筹商品未通过
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/removeCrowd")
	public String removeCrowd(HttpServletRequest request,Integer itemId){
		Item item = itemService.findById(itemId);
		if(item != null){
			CrowdItem citem = crowdItemService.findByItemId(itemId);
			citem.setStatus(2);
			crowdItemService.update(citem);
		}
		return REDIRECT_ITEM_URL;
	}
	
	/**
	 * 禁用商品 setStatus() 0正常  ，1禁用
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request,Integer id){
		Item item = itemService.findById(id);
		if(item != null){
			item.setStatus(1);
			itemService.update(item);
		}
		return REDIRECT_ITEM_URL;
	}
	
	
	
	/**
	 * 批量审核众筹商品
	 * @param ids 商品id
	 * @param op 审核操作状态 1通过，-1未通过
	 * @return
	 */
	@RequestMapping("/batchHandle")
	public String batchHandle(HttpServletRequest request,Integer []ids,Integer op){
		if(ids != null){
			for (Integer id : ids) {
				if(id != null){
					CrowdItem crowdItem = crowdItemService.findById(id);
					crowdItem.setStatus(op);
					crowdItemService.update(crowdItem);
				}
			}
		}
		String param = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		param = param.replace("?", "&");
		return REDIRECT_ACTIVITY_URL + "?url=" + AD_URL + param ;
	}
	
	
	
	/**setStatus() -1未通过审核，0待审核，1审核通过，2已取消
	 * 审核众筹商品页面
	 * @param request
	 * @return
	 */
	@AccessMenu
	@RequestMapping(value="/oper")
	public String oper(HttpServletRequest request,Integer id,Integer op){
		CrowdItem item = crowdItemService.findById(id);
		item.setStatus(op);
		crowdItemService.update(item);
		String param = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		param = param.replace("?", "&");
		return REDIRECT_ACTIVITY_URL + "?url=" + AD_URL + param ;
	}
	
	
	/**
	 * 审核众筹商品页面
	 * @param request
	 * @return
	 */
	@AccessMenu
	@RequestMapping(value="/view")
	public String setItemToZC(HttpServletRequest request,Integer id){
		VCrowdItem item = crowdItemService.itemInfo(id);
		item.setDetail(Escape.unescape(item.getDetail()));
		request.setAttribute("item", item);
		return "/materials/item/zc_item_view";
	}
	
	
	
	
	/**
	 * 设置商品为众筹活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setItemToZC",method=RequestMethod.POST)
	public String setItemToZC(HttpServletRequest request,CrowdActitvity activity,CrowdItem item,Integer itemId,@RequestParam Integer op){
		itemService.save(activity,item,itemId);
		return REDIRECT_ITEM_URL;
	}
	
	/**
	 * 设置商品为众筹活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setItemToZC",method=RequestMethod.GET)
	public String setItemToZC(HttpServletRequest request,Integer itemId,@RequestParam(required=false)Integer op){
		List<CrowdItem> list = crowdItemService.selectByProperty("itemId", itemId, "itemId", true);
		if(!list.isEmpty()){
			CrowdItem item = list.get(0);
			request.setAttribute("item", item);
			if(item.getActivityId() != null){
				CrowdActitvity actitvity = crowdActitvityService.findById(item.getActivityId());
				request.setAttribute("actitvity", actitvity);
			}
		}
		request.setAttribute("itemId", itemId);
		request.setAttribute("op", op);
		return "/materials/item/set_item_tozc";
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Item item,Integer []imgId){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		if(CheckedUtil.isNotEmpty(item.getId())){
			item.setDetail(Escape.escape(item.getDetail()));
			itemService.updateItme(item,imgId,(MultipartRequest)request);
		}else {
			//字段值保留
			item.setStock(item.getStock()==null?10000:item.getStock());
			item.setZcFlag(false);
			item.setSubTitle("");
			item.setStartSell(true);
			
			
			item.setCreateTime(new Date());
			item.setUpdateTime(new Date());
			item.setSpId(su.getSpId());
			item.setStatus(0);
			item.setIsLock(0);
			item.setSalesNum(0);
			item.setFollowNum(0);
			item.setIsFree(0);
			item.setRelId(0);
			item.setSku1(0);
			item.setSku2(0);
			item.setSkuData("");
			item.setDetail(Escape.escape(item.getDetail()));
			itemService.saveItem(item,imgId,(MultipartRequest)request);
		}
		return REDIRECT_ITEM_URL;
	}
	
	
	/**
	 * 商品编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,@RequestParam(required=false) Integer id){
//		List<ItemCategory> cates = itemCategoryService.select(null);
		if(CheckedUtil.isNotEmpty(id)){
			Item item = itemService.selectById(id);
			String detail = Escape.unescape(item.getDetail());
			//detail.replace("/attachment", impl.getAccessPath() + "/attachment");
			item.setDetail(detail);
			request.setAttribute("item", item);
			
//			AreaDomain aDomain = new AreaDomain();
//			aDomain.setCityDomain(item.getCityDomain());
//			List<AreaDomain> list =  areaDomainService.select(aDomain);
//			if(!list.isEmpty()){
//				request.setAttribute("cityDomain", item.getCityDomain());
//				request.setAttribute("city",list.get(0).getCity() == null ? "(无)" : list.get(0).getCity());
//			}
			
			List<VAttachment> attachments = attachmentService.findAttachment(item.getId(), AttachmentConstant.ITEM_PICTURE_PATH);
			request.setAttribute("attachments", attachments);
			if(attachments.size() < 5){
				List<String> inputs = new ArrayList<String>();
				for (int i = 0; i < 5 - attachments.size(); i++) {
					String input = "<img src=\"" + request.getContextPath() + "/images/materials/defaultAdd.png\" id=\"img"+(i+999)+"\" onclick=\"selectFile(this)\"/>" +
					"<input class=\"file\" name=\"file"+(i+999)+"\"type=\"file\" onchange=\"changeImage(this,100,100,"+(i+999)+",this.value)\"/>";
					inputs.add(input);
				}
				request.setAttribute("inputs", inputs);
			}
		}
	
		request.setAttribute("cates", itemCategoryService.getAllParentItemCategory());
		return "/materials/item/edit";
	}
	
	
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String list(HttpServletRequest request,@RequestParam(required=false)String status) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		String state = status == null ? "0" : status;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("spId", su.getSpId());
		params.put("status",state);
		Pagination.setSearchParams(params);
		//request.setAttribute("areaList", areaDomainService.finAll());
		//request.setAttribute("brands", brandService.select(null));
		Pagination.setPageParams(request, itemMapper,"selecSpItemList", "selectSpItemCount");
		request.setAttribute("pagination", itemService.paginationCustom(params));
		request.setAttribute("status", state);
		return "/materials/item/item_list";
	}

	@ResponseBody
	@RequestMapping("/pagination.do")
	public Object pagination(HttpServletRequest request,@RequestParam(required=false)String status) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("spId", su.getSpId());
		params.put("status", status == null || status.equals("-1") ? "0" : status);
		Pagination.setPageParams(request, itemMapper,"selecSpItemList", "selectSpItemCount");
		return new ResponsePagination(itemService.paginationCustom(params));
	}
	
	/**
	 * 图片上传
	 * @param ctx
	 * @throws IOException
	 * @throws FileUploadException 
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/upload_image")
	public void upload_img(HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException {
		PrintWriter out = response.getWriter();
		//定义允许上传的文件扩展名
		//HashMap<String, String> extMap = new HashMap<String, String>();
		//extMap.put("image", "gif,jpg,jpeg,png,bmp");
		response.setContentType("text/html; charset=UTF-8");
		if(!ServletFileUpload.isMultipartContent(request)){
			out.println(getError("请选择文件。"));
			return;
		} 
		Attachment att = attachmentService.uploadOne((MultipartRequest)request);
		String accessPath = Sysconfig.CONFIG_PARAM.get(ConfigConstant.IMAGE_URL);
		String url = accessPath + att.filepath;
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url",url);
		out.println(obj.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
