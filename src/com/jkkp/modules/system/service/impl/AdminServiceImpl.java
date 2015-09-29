package com.jkkp.modules.system.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.CommonResult;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.constants.SystemConstant;
import com.jkkp.modules.system.mapper.AdminMapper;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.service.IAdminService;
import com.jkkp.modules.system.view.VAdmin;
import com.jkkp.utils.CommonUtil;

@Service("adminService")
public class AdminServiceImpl extends ServiceSupport<Admin, VAdmin, Integer> implements IAdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	protected Mapper<Admin> getMapper() {
		return adminMapper;
	}

	public String findNameById(Integer id) {
		if (id != null && id > 0) {
			Admin admin = this.findById(id);
			return admin != null ? admin.getUsername() : null;
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Admin admin) throws Exception {
		admin.setPass(CommonUtil.md5(admin.getPass()));
		if (admin.getId() != null && admin.getId() > 0) {
			Admin adminHandle = this.findById(admin.getId());
			String oldUsername = adminHandle.getUsername();
			adminHandle.setUsername(admin.getUsername());
			adminHandle.setRid(admin.getRid());
			adminHandle.setNickname(admin.getNickname());
			adminHandle.setMobile(admin.getMobile());
			adminHandle.setEmail(admin.getEmail());
			adminHandle.setPass(admin.getPass());
			this.update(adminHandle);
			if (!oldUsername.equals(admin.getUsername())) {
				adminMapper.deleteResourceRole(oldUsername);
				adminMapper.saveResourceRole(admin.getUsername(), SystemConstant.SYS_RESOURCE_ROLE);
			}
		} else {
			if (!StringUtils.isNotEmpty(admin.getCityDomain())) {
				admin.setCityDomain("");
			}
			if (admin.getStatus() == null) {
				admin.setStatus(0);
			}
			this.save(admin);
			
			adminMapper.saveResourceRole(admin.getUsername(), SystemConstant.SYS_RESOURCE_ROLE);
		}
	}

	public CommonResult<String> checkAdmin(Admin admin) {
		Admin entity = new Admin();
		entity.setUsername(admin.getUsername());
		List<Admin> adminList = this.select(entity);
		if (admin.getId() != null && admin.getId() > 0) {
			for (Admin item : adminList) {
				if (!item.getId().equals(admin.getId())) {
					return new CommonResult<String>("账号" + admin.getUsername() + "已经存在！");
				}
			}
		} else {
			if (adminList != null && !adminList.isEmpty()) {
				return new CommonResult<String>("账号" + admin.getUsername() + "已经存在！");
			}
		}
		return new CommonResult<String>(true);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAdmin(Integer id) {
		Admin adminHandle = this.findById(id);
		this.deleteById(id);
		adminMapper.deleteResourceRole(adminHandle.getUsername());
	}
}
