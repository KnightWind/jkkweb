package com.jkkp.modules.basedata.service.impl;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.AreaDomainMapper;
import com.jkkp.modules.basedata.model.AreaDomain;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.basedata.view.VAreaDomain;

/**
 * 梁怡立
 * 
 * @author Administrator
 *
 */
@Service("areaDomainService")
public class AreaDomainServiceImpl extends ServiceSupport<AreaDomain, VAreaDomain, Integer> implements IAreaDomainService {

	@Autowired
	private AreaDomainMapper areaDomainMapper;

	@Override
	protected Mapper<AreaDomain> getMapper() {
		return areaDomainMapper;
	}
	public List<AreaDomain> finAll(){ 
		return areaDomainMapper.findAll();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AreaDomain operate(Integer id, boolean isOpen) {
		AreaDomain bean = this.findById(id);
		if (isOpen) {
			bean.setIsOpen(AreaDomain.IS_OFFLINE);
			bean.setOfflineTime(new Date());
		} else {
			bean.setIsOpen(AreaDomain.IS_OPEN_YES);
			bean.setOpenTime(new Date());
		}
		this.update(bean);
		return bean;
	}
	@Override
	public List<AreaDomain> finName(String name) {
		return areaDomainMapper.findName(name);
	}
}
