package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.view.VPhone;
import com.jkkp.modules.sale_theme.view.VDaoChu;
import com.jkkp.modules.sale_theme.view.VRecommend;
import com.jkkp.modules.sale_theme.view.VUserActivityList;

public interface ActivityJoinSignMapper extends Mapper<ActivityJionSign> {
	List<VUserActivityList> qryall(Map<String, Object> map);

	Long qryRecommenNum(Map<String, Object> map);// phone

	// web后台
	public List<ActivityJionSign> selectAllActivityJoinSign(Map<String, Object> map);

	public Long selectAllActivityJoinSignCount(Map<String, Object> map);
    public List<VDaoChu> querydaochu(Map<String, Object> map);
    public List<VRecommend> queryRecommend(Map<String, Object> map);
	Integer getLastAdminId();

	List<VPhone> selectPhone(Map<String, Object> map);
}
