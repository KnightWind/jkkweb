package com.jkkp.modules.sale_theme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.sale_theme.mapper.ActivityProductMapper;
import com.jkkp.modules.sale_theme.model.ActivityProduct;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.service.IActivityProductService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@RequestMapping("/activityProduct")
@Controller
public class ProductController extends BaseController {

	private final static String REDIRECT_URL  = "redirect:/activityProduct/index.xhtml";
	
	@Autowired
	private ActivityProductMapper activityProductMapper;
	@Autowired
	private IActivityProductService activityProductService;
	@Autowired
	private IActivityThemeService activityTheme;
	@Autowired
	private IItemService itemService;
	@Autowired
	private AttachmentServiceImpl attachmentServiceImpl;
	
	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,Integer id){
		if(id != null){
			request.setAttribute("duct", activityProductService.findProductInfoById(id));
		}
		List<ActivityTheme> ah = activityTheme.select(null);
		request.setAttribute("ah", ah);
		request.setAttribute("basePath", attachmentServiceImpl.getAccessPath());
		request.setAttribute("mid", request.getParameter("mid"));
		request.setAttribute("pid", request.getParameter("pid"));
		return "/saleActivity/product_eidt";
	}
	
	
	/**
	 * 删除商品
	 */
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request,HttpServletResponse response,Integer id){
		if(id != null){
			ActivityProduct ap = activityProductService.findById(id);
			if(ap != null){
				Item item = itemService.findById(ap.getItemId());
				activityProductService.remove(ap,item);
			}
		}
		String params = getParams(request);
		return REDIRECT_URL + params;
	}

	
	/**
	 * 保存商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request,HttpServletResponse response){
		String sid = request.getParameter("id");
		Integer id    = (sid ==null || sid.equals("")) ? null : Integer.valueOf(sid);
		Integer aid   = Integer.valueOf(request.getParameter("aid"));
		String name   = request.getParameter("name");
		Double iprice = Double.valueOf(request.getParameter("iprice"));
		Double price  = Double.valueOf(request.getParameter("price"));
		Integer order=Integer.valueOf(request.getParameter("order"));
		activityProductService.save(id,aid,name,iprice,price,request,order);
		String params = getParams(request);
		return REDIRECT_URL + params;
	}
	
	/**
	 * 商品列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		List<ActivityTheme> ah = activityTheme.select(null);
		Pagination.setPageParams(request, activityProductMapper,"productList", "productListCount");
		request.setAttribute("pagination", activityProductService.paginationCustom());
		request.setAttribute("ah", ah);
		request.setAttribute("basePath", attachmentServiceImpl.getAccessPath());
		return "/saleActivity/product_list";
	}

	
	/**
	 * 商品列表 分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, activityProductMapper,"productList", "productListCount");
		return new ResponsePagination(activityProductService.paginationCustom());
	}
	
	
	/**
	 * 获取request pid mid参数
	 * @param request
	 * @return
	 */
	private String getParams(HttpServletRequest request) {
		return RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
	}
}
