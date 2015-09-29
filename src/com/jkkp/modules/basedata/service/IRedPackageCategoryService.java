package com.jkkp.modules.basedata.service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.modules.basedata.model.RedPackageCategory;
import com.jkkp.modules.basedata.view.VRedPackageCategory;

public interface IRedPackageCategoryService extends IService<RedPackageCategory, VRedPackageCategory, Integer> {

	void save(int[] ids, RedPackage pack);

}
