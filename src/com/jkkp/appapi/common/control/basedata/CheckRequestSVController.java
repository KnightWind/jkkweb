package com.jkkp.appapi.common.control.basedata;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.service.interfaces.IJlGcdSV;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentSV;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.modules.appointment.view.VJlappointment1;
import com.jkkp.modules.basedata.mapper.CheckRequestMapper;
import com.jkkp.modules.basedata.model.CheckRequest;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VCheckRequest;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.JlGcd;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;
import com.jkkp.appapi.common.constants.*;
import com.jkkp.appapi.common.filter.PaginationInterceptor;

@Controller
public class CheckRequestSVController extends BaseController {
	public static final String pfix="/check_request";
	@Autowired public CommonJsonMap commonJsonMap;
	@Autowired public CheckRequestMapper dao;
	@ResponseBody @RequestMapping(pfix+"_list.do")//验收请求列表
	public Map<String,Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject json_obj=JSONObject.fromObject(json);
				String and=getAndByJSON(request,json_obj);
				Pager pager=new Pager(and,json_obj,dao.getCount(and));
				List<VCheckRequest> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"_detail.do")//验收请求详情
	public Map<String,Object> detail(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("id");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				VCheckRequest bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录【"+id+"】不存在");return rs;}
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"_update_checkTime.do")//验收请求修改
	public Map<String,Object> update_checkTime(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("id");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				VCheckRequest bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录【"+id+"】不存在");return rs;}
				String checkTime=BaseTools.getValueByKey(jobj, "checkTime");
				if(StringUtils.isBlank(checkTime)){rs.put("mess","验收时间【checkTime】不能为空");return rs;}
				if(checkTime.length()==10){//2010-10-10
					bean.checkTime=BaseTools.dmt.parse(checkTime);
				}else if(checkTime.length()==19){
					bean.checkTime=BaseTools.fmt.parse(checkTime);
				}else{
					rs.put("mess","验收时间【checkTime】格式错误");return rs;
				}
				int rsFlag=dao.updateByPrimaryKey(bean);
				rs.put("mess",rsFlag>0?"修改成功":"修改失败");
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"_release.do")//根据上一条验收请求，发布一条新的验收请求
	public Map<String,Object> release(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("id");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				VCheckRequest bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录【"+id+"】不存在");return rs;}
				String checkTime=BaseTools.getValueByKey(jobj, "checkTime");
				if(StringUtils.isBlank(checkTime)){rs.put("mess","验收时间【checkTime】不能为空");return rs;}
				if(checkTime.length()==10){//2010-10-10
					bean.checkTime=BaseTools.dmt.parse(checkTime);
				}else if(checkTime.length()==19){
					bean.checkTime=BaseTools.fmt.parse(checkTime);
				}else{
					rs.put("mess","验收时间【checkTime】格式错误");return rs;
				}
				System.out.println();
				//把旧的验收请求复制到新的验收请求
				VCheckRequest newCheckRequest=new VCheckRequest();
				BaseTools.CopyBean(bean, newCheckRequest);
				newCheckRequest.setId(null);
				int rsFlag=dao.insert(newCheckRequest);
				
				rs.put("mess",rsFlag>0?"修改成功":"修改失败");
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"_save.do")//验收请求保存
	public Map<String,Object> save(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("id");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				VCheckRequest bean=new VCheckRequest();
				System.out.println(JSONObject.fromObject(bean));
				bean=(VCheckRequest)AllDao.copyProperties(bean,jobj);
				int rsFlag=0;
				if(bean.id==null||bean.id==0){
					bean.createTime=new Date();
					rsFlag=dao.insert(bean);
					rs.put("mess",rsFlag>0?"新建成功":"新建失败");
				}else{
					if(!BaseTools.isNumber(id)){rs.put("mess","id不是数字");return rs;}
					VCheckRequest temp=dao.getBeanById(Integer.parseInt(id));
					if(temp.id==null||temp.id==0){
						rs.put("mess","记录【"+id+"】不存在，不能修改");return rs;
					}else{
						rsFlag=dao.updateByPrimaryKey(bean);
						rs.put("mess",rsFlag>0?"修改成功":"修改失败");
					}
				}
				System.out.println(JSONObject.fromObject(bean));
				rs.put("rsFlag",rsFlag);
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"gcdId","gcd_id", "1", "1");
		and+=BaseTools.getAndByJson(json,"status","status", "1", "1");
		and+=BaseTools.getAndByJson(json,"sub_type","sub_type", "1", "1");
		and+=BaseTools.getAndByJson(json,"subType","sub_type", "1", "1");
		and+=BaseTools.getAndByJson(json,"stagName","stagName", "1");
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}
}