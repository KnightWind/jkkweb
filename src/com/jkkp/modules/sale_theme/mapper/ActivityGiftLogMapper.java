package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityGiftLog;
import com.jkkp.modules.sale_theme.view.VActivityGiftLog;

public interface ActivityGiftLogMapper extends Mapper<ActivityGiftLog> {
	List<VActivityGiftLog> takeGift(Map<String,Object> map);
	long takeGiftCount(Map<String,Object> map);
}
