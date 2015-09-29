package com.jkkp.appapi.common.control.appointment;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentSV;
import com.jkkp.appapi.modules.mapper.VJLAppointmentPush1;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.mapper.JlappointmentPushMapper;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.modules.appointment.view.VJlappointmentPush;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.mapper.SysconfigMapper;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;
import com.jkkp.appapi.common.utils.*;
import com.jkkp.appapi.common.constants.*;
import com.jkkp.appapi.common.filter.PaginationInterceptor;
@Controller
public class JlappointmentPushSVController  extends BaseController {
	private final String prefix="/jlappointmentpush_";
	@Autowired public IJlappointmentSV iJlappointmentSV;
	@Autowired public IJlappointmentPushSV iJlappointmentPushSV;
	@Autowired public CommonJsonMap commonJsonMap;
	@Autowired public JlappointmentPushMapper dao;
	@Autowired public JlappointmentMapper pdao;
	@Autowired SysconfigMapper sysconfigMapper;
	@Autowired IBaseinf ibase;
	@ResponseBody @RequestMapping("jlappointmentpush_list.do")//预约推送列表
	public Map<String,Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					JSONObject json_obj=JSONObject.fromObject(json);
					String and=getAndByJSON(request,json_obj);
					System.out.println(and);
					Pager pager=new Pager(and,json_obj,dao.getCount(and));
					List<VJlappointmentPush> list=dao.getPageList(pager);
					AllDao.copyProperties(rs,pager);
					//rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));//过滤空值的属性
					rs.put("data",list);
				}
			}
//			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@RequestMapping(prefix+"detail.do") @ResponseBody 	//预约推送详情
	public Map<String,Object> detail(HttpServletRequest request) throws Exception {
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
					if(StringUtils.isBlank(id)){
						Object id_obj=jobj.get("id");
						if(id_obj!=null) id=id_obj.toString().trim();
					}
					if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
					VJlappointmentPush bean=dao.getBeanById(Integer.parseInt(id));
					
					if(bean==null){rs.put("mess","记录不存在");return rs;}
					JSONObject data=JSONObject.fromObject(bean,AllDao.jcfg);
					data.remove("jlappointment");
					data.remove("supplier");
					rs.put("data",data);
					rs.put("ver", "1.0");rs.put("ret", "");
				}
			}
			BaseTools.showMessageByJSON(JSONObject.fromObject(rs,AllDao.jcfg), "");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(prefix+"yingdan.do")	//预约应单
	public Map<String,Object> yingdan(HttpServletRequest request) throws Exception {
		return edit(request,"yingdan");
	}
	@ResponseBody @RequestMapping(prefix+"qidan.do")	//预约弃单
	public Map<String,Object> qidan(HttpServletRequest request) throws Exception {
		return edit(request,"qidan");
	}
	@ResponseBody @RequestMapping(prefix+"cancel.do")	//预约取消
	public Map<String,Object> cancel(HttpServletRequest request) throws Exception {
		return edit(request,"cancel");
	}
	
	/**
	 * 查询已选监理数，最大可选监理数
	 * @param request
	 * @return
	 */
	@ResponseBody	@RequestMapping("queryJlSpPushNum.do")
	public Object queryJlSpPushNum(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> dataMap = null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		//如果判断异常，则退出不做业务处理
		if(!ApiCommonUtil.dealCommonBuget(mapBusi,map,request)){				
			return -1;
		}
		try {
			map = commonJsonMap.setRequestMap(request);
			dataMap = iJlappointmentPushSV.queryJlSpPushNum(map, mapBusi);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		}finally {
			return commonJsonMap.autoMap(dataMap,mapBusi);
		}
		
		
	}
	
	
	@ResponseBody	@RequestMapping(prefix+"add.do")
	public Object jlappointmentpush_add(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map,request)){				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			//refactoring by panqiong 20150811
			iJlappointmentPushSV.doTrasacChooseJLSp(map, mapBusi);
			
			
			
//			for (int i = 0; i < id_list.length; i++) {
//				// System.out.print(id_list[i]+"\r\n");、
//				List<JlappointmentPush> jlplist = iJlappointmentPushSV
//						.selectByProperty(new String[] { "aid", "spId" },
//								new Object[] { jlappointment_id, id_list[i] });
//				if (jlplist.size() <= 0) {
//					// 对应的监理预约单没有对应的监理时候，就增加推送监理
//					JlappointmentPush jlappointmentPush = new JlappointmentPush();
//					jlappointmentPush.setAid(jlappointment_id);
//					jlappointmentPush
//							.setStatus(ConstantAppStatus.J_PUSH_DAI_YING_DAN);
//					jlappointmentPush.setCreateTime(StringAndDate.getTime());
//					jlappointmentPush.setSpId(Integer.valueOf(id_list[i]));
//					iJlappointmentPushSV.save(jlappointmentPush);
//
//					jlappointment = iJlappointmentSV.findById(jlappointment_id);
//					jlappointment.setStatus(Integer
//							.valueOf(ConstantAppStatus.J_DAI_QIANG_DAN));
//					iJlappointmentSV.update(jlappointment);
//				}
//			}

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
	
	
	@SuppressWarnings("finally")
	@ResponseBody @RequestMapping(prefix+"query.do")
	public Object jlappointmentpush_query(HttpServletRequest request) {
		List<VJLAppointmentPush1> jlpushlist=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request)){				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

     		currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination( map);	
			jlpushlist = iJlappointmentPushSV.queryJAppPushDetail1(map);
			if(jlpushlist.size()>0){
				for (VJLAppointmentPush1 v : jlpushlist) {
					v.setHeadimg(ibase.getHeadimg(v.getSpId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE));
				}
			}
			//查询下一个页面数否有数据，如无则返回hastnest为false
			map= PaginationInterceptor.nextPagination( map);
			if(iJlappointmentPushSV.queryJAppPushDetail1(map).size()>0){
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
			return commonJsonMap.autoMap(jlpushlist,mapBusi);
		}
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1","1");
		and+=BaseTools.getAndByJson(json,"aid","aid", "1","1");
		and+=BaseTools.getAndByJson(json,"sp_id","sp_id", "1","1");//监理id
		and+=BaseTools.getAndByJson(json,"spId","sp_id", "1","1");
		and+=BaseTools.getAndByJson(json,"status","status", "1","1");
		and+=BaseTools.getAndByJson(json,"zxTime","zxTime", "1");
		
		String status=BaseTools.getValueByKey(json,"status");
		if(StringUtils.isBlank(status)) status=BaseTools.getValueByKey(json,"status");
		if("0".equals(status)){
			and+=" or (lower(status)  =lower('11') )  ";
		}
		and+=" order by ";
		if(StringUtils.isBlank(status)) status=BaseTools.getValueByKey(json,"status");
		if("0".equals(status)){
			and+=" status  ASC , ";
		}
		and+=" create_time desc "+enter;
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}

	public Map<String,Object> edit(HttpServletRequest request,String doType) throws Exception {
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
					if(StringUtils.isBlank(id)){
						Object id_obj=jobj.get("id");
						if(id_obj!=null) id=id_obj.toString().trim();
					}
					if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
					VJlappointmentPush bean=dao.getBeanById(Integer.parseInt(id));
					if(bean==null){rs.put("mess","记录不存在");return rs;}
					if("yingdan".equals(doType)){//预约应单
						if(bean.status!=null&&bean.status!=10){
							bean.status=10;dao.updateByPrimaryKey(bean);
							VJlappointment pbean=pdao.getBeanById(bean.aid);
							if(pbean!=null){
								pbean.status=(int)bean.status;
								pdao.updateByPrimaryKey(pbean);
								//修改监理预约主表状态
								Jlappointment jla =iJlappointmentSV.findById(pbean.getId());
								if(jla.getStatus()<20)
								{
									jla.setStatus(20);
									iJlappointmentSV.update(jla);
								}
							}
							System.out.println("预约应单=修改status为"+10);
							rs.put("mess","应单成功");
						}else if(bean.status!=null&&bean.status==10){
							rs.put("mess","已经应单");
						}
					}else if("cancel".equals(doType)){//预约取消
						String reason=BaseTools.getValueByKey(jobj,"reason");
						if(bean.status!=null&&bean.status!=11){
							if(StringUtils.isNotBlank(reason)) bean.reason=reason;
							bean.status=11;dao.updateByPrimaryKey(bean);
							/*取消应单不能改监理预约主单状态
							VJlappointment pbean=pdao.getBeanById(bean.aid);
							if(pbean!=null){
								pbean.status=(int)bean.status;
								pdao.updateByPrimaryKey(pbean);	
							}
							*/
							System.out.println("预约取消=修改status为11");
							rs.put("mess","取消成功");
						}else if(bean.status!=null&&bean.status==11){
							rs.put("mess","已经取消");
						}
					}
					else if("qidan".equals(doType)){//用户选定后弃单
						String reason=BaseTools.getValueByKey(jobj,"reason");
						{
							if(StringUtils.isNotBlank(reason)) bean.reason=reason;
							bean.random=2;dao.updateByPrimaryKey(bean);
							/*取消应单不能改监理预约主单状态
							VJlappointment pbean=pdao.getBeanById(bean.aid);
							if(pbean!=null){
								pbean.status=(int)bean.status;
								pdao.updateByPrimaryKey(pbean);	
							}
							*/
							System.out.println("弃单 random=2");
							rs.put("mess","弃单成功");
						}
					}
					rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
					rs.put("ver", "1.0");rs.put("ret", "");
				}
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
}