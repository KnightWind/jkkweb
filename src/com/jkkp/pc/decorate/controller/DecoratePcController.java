package com.jkkp.pc.decorate.controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jkkp.common.BaseController;

import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.pc.common.constants.AjaxHelper;
import com.jkkp.pc.decorate.service.IDecorateService;
import com.jkkp.pc.decorate.view.VDecorate;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;
 
@Controller
@RequestMapping("/main/decoratePC")
public class DecoratePcController extends BaseController {

	private final static int PAGE_SIZE = 20;
	
	@Autowired
	private IDecorateService decorateService;
	@Autowired
	private IAttachmentService attachmentService;

	/**
	 * pc 装修图库列表
	 */
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam(required=false)Integer page) {
		request.setAttribute("designCate", decorateService.getDesignCases());
		if(page == null)page = 0;
		Map<String, Object> paramMap = new Hashtable<String, Object>();
		paramMap.put("page", page * PAGE_SIZE);
		paramMap.put("pageSize", PAGE_SIZE);
		setAttr(request,paramMap);
		List<VDecorate> designList = decorateService.queryDecorateByPBL(paramMap);
		long countPage = decorateService.queryDecorateByPBLCount(paramMap);
		countPage = (int) ((countPage - 1) / PAGE_SIZE + 1);
		request.setAttribute("designList", designList);
		request.setAttribute("countPage", countPage);
		return "/pc/decorate/decorate_list";
	}
	
	/**
	 * pc 装修图库详情ajax请求 返回json
	 */
	
	@ResponseBody
	@RequestMapping("/aj_loadData")
	public void aj_loadData(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false)Integer id) throws IOException{
		Map<String, Object> paramMap = new Hashtable<String, Object>();
		AjaxHelper.setStatusAndMsg(paramMap, "加载成功！", AjaxHelper.SUCCESS_CODE);
		try {
			if(CheckedUtil.isNotEmpty(id)){
				VDecorate vd = decorateService.queryDecorateById(id);
				List<VAttachment> attList = attachmentService.findAttachment(id, AttachmentConstant.DESIGN_TYPE);
				Attachment att = null;
				if(CheckedUtil.isNotEmpty(vd.getPid())){
					att = attachmentService.findById(vd.getPid());
					vd.setCover(attachmentService.findForDownloadById(vd.getPid()));
					if(CheckedUtil.isNotEmpty(att)){
						for (VAttachment va : attList) {
							if(va.getId().equals(vd.getPid())){
								attList.remove(va);
								break;
							}
						}
					}
				}else{
					if(CheckedUtil.isNotEmpty(attList) && attList.size() > 0){
						vd.setCover(attList.get(0).getDownloadPath());
					}else{
						vd.setCover("");
					}
				}
				vd.setAttList(attList);
				paramMap.put("data", vd);
			}else{
				AjaxHelper.setStatusAndMsg(paramMap, "加载出错！", AjaxHelper.FAIL_CODE);
			}
		} catch (Exception e) {
			AjaxHelper.setStatusAndMsg(paramMap, "加载出错！", AjaxHelper.FAIL_CODE);
		}finally{
			AjaxHelper.objectToJson(response,paramMap);
		}
	}
	
	
	
	/**
	 * pc 装修图库列表ajax请求  返回json
	 */
	
	@ResponseBody
	@RequestMapping("/aj_loadDataList")
	public void aj_loadDataList(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false)Integer page) throws IOException{
		Map<String, Object> paramMap = new Hashtable<String, Object>();
		try {
			if(page == null)page = 0;
			paramMap.put("page", page * PAGE_SIZE);
	 		paramMap.put("pageSize", PAGE_SIZE);
	 		setAttr(request,paramMap);
			List<VDecorate> list = decorateService.queryDecorateByPBL(paramMap);
			long countPage = decorateService.queryDecorateByPBLCount(paramMap);
			countPage = (int) ((countPage - 1) / PAGE_SIZE + 1);
			paramMap.clear();
			paramMap.put("data", list);
			paramMap.put("page", page);
			paramMap.put("countPage", countPage);
			AjaxHelper.setStatusAndMsg(paramMap, "加载成功！", AjaxHelper.SUCCESS_CODE);
		} catch (Exception e) {
			AjaxHelper.setStatusAndMsg(paramMap, "加载出错！", AjaxHelper.FAIL_CODE);
		}finally{
			AjaxHelper.objectToJson(response, paramMap);
		}
	}
	
	/**
	 * 设置参数
	 * @param request
	 * @param paramMap
	 */
	public void setAttr(HttpServletRequest request,Map<String, Object> paramMap){
		String kongjian = request.getParameter("kongjian");
		paramMap.put("kongjian", CommonUtil.stringToInteger(kongjian == null || kongjian == "" ? "0" : kongjian));
		String fengge = request.getParameter("fengge");
		paramMap.put("fengge",  CommonUtil.stringToInteger(fengge == null || fengge == "" ? "0" : fengge));
		String huxing = request.getParameter("huxing");
		paramMap.put("huxing",  CommonUtil.stringToInteger(huxing == null || huxing == "" ? "0" : huxing));
	}
	
	
	
}
