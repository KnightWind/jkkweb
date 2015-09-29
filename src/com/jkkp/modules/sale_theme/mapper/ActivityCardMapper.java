package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.modules.sale_theme.view.VActivityCard;

public interface ActivityCardMapper extends Mapper<ActivityCard> {

	public List<VActivityCard> selectActivityCard(Map<String, Object> param);
	public long selectActivityCardCount(Map<String, Object> param);
	public VActivityCard selectActivityCardById(Integer id);
	
}
