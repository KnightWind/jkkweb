package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.view.VActivityGift;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;
import com.jkkp.modules.sale_theme.view.VActivityProduct;
import com.jkkp.modules.sale_theme.view.VActivityTheme;
import com.jkkp.modules.sale_theme.view.VActivityVoucher;
import com.jkkp.modules.sale_theme.view.VMyVouchers;
import com.jkkp.modules.sale_theme.view.VUserActivityList;
import com.jkkp.modules.sale_theme.vo.LotteryResult;

public interface ISaleActivityService {
	// 引流主题列表 只支持一个活动 如果需要支持多个 返回list即可
	public VActivityTheme selectActivityThemeWX();
	
	//根据id获取活动主题对象
	public VActivityTheme getActivityThemeById(int id);

	/**用户是否已经报名此活动*/
	public ActivityJionSign selectActivityJoinSignIsExits(int saleActivityThemeId, String phone);
	
	//用户签到

	public VActivityProduct selectOneActivityProductDetail(int id,String phoneNo);

	// 用户报名
	public Map<String, Object> signIn(ActivityJionSign ajs, String from,String fromPhone, Integer voucherId, Integer activityId);

	// 用户签到
	public Map<String, Object> signActivityTheme(String phone, Integer activityId);

	// 活动详情
	public Map<String, Object> detial(int activiyid);

	// 活动详情已经报名
	public Map<String, Object> detialJoined(int activiyid);

	// 用户参与的活动
	public List<VUserActivityList> UserActivityList(String phone, int rowStart,int limit);

	// 用户参与的活动
	public List<VMyVouchers> findMyVouchers(String phone, int rowStart,int limit);
	
	//我的活动礼包
	public List<VActivityGift> selectMyActivityGiftLogs(Map<String, Object> map);

	
	/**查找所有活动的所有现金券*/
	List<VActivityVoucher> findActivityVouchers(int saleActivityThemeId, int pageNo, int pageSize);
	
	/**
	 * @param saleActivityThemeId 销售主动活动
	 * @return 奖项列表
	 */
	List<Awards> findAwards(int saleActivityThemeId);
	
	/**
	 * @param saleActivityThemeId 销售主动活动
	 * @param phone 手机号码
	 * @return 用户的抽奖结果
	 */
	LotteryResult getLuckyAward(int saleActivityThemeId, String phone);
	
	/**获取剩余抽奖次数*/
	int getLotteryChance(int activityId, String phone);
	
	/**
	 * 
	 * @param activityId 销售活动ID 引用ActivityThmeme表
	 * @param phone 手机号码
	 * @param win 是否中奖
	 * @param receive 是否已经领奖，为空时返回全部中奖人的所有奖品
	 * @param pageNo
	 * @param pageSize 
	 * @return 返回中奖人的奖品列表
	 */
	List<VActivityLotteryLog> findLuckyPeopleAwards(Integer saleActivityThemeId,String phone, Boolean win, Boolean receive, Integer pageNo, Integer pageSize);
	
	/**发送短信接口,返回是否已经成功调用短信接口*/
	public Boolean sendMsm(String phone, String content);

	/**
	 * 获取现金券详情
	 * @param cardId
	 * @return
	 */
	VActivityVoucher getActivityVoucherById(Integer id);

	/**
	 * 根据id获取礼包
	 * @param id
	 * @return
	 */
	ActivityGift getActicityGiftById(Integer id);
	//保存推荐号码
	int recommend(Integer activityID,String fromPhone, String toPhone);
	//用户推荐成功次数
	Long recommendNum(String fromPhone);
	//保存分享记录
	Boolean share(int activityId,String phone,int type,String title, String url);
	// 领取现金券
	public Map<String, Object> insertOneVoucher(String phone, int voucherId);

	//购买卡后检查是否购卡手机号是否被推荐
	public Map<String, Object> tuijian(String phone);
	//判断用户是否为会员
	public List<ActivityCardDeals> checkCard(String phone);

	public void saveCardAndGift(ActivityCardDeals ac, ActivityGiftLog ag);

	public ActivityJionSign checkLoginStatus(String phone);

	//获取报名表最后一条记录的adminId
	public Integer getLastAdminId();
}
