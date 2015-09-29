package com.jkkp.appapi.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.appapi.common.service.interfaces.IRegionSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VAppointmentDetial;
import com.jkkp.appapi.modules.mapper.VIQiangDan;
import com.jkkp.appapi.modules.mapper.VISupplierQingDan;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.mapper.AppointmentPushMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.mapper.SysconfigMapper;
import com.jkkp.modules.system.model.Sysconfig;


/**
 * 
 * @author 朱国忠
 *
 */

@Service("appointmentSV")
public class AppointmentSVImpl extends
		ServiceSupport<Appointment, VAppointment, Integer> implements
		IAppointmentSV {
	private static String lock="";
	@Autowired	AppointmentMapper appointmentMapper;
	@Autowired IRegionSV regionservice;
	@Autowired IDesignCateService IDesignCateService;
	@Autowired
	IAppointmentPushSV appointmentPushSV;
	@Autowired	
	SysconfigMapper sysconfigMapper;
	@Autowired
	AppointmentPushMapper appointmentPushMapper;
	
	@Override
	protected Mapper<Appointment> getMapper() {
		return appointmentMapper;
	}


	@Override
	public List<VAppointment> findByAppointmentId(Map<String, Object> params) {
		
		
		return appointmentMapper.findByAppointmentId(params);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Appointment appointment) {
		
		if (appointment.getId() == null || appointment.getId() == 0) {
			this.save(appointment);
		} else {
			this.update(appointment);
		}
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void remove(Integer appointmentId) {
		this.deleteById(appointmentId);
	}


	@Override
	public List<Appointment> findParentList(String columnName, String value) {
		
		return this.selectByProperty(columnName, value);
	}


	@Override
	public boolean updateCAP(Appointment a) {
		// TODO Auto-generated method stub
		Appointment anow=this.findById(a.getId());
		System.out.print("mysql ver="+anow.getVersion());
		System.out.print("user ver="+a.getVersion());
		int dbver=anow.getVersion();
		int usver=a.getVersion();
		if(dbver==usver){
			a.setVersion(a.getVersion()+1);
			this.update(a);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public VIQiangDan qiangDan(Integer aid) {
		return appointmentMapper.qiangDan(aid);
	}


	@Override
	public List<VISupplierQingDan> qiangDanSupplier(Map<String, Object> params) {
		return appointmentMapper.qiangDanSupplier(params);
	}


	@Override
	public long getAppointmentCount() {
		return appointmentMapper.getAppointmentCount();
	}


	@Override
	public List<VAppointment> queryAppointmentList(Map<String, Object> params) {
		return appointmentMapper.queryAppointmentList(params);
	}

	@Override
	public VAppointment queryAppointmentDetailByViewAid(Map<String, Object> params) {
		return appointmentMapper.queryAppointmentDetailByViewAid(params);
	}


	@Override
	public VAppointmentDetial querydetial(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id+"");
		VAppointmentDetial v=appointmentMapper.qrydetial(map);
		/*private String RegionName;//地区名字 广东广州番禺
		private String ServiceMethonName;//服务方式 全包半包
		private String HouseStatus;//房屋状态 新房旧房
		private String FitmentRange;//装修范围 整装局部
		private String HousePurpose;//装修用途 儿童房
		private String houseStyle;//装修风格 简欧
		*/
		 List<AppointmentPush> ap=appointmentPushSV.selectByProperty(new String[] { "aid", "status" }, new Object[] {
		          id,ConstantAppStatus.PUSH_DAI_YING_DAN  });
		 v.setUngrabno(ap.size());
		if(v==null){
			return null;
		}
			
		if(v.getRegionid()!=null){
			v.setRegionName(regionservice.RegionName(v.getRegionid()));
		}else {
			v.setRegionName("");
		}
		if(v.getMethod()!=null){
			if(v.getMethod()==1){
				v.setServiceMethonName("半包");
			}
			else if(v.getMethod()==2){
				v.setServiceMethonName("全包");
			}
		}else {
			v.setServiceMethonName("");
		}
		if(v.getSubType()!=null){
		if(v.getSubType()==1){
			v.setHouseStatus("新房装修");
		}else if(v.getSubType()==2){
			v.setHouseStatus("旧房翻新");
		}}else {
			v.setHouseStatus("");
		}
		if(v.getWholeHouse()!=null){
		if(v.getWholeHouse()==1){
			v.setFitmentRange("整装");
		}else if(v.getWholeHouse()==2){
			v.setFitmentRange("局部");
		}}else {
			v.setFitmentRange("");
		}
		String houstpurpuse="";
		if(v.getHouseType()!=null){
	 		String[]  id_list = null;
	 		id_list=v.getHouseType().split(",");
	 		for (int i = 0; i < id_list.length; i++) {
	 			houstpurpuse=houstpurpuse+IDesignCateService.findById(Integer.valueOf(id_list[i])).getCateName();	
			}
		}
		v.setHousePurpose(houstpurpuse);
		if(v.getHousestyle()!=null){
			if(v.getHousestyle()!=null&v.getHousestyle()!=0){
				if(IDesignCateService.findById(v.getHousestyle()).getCateName()!=null)
				v.setHouseStyleS(IDesignCateService.findById(v.getHousestyle()).getCateName());		
			}
		}	
		if(v.getReviewTime()!=null){
			v.setReviewTimestring(StringAndDate.DateToString(v.getReviewTime()));		
		}
		if(v.getZxTime()!=null){
			v.setZxTimestring(StringAndDate.DateToString(v.getZxTime()));		
		}
		return v;
	}
	
	
	/**
	 * 商家应单操作
	 * 参数appointmentPushId
	 */
	@Override
	@Transactional
	public synchronized Map doTranscRespondAppointment(Map paramMap,HttpServletRequest request) throws Exception {
			Map dataMap = new HashMap();
			//查询push表数据 根据appointmentPushId
			AppointmentPush appointmentPush = appointmentPushSV.queryAppPushlByid(paramMap);
			if(appointmentPush==null){
				dataMap.put("resultCode", "0001");
				dataMap.put("resultInfo", "数据不存在");
				throw new  RuntimeException("USERERR#0001#push数据不存在");//触发回滚
			}else if(appointmentPush!=null&&appointmentPush.getStatus()!=ConstantAppStatus.PUSH_DAI_YING_DAN){
				throw new  RuntimeException("USERERR#0002#push表非待应单状态");//触发回滚
			}
//			//查询抢单数量是否已经超过最大数量
			paramMap.put("paraName", "APPOINTMENT_MAX_SUPPLIER");
			Sysconfig config = sysconfigMapper.selectByMap(paramMap);
			int max = Integer.parseInt(Sysconfig.CONFIG_PARAM
					.get(ConfigConstant.APPOINTMENT_MAX_SUPPLIER));
//			//查询该单的已抢单商家数
			paramMap.put("appointmentId", appointmentPush.getAid());
//			//已抢单数量
			int size = appointmentPushSV.queryAppPushlGrab(paramMap);
			if(size>=max){
				throw new  RuntimeException("USERERR#0003#已超过最大抢单人数");//触发回滚
			}
//			
//			//查询装修预约单数据
			Appointment appment =  appointmentMapper.selectByPrimaryKey(appointmentPush.getAid());
//			//只有待抢单和已抢单(待量房)状态  才允许继续抢
			if(appment.status!=ConstantAppStatus.DAI_QIANG_DAN&&appment.status!=ConstantAppStatus.DAI_LIANG_FANG){
				throw new  RuntimeException("USERERR#0004#aptment预约表状态为非待抢单状态");//触发回滚
			}
			
			//修改push表状态  根据appointmentPushId
			appointmentPush.setStatus(ConstantAppStatus.PUSH_YI_YING_DAN);
			appointmentPushSV.update(appointmentPush);
			appment.setStatus(ConstantAppStatus.DAI_LIANG_FANG);
			//更新预约单状态
			appointmentMapper.updateByPrimaryKey(appment);
			
			//当前抢单的商家为最后一个抢单的，剩下的push单状态更新为已结束 不允许再抢单
			if(max-size==1){
				Map map = new HashMap();
				map.put("status", ConstantAppStatus.PUSH_JIE_SHU_QIANGDAN);
				map.put("aid", appointmentPush.getAid());
				appointmentPushMapper.upadteStatusByParam(map);
			}
			dataMap.put("resultCode", "0000");
			dataMap.put("resultInfo", "抢单成功");
			return dataMap;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean del(Integer appointmentId) {
		// TODO Auto-generated method stub
		if(this.findById(appointmentId)!=null){
			this.deleteById(appointmentId);
			return true;
		}
		return false;
	}


	//用户关闭抢单，关闭所有推送单
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Boolean over(int appointmentid) {
		// TODO Auto-generated method stub
		Appointment a=this.findById(appointmentid);
		Integer countstatus=0;
		if(a==null)
			return true;
		List<AppointmentPush> other = appointmentPushSV.selectByProperty("aid",
				appointmentid,"aid",true);
		if (other.size()>0){//如果都是待应单就把用户预约单关闭
			for (AppointmentPush o : other) {
					if(o.getStatus()==ConstantAppStatus.PUSH_DAI_YING_DAN){
						countstatus=countstatus+1;
					}
			}
			if(countstatus==other.size()){
				a.setStatus(ConstantAppStatus.CLOSE_GRAB);
				this.update(a);
			}else {
				return false;
			}
		}
		a.setStatus(ConstantAppStatus.CLOSE_GRAB);
		this.update(a);
		return true;
	}


	@Override
	public int getAppointmentCountLiangFang() {
		// TODO Auto-generated method stub
		return  appointmentMapper.getAppointmentLiangFangCount();
		
	}


	@Override
	//改为和传入参数一样的返回值
	public Map<String, Object> querydetial1(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id+"");
		VAppointmentDetial v=appointmentMapper.qrydetial(map);
		/*private String RegionName;//地区名字 广东广州番禺
		private String ServiceMethonName;//服务方式 全包半包
		private String HouseStatus;//房屋状态 新房旧房
		private String FitmentRange;//装修范围 整装局部
		private String HousePurpose;//装修用途 儿童房
		private String houseStyle;//装修风格 简欧
		*/
		 List<AppointmentPush> ap=appointmentPushSV.selectByProperty(new String[] { "aid", "status" }, new Object[] {
		          id,ConstantAppStatus.PUSH_DAI_YING_DAN  });
		 v.setUngrabno(ap.size());
		if(v==null){
			return null;
		}
			
		if(v.getRegionid()!=null){
			v.setRegionName(regionservice.RegionName(v.getRegionid()));
		}else {
			v.setRegionName("");
		}
		if(v.getMethod()!=null){
			if(v.getMethod()==1){
				v.setServiceMethonName("半包");
			}
			else if(v.getMethod()==2){
				v.setServiceMethonName("全包");
			}
		}else {
			v.setServiceMethonName("");
		}
		if(v.getSubType()!=null){
		if(v.getSubType()==1){
			v.setHouseStatus("新房装修");
		}else if(v.getSubType()==2){
			v.setHouseStatus("旧房翻新");
		}}else {
			v.setHouseStatus("");
		}
		if(v.getWholeHouse()!=null){
		if(v.getWholeHouse()==1){
			v.setFitmentRange("整装");
		}else if(v.getWholeHouse()==2){
			v.setFitmentRange("局部");
		}}else {
			v.setFitmentRange("");
		}
		String houstpurpuse="";
		if(v.getHouseType()!=null){
	 		String[]  id_list = null;
	 		id_list=v.getHouseType().split(",");
	 		for (int i = 0; i < id_list.length; i++) {
	 			houstpurpuse=houstpurpuse+IDesignCateService.findById(Integer.valueOf(id_list[i])).getCateName();	
			}
		}
		v.setHousePurpose(houstpurpuse);
		if(v.getHousestyle()!=null){
			if(v.getHousestyle()!=null&v.getHousestyle()!=0){
				if(IDesignCateService.findById(v.getHousestyle()).getCateName()!=null)
				v.setHouseStyleS(IDesignCateService.findById(v.getHousestyle()).getCateName());		
			}
		}	
		if(v.getReviewTime()!=null){
			v.setReviewTimestring(StringAndDate.DateToString(v.getReviewTime()));		
		}
		if(v.getZxTime()!=null){
			v.setZxTimestring(StringAndDate.DateToString(v.getZxTime()));		
		}
		return null;
	}
}
