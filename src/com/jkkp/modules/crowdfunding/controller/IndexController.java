package com.jkkp.modules.crowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.UserLoginType;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.view.VPhone;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.ISupplierUserService;
import com.jkkp.utils.CommonUtil;

@RequestMapping("/material/index")
@Controller
public class IndexController extends BaseController {

	@Autowired
	private ISupplierUserService supplierUserService;
	@Autowired
	private IActivityCardDealsService activityCardDealsService;
	@Autowired
	private IActivityJionSignService activityJionSignService;
	
	
	/**
	 * 抽奖操作
	 */
	@ResponseBody
	@RequestMapping("/lottery.do")
	public Object lottery(HttpServletRequest request,HttpSession session, Integer baoming,Integer qiandao,Integer gouka,Integer xianxia){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(baoming != null && baoming.equals(1)){
			paramMap.put("baoming", baoming);
		}
		if(qiandao != null && qiandao.equals(1)){
			paramMap.put("qiandao", qiandao);
			paramMap.put("baoming", 1);
		}
		if(qiandao == 1 && baoming == 1){
			paramMap.put("qiandao", 0);
			paramMap.put("baoming", 0);
		}
		if(gouka != null && gouka.equals(1)){
			paramMap.put("gouka", gouka);
		}
		if(xianxia != null && xianxia.equals(1)){
			paramMap.put("xianxia", xianxia);
		}
		try {
			String code = "";
			List<String> luckPhone = (List<String>) session.getAttribute("luckPhone");
			String phone = activityCardDealsService.randomLottery(paramMap);
			if(phone == null){
				resultMap.put("code", String.valueOf(3));
				return resultMap;
			}
			List<VPhone> list = activityJionSignService.selectPhone(paramMap);
			if(luckPhone == null){
				luckPhone = new ArrayList<String>();
				luckPhone.add(phone);
				code = String.valueOf(0);
				session.setAttribute("luckPhone", luckPhone);
			}else{
				if(luckPhone.contains(phone)){
					code = String.valueOf(4);
				}else{
					code = String.valueOf(0);
					luckPhone.add(phone);
				}
			}
			resultMap.put("thenum", phone);
			resultMap.put("phoneList", list);
			resultMap.put("code", code);
		} catch (Exception e) {
			resultMap.put("code", String.valueOf(1));
			throw new RuntimeException("随机抽奖出错了!",e);
		}
		return resultMap;
	}
	
	
	/**
	 * 跳转到抽奖页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/lotteryView")
	public Object lottery(HttpServletRequest request){
		return "/materials/lottery/lottery_view";
	}
	
	
	/**
	 * 本地调试登陆入口
	 * @param request
	 * @param name
	 * @param pass
	 * @return
	 */
	@RequestMapping("/crowdlogin.do")
	public String index(HttpServletRequest request,String name,String pass){
		String pwd = null;
		try {
			pwd = CommonUtil.md5(pass);
			SupplierUser supplier = supplierUserService.login(name, pwd,UserLoginType.BuildingMaterialsFactory_TYPE);
			if (supplier != null) {
				request.getSession().setAttribute("su", supplier);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/materials/index";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "redirect:/materials/index.xhtml";
	}
}
