package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCollect;

public interface SupplierCollectMapper extends Mapper<SupplierCollect>{

	List<SupplierCollect> querySupCollByUid(Map<String, Object> map);//收藏的商家
	List<VSupplierBnjn> queryShouCan(Map<String, Object> map);
	List<SupplierCollect> querySupCollByUidSpid(Map<String, Object> map);
	List<VSupplierBnjn> querySupplierListDetial(Map<String, Object> map);
}
