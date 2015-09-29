package com.jkkp.appapi.common.service.impl;

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

import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ISaleActivityService;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.service.IActivitiesService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.product.mapper.ItemMapper;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.sale_theme.mapper.ActivityCardMapper;
import com.jkkp.modules.sale_theme.mapper.ActivityJoinSignMapper;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherNumMapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityCompany;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.model.ActivityRecommend;
import com.jkkp.modules.sale_theme.model.ActivityShare;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.service.IActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.IActivityCardService;
import com.jkkp.modules.sale_theme.service.IActivityCompanyService;
import com.jkkp.modules.sale_theme.service.IActivityGiftLogService;
import com.jkkp.modules.sale_theme.service.IActivityGiftService;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.sale_theme.service.IActivityLotteryLogService;
import com.jkkp.modules.sale_theme.service.IActivityProductService;
import com.jkkp.modules.sale_theme.service.IActivityRecommendService;
import com.jkkp.modules.sale_theme.service.IActivityShareService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.modules.sale_theme.service.IActivityVoucherNumService;
import com.jkkp.modules.sale_theme.service.IActivityVoucherService;
import com.jkkp.modules.sale_theme.view.VActivityGift;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;
import com.jkkp.modules.sale_theme.view.VActivityProduct;
import com.jkkp.modules.sale_theme.view.VActivityTheme;
import com.jkkp.modules.sale_theme.view.VActivityVoucher;
import com.jkkp.modules.sale_theme.view.VMyVouchers;
import com.jkkp.modules.sale_theme.view.VUserActivityList;
import com.jkkp.modules.sale_theme.vo.LotteryResult;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

@Service("saleActivityService")
public class SaleActivityServiceImpl implements ISaleActivityService { 
	@Autowired
	IActivityThemeService activityThemeService;
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	IActivityJionSignService activityJionSignService;
	@Autowired
	IActivityProductService activityProductService;
	@Autowired
	IActivityCompanyService ActivityCompanysv;
	@Autowired
	IActivitiesService activitysv;
	@Autowired
	IBaseinf ibaseinfsv;
	@Autowired
	IActivityGiftService activitygiftsv;
	@Autowired
	ActivityJoinSignMapper ActivityJoinSignMapper;
	@Autowired
	ActivityVoucherNumMapper ActivityVoucherNumMapper;
	@Autowired
	IActivityVoucherService ActivityVoucherService;
	@Autowired
	AttachmentServiceImpl attachmentServiceImpl;
	@Autowired
	IActivityRecommendService ActivityRecommendService;
	@Autowired
	IActivityShareService activityShareService;
	@Autowired
	IActivityVoucherNumService activityVoucherNumService;
	@Autowired
	IActivityCardService activityCardService;
	@Autowired
	IActivityCardDealsService activityCardDealsService;

	@Autowired
	private IActivitiesService activitiesService;
	@Autowired
	private IActivityLotteryLogService iallService;
	@Autowired
	ItemMapper itemmapper;
	@Autowired
	IItemService itemsv;
	@Autowired
	ActivityCardMapper activityCardMapper;
	@Autowired
	private IActivityGiftLogService activityGiftLogService;

	@Override
	public VActivityTheme selectActivityThemeWX() {
		List<VActivityTheme> list = activityThemeService
				.selectAllActivityThemeWX();
		if (list.size() > 0) {
			VActivityTheme bean = list.get(0);
			List<String> stringList = attachmentService.findForDownload(
					bean.getId(), AttachmentConstant.WX_ACTIVITY_THEME);
			if (stringList.size() > 0) {
				bean.setImagePath(stringList.get(0));
			}
			return bean;
		}
		return null;
	}

	@Override
	public VActivityProduct selectOneActivityProductDetail(int id,
			String phoneNo) {
		VActivityProduct bean = activityProductService
				.selectOneWXActivityProductDetail(id);
		if (bean.getTitle() == null) {
			bean.setTitle(bean.getItemTile());
		}
		if (bean != null) {
			List<String> stringList = attachmentService.findForDownload(
					bean.getId(), AttachmentConstant.ITEM_PICTURE_PATH);
			// 商品图片路径
			if (stringList.size() > 0) {
				bean.setImagePath(stringList.get(0));
			}
			// 判断是否已经报名
			ActivityJionSign joined = selectActivityJoinSignIsExits(
					bean.getActivityId(), phoneNo);
			bean.setIsExist(joined != null);
		}
		return bean;
	}

	@Override
	public Map<String, Object> signIn(ActivityJionSign ajs, String from,
			String fromPhone, Integer voucherId, Integer activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer result = activityJionSignService.signIn(ajs, from, fromPhone,
				voucherId, activityId);
		map.put("status", result);
		String msg = "报名成功";
		//result = 3; //TODO 删除此行代码
		if (result == 1) {
			msg = "已经注册";
		}else{
			this.sendMsm(ajs.getPhone(), "恭喜您成功报名9月5日举办的2015家居建材采购大会天津站(西青区知景道198号,社会山国际会议中心三层)。");
			this.sendMsm(ajs.getPhone(),"采购大会价格低于市场价20~40%!有疑问请拨打4001118108【家可可】");
		}
		if (result == 2) {
			 ActivityVoucher av=ActivityVoucherService.findById(voucherId);
			 this.sendMsm(ajs.getPhone(), "您已领取"+av.getName()+"现金券(使用细则以网站内容为准),9月5日举办的2015家居建材采购大会天津站(西青区知景道198号,社会山国际会议中心三层)");
			 this.sendMsm(ajs.getPhone(), "现场凭手机号到指定商家使用!有疑问请拨打4001118108【家可可】");
		}
//		if (result == 3) {
//			//map.put("msg", "报名成功,并已签到");
//		}
//		if (result == 4) {
//			//map.put("msg", "报名成功,并推送满10人");
//		}
//		if (result == 5) {
//			//map.put("msg", "报名成功,推送不满10人");
//		}
//		if (result == 6) {
//			//map.put("msg", "报名成功");
//		}
		map.put("msg", msg);
		return map;
	}

	@Override
	public Map<String, Object> signActivityTheme(String phone,Integer activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer result = activityJionSignService.signActivityTheme(phone,activityId);
		map.put("status", result);
		map.put("msg", "签到成功！");
		if (result == 0) {
			map.put("from", "qiandao");
		}
		if (result == 1) {
			map.put("msg", "您已签到,无需重复签到！");
		}
		if(result == 0 || result == 2){
			String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.MSG_SIGN_ACTIVITY);
			SendMsgUtil.send(phone, smsContent);
		}
		//判断用户是否是vip
		//List<ActivityCardDeals> cardDealsList = activityCardDealsService.check(phone);	
	    List<ActivityJionSign> vipList = activityJionSignService.checkVip(phone);
		map.put("isVip", 0);
		if(vipList.size()>0){
			map.put("isVip", 1);
		}
		return map;
	}

	// 查询没有报名的活动详情
	@Override
	public Map<String, Object> detial(int activiyid) {
		int id = 0;
		if (activiyid != 0) {
			id = activiyid;
		} else {
			ActivityTheme a = activityThemeService.getlatest();
			if (a != null)
				id = a.getId();
			else {

			}
		}
		// TODO Auto-generated method stub
		ActivityTheme a = activityThemeService.findById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> compmainimg = new ArrayList<String>();
		List<String> compotherimg = new ArrayList<String>();
		map.put("activityCompanyMainImgs", compmainimg);
		map.put("activityCompanyOtherImgs", compotherimg);
		map.put("activityImg", "");
		map.put("activity", a);
		map.put("activityid", id);
		map.put("item", itemsv.activityitems(map));
		map.put("voucher",
				ActivityVoucherService.findActivityVouchers(activiyid, 1, 200));
		List<ActivityCompany> ac = ActivityCompanysv.selectByProperty(
				new String[] { "activityId", "companyType" }, new Object[] {
						id, 0 });// 主办单位
		List<ActivityCompany> acc = ActivityCompanysv.selectByProperty(
				new String[] { "activityId", "companyType" }, new Object[] {
						id, 1 });// 承办单位
		if (ac.size() > 0) {
			for (int i = 0; i < ac.size(); i++) {
				if (ibaseinfsv.getHeadimg(ac.get(i).getCompanyId(),
						AttachmentConstant.SUPPLIER_COMPANY_TYPE) != "")
					compmainimg.add(ibaseinfsv.getHeadimg(ac.get(i)
							.getCompanyId(),
							AttachmentConstant.SUPPLIER_COMPANY_TYPE));
			}
		}
		if (acc.size() > 0) {
			for (int i = 0; i < acc.size(); i++) {
				if (ibaseinfsv.getHeadimg(acc.get(i).getCompanyId(),
						AttachmentConstant.SUPPLIER_COMPANY_TYPE) != "")
					compotherimg.add(ibaseinfsv.getHeadimg(ac.get(i)
							.getCompanyId(),
							AttachmentConstant.SUPPLIER_COMPANY_TYPE));
			}
		}
		List<String> ActivityImg = attachmentService.findForDownload(id,
				AttachmentConstant.WX_ACTIVITY_THEME);
		if (ActivityImg.size() > 0) {
			map.put("activityImg", ActivityImg.get(0));
		}
		return map;
	}

	// 已经报名的活动的详情
	@Override
	public Map<String, Object> detialJoined(int activiyid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>(
				this.detial(activiyid));
		List<ActivityCard> cd = activityCardService.selectByProperty(
				"activityId", activiyid, "activityId", true);
		if (cd.size() > 0) {
			map.put("activityCard", cd.get(0));
			List<ActivityGift> ag = activitygiftsv.selectByProperty("cardId",
					cd.get(0).getId(), "cardId", true);
			if (ag.size() > 0) {
				map.put("activityGift", ag.get(0));
			} else {
				map.put("activityGift", new ActivityGift());
			}
		} else {
			map.put("activityCard", "");
		}
		return map;
	}

	// 用户参与的活动
	@Override
	public List<VUserActivityList> UserActivityList(String phone, int rowStart,
			int limit) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("phone", phone);
		inMap.put("rowStart", rowStart);
		inMap.put("limit", limit);
		List<VUserActivityList> retmap = new ArrayList<VUserActivityList>(
				ActivityJoinSignMapper.qryall(inMap));
		if (retmap.size() > 0) {
			for (VUserActivityList activityJionSign : retmap) {
				activityJionSign.setActivityTheme(activityThemeService
						.findById(activityJionSign.getActivityId()));
				List<String> ActivityImg = attachmentService.findForDownload(
						activityJionSign.getActivityId(),
						AttachmentConstant.WX_ACTIVITY_THEME);
				if (ActivityImg.size() > 0) {
					activityJionSign.setActivityThemeImg(ActivityImg.get(0));
				}
			}
		}
		return retmap;
	}

	// 我的现金券
	@Override
	public List<VMyVouchers> findMyVouchers(String phone, int rowStart,
			int limit) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("phone", phone);
		inMap.put("rowStart", rowStart);
		inMap.put("limit", limit);
		List<VMyVouchers> retmap = new ArrayList<VMyVouchers>(ActivityVoucherNumMapper.qryall(inMap));
		if (retmap.size() > 0) {
			for (VMyVouchers mv : retmap) {
				ActivityVoucher vo = ActivityVoucherService.findById(mv.getVoucherId());
				if(vo != null){
					mv.setActivityVoucher(vo);
				}
				List<VAttachment> list = attachmentService.findAttachment(mv.getActivityVoucher().getId(), AttachmentConstant.WX_ACTIVITY_VOUCHER);
				if(list.size() > 0){
					mv.setPath(list.get(0).getDownloadPath());
				}else{
					mv.setPath("");
				}
			}
		}
		return retmap;
	}

	@Override
	public List<Awards> findAwards(int saleActivityThemeId) {
		return activitiesService.findAwards(saleActivityThemeId);
	}

	@Override
	public LotteryResult getLuckyAward(int saleActivityThemeId, String phone) {
		return activitiesService.getLuckyAward(saleActivityThemeId, phone);
	}

	@Override
	public List<VActivityLotteryLog> findLuckyPeopleAwards(Integer activityId, String phone, Boolean win, Boolean receive, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null || pageNo < 1) {
			pageNo = 1;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = 20;
		}
		return iallService.findLuckyPeopleAwards(activityId, phone, win, receive, pageNo, pageSize);
	}

	@Override
	public Boolean sendMsm(String phone, String content) {
		boolean flag = false;
		try {
			SendMsgUtil.send(phone, content);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ActivityJionSign selectActivityJoinSignIsExits(
			int saleActivityThemeId, String phone) {
		return activityJionSignService.selectActivityJoinSignIsExits(
				saleActivityThemeId, phone);
	}

	@Override
	public List<VActivityVoucher> findActivityVouchers(int saleActivityThemeId,
			int pageNo, int pageSize) {
		return ActivityVoucherService.findActivityVouchers(saleActivityThemeId,
				pageNo, pageSize);
	}

	// 我的活动礼包
	public List<VActivityGift> selectMyActivityGiftLogs(Map<String, Object> map) {
		List<VActivityGift> resultList = activitygiftsv
				.selectMyActivityGift(map);
		return resultList;
	}

	@Override
	public VActivityTheme getActivityThemeById(int id) {
		return activityThemeService.getActivityThemeById(id);
	}

	@Override
	public VActivityVoucher getActivityVoucherById(Integer id) {
		VActivityVoucher vav = ActivityVoucherService.getActivityVoucherById(id);
		if(vav != null){
			List<VAttachment> list = attachmentService.findAttachment(vav.getId(), AttachmentConstant.WX_ACTIVITY_VOUCHER);
			if(list.size() > 0){
				vav.setFilepath(list.get(0).getDownloadPath());
			}
		}
		return vav;
	}

	@Override
	@Transactional
	public ActivityGift getActicityGiftById(Integer id) {
		return activitygiftsv.findById(id);
	}

	// 保存推荐号码
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int recommend(Integer activityID, String fromPhone, String toPhone) {
		int ret = 0;
		if(activityID == null || StringUtils.isBlank(fromPhone) || StringUtils.isBlank(toPhone)) {
			return ret;//错误的数据
		}
		List<ActivityRecommend> ar = ActivityRecommendService.selectByProperty(
				new String[] { "activityId", "toPhone", "fromPhone" },
				new Object[] { activityID, toPhone, fromPhone });
		List<ActivityRecommend> arrecommend = ActivityRecommendService.selectByProperty(
				new String[] {"toPhone"},
				new Object[] { toPhone});
		if(arrecommend.size()>0){
			ret = 4;//别人推荐过了
			return ret;
		}else if(activityJionSignService.checkregister(toPhone)==true){
			ret = 3;//注册过了
			return ret;
		}else if (ar.size() > 0) {
			ret = 2;//自己推荐过了
			return ret;
		} else {
			ActivityRecommend Recommend = new ActivityRecommend();
			Recommend.setActivityId(activityID);
			Recommend.setFromPhone(fromPhone);
			Recommend.setToPhone(toPhone);
			Recommend.setStatus(0);
			Recommend.setCreateTime(new Date());
			ActivityRecommendService.save(Recommend);
			ret = 1;//成功
			return ret;
		}
	}

	@Override
	public Boolean share(int activityId, String phone, int type, String title,
			String url) {
		// TODO Auto-generated method stub
		ActivityTheme at = activityThemeService.findById(activityId);
		if (at != null) {
			ActivityShare entity = new ActivityShare();
			if (type == 1) {
				entity.setLotteryChance(at.getShareCardChance());
			} else if (type == 2) {
				entity.setLotteryChance(at.getShareLotteryChance());
			} else if (type == 3) {
				entity.setLotteryChance(at.getShareChance());
			} else { 
				return false;
			}
			entity.setActivityId(activityId);
			entity.setTitle(title);
			entity.setPhone(phone);
			entity.setType(type);
			entity.setUrl(url);
			entity.setCreateTime(new Date());
			entity.setUesedChance(0);
			activityShareService.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public Long recommendNum(String fromPhone) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if (fromPhone == null)
			return Long.valueOf("0");
		map.put("phone", fromPhone);
		return  ActivityJoinSignMapper.qryRecommenNum(map);
//		if (aj != null)
//			return aj.getId();
//		else {
//			return 0;
//		}
	}

	@Override
	public int getLotteryChance(int activityId, String phone) {
		return iallService.getLotteryChance(activityId, phone);
	}

	@Override
	public Map<String, Object> insertOneVoucher(String phone, int voucherId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//领券约束
		ActivityCardDeals acdBean=new ActivityCardDeals();
		acdBean.setPhone(phone);
		acdBean.setPayStatus(1);
		List<ActivityCardDeals> acdList=activityCardDealsService.select(acdBean);
		Integer rel =0;
		//会员领券
		if(acdList.size()>0){
			rel = activityVoucherNumService.insertOneVoucher(phone,voucherId);
		}
		//普通用户领券
		if(acdList.size()==0){
			ActivityVoucherNum avn=new ActivityVoucherNum();
			avn.setPhone(phone);
			List<ActivityVoucherNum> avnList=activityVoucherNumService.select(avn);
			if(avnList.size()>0){
				rel=2;
			}else{
				rel = activityVoucherNumService.insertOneVoucher(phone,voucherId);
			}
		}
		
		if (rel == 0) {
			map.put("rel", false);
			map.put("info", "已领取该现金券,无需重复领取");
		}else if(rel==2){
			map.put("rel", false);
			map.put("info", "亲，vip用户才能领取多张优惠券哦!");
		}else {
			map.put("rel", true);
			map.put("info", "领取成功");
			 ActivityVoucher av=ActivityVoucherService.findById(voucherId);
			 this.sendMsm(phone, "您已领取【"+av.getName()+"】现金券（使用细则以网站内容为准），9月5日举办的2015家居建材采购大会天津站(西青区知景道198号,社会山国际会议中心三层)");
		     this.sendMsm(phone, "现场可凭手机号到指定商家使用！有疑问请拨打4001118108【家可可】");
		} 
		return map;
	}

	@Transactional(readOnly=false)
	public Map<String, Object> tuijian(String phone) {
		return activityJionSignService.tuijian(phone);
	}

	@Override
	public List<ActivityCardDeals> checkCard(String phone) {
		return activityCardDealsService.check(phone);
	}

	@Override
	@Transactional(readOnly=false)
	public void saveCardAndGift(ActivityCardDeals ac, ActivityGiftLog ag) {
		activityCardDealsService.update(ac);
		ActivityGiftLog agl = new ActivityGiftLog();
		agl.setPhone(ag.getPhone());
		agl.setGiftId(ag.getGiftId());
		List<ActivityGiftLog> list = activityGiftLogService.select(agl);
		if(list == null || list.size() <= 0){
			ag.setUsed(false);
			ag.setUpdateTime(new Date());
			activityGiftLogService.save(ag);
		}
		ActivityCard card = activityCardService.findById(ac.getCardId());
		ActivityJionSign ajs = new ActivityJionSign();
		ajs.setPhone(ag.getPhone());
		ajs.setActivityId(card.getActivityId());
		List<ActivityJionSign> ajss =  activityJionSignService.select(ajs);
		//System.out.println("saveCardAndGift===ajss.size()=" + ajss.size());
		if(!ajss.isEmpty()){
			ActivityJionSign sign = ajss.get(0);
			sign.setVip(1);
			activityJionSignService.update(sign);
			
			String phone = ac.getPhone();
			ActivityRecommend recommend = new ActivityRecommend();
			recommend.setToPhone(phone);
			recommend.setActivityId(card.getActivityId());
			List<ActivityRecommend> recommends = ActivityRecommendService.select(recommend);
			//System.out.println("saveCardAndGift===recommends.size()=" + recommends.size());
			for(ActivityRecommend rd : recommends) {//所有推荐过此手机号的推荐记录
				rd.setStatus(1);
				rd.setSuccessTime(new Date());
				ActivityRecommendService.update(rd);
				String fromPhone = rd.getFromPhone();
				//System.out.println("saveCardAndGift===fromPhone=" + fromPhone);
				if(StringUtils.isNotBlank(fromPhone)) {
					ajs = new ActivityJionSign();
					ajs.setPhone(fromPhone);
					ajs.setActivityId(card.getActivityId());
					ajss =  activityJionSignService.select(ajs);
					//System.out.println("saveCardAndGift===ajss.isEmpty()=" + ajss.isEmpty());
					if(!ajss.isEmpty()) { //2.确认推荐人已经报名
						int count = 0;
						List<ActivityRecommend> records = ActivityRecommendService.selectByProperty(
								new String[]{"activityId","fromPhone"}, new Object[]{card.getActivityId(), fromPhone});
						//System.out.println("saveCardAndGift===records.isEmpty()=" + records.isEmpty());
						for(ActivityRecommend ar : records) {
							ActivityCardDeals tmp = new ActivityCardDeals();
							tmp.setPhone(ar.getToPhone());
							tmp.setPayStatus(1);
							boolean vip = !activityCardDealsService.select(tmp).isEmpty();
							if(vip) { //有成功购卡的记录
								count ++;
							}
						}
						//System.out.println("saveCardAndGift===count=" + count);
						if(count >= 2) {//有成功购卡的记录,且数量 大于2
							agl = new ActivityGiftLog();
							agl.setPhone(fromPhone);
							agl.setGiftId(ag.getGiftId());
							list = activityGiftLogService.select(agl);
							//System.out.println("saveCardAndGift===giftLog size=" + list.size());
							if(list.isEmpty()){ //3.查看推荐人是否已经有此VIP卡的礼包
								agl.setCreateTime(new Date());
								agl.setUsed(false);
								agl.setUpdateTime(new Date());
								activityGiftLogService.save(agl);
							}
							
							ActivityCardDeals acd = new ActivityCardDeals();
							acd.setCardId(card.getId());
							acd.setPhone(fromPhone);
							List<ActivityCardDeals> acdList = activityCardDealsService.select(acd);
							////System.out.println("saveCardAndGift===acdList size=" + acdList.size());
							if(acdList.isEmpty()) { 
								acd.setPayStatus(1);
								acd.setPaymenType(100);
								acd.setDecorateReq("推荐好友" + count + "人免费获得VIP");
								acd.setGiftId(ag.getGiftId());
								acd.setCreateTime(new Date());
								acd.setShared(false);
								activityCardDealsService.save(acd);
							}
							sign = ajss.get(0);
							sign.setVip(1); //赠送VIP身份
							String msgContennt = "恭喜您成功升级为2015家居建材采购大会天津站（南开区宾水西道天津体育馆【大馆】）VIP会员，9月5日活动当天，凭手机号现场领取VIP专属大礼包！";
							SendMsgUtil.send(ag.getPhone(), msgContennt);
							msgContennt = "关注微信：家可可装修服务平台  每天还可享3次抽奖机会！有疑问请拨打4001118108【家可可】";
							SendMsgUtil.send(ag.getPhone(), msgContennt);
							activityJionSignService.update(sign);
							//System.out.println("saveCardAndGift===update vip joinsign id=" + sign.getId());
						}
					}
				}
			}
		}
		
		
	}

	@Transactional(readOnly=true)
	public ActivityJionSign checkLoginStatus(String phone) {
		ActivityJionSign ajs = new ActivityJionSign();
		ajs.setPhone(phone);
		List<ActivityJionSign> list = activityJionSignService.select(ajs);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return new ActivityJionSign();
	}

	@Override
	public Integer getLastAdminId() {
		return ActivityJoinSignMapper.getLastAdminId();
	}
}
