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
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.crowdfunding.mapper.ZcBankCardMapper;
import com.jkkp.modules.crowdfunding.model.ZcBankCard;
import com.jkkp.modules.crowdfunding.service.IZcBankCardService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.LoginUrlConstant;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/material/financeBankCard")
public class FinanceBankCardController extends BaseController {
	
	/**
	 * 目录前缀
	 */
	private final static String BASE_DIR = "/materials/finance/";
	
	@Autowired
	private IRegionService regionService;
	@Autowired
	private IZcBankCardService zcBankCardService;
	@Autowired
	private ZcBankCardMapper zcBankCardMapper;
	
	
	
	/**
	 *  商家绑定银行卡列表
	 * @param request
	 * @return 
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/spBindBankList")
	public String spBindBankList(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		Pagination.setPageParams(request, zcBankCardMapper,"findPage", "countPage");
		request.setAttribute("pagination", zcBankCardService.paginationCustom(params));
		return BASE_DIR + "ad_bind_card";
	}
	

	/**
	 * 商家绑定银行卡列表上下分页
	 */
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Pagination.setPageParams(request, zcBankCardMapper,"findPage", "countPage");
		return new ResponsePagination(zcBankCardService.paginationCustom(params));
	}
	
	
	@RequestMapping("/edit")	
	public String cardAdd(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("parentRegions", regionService.getParentRegions());
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		
		if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
		
		List<ZcBankCard> cardList = zcBankCardService.selectByProperty("spId", su.getSpId());
		if(cardList.size()>0){
			request.setAttribute("card", cardList.get(0));
			Region region= regionService.findById(cardList.get(0).getRegionId());
			if(region!=null){
				request.setAttribute("regionName", region.getRegionname());
				Region parentRegion = regionService.findById(region.getParentid());
				request.setAttribute("pRegionName", parentRegion.getRegionname());
			}
			return BASE_DIR+"bankCard_info";
		}
		return BASE_DIR + "bankCard_add";
	}
	
	@RequestMapping("/add")	
	public String bankCardAdd(HttpServletRequest request){
		request.setAttribute("parentRegions", regionService.getParentRegions());
		return BASE_DIR + "bankCard_add";
	}
	
	@ResponseBody
	@RequestMapping("/saveOne.do")
	public Object saveOneCard(String bankName,String cardNo,Integer regionId,String payPwd,HttpServletRequest request,HttpServletResponse response){
		try {
			if(bankName.isEmpty()){
				return new ResponseObject(false, "请输入银行卡发卡行名称");
			}
			if(cardNo.isEmpty()){
				return new ResponseObject(false, "请输入银行卡号");
			}
			if(regionId==null){
				return new ResponseObject(false, "请选择银行卡所在地");
			}
			VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
			
			if(su == null) return LoginUrlConstant.CROWD_LOGIN_URL; 
			
			ZcBankCard bean=new ZcBankCard(cardNo, bankName, regionId, payPwd,su.getSpId());
			//把之前的记录先删除
			zcBankCardService.deleteSupplierBankCard(su.getSpId());
			//绑定
			zcBankCardService.saveOne(bean);
			return new ResponseObject(true, "设置成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("web保存建材银行卡绑定信息出错");
			return new ResponseObject(false, "保存出错");
		}
	}
}
