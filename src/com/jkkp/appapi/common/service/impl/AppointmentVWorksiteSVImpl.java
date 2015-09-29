/**
 * 
 */
package com.jkkp.appapi.common.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentVWorksiteSV;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.putapp.PushApp;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentVwWorksiteMapper;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.modules.appointment.view.VAppointmentVwWorksite;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.jkkp.modules.supplier.model.Supplier;

/**
 * 预约单信息
 * @author panqiong
 *
 */
@Service("appointmentVWorksiteSVImpl")
public class AppointmentVWorksiteSVImpl  extends ServiceSupport<AppointmentVwWorksite, VAppointmentVwWorksite, Integer> implements IAppointmentVWorksiteSV {

	@Autowired	
	AppointmentVwWorksiteMapper apVwWorksiteMapper;
	@Autowired	
	IAppointmentPushSV appointmentPushSV;
	@Autowired	
	ISuppMessagePushSV suppMessagePushSV;
	@Autowired
	IDesignCateService designcatesv;
	@Autowired
	ISupplierSV supplierSV;
	
	@Override
	protected Mapper<AppointmentVwWorksite> getMapper() {
		return apVwWorksiteMapper;
	}
	/**
	 * 新增预约看工地信息 
		1.入库appointment_vw_worksite表
		2.入库appointmentPush
		//3.保存appointmentMember表（ps：似乎这张表并没有什么用）
		4. 做b端信息推送
		
	 */
	@Override
	@Transactional
	public Map addViewWorksiteAppointment(Map paramMap,
			HttpServletRequest request, Map resultMap) throws Exception {
		String appoint_address = (String) paramMap.get("appoint_address");//预约地址
		int userId = Integer.parseInt((String) paramMap.get("user_id"));//用户id
		int engineeringId = Integer.parseInt((String)paramMap.get("engineering_id"));//预约工地的工程单id
		int aid = Integer.parseInt((String) paramMap.get("aid"));//用户的装修需求单id
		String appoint_time = (String) paramMap.get("appoint_time");//预约时间		
		int spId = Integer.parseInt((String) paramMap.get("spId"));//预约工地的商家id
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date appointTime = new Date(sdf.parse(appoint_time).getTime());//utildate 转换成sqldate
		Date createTime = new Date(System.currentTimeMillis());
		int status=ConstantAppStatus.VW_WORKSITE_DYD;//待应单状态
		//--预约单信息保存开始
		AppointmentVwWorksite apVWorksite = new AppointmentVwWorksite( aid,  appoint_address,
				 appointTime,  createTime,  userId,
				 engineeringId,  status, spId);
		this.save(apVWorksite);
		//--预约单信息结束
		//推送信息开始
//		AppointmentPush appointmentPush=null; 
//		appointmentPush=new AppointmentPush();
//		appointmentPush.setSpId(Integer.valueOf(spId));
//		appointmentPush.setAid(aid);
//		appointmentPush.setCreateTime(createTime);
//		appointmentPush.setStatus(ConstantAppStatus.PUSH_DAI_YING_DAN);
//		appointmentPush.setSupplierCount(0);
//		appointmentPush.setSendCollectState(0);
//		appointmentPush.setRandom(0);
//		appointmentPushSV.save(appointmentPush);		
		//推送信息入库结束
		
		//判断商家是否在线，如果在线则推送给B端商户
		Supplier supplier=supplierSV.findById(spId);
		List<SuppMessagePush>  suppMessagePushs=suppMessagePushSV.selectByUserIdAndType(supplier.getId(), supplier.getType());
		if(suppMessagePushs.size()>0){
			//推送到B端，让其显示新增的预约信息
			for(int j=0;j<suppMessagePushs.size();j++){
				PushApp.pushApp(suppMessagePushs.get(j));
			}
		}
		//返回预约单id
		resultMap.put("id", apVWorksite.getId());
		return resultMap;
	}
	
	
	
	@Override
	public List<VAppointmentVwWorksite> queryAppointmentVWorksiteList(Map paramMap) throws Exception {
		return apVwWorksiteMapper.queryAppointmentVWorksiteList(paramMap);
	}
	@Override
	public VAppointmentVwWorksite queryAppointmentVWorksiteDetail(Map paramMap)
			throws Exception {
		VAppointmentVwWorksite viewWorksite = null;
		if(paramMap.get("id")==null&&"".equals(paramMap.get("id"))){
			throw new RuntimeException("id不能为空");
		}
		List<VAppointmentVwWorksite> list =  apVwWorksiteMapper.queryAppointmentVWorksiteList(paramMap);
		if(list!=null&&list.size()==1){
			viewWorksite = list.get(0);
			viewWorksite.setDcateName(designcatesv.findname(viewWorksite.getDcateId()));
			return list.get(0);
		}else if(list!=null&&list.size()>1){
			throw new RuntimeException("返回多条数据");
		}else{
			return null;
		}
		
	}
	/**
	 * 商家应单
	 * spUserId
	 * id
	 */
	@Override
	@Transactional
	public Map appointmentVWorksiteRespond(Map paramMap) throws Exception {
		Map dataMap = new HashMap();
		//参数解析
		int id = Integer.parseInt((String)paramMap.get("id"));//看工地预约单id
		int spUserId =  Integer.parseInt((String)paramMap.get("spUserId"));//应单商家用户id
		
		int status = ConstantAppStatus.VW_WORKSITE_YYD;//已应单状态
		int preStatus = ConstantAppStatus.VW_WORKSITE_DYD;
		Date respondTime = new Date(System.currentTimeMillis());//应单时间
		//--
		//更新预约单表
		VAppointmentVwWorksite view = new VAppointmentVwWorksite();
		view.setId(id);
		view.setStatus(status);
		view.setSpUserId(spUserId);
		view.setRespondTime(respondTime);
		view.setPreStatus(preStatus);
		int effect = this.apVwWorksiteMapper.updateVWorksiteInfo(view);
		if(effect>0){
			dataMap.put("busiCode", "0");
			dataMap.put("busiInfo", "业务处理成功");
		}else{
			dataMap.put("busiCode", "9001");
			dataMap.put("busiInfo", "更新失败，没有更新到任何数据");
		}
		return dataMap;
	}
}
