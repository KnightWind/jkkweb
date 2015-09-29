package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.view.VPreced;
import com.jkkp.modules.sale_theme.model.ActivityCardDeals;
import com.jkkp.modules.sale_theme.view.Statistics;
import com.jkkp.modules.sale_theme.view.VActivityCardDeals;

public interface ActivityCardDealsMapper extends Mapper<ActivityCardDeals> {

	VActivityCardDeals findActivityCardDealsById(@Param("businessId")Integer businessId, @Param("fileType")int fileType);
	
	//web后台
	public List<VActivityCardDeals> selectAllActivityCardDeals(Map<String, Object> map);
	
	public Long selectAllActivityCardDealsCount(Map<String, Object> map);

	long selectCountWX(@Param("phone")String phone, @Param("cardId")Integer cardId, @Param("status")Integer status);

	Integer getCountByPhone(@Param("phone")String phone);
	
	//建材后台
	public List<VActivityCardDeals> findPage(Map<String, Object> map);
	public Long countPage(Map<String, Object> map);
	public List<Statistics> caclSaleInfo(Map<String, Object> map);
	List<VPreced> getPrecedList();
	String randomLottery(Map<String, Object> paramMap);
	
}
