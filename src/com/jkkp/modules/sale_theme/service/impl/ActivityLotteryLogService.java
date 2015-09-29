package com.jkkp.modules.sale_theme.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityLotteryLogMapper;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityLotteryLog;
import com.jkkp.modules.sale_theme.model.ActivityShare;
import com.jkkp.modules.sale_theme.service.IActivityLotteryLogService;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;
import com.jkkp.utils.DateUtil;

@Service
public class ActivityLotteryLogService extends ServiceSupport<ActivityLotteryLog, VActivityLotteryLog, Integer>
		implements IActivityLotteryLogService {
	@Autowired
	private ActivityLotteryLogMapper allMapper;
	@Autowired
	private ActivityCardDealsService acdService;
	@Autowired
	private ActivityShareService asService;

	@Override
	protected Mapper<ActivityLotteryLog> getMapper() {
		return allMapper;
	}

	public List<ActivityLotteryLog> findLastLogs(Integer limit) {
		return allMapper.findLastLogs(limit);
	}

	/***
	 * 
	 * @param activityId
	 *            活动ID
	 * @param phone
	 *            手机号码
	 * @param date
	 *            日期
	 * @return 返回date日期当天的抽奖日次数
	 */
	public List<ActivityLotteryLog> findLotteryLogs(int activityId, String phone, Date beginTime, Date endTime) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("activityId", activityId);
		queryParams.put("phone", phone);
		
		beginTime = DateUtil.parse(DateUtil.format(beginTime, "yyyy-MM-dd 00:00:00"));
		endTime = DateUtil.parse(DateUtil.format(endTime, "yyyy-MM-dd 23:59:59"));
		
		queryParams.put("beginTime", beginTime);
		queryParams.put("endTime", endTime);
		return allMapper.findLotteryLogs(queryParams);
	}

	/***
	 * 
	 * @param activityId 销售活动主题ID
	 * @param phone 手机号码
	 * @return 抽奖机会次数
	 */
	@Override
	public int getLotteryChance(int activityId, String phone) {
		ActivityCardDeals acd = new ActivityCardDeals();
		acd.setPhone(phone);
		acd.setPayStatus(1);
		List<ActivityCardDeals> deals = acdService.select(acd);
		if (deals != null && !deals.isEmpty()) {
			acd = deals.get(0);
			Date now = new Date();
			Date begin = DateUtil.parse(DateUtil.getDateTime("yyyy-MM-dd 00:00:00", now));
			if(acd.getTodayChance() == null || acd.getLotteryTime() == null || acd.getLotteryTime().before(begin)) {
				acd.setLotteryTime(now);
				acd.setTodayChance(3); //每天赠送3次机会
				acdService.update(acd);
			}
		}
		Integer chance = acd.getTodayChance();
		if(chance == null)
			chance = 0;
		ActivityShare as = new ActivityShare();
		as.setPhone(phone);
		as.setActivityId(activityId);
		List<ActivityShare> asList = asService.select(as);
		if (asList != null && !asList.isEmpty()) {
			for (ActivityShare a : asList) {
				if(a.getLotteryChance() != null && a.getLotteryChance() > 0) {
					chance += a.getLotteryChance();
				}
				
				if(a.getUesedChance() != null && a.getUesedChance() > 0) {
					chance -= a.getUesedChance();
				}
			}
		}
		if(chance < 0)
			chance = 0;
		return chance;
	}

	@Override
	public List<VActivityLotteryLog> findLuckyPeopleAwards(Integer activityId, String phone, Boolean win, Boolean receive, int pageNo, int pageSize) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("activityId", activityId);
		queryParams.put("phone", phone);
		queryParams.put("win", win);
		queryParams.put("receive", receive);
		queryParams.put("pageNo", pageNo);
		queryParams.put("pageSize", pageSize);
		return allMapper.findLuckyPeopleAwards(queryParams);
	}
	
}
