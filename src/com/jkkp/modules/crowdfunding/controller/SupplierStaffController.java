package com.jkkp.modules.crowdfunding.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.service.IBankService;
import com.jkkp.modules.basedata.service.ISupplierBranchService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;
import com.jkkp.utils.ExcelPoiUtils;

/**
 * 商家员工管理
 * @author ManNi
 *
 */
@Controller
@RequestMapping("/material/supplierStaff")
public class SupplierStaffController extends BaseController {
 
	@Autowired
	private IBankService bankService;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	@Autowired
	private ISupplierBranchService supplierBranchService;
	@Autowired
	private ISupplierService supplierService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest req){
		VSupplierUser su = (VSupplierUser) req.getSession().getAttribute("su");
		
		Integer status=CommonUtil.stringToInteger(req.getParameter("status"));
		//商家员工-主管
		List<SupplierCompanyStaff> supplierStaff = supplierCompanyStaffService.getAllSupplierStaff(su.getSpId());
		req.setAttribute("supplierZG", supplierStaff);
		
		//银行列表
		req.setAttribute("bankList", bankService.getBankList());
		
		//商户门店
		req.setAttribute("supplierBranch", supplierBranchService.getSupplierSupplierBranch(su.getSpId()));
		
		//员工列表
		req.setAttribute("supplierStaff", supplierCompanyStaffService.selectJCSupplierStaff(su.getSpId(),status));
		req.setAttribute("status", status);
		return "/materials/account/supplierStaff";
	}
	
	@ResponseBody
	@RequestMapping("/saveOrUpdate.do")
	public Object saveOrUpdate(HttpServletRequest req){
		try {
			VSupplierUser su = (VSupplierUser) req.getSession().getAttribute("su");
			
			Supplier supplier = supplierService.findById(su.getSpId());
			
			Integer id=CommonUtil.stringToInteger(req.getParameter("id"));
			String name=req.getParameter("name");
			String mobile=req.getParameter("mobile");
			String bankAccount=req.getParameter("bankAccount");
			Integer bankId=CommonUtil.stringToInteger(req.getParameter("bankId"));
			String accountName=req.getParameter("accountName");
			Integer leaderId=CommonUtil.stringToInteger(req.getParameter("leaderId"));
			Integer branchId=CommonUtil.stringToInteger(req.getParameter("branchId"));
			SupplierCompanyStaff bean=new SupplierCompanyStaff(id, name, mobile, branchId, leaderId, bankAccount, accountName, bankId,su.getSpId());
			//默认修改后需审核
			bean.setStatus(0);
			
			//默认设置员工提成比例为商家的提成比例
			bean.setGainRate(supplier.getGainRate());
			supplierCompanyStaffService.saveOrUpdate(bean);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(false, "保存出错");
		}
	}
	
	@RequestMapping(value="staffExcelUtil.do")
	public void staffExcelUtil(HttpServletRequest req,HttpServletResponse res){
		try {
			VSupplierUser su = (VSupplierUser) req.getSession().getAttribute("su");
			
			List<VSupplierCompanyStaff> dataList = supplierCompanyStaffService.selectJCSupplierStaff(su.getSpId(),null);
			
			List<String[]> contentList = new ArrayList<String[]>();
			String[] title = new String[]{"手机号码","姓名","所属门店","所属主管","总提成","审核状态"};			
			contentList.add(title);
			 for (int i = 0; i < dataList.size(); i++) {
		        	VSupplierCompanyStaff bean = dataList.get(i);
		        	String[] item = new String[]{bean.getMobile()==null?"":bean.getMobile().toString(),bean.getName()==null?"":bean.getName().toString(),bean.getBranchName()==null?"":bean.getBranchName().toString(),bean.getLeaderName()==null?"":bean.getLeaderName().toString(),bean.getTotalSttleMoney()==null?"":bean.getTotalSttleMoney().toString(),bean.getStatusVal()==null?"":bean.getStatusVal().toString()};
		        	contentList.add(item);
		        }
			String fileName="员工信息"+DateUtil.format(new Date(), "yyyy年MM月dd日HH时mm分ss秒")+".xls";
			res.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream out = res.getOutputStream();
			res.setContentType("application/vnd.ms-excel");
			ExcelPoiUtils.writeToExcelOutputStream(out, fileName, contentList, ExcelPoiUtils.Type.XLS);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
