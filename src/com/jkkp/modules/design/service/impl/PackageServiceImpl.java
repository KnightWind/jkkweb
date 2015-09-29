package com.jkkp.modules.design.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.PackageMapper;
import com.jkkp.modules.design.model.Package;
import com.jkkp.modules.design.service.IPackageService;
import com.jkkp.modules.design.view.VPackage;

@Service("packageService")
public class PackageServiceImpl extends ServiceSupport<com.jkkp.modules.design.model.Package, VPackage, Integer>
		implements IPackageService {

	@Autowired
	private PackageMapper packageMapper;

	@Override
	protected Mapper<com.jkkp.modules.design.model.Package> getMapper() {
		return packageMapper;
	}

	@Override
	public VPackage convertBeanToView(com.jkkp.modules.design.model.Package entity) {
		return super.convertBeanToView(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void offline(Integer id) {
		com.jkkp.modules.design.model.Package entity = this.findById(id);
		entity.setStatus(-1);
		this.update(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void online(Integer id) {
		com.jkkp.modules.design.model.Package entity = this.findById(id);
		entity.setStatus(0);
		this.update(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Package package1) {
		if(package1.getId()!=null && package1.getId()>0){
			Package package2=this.findById(package1.getId());
			package2.setType(package1.getType());
			package2.setPrice(package1.getPrice());
			package2.setPackageName(package1.getPackageName());
			package2.setStock(package1.getStock());
			this.update(package2);
		}else {
			this.save(package1);
		}
	}
}
