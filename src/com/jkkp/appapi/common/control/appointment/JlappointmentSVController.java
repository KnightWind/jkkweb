package com.jkkp.appapi.common.control.appointment;

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
import com.jkkp.modules.basedata.model.Engineerings;
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
public class JlappointmentSVController extends BaseController {
	public static final String head="/jlappointment_";
	@Autowired public IJlappointmentSV iJlappointmentSV;
	@Autowired public IJlappointmentPushSV iJlappointmentPushSV;
	@Autowired public IEngineeringsService iEngineeringsService;
	@Autowired public IJlGcdSV jlsv;
	@Autowired public CommonJsonMap commonJsonMap;
	@Autowired public JlappointmentMapper dao;
	@Autowired public SupplierUserMapper supplierUserMapper;
	@ResponseBody @RequestMapping(head+"list.do")//预约列表
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
				List<VJlappointment> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"detail.do")//预约详情
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
				VJlappointment bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				if(bean.engineerings!=null){
					System.out.println("bean.engineerings【"+bean.engineerings+"】");
					System.out.println("jlcomplainList【"+bean.engineerings.jlcomplainList+"】");
				}
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"load.do")//监理登录
	public Map<String,Object> load(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				Object userName=jobj.get("userName"); 
				if(userName==null)userName=jobj.get("username"); 
				Object pass=jobj.get("pass");
				if(userName==null||StringUtils.isBlank(userName.toString().trim())){
					rs.put("mess","帐号【userName】不能为空");
					rs.put("doneCode", "0012");return rs;
				}
				if(pass==null||StringUtils.isBlank(pass.toString().trim())){
					rs.put("mess","密码【pass】不能为空");
					rs.put("doneCode", "0012");return rs;
				}
				VSupplierUser suser=supplierUserMapper.getBeanByUsername(userName.toString());
				if(suser==null){rs.put("doneCode", "0003");rs.put("mess","用户不存在");return rs;}
				if(StringUtils.isBlank(suser.userpwd)){rs.put("mess","密码不存在");return rs;}
				if(suser.supplier==null){rs.put("mess","商家不存在");return rs;}
				if(suser.supplier.id==null){rs.put("mess","商家不存在");return rs;}
				if(suser.supplier.type!=5){rs.put("mess","用户账号或密码错");return rs;}
				String pwd=CommonUtil.md5(pass.toString().trim());
				if (pwd.equals(suser.userpwd)) {
					JSONObject data=new JSONObject();
					data.put("suppliuerId", suser.getSpId());
					data.put("suppliuerName", suser.getUsername());
					rs.put("data",data);
					rs.put("mess","登录成功！");
				} else {
					rs.put("doneCode", "0012");
					rs.put("mess", "登录失败！");
				}
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			//if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/jlappointment_list_query.do")
	public Object jlappointment_list_query(HttpServletRequest request) {
		List<VIAppPushAndAppAndDesignCase> appointmentdesignlist = null;
		List<VJlappointment1> jllist = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid = CommonUtil.stringToInteger((String) map.get("uid"));
			currentPage = (String) map.get("curpage");
			pagination.put("currentPage", currentPage);

			map = PaginationInterceptor.pagination(map);
			jllist = iJlappointmentSV.queryJAppDetail1(map);

			// 查询下一个页面数否有数据，如无则返回hastnest为false
			map = PaginationInterceptor.nextPagination(map);
			if (iJlappointmentSV.queryJAppDetail1(map).size() > 0) {
				pagination.put("hasnext", true);
			} else {
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(jllist, mapBusi);
		}
	}

	@ResponseBody
	@RequestMapping("/jlappointment_update.do")
	public Object jlappointment_update(HttpServletRequest request) {
		Jlappointment jlapp = null;
		JlappointmentPush jlpush = null;
		Engineerings eng = null;
		Map<String, Object> map = null;
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer japp_id = CommonUtil.stringToInteger((String) map.get("japp_id"));
			Integer japp_push_id = CommonUtil.stringToInteger((String) map.get("japp_push_id"));
			jlapp = iJlappointmentSV.findById(japp_id);
			if(jlapp.getStatus()==ConstantAppStatus.J_XUAN_DING){
				mapBusi.put("mess", "已经选监理");
				mapBusi.put("doneCode", "0000");
				return -1;
			}
			jlpush = iJlappointmentPushSV.findById(japp_push_id);
			jlapp.setStatus(ConstantAppStatus.J_XUAN_DING);// 在监理中，待量房就是用户选定了。
			jlapp.setSpId(jlpush.getSpId());// 在监理中，待量房就是用户选定了。
			iJlappointmentSV.update(jlapp);
			
			iJlappointmentPushSV.overOtherJLPush(japp_push_id);//关闭其他监理的推送
			
			
			jlpush.setStatus(ConstantAppStatus.J_PUSH_XUANDING);// 监理推送为用户已应单
			eng = iEngineeringsService.findById(jlapp.getGcdId());
			eng.setJlId(jlpush.getSpId());
			eng.setJlPushId(japp_push_id);
			iEngineeringsService.update(eng);
			
			iJlappointmentPushSV.update(jlpush);
			
			if (jlsv.selectByProperty("gcd_id", jlapp.getGcdId()).size()==0) {
				//假如工程单没有对应监理工程单关系，就新建
				JlGcd jlGcd = new JlGcd();
				jlGcd.setActivity(1);
				jlGcd.setGcdId(jlapp.getGcdId());
				jlGcd.setSpId(jlpush.getSpId());
				jlsv.save(jlGcd);
			} else {
				//假如存在以前的工程单监理关系，就设置之前的监理为旧监理，选中的监理为激活
				List<JlGcd> jlGcdlist = jlsv.selectByProperty("gcd_id",
						jlapp.getGcdId());
				for (JlGcd jl : jlGcdlist) {
					if (jl.getSpId() == jlpush.getSpId()) {
						if (jl.getActivity() != 1) {
							jl.setActivity(1);
							jlsv.update(jl);
						}
					} else {
						if (jl.getActivity() != 0) {
							jl.setActivity(0);
							jlsv.update(jl);
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"sp_id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"status","status", "1", "1");
		and+=BaseTools.getAndByJson(json,"sub_type","sub_type", "1", "1");
		and+=BaseTools.getAndByJson(json,"subType","sub_type", "1", "1");
		Object pointx=json.get("pointx");
		Object pointy=json.get("pointy");
		boolean flag1=pointx!=null&&StringUtils.isNotBlank(pointx.toString())&&BaseTools.isNumber(pointx);
		boolean flag2=pointy!=null&&StringUtils.isNotBlank(pointy.toString())&&BaseTools.isNumber(pointy);
		if(flag1&&flag2){//定位工地
			Object distance=json.get("distance");
			if(distance==null) distance=2000;			//默认方圆2000米
			and="and power(pointx-"+pointx+",2)+power(pointy-"+pointy+",2)";
			and+="<=power("+distance+"/11111.1,2)"+enter;
		}else{
			if(flag1) and+=" and pointx="+pointx+" "+enter;
			if(flag2) and+=" and pointy="+pointy+" "+enter;
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}
}