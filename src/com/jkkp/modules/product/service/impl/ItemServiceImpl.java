package com.jkkp.modules.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.crowdfunding.model.CrowdActitvity;
import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.modules.crowdfunding.service.ICrowdActitvityService;
import com.jkkp.modules.crowdfunding.service.ICrowdItemService;
import com.jkkp.modules.product.mapper.ItemMapper;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.product.view.VItem;
import com.jkkp.modules.product.view.VItemHot;
import com.jkkp.modules.system.model.Admin;

/**
 * 梁怡立
 * 
 * @author Administrator 2015-5-8
 */
@Service("itemService")
public class ItemServiceImpl extends ServiceSupport<Item, VItem, Integer>implements IItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	IAttachmentService attachmentService;

	@Autowired
	private ICrowdItemService crowdItemService;
	@Autowired
	private ICrowdActitvityService crowdActitvityService;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Item item) {
		if (item.getId() != null && item.getId() > 0) {

			this.update(item);
		} else {
			this.save(item);
		}
	}

	@Override
	protected Mapper<Item> getMapper() {
		return itemMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void remove(Integer id) {
		this.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Item operate(Integer id, boolean isOpen) {
		Item item = this.findById(id);
		if (isOpen) {
			item.setIsLock(0);
			item.setUpdateTime(new Date());
		} else {
			item.setIsLock(1);
			item.setUpdateTime(new Date());
		}
		this.update(item);
		return item;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Item oper(Integer id, boolean isOpen) {
		Item item = this.findById(id);
		if (isOpen) {
			item.setIsFree(0);
			item.setUpdateTime(new Date());
		} else {
			item.setIsFree(1);
			item.setUpdateTime(new Date());
		}
		this.update(item);
		return item;
	}

	@Override
	public List<Item> name(String name) {
		return itemMapper.name(name);
	}

	@Override
	public List<Item> fin(String name) {
		return itemMapper.fin(name);
	}

	@Override
	public List<VItemHot> activityitems(Map<String, Object> map) {
		List<VItemHot> hotitem = new ArrayList<VItemHot>(itemMapper.activityitem(map));
		if (hotitem.size() > 0) {
			for (VItemHot vItemHot : hotitem) {
				Double price = vItemHot.getPrice();
				if (price == null)
					price = 0d;
				Double ptprice = vItemHot.getPromotionalprice();
				if (ptprice == null) {
					ptprice = 0d;
				}
				if (price > ptprice) {
					// 设置节省价钱
					vItemHot.setSaveprice(vItemHot.getPrice() - vItemHot.getPromotionalprice());
				} else {
					vItemHot.setSaveprice((double) 0);
				}
				List<String> Img = attachmentService.findForDownload(vItemHot.getId(),
						AttachmentConstant.ITEM_PICTURE_PATH);
				if (Img.size() > 0) {
					vItemHot.setItemimg(Img.get(0));
				} else {
					vItemHot.setItemimg("");
				}
			}

		}
		return hotitem;
	}

	@Override
	public Item selectById(Integer id) {
		return itemMapper.selectById(id);
	}

	//setStatus() -1未通过审核，0待审核，1审核通过，2已取消
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(CrowdActitvity activity, CrowdItem item, Integer itemId) {
		if (activity != null && item != null && itemId != null) {
			item.setStatus(0);
			if(item.getPrivilege() == null){
				item.setPrivilege(1D);
			}
			if (item.getRefund() == 0) {
				item.setProportion(null);
			}
			if (item.getId() != null) {
				crowdItemService.updateByPrimaryKeySelective(item);
				activity.setId(item.getActivityId());
				crowdActitvityService.updateByPrimaryKeySelective(activity);
			} else {
				crowdActitvityService.save(activity);
				if (itemId != null) {
					item.setItemId(itemId);
					item.setCreateDate(new Date());
					item.setFlag(true);
					item.setActivityId(activity.getId());
					crowdItemService.save(item);
				}
			}
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateItme(Item item, Integer[] imgId, MultipartRequest request) {
		// 删除更换后的图片记录跟 imgId[] 更换图片后对应附件表的id集合
		if (imgId != null) {
			for (Integer attId : imgId) {
				if (attId != null) {
					Attachment at = attachmentService.findById(attId);
					if (at != null) {
						attachmentService.delete(at);
					}
				}
			}
		}
		saveAttachment(request, item);
		this.updateByPrimaryKeySelective(item);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveItem(Item item, Integer[] imgId, MultipartRequest request) {
		this.save(item);
		saveAttachment(request, item);
	}

	public void saveAttachment(MultipartRequest request, Item item) {
		List<Attachment> atts = attachmentService.uploadMulti(request);
		for (Attachment att : atts) {
			Admin admin = new Admin();
			attachmentService.saveAttachment(att, admin, item.getId(), AttachmentConstant.ITEM_PICTURE_PATH);
		}
	}

}