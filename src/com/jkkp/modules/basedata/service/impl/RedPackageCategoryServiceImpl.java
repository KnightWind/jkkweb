package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.RedPackageCategoryMapper;
import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.modules.basedata.model.RedPackageCategory;
import com.jkkp.modules.basedata.service.IRedPackageCategoryService;
import com.jkkp.modules.basedata.view.VRedPackageCategory;
import com.jkkp.modules.product.model.ItemCategory;
import com.jkkp.modules.product.service.IItemCategoryService;

@Service("redPackageCategoryService")
public class RedPackageCategoryServiceImpl extends ServiceSupport<RedPackageCategory, VRedPackageCategory, Integer> implements IRedPackageCategoryService {

	@Autowired
	private RedPackageCategoryMapper redPackageCategoryMapper;
	@Autowired
	private IItemCategoryService itemCategoryService;
	
	
	@Override
	protected Mapper<RedPackageCategory> getMapper() {
		return redPackageCategoryMapper;
	}


	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void save(int[] ids, RedPackage pack) {
		List<RedPackageCategory> list = this.selectByProperty("rpId", pack.getId());
		for (RedPackageCategory rpc : list) {
			this.delete(rpc);
		}
		if(ids != null){
			for (int id : ids) {
				ItemCategory ic = itemCategoryService.findById(id);
				RedPackageCategory rc = new RedPackageCategory();
				if(ic != null){
					rc.setIcId(id);
					rc.setCategoryName(ic.getName());
				}
				rc.setRpId(pack.getId());
				this.save(rc);
			}
		}
	}

}
