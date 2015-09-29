package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.crowdfunding.view.VCrowdItem;

public interface CrowdItemMapper extends Mapper<CrowdItem> {

	public List<VCrowdItem> findPage(Map<String,Object> param);
	public long countPage(Map<String,Object> param);
	public VCrowdItem zcItemInfo(@Param("id")Integer id);
	public CrowdItem findByItemId(@Param("itemId")Integer itemId);
	
	//取消众筹商品审核列表
	List<VCrowdItem> cancelCrowdItemList(Map<String, Object> params);
	long cancelCrowdItemListCount(Map<String, Object> params);
	
}
