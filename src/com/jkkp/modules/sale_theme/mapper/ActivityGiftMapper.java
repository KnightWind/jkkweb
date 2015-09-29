package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.modules.sale_theme.view.VActivityGift;

public interface ActivityGiftMapper extends Mapper<ActivityGift> {
	public List<VActivityGift> selectActivityGift(Map<String, Object> param);

	public long selectActivityGiftCount(Map<String, Object> param);

	// 微信引流活动--我的活动
	public List<VActivityGift> selectMyActivityGift(Map<String, Object> map);
}
