package com.jkkp.appapi.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.modules.mapper.VIEngineerings;
import com.jkkp.appapi.modules.mapper.VIEngineeringsV1;
import com.jkkp.appapi.modules.mapper.VIEngineeringsV2;
import com.jkkp.appapi.modules.mapper.VIEngneerings;
import com.jkkp.appapi.modules.mapper.VIEngneeringsV1;
import com.jkkp.appapi.modules.mapper.VIPingJiaSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.service.IAppointmentMemberService;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.supplier.mapper.JiakebaoMapper;
import com.jkkp.modules.supplier.model.Jiakebao;
import com.jkkp.utils.CommonUtil;

@Service("engineeringsService")
public class EngineeringsServiceImpl extends
		ServiceSupport<Engineerings, VEngineerings, Integer> implements
		IEngineeringsService {
	@Autowired
	private IAppointmentMemberService appointmentMemberService;
	@Autowired
	private AppointmentPushSVImpl apppushsv;
	@Autowired
	private AppointmentSVImpl appsv;
	@Autowired
	private JiaKeBaoSVImpl jkbsv;
	@Autowired
	private JiakebaoMapper jkbMapper;
	@Autowired
	private EngineeringsMapper OneMapper;
	@Autowired 
	private JlappointmentSVImpl iJlappointmentSV;
	@Autowired
	IDesignService designService;
	@Override
	protected Mapper<Engineerings> getMapper() {
		return OneMapper;
	}

	public List<VIEngneerings> qryEngineerDetialByid(Map<String, Object> map) {
		return OneMapper.qryEngineerDetialByid(map);
	}

	@Override
	public List<VIEngneeringsV1> qrySuppEngBySpId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return OneMapper.qrySuppEngBySpId(map);
	}

	@Override
	public List<VIEngineerings> qryEngStartWorkByEngId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return OneMapper.qryEngStartWorkByEngId(map);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized Engineerings addEngineerings(Integer AppPushId) {
		Engineerings entity = null;
		try {
			AppointmentPush apppush = apppushsv.findById(AppPushId);
			Integer AppId = apppush.getAid();
			Float ZxFund = apppush.getQuote().floatValue();

			Appointment appointment = appsv.findById(AppId);
			AppointmentMember appointmentMember=null;
			List<AppointmentMember> appointmentMembers = appointmentMemberService
					.selectByProperty("aid", AppId);
			if(appointmentMembers.size()>0)
				appointmentMember = appointmentMembers.get(0);

			List<Engineerings> engineerings = this.selectByProperty(
					"appPushId", AppPushId);
			if (engineerings != null && !engineerings.isEmpty()) {
				entity = engineerings.get(0);
			} else {
				entity = new Engineerings();
				entity.setAid(AppId);
				entity.setAppPushId(AppPushId);// 推送id
				entity.setCreateTime(new Date());
				if (appointment.getUser() != null)
					entity.setCreauser(appointment.getUser());// 创建用户

				entity.setPointx(String.valueOf(CommonUtil.isNull(
						appointment.getPointx(), 0)));
				entity.setPointy(String.valueOf(CommonUtil.isNull(
						appointment.getPointy(), 0)));

				List<Design> designs = designService.selectByProperty(
						new String[] { "aid", "spId" },
						new Object[] { apppush.getAid(), apppush.getSpId() });
				if (!designs.isEmpty()) {
					Design design = designs.get(0);
					entity.setDesignId(design.getId());// 设计方案id
					entity.setSjsId(design.getStaffid());// 设计师id
					if(design.getSpace()!=null)
						entity.setSpace(design.getSpace());
				}
				entity.setJlbgId(0);
				entity.setJlId(0);
				entity.setJlPushId(0);
				entity.setNote("");
				entity.setSpId(apppush.getSpId());// 商家id
				if (appointment.getZxTime() != null)// 装修时间
					entity.setStartTime(appointment.getZxTime());
				else {
					entity.setStartTime(new Date());
				}
				if (appointment.getCommunity() != null) {
					entity.setCommunity(appointment.getCommunity());
				}
				if(appointmentMember!=null)
					entity.setUid(appointmentMember.getMid());// 用户id
				entity.setZxFund(ZxFund);// 装修款
				entity.setZxStage(10);
				entity.setJgFund((float) (ZxFund * 0.2));// 家可保款
				entity.setToFund(ZxFund + (float) (ZxFund * 0.2));// 应付款
				try {
					entity.setBudget(Float.valueOf(apppush.getQuote().toString()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.save(entity);
			}

			List<Jiakebao> jkb = jkbMapper.qryJiakebaoBygcdId(entity.getId());
			if (jkb.isEmpty()) {
				Jiakebao jkb1 = new Jiakebao();
				jkb1.setAgreementId(null);
				jkb1.setCreateTime(new Date());
				jkb1.setGcdId(entity.getId());
				jkb1.setCreateUser(appointment.getUser());
				jkb1.setJlId(null);
				jkb1.setPaymoney((float) (ZxFund * 0.2));
				jkbsv.save(jkb1);
			}

			List<Jlappointment> jl = iJlappointmentSV.selectByProperty("gcdId",
					entity.getId());
			if (jl.isEmpty()) {
				Jlappointment jl1 = new Jlappointment();
				jl1.setGcdId(entity.getId());
				jl1.setSpId(apppush.getSpId());
				jl1.setCreateTime(new Date());
				jl1.setUid(appointmentMember.getMid());
				jl1.setStatus(ConstantAppStatus.J_WEI_FA_BU);
				jl1.setUid(appointmentMember.getAid());
				iJlappointmentSV.save(jl1);
			}

			apppushsv.cancelOtherPush(AppPushId);// 关闭其他订单
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public List<VIEngineeringsV1> qryEngOpinionByEngId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return OneMapper.qryEngOpinionByEngId(map);
	}

	@Override
	public List<VIEngineeringsV2> qryEngCheckByEngId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return OneMapper.qryEngCheckByEngId(map);
	}

	@Override
	public VIPingJiaSV getPingJia(Integer gcdid) {
		return OneMapper.getPingJia(gcdid);
	}

	@Override
	public VIPingJiaSV getPingJiaJl(Integer gcdid) {
		return OneMapper.getPingJiaJl(gcdid);
	}

	@Override
	public VIPingJiaSV getPingJiaSjs(Integer gcdid) {
		return OneMapper.getPingJiaSjs(gcdid);
	}

	@Override
	public List<VEngineerings> qryEngByUid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return OneMapper.qryEngByUid(map);
	}
}
