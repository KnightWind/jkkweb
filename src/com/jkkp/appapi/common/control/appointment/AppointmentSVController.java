package com.jkkp.appapi.common.control.appointment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.appapi.common.service.interfaces.ILoginSV;
import com.jkkp.appapi.common.service.interfaces.IRegionSV;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierConditionSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VAppointmentDetial;
import com.jkkp.appapi.modules.mapper.VIAppPushAndAppAndDesignCase;
import com.jkkp.appapi.modules.mapper.VIEngneeringsV1;
import com.jkkp.appapi.modules.mapper.VISupplierQingDan;
import com.jkkp.appapi.modules.mapper.VRetCondition;
import com.jkkp.appapi.modules.vo.AppointmentDetialVO;
import com.jkkp.appapi.putapp.PushApp;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.model.SupplierCondition;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;
import com.jkkp.utils.Pager;
@SuppressWarnings("finally")
@Controller
@RequestMapping("/")
public class AppointmentSVController  extends BaseController {
	@Autowired	IAppointmentSV appointmentSV;
	@Autowired	ISupplierCollectSV supplierCollectSV;
	@Autowired	IAppointmentPushSV appointmentPushSV;
	@Autowired	IAppointmentMemberSV appointmentMemberSV;
	@Autowired 	CommonJsonMap commonJsonMap;
	@Autowired	ISuppMessagePushSV suppMessagePushSV;
	@Autowired	ISupplierConditionSV iSupplierConditionSV;
	@Autowired IMemberService mensv;
	@Autowired public AppointmentMapper dao;
	@Autowired IRegionSV regionsv;
	@Autowired ISupplierSV suppsv;
	@Autowired ILoginSV iloginsv;
	@Autowired	private IAttachmentService attachmentService;
	@ResponseBody @RequestMapping("/appointment_list.do")//工地列表
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
				List<VAppointment> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			System.out.println(JSONObject.fromObject(rs,AllDao.jcfg));
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping("/appointment_detail.do")
	public Object queryDetail(HttpServletRequest request) {
		List<VAppointment> appointments=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request)){				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			appointments = appointmentSV.findByAppointmentId(map);	
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			map=commonJsonMap.autoMap(appointments,mapBusi);
			System.out.println(JSONObject.fromObject(map,AllDao.jcfg));
			return map;
		}
	}
	@ResponseBody @RequestMapping("/appointment_edit.do")//工地详情ysc
	public Map<String,Object> edit(HttpServletRequest request) throws Exception {
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
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("appointmentId");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("appointmentid");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				VAppointment bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				if(bean.engineerings!=null) bean.design=bean.engineerings.design;
				bean.engineerings=null;
				JSONObject data=JSONObject.fromObject(bean,AllDao.jcfg);
				
				rs.put("data",data);
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	
	
	
	@ResponseBody @RequestMapping("/addWeiXinYouKeAppointment.do")
	public Object addWeiXinYouKeAppointment(HttpServletRequest request) {
		Appointment appointment=new Appointment();
		AppointmentMember appointmentMember =new AppointmentMember();
		List<SupplierCollect> supplierCollects=null;
		Map<String, Object> data=new HashMap<String, Object>();
		List<SuppMessagePush>  suppMessagePushs=null;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		boolean success=false;
		String deposit=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_DEPOSIT);
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			String community = (String) map.get("community");	
			String budget = (String) map.get("budget");
			String space = (String) map.get("space");
			
			String memberId ="";
			if(map.containsKey("memberId")){
				memberId = (String) map.get("memberId");
			if(memberId.equals("")){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "会员id不能为空");
				return -1;
			}
			}
			
			Integer regionId  = 0;//默认为0，后面判断不为0就判断条件
			//String cityname=(String) map.get("cityname");
			//Region region=regionsv.findRegionByName(map);
			Region region=null;
			if (region!=null) {
				regionId=region.getRegionid();
			}
			if( (String) map.get("regionId")!=null){
				regionId=Integer.valueOf(map.get("regionId").toString());
				appointment.setRegionid(regionId);
			}
			if( (String) map.get("regionid")!=null){
				regionId=Integer.valueOf(map.get("regionid").toString());
				appointment.setRegionid(regionId);
			}
			
			String reviewTime=null;
			if(map.containsKey("reviewTime"))
				reviewTime = (String) map.get("reviewTime");
			if(map.containsKey("reviewTimestring"))
				reviewTime = (String) map.get("reviewTimestring");
			String method=null;
			if(map.containsKey("method"))
				method = (String) map.get("method");
			String user=null;
			if(map.containsKey("user"))
				user = (String) map.get("user");
			String mobile =null;
			if(map.containsKey("mobile"))
				mobile = (String) map.get("mobile");
			String address=null;
			if(map.containsKey("address"))
				address = (String) map.get("address");

			String isCollectSupplier="";
			if(map.containsKey("isCollectSupplier"))
				isCollectSupplier = (String) map.get("isCollectSupplier");
			
			String housetype=null;
			if(map.containsKey("HouseType"))
				housetype = (String) map.get("HouseType");
			if(map.containsKey("houseType"))
				housetype = (String) map.get("houseType");
			String wholehouse=null;
			if(map.containsKey("WholeHouse"))
				wholehouse = (String) map.get("WholeHouse");
			if(map.containsKey("wholeHouse"))
				wholehouse = (String) map.get("wholeHouse");
			String su_type=null;
			if(map.containsKey("su_type"))
				su_type = (String) map.get("su_type");
			if(map.containsKey("suType"))
				su_type = (String) map.get("suType");
			String housestyle=null;
			if(map.containsKey("housestyle"))
				housestyle = (String) map.get("housestyle");
			appointment.setType(1);//预约量房单类型
			
			if(reviewTime!=null&&!reviewTime.equals(""))
				appointment.setReviewTime(StringAndDate.StringToDate(reviewTime));
			
			appointment.setCommunity(community);
			appointment.setAddress(address);
			if(method!=null&&!method.equals(""))
				appointment.setMethod(Integer.valueOf(method));
			appointment.setUser(user);
			if(user==null||user.equals("")){
				//保证预约单有手机号码，方便后面推送。
				if(memberId!=""){
				Member member= mensv.findById(Integer.valueOf(memberId));
				if(member!=null){
					if(member.getNickname()!=null)
					appointment.setUser(member.getNickname());
				}}
				else {
					appointment.setUser("家可可会员");
				}
			}
			if(budget!=null&&!budget.equals(""))
				appointment.setBudget(Float.valueOf(budget));
			appointment.setStatus(10);
			
			if(space!=null&&!space.equals(""))
				appointment.setSpace(Float.valueOf(space));
			if(mobile!=null&&!mobile.equals(""))
				appointment.setMobile(mobile);
			else {
				//保证预约单有手机号码，方便后面推送。
				if(memberId!=""){
				Member member= mensv.findById(Integer.valueOf(memberId));
				if(member!=null){
					if(member.getMobile()!=null)
					appointment.setMobile(member.getMobile());
				}}
			}
			if(housetype!=null&&!housetype.equals(""))
				appointment.setHouseType(housetype);
			if(wholehouse!=null&&!wholehouse.equals(""))
				appointment.setWholeHouse(Integer.valueOf(wholehouse));
			if(su_type!=null&&!su_type.equals(""))
				appointment.setSuType(Integer.valueOf(su_type));
			if(housestyle!=null&&!housestyle.equals(""))
				appointment.setHousestyle(Integer.valueOf(housestyle));
			appointment.setCreateTime(time);
			
			//装修时间
			if(map.containsKey("zxTime")){
				String zxTime = (String) map.get("zxTime");
				if(zxTime!=null&&!zxTime.equals("")){
					Date amountTime=StringAndDate.StringToDate(zxTime);//商家量房时间
					appointment.setZxTime(amountTime);
				}
			}
			//装修时间
			if(map.containsKey("zxTimestring")){
				String zxTime = (String) map.get("zxTimestring");
				if(zxTime!=null&&!zxTime.equals("")){
					Date amountTime=StringAndDate.StringToDate(zxTime);//商家量房时间
					appointment.setZxTime(amountTime);
				}
			}
			
			appointment.setType(1);//预约量房单
			//保存预约信息
			appointmentSV.save(appointment);
			//如果勾选收藏商家，则提取收藏商家，推送到发布表appointment_push
			
			Integer aid=appointment.getId();
			AppointmentPush appointmentPush=null; 
			if(isCollectSupplier.equals("1")){
				supplierCollects=supplierCollectSV.querySupCollDetailByUid(map);
				if(supplierCollects.size()>0){
					for(int i=0;i<supplierCollects.size();i++){
						Integer spId=supplierCollects.get(i).getSpId();
						VRetCondition result=iSupplierConditionSV.judgeSuppConditionResult(spId, Float.valueOf(budget), regionId);
						//如果判断不满足商家设置条件，则跳过推送
						if(!result.isFlag())
							continue;
						success=true;
						appointmentPush=new AppointmentPush();

						appointmentPush.setSpId(spId);
						appointmentPush.setAid(aid);
						appointmentPush.setSendCollectState(1);//sendCollectState：0为不勾选收藏推送  1为勾选收藏推送
						appointmentPush.setCreateTime(time);
						appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_ShangJia_YINGDAN);
						Appointment a=appointmentSV.findById(appointment.getId());//匹配到商家就修改预约主表状态
						a.setStatus(ConstantAppStatus.DAI_QIANG_DAN);
						appointmentSV.update(a);
						appointmentPush.setReminder(1);
						if(appointment.getReviewTime()!=null){
							//商家量房时间
						appointmentPush.setAmountTime(appointment.getReviewTime());
						}		
						appointmentPush.setMoney(Float.valueOf(deposit));
						appointmentPush.setSupplierCount(0);
						appointmentPush.setRandom(0);
						appointmentPushSV.save(appointmentPush);
						
				
						//短信推送给商家
						Supplier supplier=suppsv.findById(spId);
						if(supplier!=null){
							String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APP_PUSH_NEWAPP);//xx装修公司修改了量房时间为
							SendMsgUtil.send(supplier.getBindMobile(),smsContent);
						}
						
						//判断商家是否在线，如果在线则推送给B端商户
						suppMessagePushs=suppMessagePushSV.selectByUserIdAndType(supplier.getId(), supplier.getType());
						if(suppMessagePushs.size()>0){
							//推送到B端，让其显示新增的预约信息
							for(int j=0;j<suppMessagePushs.size();j++)
								PushApp.pushApp(suppMessagePushs.get(j));
						}
					}
						
				}
			}else{
				List<SupplierCondition> suppliers = iSupplierConditionSV.judgeSuppCondition(map);
				//如果没找到满足商家条件匹配的单，操作失败
				if(suppliers.size()<=0){
					//mapBusi.put("doneCode", "0000");
					//mapBusi.put("mess", "未找到相匹配的商家推送，请核查对应预算和选择地市！");
					//return -1;
				}
				if(suppliers.size()>0){
					success=true;
				}
				for(int i=0;i<suppliers.size();i++){
					System.out.print("spid"+suppliers.get(i).getSpId()+"\r\n");
					Integer spId=suppliers.get(i).getSpId();
					appointmentPush=new AppointmentPush();

					appointmentPush.setSpId(spId);
					appointmentPush.setAid(aid);
					appointmentPush.setSendCollectState(0);//sendCollectState：0为不勾选收藏推送  1为勾选收藏推送
					appointmentPush.setCreateTime(time);
					appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_YING_DAN);
					appointmentPush.setReminder(1);
					if(appointment.getReviewTime()!=null){
						//商家量房时间
					appointmentPush.setAmountTime(appointment.getReviewTime());
					}		
					appointmentPush.setMoney(Float.valueOf(deposit));
					appointmentPush.setSupplierCount(0);
					appointmentPush.setRandom(0);
					appointmentPushSV.save(appointmentPush);
					
					
					
					//短信推送给商家
					Supplier supplier=suppsv.findById(spId);
					if(supplier!=null){
						String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APP_PUSH_NEWAPP);//xx装修公司修改了量房时间为
						if(supplier.getBindMobile()!=null)
							SendMsgUtil.send(supplier.getBindMobile(),smsContent);
					}
					
					//判断商家是否在线，如果在线则推送给B端商户
					suppMessagePushs=suppMessagePushSV.selectByUserIdAndType(appointmentPush.getSpId(), supplier.getType());
					if(suppMessagePushs.size()>0){
						//推送到B端，让其显示新增的预约信息
						for(int j=0;j<suppMessagePushs.size();j++)
							PushApp.pushApp(suppMessagePushs.get(j));
					}
				}
					
			}
			
			//保存预约会员关联表
			appointmentMember.setAid(aid);
			if(memberId!=null&&!memberId.equals("")){//假如有传入会员id就保存关系表，没有就后面再保存
				appointmentMember.setMid(Integer.valueOf(memberId));
				appointmentMember.setCreateTime(time);
				appointmentMemberSV.save(appointmentMember);
			}
			data.put("appointmentId", appointment.getId());
			data.put("smsCode", "");
			data.put("newregister", "0");
			data.put("mobile", mobile);
			if(success==true){
				data.put("matchstatus", "1");
				//游客发布短信
				String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_THANKYOU_USED_JKK);
				if(appointment.getMobile()!=null)
					SendMsgUtil.send(appointment.getMobile(),smsContent);
				
				Map<String, Object> regmap=iloginsv.visitorRegister(mobile,  appointment.getId());
				data.put("newregister", regmap.get("newregister"));
				data.put("smsCode", regmap.get("smsCode"));
			}
			else {
				data.put("matchstatus", "0");
			}
			
			if(success==false){
				if(memberId!=null&&!memberId.equals("")){//假如有传入会员id就保存关系表，没有就后面再保存
					appointmentMemberSV.delete(appointmentMember);
				}				
				appointmentSV.delete(appointment);
				data.put("appointmentId", "0");
				data.put("matchstatus", "0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
		
		
	}
	
	
	
	
	
	
	
	
	@ResponseBody @RequestMapping("/appointment_add.do")
	public Object addAppointment(HttpServletRequest request) {
		Appointment appointment=new Appointment();
		AppointmentMember appointmentMember =new AppointmentMember();
		List<SupplierCollect> supplierCollects=null;
		Map<String, Object> data=new HashMap<String, Object>();
		List<SuppMessagePush>  suppMessagePushs=null;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
		Map<String, Object> map = null;
		Date pushamountTime=null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		boolean success=false;
		String deposit=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_DEPOSIT);
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			String community = (String) map.get("community");	
			String budget = (String) map.get("budget");
			String space = (String) map.get("space");
			
			String memberId = (String) map.get("memberId");
			if(memberId.equals("")){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "会员id不能为空");
				return -1;
			}
			
			Integer regionId  = 0;//默认为0，后面判断不为0就判断条件
			//String cityname=(String) map.get("cityname");
			//Region region=regionsv.findRegionByName(map);
			Region region=null;
			if (region!=null) {
				regionId=region.getRegionid();
				appointment.setRegionid(Integer.valueOf(regionId));//水电 选项
			}
			
			String regionIdstring=null;
			if(map.containsKey("regionId")){
				regionIdstring = (String) map.get("regionId");
				if(regionIdstring!=null&&!regionIdstring.equals("")){
					appointment.setRegionid(Integer.valueOf(regionIdstring));//水电 选项
				}
			}
			if(map.containsKey("regionid")){
				regionIdstring = (String) map.get("regionid");
				if(regionIdstring!=null&&!regionIdstring.equals("")){
					appointment.setRegionid(Integer.valueOf(regionIdstring));//水电 选项
				}
			}
			
			String reviewTime=null;
			if(map.containsKey("reviewTime"))
				reviewTime = (String) map.get("reviewTime");
			if(map.containsKey("reviewTimestring"))
				reviewTime = (String) map.get("reviewTimestring");
			String method=null;
			if(map.containsKey("method"))
				method = (String) map.get("method");
			String user=null;
			if(map.containsKey("user"))
				user = (String) map.get("user");
			String mobile =null;
			if(map.containsKey("mobile"))
				mobile = (String) map.get("mobile");
			String address=null;
			if(map.containsKey("address"))
				address = (String) map.get("address");

			String isCollectSupplier="";
			if(map.containsKey("isCollectSupplier"))
				isCollectSupplier = (String) map.get("isCollectSupplier");
			
			String housetype=null;
			if(map.containsKey("HouseType"))
				housetype = (String) map.get("HouseType");
			if(map.containsKey("houseType"))
				housetype = (String) map.get("houseType");
			String wholehouse=null;
			if(map.containsKey("WholeHouse"))
				wholehouse = (String) map.get("WholeHouse");
			if(map.containsKey("wholeHouse"))
				wholehouse = (String) map.get("wholeHouse");
			String su_type=null;
			if(map.containsKey("su_type"))
				su_type = (String) map.get("su_type");
			if(map.containsKey("suType"))
				su_type = (String) map.get("suType");
			String housestyle=null;
			if(map.containsKey("housestyle"))
				housestyle = (String) map.get("housestyle");
			
			String water=null;
			if(map.containsKey("water")){
				water = (String) map.get("water");
				if(water!=null&&!water.equals("")){
					appointment.setWater(Integer.valueOf(water));//水电 选项
				}
			}
			String pointx=null;
			if(map.containsKey("pointx")){
				pointx = (String) map.get("pointx");
				if(pointx!=null&&!pointx.equals(""))
				appointment.setPointx(Double.valueOf(pointx));//坐标x
			}else {
				appointment.setPointx(0.0);//坐标x
			}
			String pointy=null;
			if(map.containsKey("pointy")){
				pointy = (String) map.get("pointy");
				if(pointy!=null&&!pointy.equals(""))
				appointment.setPointy(Double.valueOf(pointy));//坐标y
			}else {
				appointment.setPointy(0.0);//坐标y
			}
			
			//装修时间
			if(map.containsKey("zxTime")){
				String zxTime = (String) map.get("zxTime");
				if(zxTime!=null&&!zxTime.equals("")){
					Date amountTime=StringAndDate.StringToDate(zxTime);//商家量房时间
					appointment.setZxTime(amountTime);
				}
			}
			//装修时间
			if(map.containsKey("zxTimestring")){
				String zxTime = (String) map.get("zxTimestring");
				if(zxTime!=null&&!zxTime.equals("")){
					Date amountTime=StringAndDate.StringToDate(zxTime);//商家量房时间
					appointment.setZxTime(amountTime);
				}
			}
			appointment.setType(1);//预约量房单类型
			//预约量房时间
			if(reviewTime!=null&&!reviewTime.equals("")){
				appointment.setReviewTime(StringAndDate.StringToDate(reviewTime));
				pushamountTime=StringAndDate.StringToDate(reviewTime);
			}
			//保存上门量房时间
			String atime1=(String) map.get("amountTime");
			if(atime1!=null&&!atime1.equals("")){
			Date amountTime=StringAndDate.StringToDate(atime1);//商家量房时间
			pushamountTime=amountTime;
			appointment.setReviewTime(amountTime);
			}		
			
			appointment.setCommunity(community);
			appointment.setAddress(address);
			if(method!=null&&!method.equals(""))
				appointment.setMethod(Integer.valueOf(method));
			appointment.setUser(user);
			if(user==null||user.equals("")){
				//保证预约单有手机号码，方便后面推送。
				Member member= mensv.findById(Integer.valueOf(memberId));
				if(member!=null){
					if(member.getNickname()!=null)
					appointment.setUser(member.getNickname());
				}
			}
			if(budget!=null&&!budget.equals("")){
				appointment.setBudget(Float.valueOf(budget));
			}else {
				appointment.setBudget((float) 0.0);
			}
			appointment.setStatus(10);
			
			if(space!=null&&!space.equals("")){
				appointment.setSpace(Float.valueOf(space));
			}else {
				appointment.setSpace(Float.valueOf(0));
			}
			if(mobile!=null&&!mobile.equals(""))
				appointment.setMobile(mobile);
			else {
				//保证预约单有手机号码，方便后面推送。
				Member member= mensv.findById(Integer.valueOf(memberId));
				if(member!=null){
					if(member.getMobile()!=null)
					appointment.setMobile(member.getMobile());
				}
			}
			if(housetype!=null&&!housetype.equals(""))
				appointment.setHouseType(housetype);
			if(wholehouse!=null&&!wholehouse.equals(""))
				appointment.setWholeHouse(Integer.valueOf(wholehouse));
			if(su_type!=null&&!su_type.equals(""))
				appointment.setSuType(Integer.valueOf(su_type));
			if(housestyle!=null&&!housestyle.equals(""))
				appointment.setHousestyle(Integer.valueOf(housestyle));
			appointment.setCreateTime(time);
		
			int type=1;//默认是预约单
			if(map.containsKey("type"))//有值判断
				type = Integer.valueOf(map.get("type").toString());
			appointment.setType(type);//预约量房单
			
			//重复对于一个用户的同样提交1分钟内都是判断重复提交
			//TODO
			/*Appointment tempAppointment=new Appointment();
			tempAppointment=appointment;
			tempAppointment.setId(null);
			List<Appointment> lastapAppointment=appointmentSV.select(appointment);
			if(lastapAppointment.size()>0){
				Calendar comptime = Calendar.getInstance();
				comptime.setTime(time);
				comptime.add(Calendar.MILLISECOND,-10);//60秒内都算重复提交
				for (Appointment appointment2 : lastapAppointment) {
					Calendar apptime = Calendar.getInstance();
					apptime.setTime(appointment2.getCreateTime());
					if(comptime.compareTo(apptime)<0){
						data.put("appointmentId", -1);
						data.put("matchstatus", "-1");
						mapBusi.put("doneCode", "9999");
						mapBusi.put("mess","重复提交");
						return -1;
					}
				}
			}*/
			
			//保存预约信息
			appointmentSV.save(appointment);
			//如果勾选收藏商家，则提取收藏商家，推送到发布表appointment_push
			
			Integer aid=appointment.getId();
			AppointmentPush appointmentPush=null; 
			
			Boolean draft=false;
			if(map.containsKey("draft"))//有值判断
			{
				String d=map.get("draft").toString();
				if(d!=null&&!d.equals("")){//只有为1时候才是草稿
					if(Integer.valueOf(map.get("draft").toString())==1)
						draft=true;
				}
			}
			if(draft==false){//不是草稿就进行推送
			if(isCollectSupplier.equals("1")){
				supplierCollects=supplierCollectSV.querySupCollDetailByUid(map);
				if(supplierCollects.size()>0){
					for(int i=0;i<supplierCollects.size();i++){
						Integer spId=supplierCollects.get(i).getSpId();
						VRetCondition result=iSupplierConditionSV.judgeSuppConditionResult(spId, Float.valueOf(budget), regionId);
						//如果判断不满足商家设置条件，则跳过推送
						if(!result.isFlag())
							continue;
						success=true;
						appointmentPush=new AppointmentPush();

						appointmentPush.setSpId(spId);
						appointmentPush.setAid(aid);
						appointmentPush.setSendCollectState(1);//sendCollectState：0为不勾选收藏推送  1为勾选收藏推送
						appointmentPush.setCreateTime(time);
						appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_ShangJia_YINGDAN);
						Appointment a=appointmentSV.findById(appointment.getId());//匹配到商家就修改预约主表状态
						a.setStatus(ConstantAppStatus.DAI_QIANG_DAN);
						appointmentSV.update(a);
						appointmentPush.setReminder(1);
						if(pushamountTime!=null){
						appointmentPush.setAmountTime(pushamountTime);
						}		
						appointmentPush.setMoney(Float.valueOf(deposit));
						appointmentPush.setSupplierCount(0);
						appointmentPush.setRandom(0);
						appointmentPushSV.save(appointmentPush);
						

						//短信推送给商家
						Supplier supplier=suppsv.findById(spId);
						if(supplier!=null){
							String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APP_PUSH_NEWAPP);//xx装修公司修改了量房时间为
							SendMsgUtil.send(supplier.getBindMobile(),smsContent);
						}
						
						//判断商家是否在线，如果在线则推送给B端商户
						suppMessagePushs=suppMessagePushSV.selectByUserIdAndType(appointmentPush.getSpId(), supplier.getType());
						if(suppMessagePushs.size()>0){
							//推送到B端，让其显示新增的预约信息
							for(int j=0;j<suppMessagePushs.size();j++)
								PushApp.pushApp(suppMessagePushs.get(j));
						}
					}
						
				}
			}else{
				List<SupplierCondition> suppliers = iSupplierConditionSV.judgeSuppCondition(map);
				//如果没找到满足商家条件匹配的单，操作失败
				if(suppliers.size()<=0){
					//mapBusi.put("doneCode", "0000");
					//mapBusi.put("mess", "未找到相匹配的商家推送，请核查对应预算和选择地市！");
					//return -1;
				}
				if(suppliers.size()>0){
					success=true;
				}
				for(int i=0;i<suppliers.size();i++){
					System.out.print("spid"+suppliers.get(i).getSpId()+"\r\n");
					Integer spId=suppliers.get(i).getSpId();
					appointmentPush=new AppointmentPush();

					appointmentPush.setSpId(spId);
					appointmentPush.setAid(aid);
					appointmentPush.setSendCollectState(0);//sendCollectState：0为不勾选收藏推送  1为勾选收藏推送
					appointmentPush.setCreateTime(time);
					appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_YING_DAN);
					appointmentPush.setReminder(1);
					if(pushamountTime!=null){
						appointmentPush.setAmountTime(pushamountTime);
						}
					appointmentPush.setMoney(Float.valueOf(deposit));
					appointmentPush.setSupplierCount(0);
					appointmentPush.setRandom(0);
					appointmentPushSV.save(appointmentPush);
					
					
					
					//短信推送给商家
					Supplier supplier=suppsv.findById(spId);
					if(supplier!=null){
						String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APP_PUSH_NEWAPP);//xx装修公司修改了量房时间为
						if(supplier.getBindMobile()!=null)
							SendMsgUtil.send(supplier.getBindMobile(),smsContent);
					}
					
					//判断商家是否在线，如果在线则推送给B端商户
					suppMessagePushs=suppMessagePushSV.selectByUserIdAndType(supplier.getId(), supplier.getType());
					if(suppMessagePushs.size()>0){
						//推送到B端，让其显示新增的预约信息
						for(int j=0;j<suppMessagePushs.size();j++)
							PushApp.pushApp(suppMessagePushs.get(j));
					}
				}
					
			}
			}
			
			//保存预约会员关联表
			appointmentMember.setAid(aid);
			if(memberId!=null&&!memberId.equals(""))
				appointmentMember.setMid(Integer.valueOf(memberId));
			appointmentMember.setCreateTime(time);
			appointmentMemberSV.save(appointmentMember);
			data.put("appointmentId", appointmentMember.getAid());
			data.put("matchstatus", "1");
			
			if(success==false){
				//appointmentMemberSV.delete(appointmentMember);
				//appointmentSV.delete(appointment);
				Appointment app=appointmentSV.findById(appointment.getId());
				if(app!=null){
					app.setStatus(ConstantAppStatus.STATUS_DRAFT);//没有匹配商家就是未发布状态
					//data.put("appointmentId", "0");
					data.put("matchstatus", "0");//匹配状态，0没有匹配到，1匹配成功
					appointmentSV.update(app);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
		
		
	}
	
	@ResponseBody @RequestMapping("/appointment_update.do")
	public Object updateAppointment(HttpServletRequest request) {
		Appointment appointment=null;
		AppointmentMember appointmentMember =new AppointmentMember();
		List<SupplierCollect> supplierCollects=null;
		Map<String, Object> data=new HashMap<String, Object>();
		List<SuppMessagePush>  suppMessagePushs=null;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Date pushamountTime=null;
		boolean success=false;
		data.put("update", true);
		String deposit=Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_DEPOSIT);
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			appointment=appointmentSV.findById(Integer.valueOf(map.get("appid").toString()));
			if(appointment!=null){
				
				String community = (String) map.get("community");	
				String budget = (String) map.get("budget");
				String space = (String) map.get("space");
				
				
				Integer regionId  = 0;//默认为0，后面判断不为0就判断条件
				//String cityname=(String) map.get("cityname");
				//Region region=regionsv.findRegionByName(map);
				Region region=null;
				if (region!=null) {
					regionId=region.getRegionid();
					appointment.setRegionid(Integer.valueOf(regionId));//水电 选项
				}
				
				String regionIdstring=null;
				if(map.containsKey("regionId")){
					regionIdstring = (String) map.get("regionId");
					if(regionIdstring!=null&&!regionIdstring.equals("")){
						appointment.setRegionid(Integer.valueOf(regionIdstring));//水电 选项
					}
				}
				if(map.containsKey("regionid")){
					regionIdstring = (String) map.get("regionid");
					if(regionIdstring!=null&&!regionIdstring.equals("")){
						appointment.setRegionid(Integer.valueOf(regionIdstring));//水电 选项
					}
				}
				
				String reviewTime=null;
				if(map.containsKey("reviewTime"))
					reviewTime = (String) map.get("reviewTime");
				String method=null;
				if(map.containsKey("method"))
					method = (String) map.get("method");
				String user=null;
				if(map.containsKey("user"))
					user = (String) map.get("user");
				String mobile =null;
				if(map.containsKey("mobile"))
					mobile = (String) map.get("mobile");
				String address=null;
				if(map.containsKey("address"))
					address = (String) map.get("address");

				String isCollectSupplier="";
				if(map.containsKey("isCollectSupplier"))
					isCollectSupplier = (String) map.get("isCollectSupplier");
				
				String housetype=null;
				if(map.containsKey("HouseType"))
					housetype = (String) map.get("HouseType");
				if(map.containsKey("houseType"))
					housetype = (String) map.get("houseType");
				String wholehouse=null;
				if(map.containsKey("WholeHouse"))
					wholehouse = (String) map.get("WholeHouse");
				if(map.containsKey("wholeHouse"))
					wholehouse = (String) map.get("wholeHouse");
				String su_type=null;
				if(map.containsKey("su_type"))
					su_type = (String) map.get("su_type");
				if(map.containsKey("suType"))
					su_type = (String) map.get("suType");
				String housestyle=null;
				if(map.containsKey("housestyle"))
					housestyle = (String) map.get("housestyle");
				
				String water=null;
				if(map.containsKey("water")){
					water = (String) map.get("water");
					if(water!=null&&!water.equals("")){
						appointment.setWater(Integer.valueOf(water));//水电 选项
					}
				}
				String pointx=null;
				if(map.containsKey("pointx")){
					pointx = (String) map.get("pointx");
					if(pointx!=null&&!pointx.equals(""))
					appointment.setPointx(Double.valueOf(pointx));//坐标x
				}
				String pointy=null;
				if(map.containsKey("pointy")){
					pointy = (String) map.get("pointy");
					if(pointy!=null&&!pointy.equals(""))
					appointment.setPointy(Double.valueOf(pointy));//坐标y
				}
				
				//装修时间
				if(map.containsKey("zxTimestring")){
					String zxTime = (String) map.get("zxTimestring");
					if(zxTime!=null&&!zxTime.equals("")){
						Date amountTime=StringAndDate.StringToDate(zxTime);//商家量房时间
						appointment.setZxTime(amountTime);
					}
				}
				//装修时间
				if(map.containsKey("zxTime")){
					String zxTime = (String) map.get("zxTime");
					if(zxTime!=null&&!zxTime.equals("")){
						Date amountTime=StringAndDate.StringToDate(zxTime);//商家量房时间
						appointment.setZxTime(amountTime);
					}
				}
				appointment.setType(1);//预约量房单类型
				//预约量房时间
				if(reviewTime!=null&&!reviewTime.equals("")){
					appointment.setReviewTime(StringAndDate.StringToDate(reviewTime));
					pushamountTime=StringAndDate.StringToDate(reviewTime);
				}
				//保存上门量房时间
				if(map.containsKey("reviewTimestring")){
				String atime1=(String) map.get("reviewTimestring");
				if(atime1!=null&&!atime1.equals("")){
				Date amountTime=StringAndDate.StringToDate(atime1);//商家量房时间
				pushamountTime=amountTime;
				appointment.setReviewTime(amountTime);
				}}
				//保存上门量房时间
				if(map.containsKey("amountTime")){
				String atime1=(String) map.get("amountTime");
				if(atime1!=null&&!atime1.equals("")){
				Date amountTime=StringAndDate.StringToDate(atime1);//商家量房时间
				pushamountTime=amountTime;
				appointment.setReviewTime(amountTime);
				}}	
				
				appointment.setCommunity(community);
				appointment.setAddress(address);
				if(method!=null&&!method.equals(""))
					appointment.setMethod(Integer.valueOf(method));
				appointment.setUser(user);

				if(budget!=null&&!budget.equals(""))
					appointment.setBudget(Float.valueOf(budget));
				appointment.setStatus(10);
				
				if(space!=null&&!space.equals(""))
					appointment.setSpace(Float.valueOf(space));
				if(mobile!=null&&!mobile.equals(""))
					appointment.setMobile(mobile);
				else {
				}
				if(housetype!=null&&!housetype.equals(""))
					appointment.setHouseType(housetype);
				if(wholehouse!=null&&!wholehouse.equals(""))
					appointment.setWholeHouse(Integer.valueOf(wholehouse));
				if(su_type!=null&&!su_type.equals(""))
					appointment.setSuType(Integer.valueOf(su_type));
				if(housestyle!=null&&!housestyle.equals(""))
					appointment.setHousestyle(Integer.valueOf(housestyle));
				appointment.setCreateTime(time);
			
				int type=1;//默认是预约单
				if(map.containsKey("type"))//有值判断
					type = Integer.valueOf(map.get("type").toString());
				appointment.setType(type);//预约量房单
				//保存预约信息
				appointmentSV.update(appointment);
			
				
				
				if(success==false){
					//appointmentMemberSV.delete(appointmentMember);
					//appointmentSV.delete(appointment);
					Appointment app=appointmentSV.findById(Integer.valueOf(map.get("appid").toString()));
					if(app!=null){
						app.setStatus(ConstantAppStatus.STATUS_DRAFT);//没有匹配商家就是未发布状态
						data.put("matchstatus", "0");
						appointmentSV.update(app);
					}
				}
				if(success==true){
					Appointment app=appointmentSV.findById(Integer.valueOf(map.get("appid").toString()));
					if(app!=null){
						app.setStatus(ConstantAppStatus.DAI_QIANG_DAN);
						data.put("matchstatus", "1");
						appointmentSV.update(app);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
		
		
	}
	
	
	
	
	
	
	/**
	 * 预约看工地详情查看
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appointment_view_detail.do")
	public Object appointment_view_detail(HttpServletRequest request) {
		List<VAppointment> appointmentList = null;

		Map<String, Object> paramsMap = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";
		VAppointment myFitmentAppointment = null;//装修需求单
		VAppointment myViewAppointment = null;//预约看工地单
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, paramsMap, request)) {
				return -1;
			}
			paramsMap = commonJsonMap.setRequestMap(request);
			if(paramsMap.get("vAid")!=null&&"".equals(paramsMap.get("vAid"))){
				//查询看工地预约单的相关信息
				appointmentList = appointmentSV.queryAppointmentList(paramsMap);
				if(appointmentList!=null&&appointmentList.size()==1){
					myViewAppointment = appointmentList.get(0);
				}else{
					mapBusi.put("doneCode", "0005");
					mapBusi.put("mess", "查询的数据不正确，没有数据或返回多条数据。");
					return -1;
				}
				//查询装修需求单信息
				myFitmentAppointment = appointmentSV.queryAppointmentDetailByViewAid(paramsMap);	
			}else{
				mapBusi.put("doneCode", "0004");
				mapBusi.put("mess", "查看工地预约单id为空");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(
					setAppointment_view_detail_Result(myFitmentAppointment,myViewAppointment), mapBusi);
		}
	}
	
	/**
	 * 封装接口参数
	 * @param myFitmentAppointment
	 * @param myViewAppointment
	 * @return
	 */
	private Map setAppointment_view_detail_Result(
			VAppointment myFitmentAppointment, VAppointment myViewAppointment) {
		HashMap map = new HashMap();
		
		return map;
	}
	@ResponseBody
	@RequestMapping("/appointment_view_add.do")//预约看工地
	public Object appointment_view_add(HttpServletRequest request) {
		Appointment appointment=new Appointment();
		AppointmentMember appointmentMember =new AppointmentMember();
		List<SupplierCollect> supplierCollects=null;
		Map<String, Object> data=new HashMap<String, Object>();
		List<SuppMessagePush>  suppMessagePushs=null;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
		Map<String, Object> map = null;
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
			
			
			String community = (String) map.get("community");
			String address = (String) map.get("address");
			String user = (String) map.get("user");
			String space = (String) map.get("space");
			String mobile = (String) map.get("mobile");
			String budget = (String) map.get("budget");		
			String memberId = (String) map.get("memberId");
			String spId = (String) map.get("spId");


			if(memberId.equals("")){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "会员id不能为空");
				return -1;
			}
				
			appointment.setType(2);//预约看工地类型
			appointment.setCommunity(community);
			appointment.setAddress(address);
			appointment.setUser(user);
			if(budget!=null&&!budget.equals(""))
				appointment.setBudget(Float.valueOf(budget));
			appointment.setStatus(10);
			
			if(space!=null&&!space.equals(""))
				appointment.setSpace(Float.valueOf(space));
			if(mobile!=null&&!mobile.equals(""))
				appointment.setMobile(mobile);
			appointment.setType(2);//预约看工地
			//保存预约信息
			appointmentSV.save(appointment);
			//如果勾选收藏商家，则提取收藏商家，推送到发布表appointment_push
			
			Integer aid=appointment.getId();
			AppointmentPush appointmentPush=null; 
		
			appointmentPush=new AppointmentPush();
			appointmentPush.setSpId(Integer.valueOf(spId));
			appointmentPush.setAid(aid);
			appointmentPush.setCreateTime(time);
			appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_YING_DAN);
			appointmentPushSV.save(appointmentPush);
			
			//判断商家是否在线，如果在线则推送给B端商户
			Supplier supplier=suppsv.findById(appointmentPush.getSpId());
			suppMessagePushs=suppMessagePushSV.selectByUserIdAndType(appointmentPush.getSpId(), supplier.getType());
			if(suppMessagePushs.size()>0){
				//推送到B端，让其显示新增的预约信息
				for(int j=0;j<suppMessagePushs.size();j++)
					PushApp.pushApp(suppMessagePushs.get(j));
			}
		
			
			//保存预约会员关联表
			appointmentMember.setAid(aid);
			if(memberId!=null&&!memberId.equals(""))
				appointmentMember.setMid(Integer.valueOf(memberId));
			appointmentMember.setCreateTime(time);
			appointmentMemberSV.save(appointmentMember);
			data.put("appointmentId", appointmentMember.getAid());

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
		
		
	}

	@ResponseBody
	@RequestMapping("/appointment_status_appointmentpush_status_time_update.do")
	//修改Appointment推送表状态，传入id，修改商家推送表，商家id，量房时间amountTime
	public Object appointment_status_appointmentpush_status_time_update(HttpServletRequest request) {
		List<VAppointment> appointments=null;

		
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		AppointmentPush appointmentPush = null;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			//修改用户预约表状态
			appointments = appointmentSV.findByAppointmentId(map);
			Integer appid = Integer.valueOf((String) map
					.get("appointmentId"));
			Appointment app=null;
			app=appointmentSV.findById(appid);
			app.setStatus(ConstantAppStatus.DAI_LIANG_FANG);//用户状态
			appointmentSV.saveOrUpdate(app);
			//修改商家推送表状态
			appointmentPush = appointmentPushSV.queryAppPushlByid(map);
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YU_YUE_LIANG_FANG);//商家状态
			Date amountTime=StringAndDate.StringToDate((String) map//商家量房时间
					.get("appointmentPushamountTime"));
			appointmentPush.setAmountTime(amountTime);
			appointmentPushSV.update(appointmentPush);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( appointments,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/appointment_view_design_case.do")
	//查看装修方案，用pushid来查询，返回预约单详情和方案等商家信息
	public Object appointment_view_design_case(HttpServletRequest request) {
		List<VIAppPushAndAppAndDesignCase> appointmentdesignlist=null;

		
		Map<String, Object> map = null;
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
		
			//查看商家
			appointmentdesignlist = appointmentPushSV.queryAppPushAndAppAndDesignCaseByAppid(map);
			String photodir=Sysconfig.CONFIG_PARAM.get(ConfigConstant.PHOTO_PREFIX_URL);//图片保存路径
			if(!appointmentdesignlist.isEmpty()){
			for(VIAppPushAndAppAndDesignCase appdesign:appointmentdesignlist){   
				appdesign.setDesign_image_pid(photodir+appdesign.getDesign_image_pid());
	        }}  
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( appointmentdesignlist,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/appointment_detialByPushId.do")
	//查看装修方案，用pushid来查询，返回预约单详情和方案等商家信息
	public Object appointment_detial(HttpServletRequest request) {
		List<VIAppPushAndAppAndDesignCase> appointmentdesignlist=null;
		VIAppPushAndAppAndDesignCase ret=null;
		
		Map<String, Object> map = null;
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
		
			//查看商家
			appointmentdesignlist = appointmentPushSV.queryAppPushAndAppAndDesignCaseByAppid(map);
			if(!appointmentdesignlist.isEmpty()){		
				ret=appointmentdesignlist.get(0);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( ret,mapBusi);
		}
	}
	
	public String getAndByJSON(HttpServletRequest request,JSONObject json){//ysc查询条件
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"community","community", "1");
		and+=BaseTools.getAndByJson(json,"sp_id","sp_id", "1", "1");
		and+=BaseTools.getAndByJson(json,"spId","sp_id", "1", "1");
		//zx_stage,装修阶段10待开工20开工30水电40瓦木50竣工（含油漆）
		and+=BaseTools.getAndByJson(json,"zx_stage","zx_stage", "1", "1");
		Object pointx=json.get("pointx");Object pointy=json.get("pointy");
		boolean flag1=pointx!=null&&StringUtils.isNotBlank(pointx.toString())&&BaseTools.isNumber(pointx);
		boolean flag2=pointy!=null&&StringUtils.isNotBlank(pointy.toString())&&BaseTools.isNumber(pointy);
		if(flag1&&flag2){//查询周边工地
			Object distance=json.get("distance");
			if(distance==null) distance=2000;			//默认方圆2000米
			and="and power(pointx-"+pointx+",2)+power(pointy-"+pointy+",2)<=power("+distance+"/11111.1,2)"+enter;
		}else{
			if(flag1) and+=" and pointx="+pointx+" "+enter;
			if(flag2) and+=" and pointy="+pointy+" "+enter;
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}
	
	@ResponseBody
	@RequestMapping("/appointment_qiangdan.do")
	public Object appointment_detial_qiangdan(HttpServletRequest request) {
		List<VISupplierQingDan> list=new ArrayList<VISupplierQingDan>();
		Map<String, Object> map = null;
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer aid=CommonUtil.stringToInteger(map.get("aid").toString());
			String currentPage=(String) map.get("curpage");
			pagination.put("currentPage",currentPage);
			pagination.put("aid",aid);
			map= PaginationInterceptor.pagination(map);
			
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=appointmentSV.qiangDanSupplier(map);
			
			map= PaginationInterceptor.nextPagination(map);
			if(appointmentSV.qiangDanSupplier(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			data.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list,mapBusi);
		}
	}
	
	
	/**
	 * 预约单总数
	 * @param request
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/appointment_count.do")
	public Object appointment_count(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		long count = 0;
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			count = appointmentSV.getAppointmentCount();
			data.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
	}
	
	/**
	 * 预约单总数
	 * @param request
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/appointment_count_liangfang.do")
	public Object appointment_count_liangfang(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		long count = 0;
		try {
			//如果判断异常，则退出不做业务处理
			//if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			//{				
			//	return -1;
			//}
			count = appointmentSV.getAppointmentCountLiangFang();
			data.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
	}
	
	/**
	 * 预约单总数
	 * @param request
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/qry_match_sp.do")
	public Object qry_match_sp(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<SupplierCollect> supplierCollects=null;
		int successcnt=0;
		int allcnt=0;
		List<VRetCondition> retlist=new ArrayList<VRetCondition>();
		Map<String,Object> retmapMap=new HashMap<String, Object>();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			supplierCollects = supplierCollectSV.querySupCollDetailByUid(map);
			String memberId = (String) map.get("memberId");
			String budget = (String) map.get("budget");
			String space = (String) map.get("space");
			int regionId=Integer.valueOf(map.get("regionId").toString());
			
			if(supplierCollects.size()>0){
				for(int i=0;i<supplierCollects.size();i++){
					allcnt=allcnt+1;
					Integer spId=supplierCollects.get(i).getSpId();
					VRetCondition vRetCondition=new VRetCondition();
					vRetCondition=iSupplierConditionSV.judgeSuppConditionResult(spId, Float.valueOf(budget), regionId);
					if(vRetCondition.isFlag()==true){
						successcnt=successcnt+1;
					}
					Supplier supplier=suppsv.findById(spId);
					   //公司头像
					   List<String> comimg=attachmentService.findForDownload(spId, AttachmentConstant.SUPPLIER_COMPANY_TYPE);
					   if(comimg.size()>0)
						   	vRetCondition.setSpimg(comimg.get(0));
					   if(supplier.getSpName()!=null)
						   vRetCondition.setSpname(supplier.getSpName());
					   retlist.add(vRetCondition);			
				}
			}
			retmapMap.put("retlist", retlist);
			retmapMap.put("successcnt", successcnt);
			retmapMap.put("allcnt", allcnt);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(retmapMap,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/appointment_detial1.do")
	//查看装修方案，用pushid来查询，返回预约单详情和方案等商家信息
	public Object appointment_detial1(HttpServletRequest request) {
		VAppointmentDetial app=null;
		
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		AppointmentDetialVO vo = new AppointmentDetialVO();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			int id=Integer.valueOf(map.get("appointmentId").toString());
			app = appointmentSV.querydetial(id);
			BaseTools.CopyBean(app, vo);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
			
		} finally {
			return commonJsonMap.autoMap(vo,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/appointment_del.do")
	//删除预约单
	public Object appointment_del(HttpServletRequest request) {
		VAppointmentDetial app=null;
		
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		int id=0;
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			id=Integer.valueOf(map.get("appointmentId").toString());			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
			
		} finally {
			return commonJsonMap.autoMap(appointmentSV.del(id),mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/appointment_over.do")
	//删除预约单
	public Object appointment_over(HttpServletRequest request) {
		VAppointmentDetial app=null;
		
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		int id=0;
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			id=Integer.valueOf(map.get("appointmentId").toString());	
			appointmentSV.over(id);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(appointmentPushSV.over(id),mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/appointment_publish.do")
	public Object appointment_publish(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<SupplierCollect> supplierCollects=null;
		int successcnt=0;
		int id=0;
		List<VRetCondition> retlist=new ArrayList<VRetCondition>();
		Map<String,Object> retmapMap=new HashMap<String, Object>();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			id=Integer.valueOf(map.get("appointmentId").toString());
			Appointment appointment=appointmentSV.findById(id);
			appointment.setCreateTime(new Date());
			appointmentSV.update(appointment);
			data.put("matchstatus", "0");
			if(appointmentPushSV.pushToJudgeSuppCondition(id)==true)
				data.put("matchstatus", "1");
			data.put("appointmentId", id);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
				return commonJsonMap.autoMap(data,mapBusi);
		}
	}
}