package com.jkkp.modules.design.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.design.model.DesignCate;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/design/cate")
public class DesignCateController extends BaseController {

	@Autowired
	private IDesignCateService designCateService;

	@AccessMenu
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		Map<String, List<DesignCate>> map = designCateService.findCateList();
		request.setAttribute("huxing", map.get("huxing"));
		request.setAttribute("kongjian", map.get("kongjian"));
		request.setAttribute("fengge", map.get("fengge"));
		return "/design/design_cart";
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		String label = request.getParameter("label");
		String labelName = "";
		if ("huxing".equals(label)) {
			labelName = "户型";
		} else if ("kongjian".equals(label)) {
			labelName = "空间";
		} else if ("fengge".equals(label)) {
			labelName = "风格";
		}
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id != null && id > 0) {
			request.setAttribute("view", designCateService.findById(id));
		}
		request.setAttribute("label", label);
		request.setAttribute("labelName", labelName);
		return "/design/design_cart_edit";
	}

	@ResponseBody
	@RequestMapping("/save.do")
	public Object save(HttpServletRequest request, DesignCate designCate) {
		try {
			if(designCate.getCateName()==""){
				return new ResponseObject(false, "请输入分类名称");
			}
			designCateService.saveOrUpdate(designCate);
			return new ResponseObject(true);
		} catch (Exception e) {
			logger.error("保存数据出现异常", e);
			return new ResponseObject(false,"保存数据出现异常");
		}
	}
}
