package com.jkkp.appapi.common.control.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierUserSV;
import com.jkkp.appapi.modules.mapper.SupplierNameSN;
import com.jkkp.appapi.modules.mapper.VFangan;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.appapi.modules.mapper.VISupplier;
import com.jkkp.appapi.modules.mapper.VISupplierV1;
import com.jkkp.appapi.modules.mapper.VISupplierV2;
import com.jkkp.appapi.modules.mapper.VISupplierXiang;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.interfaceauth.service.UserTockenService;
import com.jkkp.modules.supplier.mapper.JlComplainMapper;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompany;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.IStaffCollectService;
import com.jkkp.modules.supplier.service.ISupplierCompanyService;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.view.VCertifcate;
import com.jkkp.modules.supplier.view.VJlComplain;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.modules.supplier.view.VSupplierBaseInfo;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;

@Controller
public class SupplierSVController extends BaseController{
	@Autowired public ISupplierSV supplierSVImpl;
	@Autowired public ISuppMessagePushSV suppMessagePushSV;
	@Autowired public CommonJsonMap commonJsonMap;
	@Autowired public ISupplierUserSV supplierUserSV;
	@Autowired public SupplierMapper dao;
	@Autowired public ISupplierCompanyStaffService supplierCompanyStaffService;
	@Autowired public SupplierUserMapper supplierUserMapper;
	@Autowired public IStaffCollectService iStaffCollectService;
	@Autowired public JlComplainMapper jlComplainDao;
	@Autowired ISupplierCollectSV supplierCollectSV;
	@Autowired private IAttachmentService attachmentService;
	@Autowired private ISupplierCompanyService companyService;
	@Autowired private IBaseinf ibaseinf;
	@Autowired ISupplierSV suppliersv;
	@Autowired 
	public UserTockenService userTockenService;
	
	
	
	
	@ResponseBody @RequestMapping("/supplier_ch_available.do")//监理切换
	public Map<String,Object> supplier_ch_available(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","全安校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				String id=BaseTools.getValueByKey(jobj,"id");
				String available=BaseTools.getValueByKey(jobj,"available");
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				if(StringUtils.isBlank(available)){rs.put("mess","没有available参数");return rs;}
				if(!BaseTools.isNumber(id)){rs.put("mess","id不是数字");return rs;}
				if(!BaseTools.isNumber(available)){rs.put("mess","available不是数字");return rs;}
				VSupplier bean=dao.getBeanById(Integer.parseInt(id.trim()));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
//				监理排期1可预约0排期满
				
				bean.available=Integer.parseInt(available);
				int uflag=dao.updateByPrimaryKey(bean);
				String str=bean.available==1?"可预约":"排期满";
				rs.put("mess",uflag>0?"修改成功,现"+str:"修改失败");
				rs.put("ver", "1.0");rs.put("ret", "");
				BaseTools.showMessageByJSON(JSONObject.fromObject(bean,AllDao.jcfg),"");
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/supplier_list.do")//装修公司列表
	public Map<String,Object> supplier_list(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全验证不通过");return rs;}
				JSONObject json_obj=JSONObject.fromObject(json);
				String and=getAndByJSON(request,json_obj);
				Pager pager=new Pager(and,json_obj,dao.getCount(and));
				List<VSupplier> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/supplier_detail.do")//装修公司详细信息ysc
	public Map<String,Object> supplier_detail(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","全安校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id))id=BaseTools.getValueByKey(jobj,"id");
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				if(!BaseTools.isNumber(id)){rs.put("mess","id不是数字");return rs;}
				VSupplier bean=dao.getBeanById(Integer.parseInt(id.trim()));
				List<VAttachment> attList = attachmentService.findAttachment(Integer.valueOf(id), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
				if(attList != null && attList.size() > 0)
					bean.setCoverurl(attList.get(0).getDownloadPath());
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				if(bean.type!=null&&bean.type==5){//监理详情
					JSONObject data=new JSONObject();
					data.put("photoUrl", bean.photoUrl==null?"":bean.photoUrl);				//头像
					data.put("address", bean.address==null?"":bean.address);				//地址
					data.put("spName", bean.spName==null?"":bean.spName);					//姓名
					data.put("spCode", bean.spCode==null?"":bean.spCode);
					data.put("legalPerson", bean.legalPerson==null?"":bean.legalPerson);	//监理号
					data.put("businessCode", bean.businessCode==null?"":bean.businessCode);	//从业资质
					data.put("available", bean.available==null?"0":bean.available);			//监理排期1可预约0排期满
					data.put("availableVal", (bean.available+"").equals("1")?"可预约":"排期满");		//监理排期1可预约0排期满
					List<VAttachment> zgzsList=allDao.attachmentMapper.getList(" and mainid='"+bean.id+"' and filetype=20 ");//and mainid='"+bean.id+"' and filetype=20
					List<VAttachment> cyzsList=allDao.attachmentMapper.getList(" and mainid='"+bean.id+"' and filetype=21  ");//and mainid='"+bean.id+"' and filetype=21
					JSONArray zgzsJry=new JSONArray();
					JSONArray cyzsJry=new JSONArray();
					for (int i = 0; i < zgzsList.size(); i++) {
						VAttachment atta=zgzsList.get(i);
						JSONObject temp=new JSONObject();
						temp.put("photoUrl", atta.photoUrl==null?"":atta.photoUrl);
						zgzsJry.add(temp);
					}
					for (int i = 0; i < cyzsList.size(); i++) {
						VAttachment atta=cyzsList.get(i);
						JSONObject temp=new JSONObject();
						temp.put("photoUrl", atta.photoUrl==null?"":atta.photoUrl);
						cyzsJry.add(temp);
					}
					data.put("zgzsList", zgzsJry);		//资格证书
					data.put("cyzsList", cyzsJry);		//从业证书
					//rs.put("data",data);
					rs.put("data",JSONObject.fromObject(data,AllDao.jcfg));
				}else{
					//rs.put("data",bean);
					rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				}
				rs.put("ver", "1.0");rs.put("ret", "");
				BaseTools.showMessageByJSON(JSONObject.fromObject(bean,AllDao.jcfg),"");
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/supplier_tsjl.do")//投诉记录
	public Map<String,Object> supplier_tsjl(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					JSONObject jobj=JSONObject.fromObject(json);
					id=request.getParameter("id");
					if(StringUtils.isBlank(id)) id=request.getParameter("supplierid");
					if(StringUtils.isBlank(id)) id=request.getParameter("supplierId");
					if(StringUtils.isBlank(id)){
						Object id_obj=jobj.get("id");
						if(id_obj!=null) id=id_obj.toString().trim();
					}
					if(StringUtils.isBlank(id)){
						Object id_obj=jobj.get("supplierid");
						if(id_obj!=null) id=id_obj.toString().trim();
					}
					if(StringUtils.isBlank(id)){
						Object id_obj=jobj.get("supplierId");
						if(id_obj!=null) id=id_obj.toString().trim();
					}
					if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
					if(!BaseTools.isNumber(id)){rs.put("mess","id不是数字");return rs;}
					VSupplier bean=dao.getBeanById(Integer.parseInt(id.trim()));
					if(bean==null){rs.put("mess","记录不存在");return rs;}
					String fqdtsAnd=" and t.tid='"+bean.id+"' ";						//我发起的投诉条件
					String sddtsAnd=" and t.bid='"+bean.id+"' ";						//我收到的投诉条件
					String cldtsAnd=" and exists (";									//我处理的投诉条件
					cldtsAnd+=" select xx.cid from jl_complain_details xx ";
					cldtsAnd+=" where xx.cid=t.id and xx.type_id=2 ";
					cldtsAnd+=" and xx.user_id='"+bean.id+"' ";
					cldtsAnd+=" )and t.bid='"+bean.id+"' ";								
					
					List<VJlComplain> fqdtsList=jlComplainDao.getList(fqdtsAnd);		//我发起的投诉列表
					List<VJlComplain> sddtsList=jlComplainDao.getList(sddtsAnd);		//我收到的投诉列表
					List<VJlComplain> cldtsList=jlComplainDao.getList(cldtsAnd);		//我处理的投诉列表
					
					rs.put("fqdtsList",JSONArray.fromObject(fqdtsList,AllDao.jcfg));	//我发起的投诉列表
					rs.put("sddtsList",JSONArray.fromObject(sddtsList,AllDao.jcfg));	//我收到的投诉列表
					rs.put("cldtsList",JSONArray.fromObject(cldtsList,AllDao.jcfg));	//我处理的投诉列表
					rs.put("id",bean.id);
					rs.put("ver", "1.0");rs.put("ret", "");
				}
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	
	@ResponseBody @RequestMapping("/supplier_logout.do")//装修公司退出
	public Map<String,Object> supplier_logout(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");
		try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					map = commonJsonMap.setRequestMap(request);
					String cid = (String)map.get("cid");
					//Object pass=jobj.get("pass");
					if(cid==null||StringUtils.isBlank(cid.toString().trim())){
						rs.put("mess","cid不能为空");
						rs.put("doneCode", "0012");
						return rs;
					}
					suppMessagePushSV.deleteByCid((String)cid);
					//add 20150821s
					if(map.get("tocken_id")!=null){
						userTockenService.deleteTocken((String)map.get("tocken_id"));
					}else if(map.get("login_user")!=null){
						userTockenService.deleteTockenByUserName((String)(map.get("login_user")));
					}
					//
					JSONObject data = new JSONObject();
					rs.put("data", data);
					rs.put("mess", "登出成功！");
					rs.put("ver", "1.0");
					rs.put("ret", "");
				}
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			ee.printStackTrace();
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	
	
	
	
	@ResponseBody @RequestMapping("/supplier_load.do")//装修公司登录ysc
	public Map<String,Object> supplier_load(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					map = commonJsonMap.setRequestMap(request);
					JSONObject jobj=JSONObject.fromObject(json);
					Object userName=jobj.get("userName"); 
					Object pass=jobj.get("pass");
					if(userName==null)userName=jobj.get("username"); 
					if(userName==null||StringUtils.isBlank(userName.toString().trim())){
						rs.put("mess","帐号【userName】不能为空");
						rs.put("doneCode", "0012");return rs;
					}
					if(pass==null||StringUtils.isBlank(pass.toString().trim())){
						rs.put("mess","密码【pass】不能为空");
						rs.put("doneCode", "0012");return rs;
					}
					HashMap paramMap = new HashMap();
					paramMap.put("username", userName.toString());
					//paramMap.put("type", 1);//查询商家用户信息
					VSupplierUser bean=supplierUserMapper.getBeanByMap(paramMap);
					if (bean == null) {
						rs.put("doneCode", "0003");
						rs.put("mess", "用户名错误");
						return rs;
					}
	
//					if (StringUtils.isBlank(bean.getUserpwd())) {
//						rs.put("doneCode", "0004");
//						rs.put("mess", "密码错误");
//						return rs;
//					}
					//String pwd=ApiCommonUtil.EncoderByMd5(pass.toString().trim());
					if (pass.equals(bean.getUserpwd())) {
						
						//
						JSONObject data;
						data = new JSONObject();
						//add 20150821  添加tocken
						UserTocken tocken = userTockenService.createNewTocken(bean);
						data.put("tockenId", tocken.getTockenId());	
						data.put("suppliuerId", bean.getSpId());
						data.put("suppliuerName", bean.getUsername());
						
						//B端登录时，验证登录名、cid是否存在supp_message_push，如存在跳过，无则新增
						map.put("supplierUser", bean);
						suppMessagePushSV.dealPushLoad(map);
						
						rs.put("data", data);
						rs.put("mess", "登录成功！");
					} else {
						rs.put("doneCode", "0012");
						rs.put("mess", "密码错误！");
					}
					rs.put("ver", "1.0");rs.put("ret", "");
				}
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/supplier_grab_appointV1.do")//装修公司应答预约单
	public Map<String,Object> supplier_grab_appoint(HttpServletRequest request) {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					JSONObject jobj=JSONObject.fromObject(json);
					Object appointmentPushId=jobj.get("appointmentPushId");
					Object appointmentPushStatus=jobj.get("appointmentPushStatus");
					String and=" and exists( select * "+BaseTools.enter;
					and+=" from appointment_push t1 "+BaseTools.enter;
					and+=" where t.id=t1.sp_id "+BaseTools.enter;
					if(appointmentPushId!=null&&StringUtils.isNotBlank(appointmentPushId.toString()))
						and+=" and t1.aid="+appointmentPushId+" "+BaseTools.enter;
					if(appointmentPushStatus!=null&&StringUtils.isNotBlank(appointmentPushStatus.toString()))
						and+=" and t1.status="+appointmentPushStatus+" "+BaseTools.enter;
					and+=" )"+BaseTools.enter;
					List<VISupplierV1> list=dao.getSupplierNameList(and);
					rs.put("data",list);rs.put("count",list.size());
					rs.put("ver", "1.0");rs.put("ret", "");
				}
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/supplier_resetPw.do")//装修公司重置密码ysc
	public Map<String,Object> supplier_resetPw(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				System.out.println("flag【"+flag+"】");
				if(flag){
					JSONObject jobj = JSONObject.fromObject(json);
					String resetType =  (String)jobj.get("resetType");
					String userName="";
					String mobileNum="";
					SupplierUser bean =null;
					//根据用户名重置
					if("1".equals(resetType)){
						 userName = (String)jobj.get("userName");
						 if (userName == null
									|| StringUtils.isBlank(userName.toString().trim())) {
								rs.put("mess", "帐号【userName】不能为空");
								return rs;
						 }
						 bean = supplierUserMapper
									.getBeanByUsername(userName.toString());
					//根据手机号码重置	
					}else if("2".equals(resetType)){
						mobileNum = (String)jobj.get("mobile");
						if (mobileNum == null
								|| StringUtils.isBlank(mobileNum.toString().trim())) {
							rs.put("mess", "帐号【mobileNum】不能为空");
							return rs;
						}
						bean = supplierUserMapper
									.getBeanByMobile(mobileNum);
					}else{
						rs.put("mess", "重置类型不能为空");
						return rs;
					}
					// 传输加密字段 无需再次加密
					String pass = (String)jobj.get("pass");
					if (pass == null
							|| StringUtils.isBlank(pass.toString().trim())) {
						rs.put("mess", "密码【pass】不能为空");
						return rs;
					}
					
					if (bean == null) {
						rs.put("doneCode", "0015");
						rs.put("mess", "商户未注册");
						return rs;
					}
					if (StringUtils.isBlank(bean.getUserpwd())) {
						rs.put("mess", "密码不存在");
						return rs;
					}
					//String userPwd = ApiCommonUtil.EncoderByMd5(pass.toString()
					//		.trim());
					bean.setUserpwd((String)pass);
					int cnt = supplierUserMapper.updateByPrimaryKey(bean);
					if(cnt>0){
						rs.put("mess", "重置成功");
						rs.put("doneCode", "0000");
					}else{
						rs.put("mess", "重置失败");
						rs.put("doneCode", "0020");
					}
					//rs.put("data", JSONObject.fromObject(bean, AllDao.jcfg));
					rs.put("ver", "1.0");
					rs.put("ret", "");
				}
			}
			//System.out.println(JSONObject.fromObject(rs,AllDao.jcfg));
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	
	
	
	@ResponseBody 
	@RequestMapping("/supplier_modifyPw.do")//修改密码
	public Map<String,Object> supplier_modifyPw(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				System.out.println("flag【"+flag+"】");
				if(flag){
					JSONObject jobj = JSONObject.fromObject(json);
					String resetType =  (String)jobj.get("resetType");
					String userName = (String)jobj.get("userName");
					String mobileNum = (String)jobj.get("mobile");
					String oldPwd = (String)jobj.get("oldPwd");
					// 传输加密字段 无需再次加密
					String newPwd = (String)jobj.get("newPwd");
					SupplierUser bean =null;
					//根据用户名重置
					if("1".equals(resetType)){
						 
						 if (userName == null
									|| StringUtils.isBlank(userName.toString().trim())) {
								rs.put("mess", "帐号【userName】不能为空");
								return rs;
						 }
						 bean = supplierUserMapper
									.getBeanByUsername(userName.toString());
					//根据手机号码重置	
					}else if("2".equals(resetType)){
						
						if (mobileNum == null
								|| StringUtils.isBlank(mobileNum.toString().trim())) {
							rs.put("mess", "帐号【mobile】不能为空");
							return rs;
						}
						bean = supplierUserMapper
									.getBeanByMobile(mobileNum);
					}else{
						rs.put("mess", "type不能为空");
						return rs;
					}
					if (oldPwd == null
							|| StringUtils.isBlank(oldPwd.toString().trim())) {
						rs.put("mess", "旧密码不能为空");
						return rs;
					}
					if (newPwd == null
							|| StringUtils.isBlank(newPwd.toString().trim())) {
						rs.put("mess", "新密码不能为空");
						return rs;
					}
					
					if (bean == null) {
						rs.put("doneCode", "0015");
						rs.put("mess", "商户未注册");
						return rs;
					}
					if (StringUtils.isBlank(bean.getUserpwd())) {
						rs.put("mess", "密码不存在");
						return rs;
					}
					
					if(!oldPwd.equals(bean.getUserpwd())){
						rs.put("doneCode", "0016");
						rs.put("mess", "旧密码不正确");
						return rs;
					}
					//String userPwd = ApiCommonUtil.EncoderByMd5(pass.toString()
					//		.trim());
					bean.setUserpwd((String)newPwd);
					int cnt = supplierUserMapper.updateByPrimaryKey(bean);
					if(cnt>0){
						rs.put("mess", "重置成功");
						rs.put("doneCode", "0000");
					}else{
						rs.put("mess", "重置失败");
						rs.put("doneCode", "0020");
					}
					//rs.put("data", JSONObject.fromObject(bean, AllDao.jcfg));
					rs.put("ver", "1.0");
					rs.put("ret", "");
				}
			}
			System.out.println(JSONObject.fromObject(rs,AllDao.jcfg));
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	
	
	
	
	
	@ResponseBody @RequestMapping("/supplier_appoint_list.do")//3.8 装修公司预约量房单
	public Map<String, Object> supplierAppDetail(HttpServletRequest request) {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false) return rs;
				map = commonJsonMap.setRequestMap(request);
				map.put("appointmentPushStatus","0");
				currentPage=(String) map.get("curpage");
				pagination.put("currentPage", currentPage);
				map= PaginationInterceptor.pagination(map);
				List<VISupplier> list = supplierSVImpl.findAppDetaBySpId(map);
				//查询下一个页面数否有数据，如无则返回hastnest为false
				map= PaginationInterceptor.nextPagination(map);
				if(supplierSVImpl.findAppDetaBySpId(map).size()>0){
					pagination.put("hasnext", true);
				}else{
					pagination.put("hasnext", false);
				}
				mapBusi.put("pagination", pagination);
				rs.put("pagination", pagination);
				rs.put("data",JSONArray.fromObject(list));
				rs.put("count",list.size());
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	
	@ResponseBody @RequestMapping("/supplier_appoint_his_list.do")//3.8 装修公司预约量房单
	public Map<String, Object> supplierAppHisDetail(HttpServletRequest request) {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false) return rs;
				map = commonJsonMap.setRequestMap(request);							
				currentPage=(String) map.get("curpage");
	  			pagination.put("currentPage", currentPage);
				map= PaginationInterceptor.pagination(map);
				List<VISupplier> list = supplierSVImpl.findlishi(map);
				//查询下一个页面数否有数据，如无则返回hastnest为false		
				map= PaginationInterceptor.nextPagination(map);
				if(supplierSVImpl.findlishi(map).size()>0){
					pagination.put("hasnext", true);
				}else{
					pagination.put("hasnext", false);
				}
				mapBusi.put("pagination", pagination);				
				rs.put("data",JSONArray.fromObject(list));
				rs.put("count",list.size());
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/supplier_grab_record.do")//3.9装修公司抢单记录
	public Object supplier_grab_record(HttpServletRequest request) {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String,Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false) return rs;
				map = commonJsonMap.setRequestMap(request);
				List<VISupplierV1> list = supplierSVImpl.supplierNameById(map);
				rs.put("data",JSONArray.fromObject(list));
				rs.put("count",list.size());
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ModelAttribute	//从数据库得到一条记录，防止保存时数据丢失，这样做可以不用在表单中把每个字段都写出来
	public void getModel(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id!=null){
			map.put("supplier",dao.getBeanById(id));
		}else{
			map.put("supplier",new Supplier());
		}
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="";
		and+=BaseTools.getAndByJson(json,"spName","sp_name", "1");
		and+=BaseTools.getAndByJson(json,"supplierAddress","address", "1");
		and+=BaseTools.getAndByJson(json,"id","id", "1","1");
		and+=BaseTools.getAndByJson(json,"status","status", "1","1");
		and+=BaseTools.getAndByJson(json,"type","type", "1","1");
		and+=BaseTools.getAndByJson(json,"username","username", "1");
		Object order=json.get("order");
		if("1".equals(order)){
			and+=" order by contact_mobile";//排序方式   1-预算高优先   2-预算低优先【找不到预算字段，随便找了手机做排序】
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}
	
	@ResponseBody @RequestMapping("/jl_datail.do") //C端监理详情页
	public Object jl_datail(HttpServletRequest request) throws Exception {
		List<SupplierNameSN> list = new ArrayList<SupplierNameSN>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			Integer spid=CommonUtil.stringToInteger((String) map.get("spid"));
//			currentPage=(String) map.get("curpage");
//			
//			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=supplierSVImpl.finXiangQing(uid, spid);
//			map= PaginationInterceptor.nextPagination(map);
//			if(.size()>0){
//				pagination.put("hasnext", true);
//			}else{
//				pagination.put("hasnext", false);
//			}
//			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}		
	}
	@ResponseBody @RequestMapping("/supplier_datail_xiang.do")//C端装修公司的详情页面
	public Object supplier_datail_xiang(HttpServletRequest request) throws Exception {
		List<VISupplierXiang> list = new ArrayList<VISupplierXiang>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer spid=CommonUtil.stringToInteger((String) map.get("spid"));
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
//			currentPage=(String) map.get("curpage");
//			
//			pagination.put("currentPage", currentPage);
//			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=supplierSVImpl.querySupplier(spid,uid);
//			map= PaginationInterceptor.nextPagination(map);
//			if(.size()>0){
//				pagination.put("hasnext", true);
//			}else{
//				pagination.put("hasnext", false);
//			}
//			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}		
	}
	
	//查询装修公司信息   
	@ResponseBody 
	@RequestMapping("/querySupplierById.do")
	public Object querySupplierById(HttpServletRequest request) throws Exception {
		List<VISupplierXiang> list = new ArrayList<VISupplierXiang>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		VISupplierXiang spInfo=null;
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer spid=CommonUtil.stringToInteger((String) map.get("spid"));
//			currentPage=(String) map.get("curpage");
//			
//			pagination.put("currentPage", currentPage);
//			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			spInfo=supplierSVImpl.querySupplierBySpId(spid);
//			map= PaginationInterceptor.nextPagination(map);
//			if(.size()>0){
//				pagination.put("hasnext", true);
//			}else{
//				pagination.put("hasnext", false);
//			}
//			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(spInfo, mapBusi);
		}		
	}
	
	
	
	
	@ResponseBody @RequestMapping("/sjs_datail_shoucan.do")
	public Object sjs_datail_shoucan(HttpServletRequest request) throws Exception {
		List<VIStaff> list = new ArrayList<VIStaff>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			currentPage=(String) map.get("curpage");
  		    pagination.put("currentPage", currentPage);
  		    pagination.put("uid", uid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=supplierSVImpl.querShouCan(map);
			map= PaginationInterceptor.nextPagination(map);
			if(list.size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}		
	}
	@ResponseBody @RequestMapping("/sjs_datail_delete.do")
	public Object sjs_datail_delete(HttpServletRequest request) throws Exception {
		List<VIStaff> list = new ArrayList<VIStaff>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String mm=(String) map.get("sid");
			String[] id=mm.split(",");
			for (int i = 0; i < id.length; i++) {
				iStaffCollectService.deleteById(Integer.valueOf(id[i]));
			}		
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}		
	}
	
	@SuppressWarnings("finally")
	@ResponseBody @RequestMapping("/supplierlistall.do")
	public Object supplierlistall(HttpServletRequest request) throws Exception {
		List<VISupplierV2> sclist=null;
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		List<VSupplierBnjn> sclist1=null;
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
     		currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		sclist = supplierSVImpl.SupplierList(map);
     		for(VISupplierV2 onesc:sclist){//设置商家头像
     			List<String> url=attachmentService.findForDownload(onesc.getId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
     			if(!url.isEmpty())
     				onesc.setHeadimg(url.get(0));
     		}
			map= PaginationInterceptor.nextPagination(map);
			if(supplierSVImpl.SupplierList(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);	
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(sclist, mapBusi);
		}		
	}
	
	
	@SuppressWarnings("finally")
	@ResponseBody @RequestMapping("/supplierbaseinfo.do")
	public Object supplierbaseinfo(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		VSupplierBaseInfo baseInfo = null;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id = Integer.valueOf(map.get("supplierid").toString());
			Supplier sp = supplierSVImpl.findById(id);
			List<SupplierCompany> company = companyService.selectByProperty("spId", sp.getId());
			int count = 0;
			if(company != null && company.size() > 0){
				count = company.get(0).getScale();
			}
			if(sp != null){
				String headString=ibaseinf.getHeadimg(sp.getId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
				List<SupplierCompany> supplierCompany=companyService.selectByProperty("spId", sp.getId());
				String intro="";
				if(supplierCompany.size()>0)
					intro=supplierCompany.get(0).getIntro();
				baseInfo = new VSupplierBaseInfo(sp.getSpName(), sp.getAddress(),
						sp.getPrimaryBusiness(), sp.getAbbreviation(),count,intro,headString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(baseInfo, mapBusi);
		}		
	}
	
	@SuppressWarnings("finally")
	@ResponseBody @RequestMapping("/suppliercertificate.do")
	public Object suppliercertificate(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<VCertifcate> list = new ArrayList<VCertifcate>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id = Integer.valueOf(map.get("supplierid").toString());
			//资历证书
			List<VAttachment> qualList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_QUAL_CERTIFICATE_TYPE);
			//从业证书
			List<VAttachment> pracList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_PRAC_CERTIFICATE_TYPE);
			//公司实景图
			List<VAttachment> liveList = attachmentService.findAttachment(id, AttachmentConstant.SUPPLIER_LIVE_CERTIFICATE_TYPE);
			//商家注册复印件
			List<VAttachment> registerPaperList = attachmentService.findAttachment(id, AttachmentConstant.TRADEMARK_REGINTER_COPIES);
			VCertifcate cv = null;
			for (VAttachment att : liveList) {//公司实景图
				cv = new VCertifcate(att.getId(), att.getDownloadPath(), "", getCertifcateId(att.getFiletype()));
				list.add(cv);
			}
			for (VAttachment att : qualList) {//资历证书
				cv = new VCertifcate(att.getId(), att.getDownloadPath(), "", getCertifcateId(att.getFiletype()));
				list.add(cv);
			}
			for (VAttachment att : pracList) {//从业证书
				cv = new VCertifcate(att.getId(), att.getDownloadPath(), "", getCertifcateId(att.getFiletype()));
				list.add(cv);
			}
			for (VAttachment att : registerPaperList) {//商家注册复印件
				cv = new VCertifcate(att.getId(), att.getDownloadPath(), "", getCertifcateId(att.getFiletype()));
				list.add(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}		
	}
	
	//商家证书类型
	public int getCertifcateId(int type){
		switch(type){
		case AttachmentConstant.SUPPLIER_QUAL_CERTIFICATE_TYPE:
			return 1;
		case AttachmentConstant.SUPPLIER_PRAC_CERTIFICATE_TYPE:
			return 2;
		case AttachmentConstant.SUPPLIER_LIVE_CERTIFICATE_TYPE:
			return 3;
		case AttachmentConstant.TRADEMARK_REGINTER_COPIES:
			return 4;
		default:
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody @RequestMapping("/supplierinf.do")
	public Object supplierinf(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<String> emptylist=new ArrayList<String>();//
		Map<String, Object> ret=new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer id = Integer.valueOf(map.get("supplierid").toString());
			Supplier supp=suppliersv.findById(id);
			
			List<SupplierCompany> company = companyService.selectByProperty("spId", supp.getId());
			int count = 0;
			if(company != null && company.size() > 0){
				count = company.get(0).getScale();
			}
			
			ret.put("suppliername", supp.getUsername());
			ret.put("supplierbusiness", "");
			ret.put("suppliercode", "");
			ret.put("supplierjkbflag", "");
			ret.put("abbreviation", supp.getAbbreviation());
			ret.put("personNum", count);
			ret.put("address", supp.getAddress());
			ret.put("legalPerson", supp.getLegalPerson());
			if(supp.getPrimaryBusiness()!=null)
				ret.put("supplierbusiness", supp.getCause());
			if(supp.getBusinessCode()!=null)
				ret.put("suppliercode", supp.getSpCode());
			if(supp.getJkbFlag()!=null)
				ret.put("supplierjkbflag", supp.getJkbFlag());
			ret.put("supplierimg", "");
			ret.put("qualList", emptylist);
			ret.put("pracList", emptylist);
			//监理头像
			List<String> headList = attachmentService.findForDownload(id, AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			if(headList.size()>0)
				ret.put("supplierimg", headList.get(0));
			//资历证书
			List<String> qualList = attachmentService.findForDownload(id, AttachmentConstant.SUPPLIER_QUAL_CERTIFICATE_TYPE);
			if(qualList.size()>0)
				ret.put("qualList", qualList);
			//从业证书
			List<String> pracList = attachmentService.findForDownload(id, AttachmentConstant.SUPPLIER_PRAC_CERTIFICATE_TYPE);
			if(pracList.size()>0)
				ret.put("pracList", pracList);
			//商家实景图
			List<String> pracList1 = attachmentService.findForDownload(id, AttachmentConstant.SUPPLIER_LIVE_CERTIFICATE_TYPE);
			if(pracList.size()>0)
				ret.put("shijingList", pracList1);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(ret, mapBusi);
		}		
	}
	
	
	
	@ResponseBody @RequestMapping("/staff_fangan.do")
	public Object sjs_datail(HttpServletRequest request) {
		List<VFangan> list = new ArrayList<VFangan>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);	
     		currentPage=(String) map.get("curpage");
     	    Integer spid=CommonUtil.stringToInteger(map.get("spid").toString());
			pagination.put("currentPage", currentPage);
			pagination.put("spid", spid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		list = supplierSVImpl.fangAn(map); 	
			map= PaginationInterceptor.nextPagination(map);
			if(supplierSVImpl.fangAn(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);	
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(JSONArray.fromObject(list), mapBusi);
		}		
	}
	
	@ResponseBody @RequestMapping("/supplier_update_spname.do")
	public Object sjs_update(HttpServletRequest request) {
		Map<String, Object> map = null;;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer spid=CommonUtil.stringToInteger(map.get("spid").toString());
			String name=map.get("name").toString();
			Supplier supplier=supplierSVImpl.findById(spid);
			supplier.setSpName(name);
			supplierSVImpl.update(supplier);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}		
	}
	
	@ResponseBody @RequestMapping("/supplier_update_img.do")
	public Object queryDetail(HttpServletRequest request) {
		VISMember visMember=new VISMember();
		Map<String, Object> map = null;
		List<VAttachment> imgurlpathList=null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer spid=CommonUtil.stringToInteger((String)map.get("spid"));
			//保存文件
			List<Attachment> attachment = attachmentService.uploadMulti((MultipartRequest) request);
			if(attachment==null){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess","保存文件失败");
				return -1;
			}
			Admin admin =new Admin();
			List<Attachment> list=attachmentService.findByMainId(spid,AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			for (Attachment attachment2 : list) {
				attachmentService.delete(attachment2);
			}
			attachmentService.saveAttachment(attachment,admin,spid,AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(visMember, mapBusi);
		}
	}
}
