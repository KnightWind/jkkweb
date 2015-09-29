package com.jkkp.modules.basedata.service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.RedPackage;
import com.jkkp.modules.basedata.view.VRedPackage;

public interface IRedPackageService extends IService<RedPackage, VRedPackage, Integer> {

	VRedPackage findPackageById(Integer id);

}
