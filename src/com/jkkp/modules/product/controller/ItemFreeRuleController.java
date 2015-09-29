package com.jkkp.modules.product.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.member.service.IMemberLevelRuleService;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;
import com.jkkp.modules.product.service.IItemFreeRuleCartService;
import com.jkkp.modules.product.service.impl.ItemFreeRuleServiceImpl;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/freeRule")
public class ItemFreeRuleController extends BaseController {
	@Autowired
	private ItemFreeRuleServiceImpl itemFreeRuleServiceImpl;
	@Autowired
	private IMemberLevelRuleService memberLevelRuleService;
	@Autowired
	private IItemFreeRuleCartService itemFreeRuleCartService;
	@Autowired
	private IItemCategoryService itemCategoryService;

	//0元购规则设置页面
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		request.setAttribute("memberLevelRule",
				memberLevelRuleService.select(null));
		request.setAttribute("itemFreeRule",
				itemFreeRuleServiceImpl.select(null));
		request.setAttribute("itemFreeRuleCart",
				itemFreeRuleCartService.selectAllFreeRuleCart(0));
		return "/goods/itemfreerule";
	}

	// 更新MemberLevelRule(等级条件)
	@ResponseBody
	@RequestMapping(value = "/update.do")
	public Object update(HttpServletRequest request){
		String param=request.getParameter("param");
		String[] rules=param.split("&");
		try {
			for (String s: rules) {
				String[] rulesVal=s.split("=");
				if(rulesVal.length==2){
					memberLevelRuleService.updateOne(CommonUtil.stringToInteger(rulesVal[0]),BigDecimal.valueOf(Double.parseDouble(rulesVal[1])));
				}
			}
			return new ResponseObject(true, "修改成功!");
		} catch (Exception e) {
			return new ResponseObject(false,"修改失败");
		}			
	}
	
	//修改0元购领取条件
	@AccessMenu
	@RequestMapping(value = "/cartEdit")
	public String cartEdit(HttpServletRequest request, Integer id){
		request.setAttribute("itemFreeRule", itemFreeRuleServiceImpl.findById(id));
		request.setAttribute("itemFreeRuleCart",
				itemFreeRuleCartService.selectAllFreeRuleCart(id));
		ItemCategory cate=new ItemCategory();
		cate.setParentId(0);
		request.setAttribute("cartList",itemCategoryService.select(cate));
		return "/goods/itemfreerule_edit";
	}
}
