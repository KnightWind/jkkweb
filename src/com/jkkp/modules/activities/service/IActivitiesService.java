package com.jkkp.modules.activities.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.activities.model.Activities;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.view.VActivities;
import com.jkkp.modules.sale_theme.vo.LotteryResult;

public interface IActivitiesService extends IService<Activities, VActivities, Integer> {

	void saveOrUpdate(Awards awards);
	
	void saveOrUpdate(Activities act);

	VActivities findByActId(Integer id);
	
	/**
	 * @param saleActivityThemeId 销售主动活动
	 * @return 活动的奖项列表
	 */
	List<Awards> findAwards(int saleActivityThemeId);
	
	/**
	 * @param saleActivityThemeId 销售主动活动
	 * @param phone 手机号码
	 * @return 用户的抽奖结果
	 */
	LotteryResult getLuckyAward(int saleActivityThemeId, String phone);
	
	/**
	 * @param saleActivityThemeId 销售主动活动
	 * @return 中奖的用户列表
	 */
	void findLukyPeople(int saleActivityThemeId, int pageNo, int pageSize);
}
