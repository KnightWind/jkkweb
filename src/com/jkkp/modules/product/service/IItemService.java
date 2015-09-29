package com.jkkp.modules.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.CrowdActitvity;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.view.VItem;
import com.jkkp.modules.product.view.VItemHot;
/**
 * 梁怡立
 * 
 * @author Administrator 2015-05-08
 */
public interface IItemService extends IService<Item,VItem,Integer>{
	public void saveOrUpdate(Item item);
	public void remove(Integer id);
	List<Item> name(String name);
	public Item operate(Integer id, boolean isOpen);
	public Item oper(Integer id, boolean isOpen);
	List<Item> fin(String name);
	List<VItemHot> activityitems(Map<String, Object> map);
	public Item selectById(Integer id);
	
	//建材众筹
	public void save(CrowdActitvity activity, CrowdItem item, Integer itemId);
	public void updateItme(Item item, Integer[] imgId, MultipartRequest request);
	public void saveItem(Item item, Integer[] imgId, MultipartRequest request);
}
