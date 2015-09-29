package com.jkkp.modules.product.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.PurchaseForMapper;
import com.jkkp.modules.product.model.PurchaseFor;
import com.jkkp.modules.product.service.IPurchaseForService;
import com.jkkp.modules.product.view.VPurchaseFor;

@Service("PurchaseForService")
public class PurchaseForServiceImpl extends
		ServiceSupport<PurchaseFor, VPurchaseFor, Integer> implements
		IPurchaseForService {

	@Autowired
	private PurchaseForMapper purchaseForMapper;

	@Override
	public List<PurchaseFor> getAllPurchaseFor() {
		PurchaseFor pf = new PurchaseFor();
		return this.select(pf);
	}

	@Override
	protected Mapper<PurchaseFor> getMapper() {
		return purchaseForMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(PurchaseFor pf, HttpServletRequest request) {
		if (pf.getId() != null) {
			PurchaseFor handle = this.findById(pf.getId());
			handle.setGmdname(pf.getGmdname());
			handle.setType(pf.getType());
			this.update(handle);
		} else {
			pf.setCreateTime(new Date());
			pf.setCreateUser("后台管理员" + request.getRemoteUser()==""? ""
					: ":" + request.getRemoteUser());
			this.save(pf);
		}

	}

	@Transactional
	public void deleteOnePurchaseFor(int id) {
		purchaseForMapper.deleteOnePurchaseFor(id);
	}

}
