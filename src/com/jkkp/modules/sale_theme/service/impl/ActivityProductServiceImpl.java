package com.jkkp.modules.sale_theme.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.service.IItemService;
import com.jkkp.modules.sale_theme.mapper.ActivityProductMapper;
import com.jkkp.modules.sale_theme.model.ActivityProduct;
import com.jkkp.modules.sale_theme.service.IActivityProductService;
import com.jkkp.modules.sale_theme.view.VActivityProduct;
import com.jkkp.modules.system.model.Admin;

@Service("activityProductService")
public class ActivityProductServiceImpl extends ServiceSupport<ActivityProduct, VActivityProduct, Integer>
		implements IActivityProductService {

	@Autowired
	private ActivityProductMapper activityProductMapper;
	@Autowired
	private IItemService itemService;
	@Autowired
	private IAttachmentService attachmentService;

	@Override
	public VActivityProduct selectOneWXActivityProductDetail(int id) {
		return activityProductMapper.selectOneWXActivityProductDetail(id);
	}

	@Override
	protected Mapper<ActivityProduct> getMapper() {
		return activityProductMapper;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void save(Integer id,Integer aid, String name, Double iprice, Double price,HttpServletRequest request,Integer order) {
		if(id != null){
			ActivityProduct ap = this.findById(id);
			ap.setTitle(name);
			ap.setPrice(price);
			ap.setOrders(order);
			this.update(ap);
			Item item = itemService.findById(ap.getItemId());
			if(item != null){
				item.setTitle(name);
				item.setPrice(iprice);
				saveAttachment(request, item);
				itemService.updateByPrimaryKeySelective(item);
			}
		}else{
			Item item = new Item();
			item.setTitle(name);
			item.setPrice(iprice);
			itemService.save(item);
			ActivityProduct ap = new ActivityProduct();
			ap.setTitle(name);
			ap.setPrice(price);
			ap.setActivityId(aid);
			ap.setItemId(item.getId());
			ap.setOrders(order);
			this.save(ap);
			saveAttachment(request,item);
		}
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void remove(ActivityProduct ap, Item item) {
		this.delete(ap);
		itemService.delete(item);
		deleteAttchment(item);
	}

	/**
	 * 删除附件
	 * @param item
	 */
	private void deleteAttchment(Item item) {
		List<Attachment> attList = attachmentService.findByMainId(item.getId(), AttachmentConstant.ITEM_PICTURE_PATH);
		for (Attachment attachment : attList) {
			attachmentService.delete(attachment);
		}
	}
	
	/**
	 * 查找最大order
	 * @return
	 */
	private int selectMaxOrder() {
		return activityProductMapper.selectMaxOrder();
	}

	/**
	 * 上传附件
	 * @param request
	 * @param item
	 */
	private void saveAttachment(HttpServletRequest request, Item item) {
		deleteAttchment(item);
		Attachment att = attachmentService.uploadOne((MultipartRequest)request);
		Admin admin = new Admin();
		attachmentService.saveAttachment(att, admin, item.getId(), AttachmentConstant.ITEM_PICTURE_PATH);
	}

	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public VActivityProduct findProductInfoById(Integer id) {
		return activityProductMapper.findProductInfoById(id);
	}

	

}
