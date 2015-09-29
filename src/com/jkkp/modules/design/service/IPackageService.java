package com.jkkp.modules.design.service;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Package;
import com.jkkp.modules.design.view.VPackage;

public interface IPackageService extends IService<com.jkkp.modules.design.model.Package, VPackage, Integer> {

	void offline(Integer id);
   void saveOrUpdate(Package package1);
	void online(Integer id);
}
