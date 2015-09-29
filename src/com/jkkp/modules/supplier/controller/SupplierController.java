package com.jkkp.modules.supplier.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.constants.UserLoginType;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.CertifcateType;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompany;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.model.SupplierLevel;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.ICertifcateTypeService;
import com.jkkp.modules.supplier.service.ISupplierCertifcateRelationService;
import com.jkkp.modules.supplier.service.ISupplierLevelService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.service.impl.SupplierCompanyServiceImpl;
import com.jkkp.modules.supplier.service.impl.SupplierCompanyStaffServiceImpl;
import com.jkkp.modules.supplier.service.impl.SupplierUserServiceImpl;
import com.jkkp.modules.supplier.view.SupplierCompanyStaffInfo;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestParamUtils;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private ISupplierLevelService supplierLevelService;
	@Autowired
	private SupplierUserServiceImpl supplierUserServiceImpl;
	@Autowired
	private SupplierCompanyServiceImpl service;
	@Autowired
	private SupplierCompanyStaffServiceImpl serviceImpl;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private SupplierMapper dao;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private ICertifcateTypeService certifcateTypeService;
	@Autowired
	private ISupplierCertifcateRelationService supplierCertifcateRelationService;
	@Autowired
	private ISupplierConditionService supplierConditionService;
	@Autowired
	private IRegionService regionService;
	@Autowired
	private AttachmentServiceImpl attachmentServiceImpl;

	
	
	
	/**
	 * 根据装修公司列表维护员工信息
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/supplierByType")
	public String findSupplierByType(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("typeId", 1);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierMapper, "findSupplierByType",
				"findSupplierByTypeCount");
		request.setAttribute("pagination",
				supplierService.paginationCustom(params));
		request.setAttribute("mid", request.getParameter("mid"));
		request.setAttribute("pid", request.getParameter("pid"));
		return "/supplier/supplier_staff_Manager";
	}

	@ResponseBody
	@RequestMapping("/supplierByTypePagination.do")
	public Object supplierByTypePagination(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("typeId", 1);
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, supplierMapper, "findSupplierByType",
				"findSupplierByTypeCount");
		return new ResponsePagination(supplierService.paginationCustom(params));
	}

	/**
	 * 查看审核内容
	 * 
	 * @return
	 */
	@AccessMenu
	@RequestMapping("/examineView")
	public String examineView(HttpServletRequest request,
			@RequestParam Integer id) {

		Supplier supplier = supplierService.findById(id);

		// 获取商家首页详情
		SupplierCompany supplierCompany = service.fin(id);
		// 获取商家员工列表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<SupplierCompanyStaffInfo> staffList = serviceImpl
				.findSaffListBySpId(params);
		Attachment att = new Attachment();
		att.setMainid(id);
		// 获取商家证书
		VSupplierUser su = new VSupplierUser();
		su.setSpId(id);
		this.getCertifcateList(request, su.getSpId());
		// 获取商家logo
		List<VAttachment> logoList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		request.setAttribute("logo", logoList.size() > 0 ? logoList.get(0).getDownloadPath() : "");

		//身份证
		List<VAttachment> fanList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_IDCARD_FRONT);
		List<VAttachment> zhengList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_IDCARD_NEGATIVE);
		//营业执照
		List<VAttachment> yingyeList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_BUSINESS_LICENCE);
		request.setAttribute("fanList", fanList);
		request.setAttribute("zhengList", zhengList);
		request.setAttribute("yingyeList", yingyeList);
		
		request.setAttribute("supplierCompany", supplierCompany);
		request.setAttribute("sp", supplier);
		request.setAttribute("mid", request.getParameter("mid"));
		request.setAttribute("pid", request.getParameter("pid"));
		request.setAttribute("staffList", staffList);

		return "/supplier/examineView";
	}

	/**
	 * 审核商家
	 * 
	 * @param cause
	 *            未通过原因
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/examine.do")
	public Object examine(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "flag", required = false) boolean flag,
			@RequestParam(value = "cause", required = false) String cause) {

		Supplier supplier = supplierService.findById(id);
		try {
			if (flag) {
				supplier.setProxyStatus(1);
				supplier.setCause(null);
				supplierService.update(supplier);
				return new ResponseObject(true, supplier.getSpName()
						+ "已通过审核 !");
			} else {
				supplier.setProxyStatus(-1);
				if (StringUtils.isNotEmpty(cause)) {
					supplier.setCause(cause);
				} else {
					return new ResponseObject(false, "请输入未通过原因 !");
				}
				supplierService.update(supplier);
				return new ResponseObject(true, supplier.getSpName()
						+ "未通过审核 !");
			}

		} catch (Exception e) {
			return new ResponseObject(false, "系统出错,请联系管理员 !");
		}
	}

	//商户列表
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("pagination", supplierService.pagination());
		//request.setAttribute("areaDomain", areaDomainService.finAll());
		request.setAttribute("regionList", regionService.getParentRegions());
		return "/supplier/supplier-list";
	}

	//商户列表分页以及商户审核列表分页
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(supplierService);
	}

	
	 //----------- 商家审核
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/examineList")
	public String examineList(HttpServletRequest request) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("state", 0);
//		Pagination.setSearchParams(params);
//		Pagination.setPageParams(request, supplierMapper, "findPage","countPage");
//		request.setAttribute("pagination",supplierService.paginationCustom(params));
//		request.setAttribute("areaDomain", areaDomainService.finAll());
		
		request.setAttribute("pagination", supplierService.pagination());
		request.setAttribute("regionList", regionService.getParentRegions());
		return "/supplier/examine_list";
	}

	@ResponseBody
	@RequestMapping("/proxyOneSupplier.do")
	public Object proxyOneSupplier(HttpServletRequest req){
		try {
			Integer level=CommonUtil.stringToInteger(req.getParameter("level"));
			Integer proxyStatus=CommonUtil.stringToInteger(req.getParameter("proxyStatus"));
			Integer spId=CommonUtil.stringToInteger(req.getParameter("spId"));
			Float gainRate=CommonUtil.stringToFloat(req.getParameter("gainRate"));
			supplierService.proxyOneSupplier(level, proxyStatus, spId, gainRate);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(false, "审核出错");
		}
		
		
	}
	//----------- 商家审核----------
	@AccessMenu
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request,@RequestParam(required=false) Integer id) {
		if (id != null) {
			// 获取商家首页详情
			SupplierCompany supplierCompany = service.fin(id);
			request.setAttribute("supplierCompany", supplierCompany);
			Supplier supplier = supplierService.findById(id);
			request.setAttribute("sp", supplier);

			getCertifcateList(request, id);
			// 获取商家logo
			List<VAttachment> logoList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			request.setAttribute("logo", logoList.size() > 0 ? logoList.get(0).getDownloadPath() : "");
			if(supplier.getType() == UserLoginType.RenovationCompany_TYPE){
				// 获取商家员工列表
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", id);
				List<SupplierCompanyStaffInfo> staff = serviceImpl
						.findSaffListBySpId(params);
	
				// 获取商家员工头像
				List<VAttachment> staffList = attachmentService.findAttachment(null,
						AttachmentConstant.SUPPLIER_STAFF_TYPE);
	
				// 商家员工匹配头像
				for (VAttachment attachment : staffList) {
					for (SupplierCompanyStaffInfo supplierCompanyStaffInfo : staff) {
						if (attachment.getMainid().equals(supplierCompanyStaffInfo.getId()))
							supplierCompanyStaffInfo.setAvatar(attachment.getDownloadPath());
					}
				}
				request.setAttribute("st", staff);
				return "/supplier/supplier_detail";
			}
			return "/supplier/supervisor_detail";
		}
		return "/supplier/supplier_detail";
	}

	// 商家信息修改
	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		//根服务区域
		request.setAttribute("regionList", regionService.getParentRegions());
		
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		Supplier supplier = supplierService.supplierDetail(id);
		if(supplier!=null){
			//分成比例
			supplier.setGainRate(supplier.getGainRate()*100);
			Integer regionId = supplier.getRegionId();
			request.setAttribute("regionName",regionService.selectRegionName(regionId) );
		}
		request.setAttribute("supplier", supplier);
		//根据数据库默认三个等级做处理
		if(supplier!=null&&supplier.getLevelId()!=null&&supplier.getLevelId()>6){
			request.setAttribute("supplierLevel", supplierLevelService.findById(supplier.getLevelId()));
		}
		
		return "/supplier/supplier_edit";
	}

	// 进入添加商家页面
	@AccessMenu
	@RequestMapping("/add")
	public String addOne(HttpServletRequest request) {
		request.setAttribute("regionList", regionService.getParentRegions());
		return "/supplier/supplier_add";
	}

	//保存一商户
	@RequestMapping(value = "/save.do")
	public String save(HttpServletRequest req,Integer regionId,String spName,String contactUser,String contactMobile,String legalPerson,Float gainRate,String address,Integer type,Integer levelId,Integer levelMoney,String bindMobile) {
		try {
			req.setCharacterEncoding("utf-8");
			String abbreviation = req.getParameter("abbreviation");
			System.out.println("save legalPerson=" + legalPerson);
			
			//保存的对象
			Supplier supplier=new Supplier();
			supplier.setAbbreviation(abbreviation);
			//supplier.setCityDomain(cityDomain);
			supplier.setSpName(spName);
			supplier.setContactUser(contactUser);
			supplier.setLegalPerson(legalPerson);
			supplier.setContactMobile(contactMobile);
			supplier.setGainRate(gainRate/100);
//			supplier.setStartDate(startDate);
//			supplier.setEndDate(endDate);
			supplier.setAddress(address);
			supplier.setType(type);
			supplier.setBindMobile(bindMobile);
			supplier.setLevelId(levelId);
			supplier.setRegionId(regionId);
			if(levelId!=null&&levelId==0){
				List<SupplierLevel> levelMoneyList = supplierLevelService.selectByProperty("levelMoney", levelMoney);
				if(levelMoneyList.size()>0){
					supplier.setLevelId(levelMoneyList.get(0).getId());
				}else{
				    SupplierLevel supplierLevel = supplierLevelService.saveOne(levelMoney);
				    supplier.setLevelId(supplierLevel.getId());
				}
			}
			supplierService.saveOrUpdate(supplier);
			String params = RequestParamUtils.joinRedirectParams(req, new String[]{"mid","pid"});
			return "redirect:index.xhtml" + params;
		} catch (Exception e) {
			logger.error("新建商户出错", e);
			return "404";
		} 
			
	}

	
	//更新商户信息
	@RequestMapping(value = "/updateOne.do",method=RequestMethod.POST)
	public String updateOneSupplier(HttpServletRequest req,Integer regionId,String spName,String contactUser,String contactMobile,String legalPerson,Float gainRate,String address,Integer type,Integer levelId,Integer spId,String bindMobile,Integer levelMoney) {
		try {
			req.setCharacterEncoding("utf-8");
			String abbreviation = req.getParameter("abbreviation");
			System.out.println("updateOne abbreviation=" + abbreviation);	
			Supplier supplier=new Supplier();
			supplier.setAbbreviation(abbreviation);
			supplier.setId(spId);
			//supplier.setCityDomain(cityDomain);
			supplier.setSpName(spName);
			supplier.setContactUser(contactUser);
			supplier.setLegalPerson(legalPerson);
			supplier.setContactMobile(contactMobile);
			supplier.setGainRate(gainRate/100);
//			supplier.setStartDate(startDate);
//			supplier.setEndDate(endDate);
			supplier.setAddress(address);
			supplier.setType(type);
			supplier.setBindMobile(bindMobile);
			supplier.setLevelId(levelId);
			supplier.setRegionId(regionId);
			if(levelId!=null&&levelId==0){
				List<SupplierLevel> levelMoneyList = supplierLevelService.selectByProperty("levelMoney", levelMoney);
				if(levelMoneyList.size()>0){
					supplier.setLevelId(levelMoneyList.get(0).getId());
				}else{
				    SupplierLevel supplierLevel = supplierLevelService.saveOne(levelMoney);
				    supplier.setLevelId(supplierLevel.getId());
				}
			}
			supplierService.saveOrUpdate(supplier);
			String params = RequestParamUtils.joinRedirectParams(req, new String[]{"mid","pid"});
			return "redirect:index.xhtml" + params;
		} catch (Exception e) {
			logger.error("修改商户信息出错", e);
			return "404";
		} 
	}

	//删除商家
	@ResponseBody
	@RequestMapping(value="deleteSuInfo.do")
	public Object deleteSuInfo(Integer spId){
		try {
			supplierService.deleteSupplierInfosBySpid(spId);
			return new ResponseObject(true, "删除成功");
		} catch (Exception e) {
			logger.error("删除商家信息出错");
			return new ResponseObject(false, "删除失败");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findByArea.do")
	public Object findByArea(HttpServletRequest request,
			HttpServletResponse response) {
		Supplier supplier = new Supplier();
		supplier.setCityDomain(request.getParameter("city_domain"));
		List<Supplier> list = supplierService.select(supplier);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	/**
	 * 检查登录操作
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUser")
	public Object checkUser(HttpServletRequest request,
			@RequestParam String name, @RequestParam String password,
			@RequestParam Integer type) {
		String pwd = CommonUtil.md5(password);
		SupplierUser supplier = supplierUserServiceImpl.login(name, pwd, type);
		if (supplier == null) {
			return new ResponseObject(false, "用户名或密码错误!");
		} else {
			return new ResponseObject(true, "验证成功!");
		}
	}

	/**
	 * 登录操作
	 */
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request, @RequestParam String name,
			@RequestParam String pass, @RequestParam Integer type) {
		String pwd = null;
		try {
			pwd = CommonUtil.md5(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SupplierUser supplier = supplierUserServiceImpl.login(name, pwd,
				UserLoginType.RenovationCompany_TYPE);
		if (supplier == null) {
			request.setAttribute("loginError", "用户名或密码输入错误！");
			return "/supplier/supplierlogin";
		} else {
			// JkkpSecureUtil.setAuthPass(request);
			request.getSession().setAttribute("su", supplier);
			return "/supplier/supplierlogin_list";
		}
	}

	/**
	 * 监理登录操作
	 */
	@RequestMapping("/supervisorLogin.do")
	public String supervisorLogin(HttpServletRequest request,
			@RequestParam String name, @RequestParam String pass) {
		String pwd = CommonUtil.md5(pass);
		SupplierUser supplier = supplierUserServiceImpl.login(name, pwd,
				UserLoginType.Supervisor_TYPE);
		if (supplier == null) {
			request.setAttribute("loginError", "用户名或密码错误!");
			return "redirect:/supplier/jl_login.xhtml";
		} else {
			request.getSession().setAttribute("su", supplier);
			return "/supplier/jl_index";
		}
	}

	/**
	 * 装修公司登录操作
	 */
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request, @RequestParam String name,
			@RequestParam String pass) {
		String pwd = CommonUtil.md5(pass);
		SupplierUser supplier = supplierUserServiceImpl.login(name, pwd,
				UserLoginType.RenovationCompany_TYPE);
		if (supplier == null) {
			request.setAttribute("loginError", "用户名或密码错误!");
			return "redirect:/supplier/supplierlogin.xhtml";
		} else {
			request.getSession().setAttribute("su", supplier);
			return "/supplier/supplierlogin_list";
		}
	}

	/**
	 * 商家登录页面
	 */
	@RequestMapping(value = "/supplierlogin")
	public String supplierLogin() {
		return "/supplier/supplierlogin";
	}

	/**
	 * 商家首页
	 */
	@RequestMapping(value = "/supplier_index")
	public String supplierlogin_list() {
		return "/supplier/supplierlogin_list";
	}
	
	/**
	 * 工长首页
	 */
	@RequestMapping(value = "/foreman_index")
	public String foreman_login() {
		return "/supplier/foreman_index";
	}
	
	/**
	 * 监理登录页面
	 */
	@RequestMapping(value = "/jl_login")
	public String jlLogin() {
		return "/supplier/supervisorlogin";
	}

	/**
	 * 监理首页
	 */
	@RequestMapping(value = "/jl_index")
	public String supervisor_list() {
		return "/supplier/jl_index";
	}

	/**
	 * 商家注销操作
	 */
	@RequestMapping(value = "/logout")
	public String zx_logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:supplier_index.xhtml";
	}

	/**
	 * 监理注销操作
	 */
	@RequestMapping(value = "/jl_logout")
	public String jl_logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:jl_index.xhtml";
	}
	
	/**
	 * 工长注销操作
	 */
	@RequestMapping(value = "/foreman_logout")
	public String foreman_logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:foreman_index.xhtml";
	}

	/**
	 * 获取商家证书
	 * 
	 * @param request
	 * @param su
	 */
	public void getCertifcateList(HttpServletRequest request, Integer id) {
		// 获取证书类型
		List<CertifcateType> certList = certifcateTypeService.select(null);
		// 资历证书
		//List<VAttachment> qualList = attachmentService.findAttachment(id,AttachmentConstant.SUPPLIER_QUAL_CERTIFICATE_TYPE);
		// 从业证书
		//List<VAttachment> pracList = attachmentService.findAttachment(id,AttachmentConstant.SUPPLIER_PRAC_CERTIFICATE_TYPE);
		// 家装行业从业资格类证书
		List<VAttachment> decoration   = attachmentService.findAttachment(id,AttachmentConstant.DECORATION_BUSINESS_CERTIFICATE);
		// 开户银行许可证
		List<VAttachment> bank         = attachmentService.findAttachment(id,AttachmentConstant.BANK_OPEN_LICENCE);
		// 商标注册证复印件
		List<VAttachment> trademark    = attachmentService.findAttachment(id,AttachmentConstant.TRADEMARK_REGINTER_COPIES);
		// 授权书
		List<VAttachment> author       = attachmentService.findAttachment(id,AttachmentConstant.AUTHORIZATION_CERTIFICATE);
		// 税务登记证
		List<VAttachment> taxation     = attachmentService.findAttachment(id,AttachmentConstant.TAXATION_REGINTER_CERTIFICATE);
		// 营业执照副本
		List<VAttachment> business     = attachmentService.findAttachment(id,AttachmentConstant.BUSINESS_LICENSE_COPIES);
		// 组织机构代码证
		List<VAttachment> organization = attachmentService.findAttachment(id,AttachmentConstant.ORGANIZATION_CODE_CERTIFICATE);
		// 公司实景图
		List<VAttachment> liveList     = attachmentService.findAttachment(id,AttachmentConstant.SUPPLIER_LIVE_CERTIFICATE_TYPE);

		request.setAttribute("certList", certList);
		request.setAttribute("decoration", decoration);
		request.setAttribute("bank", bank);
		request.setAttribute("trademark", trademark);
		request.setAttribute("author", author);
		request.setAttribute("taxation", taxation);
		request.setAttribute("business", business);
		request.setAttribute("organization", organization);
		request.setAttribute("liveList", liveList);
	}

	/**
	 * 监理首页详情
	 */
	@RequestMapping(value = "/supervisorDetail")
	public String supervisorDetail(HttpServletRequest request) {

		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute(
				"su");
		Supplier supplier = supplierService.findById(su.getSpId());
		request.setAttribute("sp", supplier);

		// 获取商家证书
		getCertifcateList(request, su.getSpId());

		// 获取商家logo
		List<VAttachment> logoList = attachmentService.findAttachment(
				su.getSpId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		request.setAttribute("logo", logoList.size() > 0 ? logoList.get(0)
				.getDownloadPath() : "");

		//商家等级
		Integer levelId = supplier.getLevelId();
		if(CheckedUtil.isNotEmpty(levelId)){
			SupplierLevel level = supplierLevelService.findById(levelId);
			request.setAttribute("level",level);
		}
		
		return "supplier/supervisorDetail";
	}

	/**
	 * 商家公司首页详情
	 */
	@RequestMapping(value = "/xiangqing")
	public String xiangqing(HttpServletRequest request) {

		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");

		// 获取商家首页详情
		SupplierCompany supplierCompany = service.fin(su.getSpId());
		request.setAttribute("supplierCompany", supplierCompany);
		Supplier supplier = supplierService.findById(su.getSpId());
		request.setAttribute("sp", supplier);

		getCertifcateList(request, su.getSpId());

		// 获取商家员工列表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", su.getSpId());
		List<SupplierCompanyStaffInfo> staff = serviceImpl
				.findSaffListBySpId(params);
		// 获取商家logo
		List<VAttachment> logoList = attachmentService.findAttachment(
				su.getSpId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		request.setAttribute("logo", logoList.size() > 0 ? logoList.get(0)
				.getDownloadPath() : "");        

		// 获取商家员工头像
		List<VAttachment> staffList = attachmentService.findAttachment(null,
				AttachmentConstant.SUPPLIER_STAFF_TYPE);

		// 商家员工匹配头像
		for (VAttachment attachment : staffList) {
			for (SupplierCompanyStaffInfo supplierCompanyStaffInfo : staff) {
				if (attachment.getMainid().equals(
						supplierCompanyStaffInfo.getId()))
					supplierCompanyStaffInfo.setAvatar(attachment
							.getDownloadPath());
			}
		}
		request.setAttribute("st", staff);
		
		//商家等级
		Integer levelId = supplier.getLevelId();
		if(CheckedUtil.isNotEmpty(levelId)){
			SupplierLevel level = supplierLevelService.findById(levelId);
			request.setAttribute("level",level);
		}
		return "/supplier/supplier";
	}

	/**
	 * 修改公司首页详情信息
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,SupplierCompany supplierCompany) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		supplierCompany.setSpId(su.getSpId());
		service.saveUpdate(supplierCompany);
		return "/supplier/supplierlogin_list";
	}

	/**
	 * 修改监理首页详情信息
	 */
	@RequestMapping(value = "/updateSupervisor")
	public String updateSupervisor(HttpServletRequest request, Supplier supplier) {
		supplierService.updateSupervisor(supplier);
		return "/supplier/jl_index";
	}

	/**
	 * 删除证书
	 */
	@RequestMapping("/deleteCertificate")
	public String deleteCertificate(Integer id,String returnUrl, HttpServletRequest request) {
		if (id != null && id > 0) {
			Attachment att = attachmentService.findById(id);
			attachmentService.delete(att);
			request.setAttribute("msg", "删除成功！");
		} else {
			request.setAttribute("msg", "系统异常！");
		}
		return "redirect:/supplier/" + returnUrl + ".xhtml";
	}

	/**
	 * 上传商家证书
	 */
	@RequestMapping(value = "/uploadCertificate", method = RequestMethod.POST)
	public String uploadCertificate(HttpServletRequest request, Integer cid,String returnUrl,String remark) throws IOException {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Supplier supplier = supplierService.findById(su.getSpId());
		supplier.setProxyStatus(0);
		supplierService.update(supplier, cid,remark, request);
		return "redirect:/supplier/" + returnUrl + ".xhtml";
	}

	/**
	 * 上传商家Logo图片
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
	public Object uploadLogo(Integer id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Attachment att = new Attachment();
		att.setMainid(su.getSpId());
		att.setFiletype(AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		List<Attachment> logoList = attachmentService.select(att);
		if (logoList != null && logoList.size() > 0) {
			for (Attachment attachment : logoList) {
				attachmentService.delete(attachment);
			}
		}
		Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
		if (attachment != null) {
			int mainid = su.getSpId();
			Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, mainid,AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			return new ResponseObject(true, "Logo上传成功 ！");
		}
		Supplier supplier = supplierService.findById(su.getSpId());
		supplier.setProxyStatus(0);
		supplierService.update(supplier);
		return new ResponseObject(true, "Logo上传失败 ！");
	}

	/**
	 * 添加商家员工
	 */
	@RequestMapping(value = "/saveSupplierCompanyStaff", method = RequestMethod.POST)
	public String saveSupplierCompanyStaff(SupplierCompanyStaff entity,HttpServletRequest request,MultipartFile userImg) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute("su");
		Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
		entity.setSpId(su.getSpId());
		entity.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
		serviceImpl.save(entity);
		if (attachment != null) {
			int mainid = entity.getId();
			Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, mainid,AttachmentConstant.SUPPLIER_STAFF_TYPE);
		}
		return "redirect:/supplier/xiangqing.xhtml";
	}

	/**
	 * 修改商家员工
	 */
	@RequestMapping(value = "/updateSupplierCompanyStaff", method = RequestMethod.POST)
	public String updateSupplierCompanyStaff(SupplierCompanyStaff entity,HttpServletRequest request, MultipartFile userImg,String avatar) {
		SupplierCompanyStaff oldEntity = serviceImpl.findById(entity.getId());
		Attachment att = new Attachment();
		att.setMainid(entity.getId());
		att.setFiletype(AttachmentConstant.SUPPLIER_STAFF_TYPE);
		List<Attachment> staffImageList = attachmentService.select(att);
		if (userImg.getSize() > 0) {
			if (!staffImageList.isEmpty()) {
				for (Attachment attachment : staffImageList) {
					attachmentService.deleteById(attachment.getId());
				}
			}
			Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
			if (attachment != null) {
				int mainid = entity.getId();
				Admin admin = new Admin();
				attachmentService.saveAttachment(attachment, admin, mainid,AttachmentConstant.SUPPLIER_STAFF_TYPE);
			}
		}
		if (entity.getSid() == 2) {
			entity.setSjsSuggest(null);
		}
		entity.setAvatar(null);
		entity.setCreateTime(oldEntity.getCreateTime());
		entity.setSpId(oldEntity.getSpId());
		serviceImpl.update(entity);
		return "redirect:/supplier/xiangqing.xhtml";
	}

	/**
	 * 删除员工
	 */
	@ResponseBody
	@RequestMapping(value = "/removeSupplierCompanyStaff")
	public Object removeSupplierCompanyStaff(Integer id) {
		if (id > 0) {
			serviceImpl.deleteById(id);
			Attachment att = new Attachment();
			att.setMainid(id);
			att.setFiletype(AttachmentConstant.SUPPLIER_STAFF_TYPE);
			List<Attachment> staffImageList = attachmentService.select(att);
			for (Attachment attachment : staffImageList) {
				HttpFileTools.deleteFile(attachment.getFilepath());
			}
		}
		return new ResponseObject(true, "删除成功！");
	}

	/*
	 * @RequestMapping(value = "/update") public String
	 * update(HttpServletRequest request,SupplierCompany
	 * supplierCompany,@RequestParam String[] picture,@RequestParam String[]
	 * tra,@RequestParam String[] name,@RequestParam String[] job,@RequestParam
	 * String sid,@RequestParam String hh,@RequestParam String wid) { Integer
	 * id=CommonUtil.stringToInteger(sid); Integer
	 * yid=CommonUtil.stringToInteger(wid);
	 * service.saveUpdate(supplierCompany,yid); serviceImpl.del(id);
	 * serviceImpl.saveUpdate(id, tra, name, job); sImpl.del(id);
	 * sImpl.saveOrUpdate(picture, hh, id); return "/supplier/supplier"; }
	 */
	// @ResponseBody @RequestMapping("/list_json.do")
	// public JSONObject list_json(HttpServletRequest
	// request,HttpServletResponse response,
	// @RequestParam(defaultValue="20")Integer pageSize,
	// @RequestParam(defaultValue="1")Integer pageNo) {
	// response.setContentType("text/html;charset=UTF-8");
	// JSONObject map = new JSONObject();
	// String and=supplierService.getAndByRequest(request);
	// long count=dao.getCount(and);
	// Pager pager=new Pager(and, pageSize, pageNo,count);
	// List<VSupplier> list=dao.getPageList(pager);
	// map.put("count",count);
	// map.put("pageCount",pager.pageCount);
	// map.put("pageNo", pager.pageNo);
	// map.put("pageSize",pager.pageSize);
	// map.put("list", JSONArray.fromObject(list,AllDao.jcfg));
	// return map;
	// }//这种写法，IE会对JSON提示下载

	@RequestMapping("/list4json.do")
	// 查询
	public void list4json(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(defaultValue = "20") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageNo) throws Exception {
		String and = supplierService.getAndByRequest(request);
		long count = dao.getCount(and);
		Pager pager = new Pager(and, pageSize, pageNo, count);
		List<VSupplier> list = dao.getPageList(pager);
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = JSONObject.fromObject(pager);
		json.put("data", JSONArray.fromObject(list, AllDao.jcfg));
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/edit4json.do")
	// 打开编辑取单条数据
	public void edit4json(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "0") Integer id)
			throws Exception {
		String result = "修改";
		JSONObject json = new JSONObject();
		VSupplier bean = dao.getBeanById(id);
		if (bean == null || bean.getId() == null || bean.getId() == 0) {
			bean = new VSupplier();
			result = "新建";
		}
		json.put("supplier", bean);
		request.setAttribute("supplier", bean);
		json.put("result", result);
		request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/save4json.do")
	// 新建保存或修改保存
	public void save4json(Supplier bean, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = "修改";
		JSONObject json = new JSONObject();
		if (bean == null || bean.getId() == null || bean.getId() == 0) {
			int flag = dao.insert(bean);
			result = flag > 0 ? "新建成功" : "新建失败";
		} else {
			int flag = dao.updateByPrimaryKey(bean);
			result = flag > 0 ? "修改成功" : "修改失败";
		}
		json.put("supplier", bean);
		request.setAttribute("supplier", bean);
		json.put("result", result);
		request.setAttribute("result", result);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/delete4json.do")
	// 删除
	public void save4json(Integer[] chkId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = "删除";
		int cnt = 0;
		if (chkId == null)
			result = "没有选中记录";
		else
			for (int i = 0; i < chkId.length; i++) {
				VSupplier bean = dao.getBeanById(chkId[i]);
				if (bean == null)
					continue;
				System.out.println("id【" + chkId[i] + "】" + bean);
				cnt += dao.deleteByPrimaryKey(bean);
			}
		result = cnt > 0 ? "删除成功" : "删除失败";
		response.setContentType("text/plain;charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("del_cnt", cnt);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	// @ModelAttribute //从数据库得到一条记录，防止保存时数据丢失，这样做可以不用在表单中把每个字段都写出来
	// public void getModel(@RequestParam(value="id",required=false) Integer
	// id,Map<String,Object> map){
	// if(id!=null){
	// map.put("supplier",dao.getBeanById(id));
	// }else{
	// map.put("supplier",new Supplier());
	// }
	// }

}
