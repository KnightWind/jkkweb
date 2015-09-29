package com.jkkp.modules.crowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.sale_theme.mapper.ActivityCardDealsMapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.view.Statistics;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.utils.Pagination;


@Controller
@RequestMapping("/material/sales")
public class SalesController extends BaseController {

	@Autowired
	private IActivityCardService activityCardService;
	@Autowired
	private ActivityCardDealsMapper activityCardDealsMapper;
	@Autowired
	private IActivityCardDealsService activityCardDealsService;
	@Autowired
	private IActivityJionSignService activityJionSignService;
	
	
	
	
	/**
	 * 建材手动添加报名
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/add.do")
	public Object add(HttpServletRequest request,HttpServletResponse response,ActivityJionSign ajs,Integer cardId,String desc){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			ActivityJionSign ajsJionSign = new ActivityJionSign();
			ajsJionSign.setPhone(ajs.getPhone());
			List<ActivityJionSign> list = activityJionSignService.select(ajsJionSign);
			if(!list.isEmpty()){
				resultMap.put("code", 2);
				return resultMap;
			}
			activityJionSignService.saveData(ajs,cardId,desc,su.getSpId());
			resultMap.put("code", 0);
		} catch (Exception e) {
			resultMap.put("code", 1);
			throw new RuntimeException(e);
		}
		return resultMap;
	}
	
	/**
	 * 业主信息管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/saleIndex")
	public String saleIndex(HttpServletRequest request,HttpServletResponse response){
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spId", su.getSpId());
		}
		List<ActivityCard> list =  activityCardService.selectByProperty("supplierId", su.getSpId());
		List<Statistics> statList = activityCardDealsService.caclSaleInfo(params);
		long myPreced = activityCardDealsService.getPrecedBySpId(su.getSpId());
		Pagination.setPageParams(request, activityCardDealsMapper,"findPage", "countPage");
		request.setAttribute("pagination", activityCardDealsService.paginationCustom(params));
		request.setAttribute("list",list);
		request.setAttribute("statList",statList);
		if(myPreced != 0){
			request.setAttribute("myPreced", myPreced);
		}else{
			request.setAttribute("myPreced", "暂无");
		}
		return "/materials/sales/sale_index";
	}
	
	/**
	 * 业主信息管理 列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pagination.do")
	public Object pagination(HttpServletRequest request,HttpServletResponse response) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(su != null){
			params.put("spId", su.getSpId());
		}
		Pagination.setPageParams(request, activityCardDealsMapper,"findPage", "countPage");
		return new ResponsePagination(activityCardDealsService.paginationCustom(params));
	}
	
	
}
