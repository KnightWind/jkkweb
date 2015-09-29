package com.jkkp.appapi.common.control.engineerings;

import com.jkkp.common.BaseController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.*;
import com.jkkp.appapi.modules.mapper.*;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.design.model.Design;
import com.jkkp.utils.*;
import com.jkkp.appapi.common.filter.PaginationInterceptor;
@Controller @RequestMapping("/")
public class EngineeringsSVController extends BaseController {
	public static final String head="/engineerings_";
	@Autowired IEngineeringsService engservice;// 装入自动组装返回报文格式
	@Autowired CommonJsonMap commonJsonMap;
	@Autowired ISuppMessagePushSV suppMessagePushSV;
	@Autowired	IAppointmentSV appointmentSV;
	@Autowired	IDesignSV designSV;
	@Autowired	ISystemRegulationSVService iSystemRegulationSVService;
	@Autowired ISupplierSV supplierSV;
	@Autowired IEngineeringsService engsv;
	@Autowired IJlappointmentSV Jlappointmentsv;
	@Autowired IBaseinf iBaseinf;
	@ResponseBody @RequestMapping(head+"list.do")//工单列表
	public Map<String,Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过X");return rs;}
				JSONObject json_obj=JSONObject.fromObject(json);
				String and=getAndByJSON(request,json_obj);
				Pager pager=new Pager(and,json_obj,allDao.engineeringsMapper.getCount(and));
				List<VEngineerings> list=allDao.engineeringsMapper.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));	//提高性能，过滤null值属性
				//rs.put("data",list);			//不要过滤null值属性
			}
//			BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"location4gd.do")//定位工地
	public Map<String,Object> location4gd(HttpServletRequest request) throws Exception {
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
				Object id=json_obj.get("gcId");
				if(id==null||StringUtils.isBlank(id.toString().trim()))id=json_obj.get("id");
				Object address=json_obj.get("address");
				if(address==null||StringUtils.isBlank(address.toString())){rs.put("mess","地址【address】不能为空");return rs;}
				VEngineerings bean=null;
				if(id!=null&&!id.equals("")&&BaseTools.isNumber(id.toString())){
					bean=allDao.engineeringsMapper.getBeanById(Integer.parseInt(id.toString().trim()));
				} 
				if(bean==null||StringUtils.isBlank(bean.pointx)||StringUtils.isBlank(bean.pointy)){
					String key ="f247cdb592eb43ebac6ccd27f796e2d2";
					String url="http://api.map.baidu.com/geocoder?address="+address+"&output=json&key="+key;
					String str=BaseTools.doPostByUrl(url, "UTF-8", null,null);
					JSONObject gobj=JSONObject.fromObject(str);
					System.out.println("谷歌地图查询结果："+gobj);
					Object obj=gobj.get("result");
					if(obj instanceof JSONObject){
						JSONObject result=gobj.getJSONObject("result");
						JSONObject loc=result.getJSONObject("location");
						bean=new VEngineerings();
						bean.pointx=loc.getString("lng");
						bean.pointy=loc.getString("lat");
					}
				}
				JSONObject jobj=new JSONObject();
				if(bean==null||StringUtils.isBlank(bean.pointx)||StringUtils.isBlank(bean.pointy)){
					rs.put("mess","找不到地址");
					jobj.put("pointx","");
					jobj.put("pointy","");
				}else{
					jobj.put("pointx",bean.pointx);
					jobj.put("pointy",bean.pointy);
				}
				rs.put("data",jobj);
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"detail.do")//工单详情
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
				VEngineerings bean=allDao.engineeringsMapper.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				bean.appointment=null;
				bean.jlSupplier=null;
				bean.complain=null;
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			System.out.println(JSONObject.fromObject(rs,AllDao.jcfg));
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){//ysc查询条件
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"community","community", "1");
		and+=BaseTools.getAndByJson(json,"supplierId","sp_id", "1", "1");
		and+=BaseTools.getAndByJson(json,"sp_id","sp_id", "1", "1");
		and+=BaseTools.getAndByJson(json,"spId","sp_id", "1", "1");
		and+=BaseTools.getAndByJson(json,"jlId","jl_id", "1", "1");
		
		//zx_stage,装修阶段10待开工20开工30水电40瓦木50竣工（含油漆）
		String zxStage=BaseTools.getValueByKey(json,"zx_stage");
		if(StringUtils.isBlank(zxStage)) zxStage=BaseTools.getValueByKey(json,"zxStage");
		if("work".equals(zxStage)){
			and+=" and zx_stage in(20,30,40,50) and status is NULL"+enter;
		}else if("end".equals(zxStage)){
			and+=" and zx_stage=50 and status is NOT NULL"+enter;
		}
		else {
			and+=BaseTools.getAndByJson(json,"zxStage","zx_stage", "1", "1");
			and+=BaseTools.getAndByJson(json,"zx_stage","zx_stage", "1", "1");
		}
		
		Object pointx=json.get("pointx");Object pointy=json.get("pointy");
		boolean flag1=pointx!=null&&StringUtils.isNotBlank(pointx.toString())&&BaseTools.isNumber(pointx);
		boolean flag2=pointy!=null&&StringUtils.isNotBlank(pointy.toString())&&BaseTools.isNumber(pointy);
		if(flag1&&flag2){//查询周边工地
			Object distance=json.get("distance");
			if(distance==null) distance=20000;			//默认方圆20000米
			and="and power(pointx-"+pointx+",2)+power(pointy-"+pointy+",2)<=power("+distance+"/11111.1,2)"+enter;
		}else{
			if(flag1) and+=" and pointx="+pointx+" "+enter;
			if(flag2) and+=" and pointy="+pointy+" "+enter;
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}


	// 查看工程单信息
	@ResponseBody
	@RequestMapping("/query_engineerings_detail.do")
	public Object query_engineerings_detail(HttpServletRequest request) {
		Engineerings englist = null;
		List<VIEngneerings> englEngineerings = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			// englist=engservice.findById(Integer.valueOf((String)map.get("engid")));
			englEngineerings = engservice.qryEngineerDetialByid(map);
			// appointments = appointmentSV.findByAppointmentId(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(JSONArray.fromObject(englEngineerings), mapBusi);
		}
	}
	// 查看B端工程单信息列表
	@ResponseBody
	@RequestMapping("/query_supp_eng_list.do")
	public Object query_supp_eng_list(HttpServletRequest request) {
		List<VIEngneeringsV1> englEngineerings = null;

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			
			map= PaginationInterceptor.pagination( map);
			
			englEngineerings = engservice.qrySuppEngBySpId(map);
			
			//查询下一个页面数否有数据，如无则返回hastnest为false
			map= PaginationInterceptor.nextPagination( map);
			if(engservice.qrySuppEngBySpId(map).size()>0){
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
			return commonJsonMap.autoMap(englEngineerings, mapBusi);
		}
	}
	// 查看B端工程单信息
		@ResponseBody
		@RequestMapping("/query_supp_eng_detail.do")
		public Object query_supp_eng_detail(HttpServletRequest request) {
			List<VIEngneeringsV1> englEngineerings = null;

			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> pagination= new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");
			String currentPage="";

			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				if(map.get("engineerId")==null){
					mapBusi.put("doneCode", "0004");
					mapBusi.put("mess", "工程单号为空");
				}
				map.put("curpage",null);
//				currentPage=(String) map.get("curpage");
//				pagination.put("currentPage", currentPage);
//				
//				map= PaginationInterceptor.pagination( map);
				
				englEngineerings = engservice.qrySuppEngBySpId(map);
				
				//查询下一个页面数否有数据，如无则返回hastnest为false
				//map= PaginationInterceptor.nextPagination( map);
//				if(engservice.qrySuppEngBySpId(map).size()>0){
//					pagination.put("hasnext", true);
//				}else{
//					pagination.put("hasnext", false);
//				}
//				mapBusi.put("pagination", pagination);
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());

			} finally {
				return commonJsonMap.autoMap(englEngineerings, mapBusi);
			}
		}
	
	//更新工程单信息
	@ResponseBody
	@RequestMapping("/update_engineerings.do")
	public Object update_engineerings(HttpServletRequest request) {
		Engineerings eng = null;

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			eng = engservice
					.findById(Integer.valueOf((String) map.get("engineerings_id")));//工程单id
			eng.setZxStage(Integer.valueOf((String) map.get("ZxStage")));//工程单装修阶段
			engservice.update(eng);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(eng, mapBusi);
		}
	}
	
	//获取工程单开工交底信息
	@ResponseBody
	@RequestMapping("/qry_eng_start_work.do")
	public Object qryEngStartWorkByEngId(HttpServletRequest request) {
		List<VIEngineerings> engs = null;
		List<VIEngineeringsV1> engs1 = null;
		List<VIEngineeringsV2> engs2 = null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> date = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			

			engs = engservice.qryEngStartWorkByEngId(map);
			engs1 = engservice.qryEngOpinionByEngId(map);
			engs2 = engservice.qryEngCheckByEngId(map);
			date.put("startWork", engs);
			date.put("JCheckData", engs1);
			date.put("memOpnion", engs2);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(date, mapBusi);
		}
	}
	
	//获取工程单业主意见
	@ResponseBody
	@RequestMapping("/qry_eng_opinion.do")
	public Object qryEngOpinionByEngId(HttpServletRequest request) {
		 List<VIEngineeringsV1> engs = null;

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> date = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			

			engs = engservice.qryEngOpinionByEngId(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(engs, mapBusi);
		}
	}
	
	//获取工程单业主意见
	@ResponseBody
	@RequestMapping("/qry_eng_check_detail.do")
	public Object qryEngCheckByEngId(HttpServletRequest request) {
		 List<VIEngineeringsV2> engs = null;

		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> date = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			

			engs = engservice.qryEngCheckByEngId(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(engs, mapBusi);
		}
	}
	
	//获取工程单业主意见
		@ResponseBody
		@RequestMapping("/add_eng_test.do")
		public Object add_eng_test(HttpServletRequest request) {
			 Engineerings engs = null;

			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> date = new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");

			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				engs = engservice.addEngineerings(Integer.valueOf((String) map.get("appPushId")));
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());

			} finally {
				return commonJsonMap.autoMap(engs, mapBusi);
			}
		}
	
	//根据预约单查询是否能支付
		@ResponseBody
		@RequestMapping("/qry_topay_pushid.do")
		public Object qry_topay_pushid(HttpServletRequest request) {
			List<Engineerings> eng = null;

			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> date = new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");
			Map<String, Integer> retMap=new HashMap<String, Integer>();

			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				int appPushId=Integer.valueOf((String) map.get("appPushId"));

				eng = engservice.selectByProperty("appPushId", appPushId);
				if(!eng.isEmpty()){
					retMap.put("flag", 0);
					return commonJsonMap.autoMap(retMap, mapBusi);
				}
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());

			} finally {
				retMap.put("flag", 1);//能支付
				return commonJsonMap.autoMap(retMap, mapBusi);
			}
		}
		
		//
		@ResponseBody//根据push id 查询工程单id
		@RequestMapping("/qry_eng_pushid.do")
		public Object qry_eng_pushid(HttpServletRequest request) {
			List<Engineerings> eng = null;

			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> date = new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");
			Map<String, Integer> retMap=new HashMap<String, Integer>();

			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				int appPushId=Integer.valueOf((String) map.get("appPushId"));
				eng = engservice.selectByProperty("appPushId", appPushId);
				
				if(eng.size()>0){
					List<Jlappointment> jlap=Jlappointmentsv.selectByProperty("gcdId", eng.get(0).getId());
					if(jlap.size()>0){
						date.put("jlappointmentid", jlap.get(0).getId());
					}else {
						date.put("jlappointmentid", null);
					}
					date.put("engineeringsid", eng.get(0).getId());
					date.put("designid", eng.get(0).getDesignId());
				}
				else {
					date.put("engineeringsid", null);
					date.put("designid", null);
					date.put("jlappointmentid", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());

			} finally {
				return commonJsonMap.autoMap(date, mapBusi);
			}
		}
		
		@ResponseBody//工程单详情，开工，水电，瓦木，竣工
		@RequestMapping("/qry_eng_detial1.do")
		public Object qry_eng_detial1(HttpServletRequest request) {
			Engineerings eng = null;

			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> data = new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");
			Map<String, Integer> retMap=new HashMap<String, Integer>();

			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				int engid=Integer.valueOf((String) map.get("engid"));
				int stage=Integer.valueOf((String) map.get("stage"));
				eng = engservice.findById(engid);
				if(eng!=null){
					//装修阶段10待开工20开工30水电40瓦木50竣工（含油漆）
					Appointment appointment=appointmentSV.findById(eng.getAid());
					Engineerings engineerings=engsv.findById(engid);
					Design ds= designSV.findById(eng.getDesignId());
					int dateuse=0;
					Calendar calstart = Calendar.getInstance();
					if(engineerings.getStartTime()!=null)
						calstart.setTime(engineerings.getStartTime());
					if(ds!=null){
						switch (stage) {
						case 10:
							break;
						case 20:
							calstart.add(Calendar.DAY_OF_MONTH, +0);//开始日期
							dateuse=ds.getStartWork();
							break;
						case 30:
							if(ds.getStartWork()!=null&&!ds.getStartWork().equals(""))
								calstart.add(Calendar.DAY_OF_MONTH, +ds.getStartWork());
							if(ds.getWhiteFuel()!=null&&!ds.getWhiteFuel().equals(""))
								dateuse=ds.getWhiteFuel();
							break;
						case 40:
							if(ds.getStartWork()!=null&&!ds.getStartWork().equals("")){
								if(ds.getWhiteFuel()!=null&&!ds.getWhiteFuel().equals(""))
									calstart.add(Calendar.DAY_OF_MONTH, +(ds.getStartWork()+ds.getWhiteFuel()));//开始日期
							}
							if(ds.getTileWood()!=null&&!ds.getTileWood().equals(""))
								dateuse=ds.getTileWood();
							break;
						case 50:
							if(ds.getStartWork()!=null&&!ds.getStartWork().equals("")){
								if(ds.getWhiteFuel()!=null&&!ds.getWhiteFuel().equals("")){
									if(ds.getTileWood()!=null&&!ds.getTileWood().equals(""))
									calstart.add(Calendar.DAY_OF_MONTH, +(ds.getStartWork()+ds.getWhiteFuel()+ds.getTileWood()));
									}
							}	
							if(ds.getCompletion()!=null&&!ds.getCompletion().equals(""))
							dateuse=ds.getCompletion();
							break;
						default:
							break;
						}
					}
					data.put("stagestart", calstart.getTime());
					calstart.add(Calendar.DAY_OF_MONTH, +dateuse);//结束日期
					data.put("stageend",calstart.getTime());
					if(iSystemRegulationSVService.findtxt(stage)!=null){
						data.put("txt",iSystemRegulationSVService.findtxt(stage));
					}
					data.put("jlname","");
					data.put("jlhead","");
					if(supplierSV.findById(eng.getJlId())!=null){
						data.put("jlname",supplierSV.findById(eng.getJlId()).getSpName());
						data.put("jlhead",iBaseinf.getHeadimg(eng.getJlId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE));
					}	
					//data.put("eng", eng);
					data.put("id", eng.getId());
					data.put("community", "");
					data.put("pushid", eng.getAppPushId());
					data.put("designerid", "");
					data.put("appid", eng.getAid());
					data.put("designid", "");
					data.put("jianliid", "");
					data.put("sp_id", "");
					data.put("jianlibaogaoid", "");
					data.put("jlappointment_id", "");
					data.put("jlappointment_status", "");
					if(eng.getJlId()!=null)
						data.put("jianliid", eng.getJlId());
					if(eng.getJlbgId()!=null)
						data.put("jianlibaogaoid", eng.getJlbgId());
					if(eng.getSjsId()!=null)
						data.put("designerid", eng.getSjsId());
					if(eng.getDesignId()!=null)
						data.put("designid", eng.getDesignId());
					if(eng.getCommunity()!=null)
						data.put("community", eng.getCommunity());
					if(eng.getSpId()!=null)
						data.put("sp_id", eng.getSpId());
					List<Jlappointment> jlapp=Jlappointmentsv.selectByProperty("gcdId", engid,"gcdId",true);
					if(jlapp.size()>0){
						data.put("jlappointment_id", jlapp.get(0).getId());
						data.put("jlappointment_status", jlapp.get(0).getStatus());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());

			} finally {
				return commonJsonMap.autoMap(data, mapBusi);
			}
		}
		
		@ResponseBody @RequestMapping("/pingjia_supplier.do")
		public Object querySupplierDetail(HttpServletRequest request) {
			Map<String, Object> map = null;
			VIPingJiaSV viPingJiaSV=new VIPingJiaSV();
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
				Integer gcdid=CommonUtil.stringToInteger((String)map.get("gcdid"));
				viPingJiaSV=allDao.engineeringsMapper.getPingJia(gcdid);
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());
			} finally {
				return commonJsonMap.autoMap(viPingJiaSV, mapBusi);
			}
		}
		
		@ResponseBody @RequestMapping("/pingjia_jl.do")
		public Object querySupplierJlDetail(HttpServletRequest request) {
			Map<String, Object> map = null;
			VIPingJiaSV viPingJiaSV=new VIPingJiaSV();
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
				Integer gcdid=CommonUtil.stringToInteger((String)map.get("gcdid"));
				viPingJiaSV=allDao.engineeringsMapper.getPingJiaJl(gcdid);
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());
			} finally {
				return commonJsonMap.autoMap(viPingJiaSV, mapBusi);
			}
		}
		
		@ResponseBody @RequestMapping("/pingjia_sjs.do")
		public Object querySupplierSjsDetail(HttpServletRequest request) {
			Map<String, Object> map = null;
			VIPingJiaSV viPingJiaSV=new VIPingJiaSV();
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
				Integer gcdid=CommonUtil.stringToInteger((String)map.get("gcdid"));
				viPingJiaSV=allDao.engineeringsMapper.getPingJiaSjs(gcdid);
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());
			} finally {
				return commonJsonMap.autoMap(viPingJiaSV, mapBusi);
			}
		}
		
		//
		@ResponseBody//根据push id 查询工程单id
		@RequestMapping("/qry_aeng_uid.do")
		public Object qry_aeng_uid(HttpServletRequest request) {
			List<VEngineerings> eng = null;

			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> date = new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");
			Map<String, Integer> retMap=new HashMap<String, Integer>();

			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				int uid=Integer.valueOf((String) map.get("uid"));
				eng = engservice.qryEngByUid(map);

			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());

			} finally {
				if(eng.size()==0){
					return commonJsonMap.autoMap(0, mapBusi);
				}
				return commonJsonMap.autoMap(eng.get(0).getId(), mapBusi);
			}
		}
		
		@ResponseBody
		@RequestMapping("/kaigong_add.do")
		public Object qry_aeng_uid_ss(HttpServletRequest request) {
			Map<String, Object> map = null;
			Map<String, Object> mapBusi = new HashMap<String, Object>();
			Map<String, Object> date = new HashMap<String, Object>();
			mapBusi.put("mess", "操作成功");
			mapBusi.put("doneCode", "0000");
			Map<String, Integer> retMap=new HashMap<String, Integer>();
			try {
				// 如果判断异常，则退出不做业务处理
				if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
					return -1;
				}
				map = commonJsonMap.setRequestMap(request);
				Integer uid=Integer.valueOf((String) map.get("gcdid"));
				 Engineerings engineerings=engservice.findById(uid);
				 engineerings.setStartTime(new Date());
				 engineerings.setZxStage(20);
				 Appointment appointment=appointmentSV.findById(engineerings.getAid());
				 appointment.setZxTime(engineerings.getStartTime());
				 engservice.update(engineerings);
				 appointmentSV.update(appointment);
			} catch (Exception e) {
				e.printStackTrace();
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", e.getMessage());
			} finally {
				return commonJsonMap.autoMap(null, mapBusi);
			}
		}
}
