package com.jkkp.modules.sale_theme.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityLotteryLog;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;

public interface IActivityLotteryLogService extends IService<ActivityLotteryLog, VActivityLotteryLog, Integer> {
	/**
	 * 
	 * @param activityId 销售活动ID 引用ActivityThmeme表
	 * @param receive 是否已经领奖，为空时返回全部中奖人的所有奖品
	 * @param pageNo
	 * @param pageSize 
	 * @return 返回中奖人的奖品列表
	 */
	List<VActivityLotteryLog> findLuckyPeopleAwards(Integer activityId, String phone, Boolean win, Boolean receive, int pageNo, int pageSize);
	
	/**获取剩余抽奖次数*/
	int getLotteryChance(int activityId, String phone);
}
