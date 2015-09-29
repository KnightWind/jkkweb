package com.jkkp.modules.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.product.model.PurchaseFor;
import com.jkkp.modules.product.service.IPurchaseForService;

@Controller
@RequestMapping("purchaseFor")
public class PurchaseForController extends BaseController {

	@Autowired
	private IPurchaseForService purchaseForService;

	@AccessMenu
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		request.setAttribute("purchaseForList",
				purchaseForService.getAllPurchaseFor());
		return "/product/purchaseFor_list";
	}

	@ResponseBody
	@RequestMapping("save")
	public Object save(HttpServletRequest request, PurchaseFor pf) {
		try {
			if (pf.getGmdname().isEmpty()) {
				return new ResponseObject(false, "名称不能为空");
			}
			purchaseForService.saveOrUpdate(pf, request);
			return new ResponseObject(true, "新增成功");
		} catch (Exception e) {
			logger.error("新增购买地出错");
			return new ResponseObject(false, "新增失败");
		}

	}
	
	@ResponseBody
	@RequestMapping("delete.do")
	public Object delete(int id){
		try {
			purchaseForService.deleteOnePurchaseFor(id);
			return new ResponseObject(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除购买地出错");
			return new ResponseObject(false, "删除失败");
		}
	}
	
}
