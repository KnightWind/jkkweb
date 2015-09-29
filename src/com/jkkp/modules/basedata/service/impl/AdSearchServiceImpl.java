package com.jkkp.modules.basedata.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.AdSearchMapper;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.model.AdSearchTag;
import com.jkkp.modules.basedata.service.IAdSearchService;
import com.jkkp.modules.basedata.view.VAdSearch;
@Service("adSearchService")
public class AdSearchServiceImpl  extends ServiceSupport<AdSearch,VAdSearch,Integer> implements IAdSearchService {
	
	@Autowired
	private AdSearchMapper adSearchMapper;
	@Override
	protected Mapper<AdSearch> getMapper() {		
		return adSearchMapper;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(AdSearch adSearch,String aname) {
			if(adSearch.getId()!=null&&adSearch.getId()>0){	
				adSearch.setCreateTime(new Date());
			this.update(adSearch);
		}else {
			adSearch.setCreateTime(new Date());
			adSearch.setStatus(new Byte("0"));
			this.save(adSearch);
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AdSearch operate(Integer id, boolean isOpen) {
		AdSearch adSearch=this.findById(id);
		if(isOpen){
			adSearch.setStatus(new Byte("-1"));
			adSearch.setCreateTime(new Date());
		}else {
			adSearch.setStatus(new Byte("0"));
			adSearch.setCreateTime(new Date());
		}
		this.update(adSearch);
		return adSearch;
	}
	@Override
	public List<VAdSearch> findAll() {	
		return adSearchMapper.findAll();
	}
	
}
