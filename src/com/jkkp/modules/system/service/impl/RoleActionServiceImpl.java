package com.jkkp.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.RoleActionMapper;
import com.jkkp.modules.system.model.RoleAction;
import com.jkkp.modules.system.service.IRoleActionService;
import com.jkkp.modules.system.view.VRoleAction;

@Service("roleActionService")
public class RoleActionServiceImpl extends ServiceSupport<RoleAction, VRoleAction, Integer> implements
		IRoleActionService {

	@Autowired
	private RoleActionMapper roleActionMapper;

	@Override
	protected Mapper<RoleAction> getMapper() {
		return roleActionMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAction(Integer roleId, Integer[] keys) {
		this.deleteByRid(roleId);
		for (Integer aid : keys) {
			RoleAction roleAction = new RoleAction();
			roleAction.setAid(aid);
			roleAction.setRid(roleId);
			this.save(roleAction);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByRid(Integer roleId) {
		RoleAction roleAction = new RoleAction();
		roleAction.setRid(roleId);
		roleActionMapper.delete(roleAction);
	}

	public List<RoleAction> findByRid(Integer roleId) {
		RoleAction roleAction = new RoleAction();
		roleAction.setRid(roleId);
		return this.select(roleAction);
	}
}
