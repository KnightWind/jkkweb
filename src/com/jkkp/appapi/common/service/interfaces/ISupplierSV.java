package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.SupplierNameSN;
import com.jkkp.appapi.modules.mapper.VFangan;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.appapi.modules.mapper.VISupplier;
import com.jkkp.appapi.modules.mapper.VISupplierV1;
import com.jkkp.appapi.modules.mapper.VISupplierV2;
import com.jkkp.appapi.modules.mapper.VISupplierXiang;
import com.jkkp.appapi.modules.mapper.VSupplierBnjn;
import com.jkkp.common.IService;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.view.VSupplier;

public interface ISupplierSV extends IService<Supplier,VSupplier,Integer>{

	List<VSupplier> findList(Map<String, Object> map);

	Supplier findByUserName(Map<String, Object> map);
	public List<VISupplier> findlishi(Map<String, Object> map);
	List<VISupplier> findAppDetaBySpId(Map<String, Object> map);
	public List<VFangan> fangAn(Map<String, Object> params);
	List<VISupplierV1> supplierNameById(Map<String, Object> map);
	 public List<VSupplierBnjn> zhonghe(Map<String, Object> map);
	 public List<VSupplierBnjn> xingji(Map<String, Object> map);
	 public List<SupplierNameSN> finXiangQing(Integer uid,Integer spid);
	 public List<VISupplierXiang> querySupplier(Integer spid,Integer uid);
	 public List<VIStaff> querShouCan(Map<String, Object> params);
	 public List<VISupplierV2>SupplierList(Map<String, Object> params);	 
	 public VISupplierXiang querySupplierBySpId(Integer spid);
}
