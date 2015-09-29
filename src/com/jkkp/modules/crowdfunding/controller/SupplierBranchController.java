package com.jkkp.modules.crowdfunding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.model.SupplierBranch;
import com.jkkp.modules.basedata.service.ISupplierBranchService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;

/**
 * 商家分店管理
 * @author ManNi
 *
 */
@Controller
@RequestMapping(value="/material/supplierBranch")
public class SupplierBranchController extends BaseController {
    
	@Autowired
	private ISupplierBranchService supplierBranchService;
	
	@RequestMapping(value="index")
	public String index(HttpServletRequest req){
		VSupplierUser su = (VSupplierUser) req.getSession().getAttribute("su");	
		
		req.setAttribute("supplierBranch", supplierBranchService.getSupplierSupplierBranch(su.getSpId()));
		return "/materials/account/supplierBranch";
	}
	
	@RequestMapping(value="edit")
	public String edit(){
		return "/materials/account/supplierBranch_edit";
	}
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate.do")
	public Object saveOrUpdate(HttpServletRequest req){
		try{
		VSupplierUser su = (VSupplierUser) req.getSession().getAttribute("su");	
			
		Integer id=CommonUtil.stringToInteger(req.getParameter("id"));
		Double latitude=CommonUtil.stringToDoubles(req.getParameter("latitude"));
		Double longitude=CommonUtil.stringToDoubles(req.getParameter("longitude"));
		String name=req.getParameter("name");
		String tel=req.getParameter("tel");
		String address=req.getParameter("address");
		SupplierBranch bean=new SupplierBranch(id, su.getSpId(), name, tel, address, latitude, longitude);
		supplierBranchService.saveOrUpdate(bean);
		return new ResponseObject(true, "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseObject(false, "添加分店出错了");
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteOne.do")
	public Object deleteOne(Integer id){
		try {
			supplierBranchService.deleteOne(id);
			return new ResponseObject(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(false, "删除出错");
		}
	}
}
