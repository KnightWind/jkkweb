package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.ActivityBanner;
import com.jkkp.modules.crowdfunding.model.ItemCategoryInfo;
import com.jkkp.modules.crowdfunding.view.VItemCategoryInfo;

public interface ItemCategoryInfoMapper extends Mapper<ItemCategoryInfo> {
     
	public List<ActivityBanner> getBannerByNum(Integer num);
	
	public List<VItemCategoryInfo>selectAllItemCategoryItems();
	
	public void deleteOneZCItemCategory(@Param("id") int id);
	
}
