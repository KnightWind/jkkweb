package com.jkkp.modules.basedata.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.SupplierBranchMapper;
import com.jkkp.modules.basedata.model.SupplierBranch;
import com.jkkp.modules.basedata.service.ISupplierBranchService;
import com.jkkp.modules.basedata.view.VSupplierBranch;

@Service(value="supplierBranchService")
public class SupplierBranchServiceImpl extends ServiceSupport<SupplierBranch, VSupplierBranch, Integer>
		implements ISupplierBranchService {
	
	@Autowired
	private SupplierBranchMapper supplierBranchMapper;

	@Override
	public List<SupplierBranch> getSupplierSupplierBranch(Integer spId) {
		return this.selectByProperty("spId", spId);
	}

	@Override
	protected Mapper<SupplierBranch> getMapper() {
		return supplierBranchMapper;
	}

	@Transactional
	public void saveOrUpdate(SupplierBranch bean) {
		if(bean.getId()!=null){
			
		}else{
			bean.setCreateTime(new Date());
			this.save(bean);
		}
	}

	@Override
	public void deleteOne(Integer id) {
		this.deleteById(id);
	}
	
}
