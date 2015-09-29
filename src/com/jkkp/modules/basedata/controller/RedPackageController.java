package com.jkkp.modules.basedata.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.interceptor.AvoidDuplicateSubmission;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.mapper.RedPackageMapper;
import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.modules.basedata.model.RedPackageCategory;
import com.jkkp.modules.basedata.service.IRedPackageCategoryService;
import com.jkkp.modules.basedata.service.IRedPackageService;
import com.jkkp.modules.basedata.view.VRedPackage;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.pc.common.constants.AjaxHelper;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;
import com.service.MemberRedPackageService;

@RequestMapping("/admin/redPackage")
@Controller
public class RedPackageController extends BaseController {
	
	private final static String PREFIX = "redirect:/admin/redPackage/";
	private final static String REDIRECT_REDMAGE_URL  = PREFIX + "index.xhtml";
	private final static String REDIRECT_SENDRED_URL = PREFIX + "sendRedPackageList.xhtml";
	
	@Autowired
	private IRedPackageService redPackageService;
	@Autowired
	private RedPackageMapper redPackageMapper;
	@Autowired
	private IItemCategoryService itemCategoryService;
	@Autowired
	private IRedPackageCategoryService redPackageCategoryService;
	@Autowired
	private IActivityThemeService activityThemeService;
	@Autowired
	private MemberRedPackageService memberRedPackageService;
	@Autowired
	private IMemberService memberService;
	
	
	@RequestMapping("/send")
	public String sendRedPackage(HttpServletRequest request,String op,Integer rmid,String paramStr,Integer rid){
		List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
		RedPackage pack = null;
		if(op != null){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(rid != null){
				pack = redPackageService.findById(rid);
				if(pack != null){
					paramMap.put("redPackageId", pack.getId());
					paramMap.put("price", pack.getPrice());
					paramMap.put("limitNum", pack.getLimitNum());
					Member member = memberService.findById(rmid);
					paramMap.put("phone", member==null? "" : member.getMobile());
				}
			}
			//单个给用户发送红包
			if(op.equals("one")){
				paramMap.put("memberId", rmid);
				paramsList.add(paramMap);
			}//批量给用户发送红包
			else if(op.equals("many")){
				List<Integer> mids = CheckedUtil.splitStringToArray(paramStr, ",");
				if(pack != null){
					for (Integer memberId : mids) {
						if(memberId != null){
							Map<String, Object> paramMap2 = new HashMap<String, Object>();
							paramMap2.put("redPackageId", pack.getId());
							paramMap2.put("price", pack.getPrice());
							paramMap2.put("limitNum", pack.getLimitNum());
							paramMap2.put("memberId", memberId);
							Member member = memberService.findById(memberId);
							paramMap2.put("phone", member==null? "" : member.getMobile());
							paramsList.add(paramMap2);
						}
					}
				}
			}
			String msg = memberRedPackageService.sendRedPackge(paramsList);
			System.out.println(msg);
		}
		String params = RequestParamUtils.joinRedirectParams(request, new String[]{"pid","mid"});
		return REDIRECT_SENDRED_URL + params;
	}
	
	
	
	/**
	 * 根据红包id获取红包金额
	 * @param rid
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/getRedPackageMoneyById")
	public Object getRedPackageMoneyById(HttpServletResponse response, Integer rid) throws IOException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code",AjaxHelper.FAIL_CODE);
		if(rid != null){
			RedPackage pack =  redPackageService.findById(rid);
			map.put("code",AjaxHelper.SUCCESS_CODE);
			map.put("data",pack);
			return pack;
		}
		return null;
	}
	
	
	
	
	/**
	 * 后台发红包列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true,async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/sendRedPackageList")
	public String sendRedPackageList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, redPackageMapper,"sendRedPackageList","sendRedPackageListCount");
		request.setAttribute("pagination",redPackageService.paginationCustom(map));
		request.setAttribute("themeList",activityThemeService.select(null));
		request.setAttribute("packList",redPackageService.select(null));
		return "/basedata/sendpackage_list";
	}
	
	/**
	 * 后台发红包列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendRedPackageListPage.do")
	public Object sendRedPackageListPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, redPackageMapper,"sendRedPackageList","sendRedPackageListCount");
		return new ResponsePagination(redPackageService.paginationCustom(map));
	}
	
	
	/**保存红包对象
	 * @param request
	 * @return
	 */
	@AvoidDuplicateSubmission(removeToken = true)
	@RequestMapping("/save")
	public String save(HttpServletRequest request,RedPackage pack,int[] ids){
		if(pack != null){
			pack.setUdpateTime(new Date(System.currentTimeMillis()));
			if(pack.getId() != null){
				redPackageService.updateByPrimaryKeySelective(pack);
			}else{
				pack.setAdminName("admin");
				redPackageService.save(pack);
			}
			redPackageCategoryService.save(ids,pack);
		}
		String paramStr = RequestParamUtils.joinRedirectParams(request, new String[]{"mid","pid"});
		return REDIRECT_REDMAGE_URL + paramStr;
	}
	
	/**
	 * 跳转到后台红包编辑页面
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AvoidDuplicateSubmission(saveToken = true)
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,Integer id){
		ItemCategory ic = new ItemCategory();
		ic.setParentId(0);
		List<ItemCategory> cateList = itemCategoryService.select(ic);
		request.setAttribute("cate", cateList);
		if(id != null){
			VRedPackage pack = redPackageService.findPackageById(id);
			List<RedPackageCategory> list = redPackageCategoryService.selectByProperty("rpId",pack.getId());
			if(!list.isEmpty()){
				//保存红包适用分类，方便页面回显
				List<Integer> ids = new ArrayList<Integer>();
				for (RedPackageCategory rpc : list) {
					ids.add(rpc.getIcId());
				}
				request.setAttribute("ids", ids);
			}
			request.setAttribute("cate", itemCategoryService.select(ic));
			request.setAttribute("pack", pack);
		}
		return "/basedata/redpackage_edit";
	}
	
	/**
	 * 后台红包管理列表
	 * @param request
	 * @return
	 */
	@AccessMenu
	@AccessPagination(custom = true,async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, redPackageMapper,"findPage","countPage");
		request.setAttribute("pagination",redPackageService.paginationCustom(map));
		return "/basedata/redpackage_list";
	}
	
	/**
	 * 后台红包管理列表分页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination.setPageParams(request, redPackageMapper,"findPage","countPage");
		return new ResponsePagination(redPackageService.paginationCustom(map));
	}
	
}
