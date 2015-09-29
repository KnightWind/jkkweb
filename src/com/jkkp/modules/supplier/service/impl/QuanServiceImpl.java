package com.jkkp.modules.supplier.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.QuanMapper;
import com.jkkp.modules.supplier.model.Quan;
import com.jkkp.modules.supplier.service.IQuanService;
import com.jkkp.modules.supplier.view.VQuan;
@Service("quanService")
public class QuanServiceImpl extends ServiceSupport<Quan,VQuan,Integer> implements IQuanService{
	
	@Autowired
	private QuanMapper quanMapper;
	@Override
	protected Mapper<Quan> getMapper() {		
		return quanMapper;
	}
	@Override
	public List<VQuan> finAll() {			
		return quanMapper.finAll();
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Quan operate(Integer id, boolean isOpen) {
		Quan quan=this.findById(id);
		if(isOpen){
			quan.setSaleing(-1);
			quan.setCreateTime(new Date());
		}else {
			quan.setSaleing(0);
			quan.setCreateTime(new Date());
		}
		this.update(quan);
		return quan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void offline(Integer id) {
		Quan entity = this.findById(id);
		entity.setSaleing(-1);
		this.update(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void online(Integer id) {
		Quan entity = this.findById(id);
		entity.setSaleing(0);
		this.update(entity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void push(Integer id) {
		Quan entity = this.findById(id);
		entity.setIsPush(1);
		this.update(entity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void logout(Integer id) {
		Quan entity = this.findById(id);
		entity.setStatus(-1);
		this.update(entity);
	}
}
