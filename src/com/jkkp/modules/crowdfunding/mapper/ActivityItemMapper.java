package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.ActivityItem;
import com.jkkp.modules.crowdfunding.model.ResultItemList;
import com.jkkp.modules.crowdfunding.view.VZCItem;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.supplier.model.Supplier;

public interface ActivityItemMapper extends Mapper<ActivityItem> {
   public List<ResultItemList> findCrowdItemList(Map<String,Object> map);
   
   public ActivityItem getCrowdItemDetail(Map<String,Object> map);
   
   public List<String> getImagesByItemId(int itemId);
   
   public Item getItemInfo(Integer itemId);
   
   public ActivityItem newCrowdOrder(Map<String,Object> map);
   
   public Supplier getSupplierById(Integer spId);
   
   //web后台
   public List<VZCItem> selectAllZCItemInfo(Map<String, Object> map);
   
   public Long selectAllZCItemInfoCount(Map<String, Object> map);
   
   public void updateOneItemFlag(@Param("id") int id);
   
   public void updateOneItemFlagFalse(@Param("id") Long id);
   
   public List<VZCItem> selectAllHomeZCItem();
   
   public Long selectItemId(@Param("id") Long id);
    //------------web后台------------------
}
