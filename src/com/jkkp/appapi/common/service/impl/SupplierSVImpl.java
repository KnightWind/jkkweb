package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.modules.mapper.SupplierNameSN;
import com.jkkp.appapi.modules.mapper.VFangan;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.appapi.modules.mapper.VISupplier;
import com.jkkp.appapi.modules.mapper.VISupplierV1;
import com.jkkp.appapi.modules.mapper.VISupplierV2;
import com.jkkp.appapi.modules.mapper.VISupplierXiang;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.view.VSupplier;


@Service("supplierSVImplapi")
public class SupplierSVImpl extends ServiceSupport<Supplier,VSupplier,Integer> implements ISupplierSV{

	@Autowired
	SupplierMapper supplierMapper;
	@Override
	protected Mapper<Supplier> getMapper() {
		return supplierMapper;
	}

	@Override
	public List<VSupplier> findList(Map<String, Object> map) {
		return supplierMapper.findList(map);
	}

	@Override
	public Supplier findByUserName(Map<String, Object> map) {
		return supplierMapper.findByUserName(map);
	}

	@Override
	public List<VISupplier> findAppDetaBySpId(Map<String, Object> map) {
		return supplierMapper.findAppDetaBySpId(map);
	}

	@Override
	public List<VISupplierV1> supplierNameById(Map<String, Object> map) {
		return supplierMapper.supplierNameById(map);
	}

	@Override
	public List<VSupplierBnjn> zhonghe(Map<String, Object> map) {
	
		return supplierMapper.zhonghe(map);
	}

	@Override
	public List<VSupplierBnjn> xingji(Map<String, Object> map) {
		return supplierMapper.xingji(map);
	}

	@Override
	public List<SupplierNameSN> finXiangQing(Integer uid, Integer spid) {
		return supplierMapper.finXiangQing(uid, spid);
	}

	@Override
	public List<VISupplierXiang> querySupplier(Integer spid,Integer uid) {	
		return supplierMapper.querySupplier(spid,uid);
	}

	@Override
	public List<VIStaff> querShouCan(Map<String, Object> params) {		
		return supplierMapper.querShouCan(params);
	}

	@Override
	public List<VISupplierV2> SupplierList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return supplierMapper.SupplierList(params);
	}

	@Override
	public List<VFangan> fangAn(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return supplierMapper.fangAn(params);
	}



	@Override
	public List<VISupplier> findlishi(Map<String, Object> map) {
		return supplierMapper.findlishi(map);
	}

	@Override
	public VISupplierXiang querySupplierBySpId(Integer spid) {
		return supplierMapper.querySupplierBySpId(spid);
	}
}
