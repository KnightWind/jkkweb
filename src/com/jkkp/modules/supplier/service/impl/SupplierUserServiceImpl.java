package com.jkkp.modules.supplier.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierUserMapper;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.service.ISupplierUserService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;

@Service("supplierUserService")
public class SupplierUserServiceImpl extends ServiceSupport<SupplierUser, VSupplierUser, Integer> implements
		ISupplierUserService {
	@Autowired
	private SupplierUserMapper supplierUserMapper;

	@Override
	protected Mapper<SupplierUser> getMapper() {
		return supplierUserMapper;
	}

	@Override
	public Long getc(Integer sid) {
		return supplierUserMapper.getc(sid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdate(SupplierUser supplierUser) {
		if (supplierUser.getId() != null && supplierUser.getId() > 0) {
			SupplierUser supplierUser2 = this.findById(supplierUser.getId());
			supplierUser2.setUsername(supplierUser.getUsername());
			supplierUser2.setIsAudit(supplierUser.getIsAudit());
			supplierUser2.setMobile(supplierUser.getMobile());
			supplierUser2.setIsAdmin(supplierUser.getIsAdmin());

			supplierUser2.setIsMerchandiser(supplierUser.getIsMerchandiser());

			supplierUser2.setIsDesigner(supplierUser.getIsDesigner());
			String pwd = null;
			if(supplierUser2.getUserpwd().equals(supplierUser.getUserpwd())){
			}else {
				pwd = CommonUtil.md5(supplierUser.getUserpwd());
				supplierUser2.setUserpwd(pwd);
			}
			this.update(supplierUser2);
		} else {
			try {
				supplierUser.setUserpwd(CommonUtil.md5(supplierUser.getUserpwd()));
			} catch (Exception e) {
				e.printStackTrace();
			} 
			this.save(supplierUser);
		}

	}

	@Transactional(readOnly=true)
	public VSupplierUser login(String name, String pass, Integer loginType) {
		return supplierUserMapper.login(name, pass,loginType);
	}

	@Transactional(readOnly=true)
	public long checkUserName(String name, Integer id) {
		return supplierUserMapper.checkUserName(name,id);
	}

	public VSupplierUser findSupplier(String name, Integer loginType) {
		return supplierUserMapper.findSupplier(name, loginType);
	}

	@Override
	public Boolean checkSupplierUserMobile(String mobile,Integer id) {
		List<SupplierUser> result=supplierUserMapper.selectSupplierUserByMobile(mobile,id);
		if(result.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List queryUserInfo(Map<String, Object> params) {
		return supplierUserMapper.queryUserInfo(params);
	}
}
