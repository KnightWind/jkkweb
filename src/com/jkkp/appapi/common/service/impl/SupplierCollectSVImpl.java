package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.modules.mapper.VISupplierXiang;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;

import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCollectMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.view.VSupplierCollect;

@Service("supplierCollectSV")
public class SupplierCollectSVImpl extends ServiceSupport<SupplierCollect, VSupplierCollect, Integer> implements ISupplierCollectSV{


	@Autowired SupplierCollectMapper supplierCollectMapper;
	@Override
	protected Mapper<SupplierCollect> getMapper() {
		// TODO Auto-generated method stub
		return supplierCollectMapper;
	}
	@Override
	public Integer querySupCollByUid(Map<String, Object> map) {
		

		int count=0;
		List<SupplierCollect> object=null;
		object=supplierCollectMapper.querySupCollByUid(map);
		if(object!=null)
			count=object.size();
		
		return count;
	}
	
	
	public List<SupplierCollect> querySupCollDetailByUid(Map<String, Object> map) {
		//参数 memberId

		int count=0;
		List<SupplierCollect> object=null;
		object=supplierCollectMapper.querySupCollByUid(map);
		
		return object;
	}
	@Override
	public List<VSupplierBnjn> queryShouCan(Map<String, Object> map) {	
		return supplierCollectMapper.queryShouCan(map);
	}
	public List<SupplierCollect> querySupCollByUidSpid(Map<String, Object> map){
		return supplierCollectMapper.querySupCollByUidSpid(map);
	}
	public List<VSupplierBnjn> querySupplierListDetial(Map<String, Object> map){
		return supplierCollectMapper.querySupplierListDetial(map);
	}
}
