package com.jkkp.modules.supplier.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.supplier.model.SupplierCondition;
import com.jkkp.modules.supplier.model.SupplierConditionCity;
import com.jkkp.modules.supplier.service.ISupplierConditionCityService;
import com.jkkp.modules.supplier.view.VSimpleConditionCity;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.service.IRegionService;

@RequestMapping("/condition")
@Controller
public class SupplierConditionController extends BaseController {

	@Autowired
	private ISupplierConditionService supplierConditionService;
	@Autowired
	private IRegionService regionService;
	@Autowired
	private ISupplierConditionCityService supplierConditionCityService;
	
	
	@RequestMapping("/setting")
	public String setting(HttpServletRequest request){
		VSupplierUser su = (VSupplierUser ) request.getSession().getAttribute("su");
		SupplierCondition sc = new SupplierCondition();
		sc.setSpId(su.getSpId());
		List<SupplierCondition> conditions = supplierConditionService.select(sc);
		if(conditions.size() > 0){
			SupplierCondition sc1 = conditions.get(0);
			request.setAttribute("cond",sc1);
			List<VSimpleConditionCity> scces = supplierConditionCityService.getCityBySpId(su.getSpId());
			Integer pRegionId = 0;
			Integer cRegionId = 0;
			for (VSimpleConditionCity vsc : scces) {
				if(vsc.getParentId().equals(0)){
					pRegionId = vsc.getRegionId();
					break;
				}
			}
			for (VSimpleConditionCity vsc : scces) {
				if(vsc.getParentId().equals(pRegionId)){
					cRegionId = vsc.getRegionId();
					break;
				}
			}
			if(pRegionId != 0){
				Region re = new Region();
				re.setParentid(pRegionId);
				List<Region> clist=regionService.select(re);
				request.setAttribute("clist", clist);
			}
			request.setAttribute("scces", scces);
			request.setAttribute("cRegionId", cRegionId);
			request.setAttribute("pRegionId", pRegionId);
		}else{
			request.setAttribute("cond",new SupplierCondition());
		}
		Region re = new Region();
		re.setParentid(0);
		//只显示开通的区域
		re.setStatus(1);
		List<Region> regionList = regionService.select(re);
		request.setAttribute("RegionList", regionList);
		return "supplier/supplier_condition";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,SupplierCondition condition,@RequestParam(required=false) Integer []ids){
		VSupplierUser su = (VSupplierUser ) request.getSession().getAttribute("su");
		if(condition.getId() != null && condition.getId() > 0){
			SupplierCondition old = supplierConditionService.findById(condition.getId());
			old.setXzMj(condition.getXzMj());
			old.setXzYs(condition.getXzYs());
			old.setXzZxfs(condition.getXzZxfs());
			condition.setXzMj(condition.getXzYs()/condition.getXzMj());
			supplierConditionService.update(old);
		}else{
			condition.setCreateTime(new Date());
			condition.setSpId(su.getSpId());
			condition.setXzMj(condition.getXzYs()/condition.getXzMj());
			supplierConditionService.save(condition);
		}
		supplierConditionCityService.findById(1);
		SupplierConditionCity cond = new SupplierConditionCity();
		cond.setScId(condition.getId());
		cond.setSpId(su.getSpId());
		List<SupplierConditionCity> list = supplierConditionCityService.select(cond);
		for (SupplierConditionCity scc : list) {
			supplierConditionCityService.delete(scc);
		}
		
		SupplierConditionCity sccCity = null;
		if(ids != null){
			for (Integer id : ids) {
				if(id!=null)
					sccCity = new SupplierConditionCity(0, condition.getId(), id, new Date(), su.getSpId());
				supplierConditionCityService.save(sccCity);
			}
		}
		return "redirect:/condition/setting.xhtml";
	}
	
}
