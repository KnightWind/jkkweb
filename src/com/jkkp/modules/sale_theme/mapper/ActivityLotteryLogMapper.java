package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityLotteryLog;
import com.jkkp.modules.sale_theme.view.VActivityLotteryLog;

public interface ActivityLotteryLogMapper extends Mapper<ActivityLotteryLog> {
	
	/**查找最新的limit条记录*/
	public List<ActivityLotteryLog> findLastLogs(Integer limit);

	public List<ActivityLotteryLog> findLotteryLogs(Map<String, Object> queryParams);

	public List<VActivityLotteryLog> findLuckyPeopleAwards(Map<String, Object> queryParams);
	
	//web后台管理
	public List<ActivityLotteryLog> selectAllActivityLotteryLog(Map<String, Object> map);
	
	public Long selectAllActivityLotteryLogCount(Map<String, Object> map);
}
