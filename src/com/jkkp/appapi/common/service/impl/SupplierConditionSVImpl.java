package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISupplierConditionSV;
import com.jkkp.appapi.modules.mapper.VRetCondition;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierConditionMapper;
import com.jkkp.modules.supplier.model.SupplierCondition;
import com.jkkp.modules.supplier.model.SupplierConditionCity;
import com.jkkp.modules.supplier.service.ISupplierConditionCityService;

@Service("iSupplierConditionSV")
public class SupplierConditionSVImpl extends ServiceSupport<SupplierCondition, SupplierCondition, Integer> implements ISupplierConditionSV{

	@Autowired
	SupplierConditionMapper mapper;
	@Autowired 
	ISupplierConditionCityService sccs;
	@Autowired
	@Override
	protected Mapper<SupplierCondition> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	@Override
	public VRetCondition judgeSuppConditionResult(int supplierId, float debuge, int regionId) {
		
		VRetCondition retCondition=new VRetCondition();
		retCondition.setSpid(supplierId);
		List<SupplierCondition> supplierCondition=this.selectByProperty("spId", supplierId);
//		如果商家没有设置条件先摘，默认推送
		if(supplierCondition.size()<=0)
		{
			retCondition.setFlag(true);
			return retCondition;
		}
		//如果商家设置预算大于用户提交款额，不推送
		float suppDebuge=supplierCondition.get(0).getXzYs();
		if(suppDebuge>debuge){
			retCondition.setBudget(false);
		}
		else {
			retCondition.setBudget(true);
		}
			
		
		List<SupplierConditionCity> citylist=sccs.selectByProperty("spId", supplierId);
		if(citylist.size()>0){
			for(SupplierConditionCity city:citylist){
				if(regionId==city.getRegionId()){
					retCondition.setCity(true);
					break;
				}
			}
		}
		if(retCondition.isBudget()&retCondition.isCity()){
			retCondition.setFlag(true);
		}
		else {
			retCondition.setFlag(false);
		}
		return retCondition;
		
		//如果会员所在地市不再商家设定的地市id，不推送
		/*String suppRegionId = supplierCondition.get(0).getXzCity();	
		if(regionId!=0){//app或者微信没有传入地区id
			if(!suppRegionId.contains(String.valueOf(regionId)))
				return false;
		}*/
		//return true;
	}
	@Override
	public List<SupplierCondition> judgeSuppCondition(Map<String, Object> map) {
		//参数budget regionId
		return mapper.judgeSuppCondition(map);
	}

}
