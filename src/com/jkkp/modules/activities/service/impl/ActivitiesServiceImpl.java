package com.jkkp.modules.activities.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.activities.mapper.ActivitiesMapper;
import com.jkkp.modules.activities.model.Activities;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.service.IActivitiesService;
import com.jkkp.modules.activities.service.IAwardsService;
import com.jkkp.modules.activities.view.VActivities;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.model.ActivityLotteryLog;
import com.jkkp.modules.sale_theme.model.ActivityShare;
import com.jkkp.modules.sale_theme.model.ActivityTheme;
import com.jkkp.modules.sale_theme.service.IActivityShareService;
import com.jkkp.modules.sale_theme.service.IActivityThemeService;
import com.jkkp.modules.sale_theme.service.impl.ActivityCardDealsService;
import com.jkkp.modules.sale_theme.service.impl.ActivityLotteryLogService;
import com.jkkp.modules.sale_theme.vo.LotteryResult;
import com.jkkp.utils.CheckedUtil;

@Service("activitiesService")
public class ActivitiesServiceImpl extends ServiceSupport<Activities, VActivities, Integer> implements IActivitiesService {
	private static final int minProbability = 10000000; //支持的最小中奖概率,即可配置1/10000000之一的中奖概率
	
	@Autowired
	private ActivitiesMapper activitiesMapper;
	@Autowired
	private IAwardsService awardsService;
	@Autowired
	private IActivityThemeService actThemeService;
	@Autowired
	private ActivityLotteryLogService allService;
	@Autowired
	private IActivityShareService shareService;
	@Autowired
	private ActivityCardDealsService acdService;
	
	
	@Override
	protected Mapper<Activities> getMapper() {
		return activitiesMapper;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void saveOrUpdate(Activities act) {
		
		if(act != null){
			//设置抽奖类型
			Integer type = act.getLotteryType();
			if(type == null || (type != 1 && type != 2)) {
				type = 2;
			}
			if(type == 2) {//抽奖方式：概率抽奖,每次中奖概率一致
				act.setTimes(null);
				act.setLuckyTimes(null);
			}
			act.setLotteryType(type);
			
			if(CheckedUtil.isNotEmpty(act.getId())){
				Activities old = this.findById(act.getId());
				act.setCreateTime(old.getCreateTime());
				this.update(act);
			}else{
				act.setCreateTime(new Date());
				this.save(act);
			}
			
		}
		
	}

	@Override
	public VActivities findByActId(Integer id) {
		return activitiesMapper.findByActId(id);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void saveOrUpdate(Awards awards) {
		if(awards != null){
			if(CheckedUtil.isNotEmpty(awards.getId())){
				Awards old = awardsService.findById(awards.getId());
				awards.setCreatTime(old.getCreatTime());
				awards.setActiveId(old.getActiveId());
				awardsService.update(awards);
			}else{
				awards.setCreatTime(new Date());
				awardsService.save(awards);
			}
			
		}
	}
	
	@Override
	public List<Awards> findAwards(int saleActivityThemeId) {
		ActivityTheme actTheme = actThemeService.findById(saleActivityThemeId);
		if(actTheme != null && actTheme.isEnable()) { //销售活动是否正在进行中
			Activities act = getMapper().selectByPrimaryKey(actTheme.getLotteryActivitiesId());
			if(act != null && act.isEnable()) { //抽奖活动是否正在进行中
				Awards ad = new Awards();
				ad.setActiveId(act.getId());
				return awardsService.select(ad); //抽奖活动是否设置有奖项
			}
		}
		return null;
	}

	@Override
	public LotteryResult getLuckyAward(int saleActivityThemeId, String phone) {
		LotteryResult result = new LotteryResult();
		if(StringUtils.isBlank(phone)) {
			result.setError("请先登录后再抽奖");
			return result;
		}
		ActivityTheme actTheme = actThemeService.findById(saleActivityThemeId);
		if(actTheme != null && actTheme.isEnable()) { //销售活动是否正在进行中
			Activities act = getMapper().selectByPrimaryKey(actTheme.getLotteryActivitiesId());
			if(act != null && act.isEnable()) { //抽奖活动是否正在进行中
				int lotteryChance = allService.getLotteryChance(saleActivityThemeId, phone);
				if(lotteryChance <=0) { //是否有抽奖机会
					result.setError("您今天已经没有抽奖机会啦");
					return result;
				}
				Awards ad = new Awards();
				ad.setActiveId(act.getId());
				List<Awards> awardList = awardsService.select(ad); //抽奖活动是否设置有奖项
				if(!hasMoreAwardsItem(awardList)) { //没有更多奖项
					result.setError("明天再试试吧");
					return result;
				}
				int type = act.getLotteryType(); //中奖方式
				if(type == 1) { //抽奖类型为：每轮抽奖必定有部份人中奖
					mustLuckyAwards(saleActivityThemeId, phone, act.getTimes(), act.getLuckyTimes(), awardList,result);
				} else if(type == 2) {//抽奖类型为：基于概率
					ActivityLotteryLog all = new ActivityLotteryLog();
					all.setActivityId(saleActivityThemeId);
					all.setPhone(phone);
					
					int randomIndex = (int)(awardList.size() * Math.random());
					ad = awardList.get(randomIndex);
					if(ad.getProbability() * minProbability >= Math.random() * 100 * minProbability) {
						result.setAwards(ad);
						all.setAwardId(ad.getId());
						all.setAwardContent(ad.getRank() + "等奖 " + ad.getPrizeName());
						all.setWin(true);
					} else {
						all.setWin(false);
					}
					all.setCreateTime(new Date());
					allService.save(all);
					
					updateLotteryChance(saleActivityThemeId, phone);
					return result;
				}
			}
		} else if(actTheme == null || actTheme.getStatus() != 0){
			result.setStatus(false);
			result.setError("活动已结束,抽奖无效");
		} else if(!actTheme.isEnable()){
			result.setStatus(false);
			if(actTheme.getStatus() == 0) {
				Date now = new Date();
				if(actTheme.getBeginTime().after(now)) {
					result.setError("活动未开始,抽奖无效");
				}
				if(actTheme.getEndTime().before(now)) {
					result.setError("活动已结束,抽奖无效");					
				}
			}
		}
		return result;
	}
	
	/***
	 * 
	 * @param awardList
	 * @return 是否仍有可用奖项
	 */
	private boolean hasMoreAwardsItem(List<Awards> awardList) {
		if(awardList == null || awardList.isEmpty())
			return false;
		Iterator<Awards> iter = awardList.iterator();
		while(iter.hasNext()) {
			Awards ads = iter.next();
			if(ads.getProbability() <=0) { //中奖概率<=0
				iter.remove();
				continue;
			}
			if(ads.getPrizeNum() <= 0) {//奖品数量<=0
				iter.remove();
				continue;
			}
			ActivityLotteryLog lotteryLog = new ActivityLotteryLog();
			lotteryLog.setAwardId(ads.getId());
			int luckyCount = allService.selectCount(lotteryLog);
			if(luckyCount >= ads.getPrizeNum()) { //中奖人数已经>=奖品数量 
				iter.remove();
				continue;
			}
		}
		return !awardList.isEmpty();
	}
	
	/***
	 * 必然中奖方式算法实现：如：设置每轮抽奖次数：50次,50次必然有10人次中奖
	 */
	private void mustLuckyAwards(int activityId, String phone, int times, int luckyTimes, List<Awards> awardList,LotteryResult result) {
		if(awardList == null || awardList.isEmpty()) {
			result.setError("来晚了");
			return;
		}
		ActivityLotteryLog all = new ActivityLotteryLog();
		all.setActivityId(activityId);
		int count = allService.selectCount(all);
		
		int leftTotalTimes = 0; //本轮剩余的抽奖次数
		if(count > times) {
			leftTotalTimes = times - count % times;
		} else {
			leftTotalTimes = times - count;
		}
		
		int limit = times - leftTotalTimes;
		int winedNum = 0; //统计本轮已经中奖数量
		if(limit > 0) {
			List<ActivityLotteryLog> lastLogs = allService.findLastLogs(limit);
			for(ActivityLotteryLog lastLog : lastLogs) {
				if(lastLog.getWin() != null && lastLog.getWin()) {
					winedNum ++;
				}
			}
		}
		
		int leftLuckyTimes = luckyTimes - winedNum; //本轮剩余的中奖人次
		if(leftLuckyTimes > 0) { //本轮仍有中奖机会
			int random = (int)(leftTotalTimes * Math.random());
			if(random <= leftLuckyTimes) { //中奖, leftTotalTimes分之一中奖的概率
				//实现100%中奖逻辑
				Iterator<Awards> iter = awardList.iterator();
				float start = 0;
				int i = 0;
				List<Tmp> list = new ArrayList<Tmp>();
				while(iter.hasNext()) {
					Awards ads = iter.next();
					Tmp tmp = new Tmp();
					if(i == 0) {
						start = 0;
					}
					tmp.award = ads;
					tmp.start = start; //奖项中奖概率开始
					tmp.end = (tmp.start + ads.getProbability()); //奖项中奖概率结束
					start = tmp.end;
					list.add(tmp);
					i++;
				}
				
				float rand = (float)(start * Math.random());
				for(Tmp tmp : list) {
					if(tmp.start <= rand && rand <= tmp.end) { //判断是否在该奖项的中奖概率范围
						result.setAwards(tmp.award);//返回中奖奖项
						break;
					}
				}
				if(result.getAwards() == null) {
					result.setAwards(awardList.get(0));//如果上面未命中，则默认返回第一个奖项					
				}
				//实现100%中奖逻辑
				Awards ads = result.getAwards();
				all.setAwardId(ads.getId());
				all.setAwardContent(ads.getRank() + "等奖 " + ads.getPrizeName());
				all.setWin(true);
				all.setReceive(false);
			} else {
				result.setError("再来一次试试");
				all.setWin(false);
			}
		} else {
			result.setError("再来一次试试");
			all.setWin(false);
		}
		all.setPhone(phone);
		all.setCreateTime(new Date());
		allService.save(all);
		
		updateLotteryChance(activityId, phone);
	}

	private void updateLotteryChance(int activityId, String phone) {
		boolean flag = false;
		ActivityCardDeals acd = new ActivityCardDeals();
		acd.setPhone(phone);
		acd.setPayStatus(1);
		List<ActivityCardDeals> deals = acdService.select(acd);
		if (deals != null && !deals.isEmpty()) {
			acd = deals.get(0);
			Date now = new Date();
			if(acd.getTodayChance() != null && acd.getTodayChance() > 0) {
				acd.setTodayChance(acd.getTodayChance() - 1); //1.先把今天可用的抽奖次数使用
				acd.setLotteryTime(now);
				acdService.update(acd);
				flag = true;
			}
		}
		
		if(!flag) { //2.再使用分享获得的奖励抽奖次数
			List<ActivityShare> list = shareService.findHasLotteryChanceRecords(activityId, phone);
			if(list != null && !list.isEmpty()) {
				ActivityShare as = list.get(0);
				as.setUesedChance(as.getUesedChance() + 1); //把分享记录中已经使用的抽奖次数+1
				shareService.update(as);
			}
		}
	}
	
	class Tmp {
		float start;
		float end;
		Awards award;
	}

	@Override
	public void findLukyPeople(int saleActivityThemeId, int pageNo, int pageSize) {
		
	}

}
