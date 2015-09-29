package com.jkkp.modules.product.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.product.model.PurchaseFor;
import com.jkkp.modules.product.view.VPurchaseFor;

public interface IPurchaseForService extends
		IService<PurchaseFor, VPurchaseFor, Integer> {
	public List<PurchaseFor> getAllPurchaseFor();

	public void saveOrUpdate(PurchaseFor pf,HttpServletRequest request);
	
	//删除一购买地
	public void deleteOnePurchaseFor(int id);
}
