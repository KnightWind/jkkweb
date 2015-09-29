package com.jkkp.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.RoleMapper;
import com.jkkp.modules.system.model.Role;
import com.jkkp.modules.system.service.IRoleService;
import com.jkkp.modules.system.view.VRole;

@Service("roleService")
public class RoleServiceImpl extends ServiceSupport<Role, VRole, Integer> implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	protected Mapper<Role> getMapper() {
		return roleMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Integer id, String name) {
		if (id != null && id > 0) {
			Role bean = this.findById(id);
			bean.setName(name);
			this.update(bean);
		} else {
			Role bean = new Role();
			bean.setName(name);
			this.save(bean);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<VRole> selectAllGD() {
		return roleMapper.selectAllGD();
	}




}
