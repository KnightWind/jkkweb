package com.jkkp.modules.sale_theme.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.sale_theme.mapper.ActivityJoinSignMapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.model.ActivityRecommend;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.service.IActivityGiftLogService;
import com.jkkp.modules.sale_theme.service.IActivityGiftService;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.service.IActivityRecommendService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.modules.sale_theme.service.IActivityVoucherNumService;
import com.jkkp.modules.sale_theme.view.VActivityJionSign;
import com.jkkp.modules.sale_theme.view.VDaoChu;
import com.jkkp.modules.sale_theme.view.VPhone;
import com.jkkp.modules.sale_theme.view.VRecommend;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;

@Service("activityJionSignService")
public class ActivityJionSignServiceImpl extends
		ServiceSupport<ActivityJionSign, VActivityJionSign, Integer> implements
		IActivityJionSignService {

	@Autowired
	private ActivityJoinSignMapper activityJoinSignMapper;
	@Autowired
	private IActivityRecommendService activityRecommendService;
	@Autowired
	private IActivityVoucherNumService activityVoucherNumService;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IActivityThemeService  activityThemeService;
	@Autowired
	private IActivityCardService activityCardService;
	@Autowired
	private IActivityGiftService activityGiftService;
	@Autowired
	private IActivityGiftLogService activityGiftLogService;
	@Autowired
	private IActivityCardDealsService activityCardDealsService;
	@Autowired
	private IMemberService memberService;
	
	
 
	@Override
	public ActivityJionSign selectActivityJoinSignIsExits(Integer activityId,
			String phoneNo) {
		List<ActivityJionSign> list = this.selectByProperty(new String[] {
				"activityId", "phone" }, new Object[] { activityId, phoneNo });
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	protected Mapper<ActivityJionSign> getMapper() {
		return activityJoinSignMapper;
	}

	@Transactional
	public Integer signIn(ActivityJionSign ajs, String from, String fromPhone,
			Integer voucherId, Integer activityId) {
		// 判断是否已经报名
		List<ActivityJionSign> isExits = this.selectByProperty("phone",
				ajs.getPhone(), "id", true);
		if (isExits.size() > 0) {
			return 1;
		}
		// 新增已报名
		this.save(ajs);

		//报名成功后新增人数
		if(ajs.getActivityId()!=null){
			ActivityTheme at=activityThemeService.findById(ajs.getActivityId());
			at.setJoinedNum(at.getJoinedNum()+3);
			activityThemeService.update(at);
		}
		
		//判断用户是否有装修需求，如果有，则写进一条预约记录Appointment
		if(ajs.getDecoration()==1){
			Appointment bean=new Appointment();
			bean.setUser(ajs.getName());
			bean.setMobile(ajs.getPhone());
			bean.setCreateTime(new Date());
			bean.setType(5);
			appointmentService.save(bean);
		}
		
		// 判断上一页面是否是"现金券",写入一条现金券记录
		if (from != null && from.equals("xianjinquan")) {
			ActivityVoucherNum bean = new ActivityVoucherNum();
			bean.setVoucherId(voucherId);
			bean.setPhone(ajs.getPhone());
			bean.setNum(1);
			bean.setHistoryNum(1);
			bean.setUpdateTime(new Date());
			activityVoucherNumService.save(bean);
			return 2;
		}

		// 判断上一页面是否是"签到页面",更新签到
		if (from != null && from.equals("qiandao")) {
			ActivityJionSign bean = this.selectByProperty("phone",
					ajs.getPhone()).get(0);
			bean.setSignTime(new Date());
			this.update(bean);
			return 3;
		}

		// 判断是否扫描二维码页面进来，写入一条推荐记录
		if(activityId != null && StringUtils.isNotBlank(fromPhone) && StringUtils.isNotBlank(ajs.getPhone())) {
			ActivityRecommend recommend = new ActivityRecommend();
			recommend.setActivityId(activityId);
			recommend.setFromPhone(fromPhone);
			recommend.setToPhone(ajs.getPhone());
			List<ActivityRecommend> list = activityRecommendService.select(recommend);
			if(list.isEmpty()) {
				recommend.setStatus(0);
				recommend.setCreateTime(new Date());
				recommend.setSuccessTime(new Date());
				activityRecommendService.save(recommend);
			}
	
			return 5;
		}

		// 判断是否被推荐
		ActivityRecommend arBean = new ActivityRecommend();
		arBean.setToPhone(ajs.getPhone());
		arBean.setStatus(0);
		List<ActivityRecommend> arList = activityRecommendService.select(arBean);
		if (arList.size() > 0) {
			ActivityRecommend ar = arList.get(0);
			ar.setStatus(1);
			// 更新推送表状态
			activityRecommendService.update(ar);

//          判断推送者是否已经推送足十人，如果足够十人，买卡处理
//			ActivityRecommend fromPhoneBean = new ActivityRecommend();
//			fromPhoneBean.setFromPhone(ar.getToPhone());
//			fromPhoneBean.setStatus(1);
//			List<ActivityRecommend> fromPhoneCount = activityRecommendService.select(fromPhoneBean);
//			if (fromPhoneCount.size() == 5) {
//				// 获得已购卡权限
//
//				return 4;
//			}
		}
		// 都不满足,仅仅是报名
		return 6;
	}

	@Override
	public Integer signActivityTheme(String phone,Integer activityId) {
		List<ActivityJionSign> isExits = this.selectByProperty("phone", phone);
		if (isExits.size() == 0) {
			//自动报名 + 签到
			ActivityJionSign ajs = new  ActivityJionSign();
			ajs.setActivityId(activityId);
			ajs.setPhone(phone);
			ajs.setJoinFirst(false);
			Date date = new Date();
			ajs.setJoinTime(date);
			ajs.setSignTime(date);
			ajs.setSmsInform(false);
			ajs.setVip(0);
			ajs.setDecoration(0);
			ajs.setSex(0);
			ajs.setSpId(0);
			this.save(ajs);
			
			
		    addPhoneToMember(phone);
			
			return 0;
		}
		// 操作的用户信息
		ActivityJionSign ajs = isExits.get(0);
		if (ajs.getSignTime() != null) {
			// 已签到 提示不用再签到
			return 1;
		}
		// 赋值join_first
	    ajs.setJoinFirst(true);
		ajs.setSignTime(new Date());
		this.update(ajs);
		return 2;
	}

	//引流活动用户升级为家可可用户
	public void addPhoneToMember(String phone){
		List<String> list = memberService.findAllPhone();
		if(!list.contains(phone)){
			Member member = new Member();
			member.setMobile(phone);
			member.setNickname("家可可用户");
			String pass = Sysconfig.CONFIG_PARAM.get(ConfigConstant.INIT_PASSWORD);
			if(pass == null){
				pass = "a1234567";
			}
			member.setPassword(CommonUtil.md5(pass));
			memberService.save(member);
		}
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public Map<String, Object> tuijian(String phone) {
		ActivityRecommend arBean = new ActivityRecommend();
		Map<String, Object> map = new HashMap<String, Object>();
		arBean.setToPhone(phone);
		arBean.setStatus(0);
		List<ActivityRecommend> arList = activityRecommendService.select(arBean);
		if (arList.size() > 0) {
			ActivityRecommend ar = arList.get(0);
			ar.setStatus(1);
			ar.setSuccessTime(new Date());
			// 更新推送表状态
			activityRecommendService.update(ar);

			// 判断推送者是否已经推送足5人，如果足够5人，买卡处理
			ActivityRecommend fromPhoneBean = new ActivityRecommend();
			fromPhoneBean.setFromPhone(ar.getFromPhone());
			fromPhoneBean.setStatus(1);
			List<ActivityRecommend> fromPhoneCount = activityRecommendService.select(fromPhoneBean);
			if (fromPhoneCount.size() == 2) {
				String toPhone = fromPhoneCount.get(0).getFromPhone();
				ActivityJionSign ajs = new ActivityJionSign();
				ajs.setPhone(toPhone);
				List<ActivityJionSign> list = this.select(ajs);
				if(list.size() > 0){
					ActivityJionSign sign = list.get(0);
					sign.setVip(1);
					//更新成为vip
					this.update(sign);
				}
				jiakekeVipMsg(toPhone);
				// 获得已购卡权限
				map.put("rel", 1);
				map.put("msg", "已满5人，获得会员权限");
				return map;
			}
			map.put("rel", 2);
			map.put("msg", "仅仅把报名状态改变，不足5人");
			return map;
		}
		map.put("rel", 0);
		map.put("msg", "没有被推荐，不做处理");
		return map;
	}
	
	
	public void jiakekeVipMsg(String phone) {
		try {
			String content = Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_JIAKEKE_VIP_SUCC);
			SendMsgUtil.send(phone,content);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Boolean checkregister(String phone) {
		// TODO Auto-generated method stub
		List<ActivityJionSign> aj=this.selectByProperty("phone", phone);
		if(aj.size()>0)
			return true;
		else {
			return false;
		}
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void saveData(ActivityJionSign ajs, Integer cardId, String desc, Integer spId) {
		ajs.setVip(1);
		ajs.setJoinTime(new Date());
		ajs.setDecoration(0);
		ajs.setSex(0);
		ajs.setSpId(spId);
		ActivityCard card = null;
		//根据活动卡id查询实体
		if(CheckedUtil.isNotEmpty(cardId)){
			card = activityCardService.findById(cardId);
		}
		if(CheckedUtil.isNotEmpty(card)){
			//跟据活动卡id查询对应礼包
			List<ActivityGift> gifts = activityGiftService.selectByProperty("cardId", card.getId());
			ajs.setActivityId(card.getActivityId());
			//组装一条购卡记录
			ActivityCardDeals acd = new ActivityCardDeals();
			acd.setCardId(cardId);
			acd.setCreateTime(new Date());
			acd.setDecorateReq(desc);
			acd.setPhone(ajs.getPhone());
			acd.setFee(card.getPrice());
			acd.setPayStatus(1);
			acd.setPaymenType(4);
			acd.setShared(false);
			if(!gifts.isEmpty())acd.setGiftId(gifts.get(0).getId());
			//组装一条礼包记录
			ActivityGiftLog giftLog = new ActivityGiftLog();
			giftLog.setCreateTime(new Date());
			giftLog.setGiftId(acd.getGiftId());
			giftLog.setPhone(ajs.getPhone());
			giftLog.setUpdateTime(new Date());
			giftLog.setUsed(false);
			
			this.save(ajs);
			activityCardDealsService.save(acd);
			activityGiftLogService.save(giftLog);
		}
		
		
	}
	@Override
	public List<VPhone> selectPhone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityJoinSignMapper.selectPhone(map);
	}

	@Override
	public List<VDaoChu> querydaochu(Map<String, Object> map) {
		
		return activityJoinSignMapper.querydaochu(map);
	}

	@Override
	public List<VRecommend> queryRecommend(Map<String, Object> map) {
		return activityJoinSignMapper.queryRecommend(map);
	}

	@Override
	public List<String> getAllJoinSinPhone() {
	    List<ActivityJionSign> josinSignList = this.select(null);
	    List<String> phoneList=new ArrayList<String>();
	    for (ActivityJionSign bean : josinSignList) {
	    	phoneList.add(bean.getPhone());
		}
		return phoneList;
	}

	@Override
	public List<ActivityJionSign> checkVip(String phone) {
		ActivityJionSign bean=new ActivityJionSign();
		bean.setPhone(phone);
		bean.setVip(1);
		return this.select(bean);
	}

	

	
}
