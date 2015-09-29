package com.jkkp.modules.system.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.RoleAction;
import com.jkkp.modules.system.view.VRoleAction;

public interface IRoleActionService extends IService<RoleAction, VRoleAction, Integer> {

	void saveAction(Integer roleId, Integer[] keys);

	List<RoleAction> findByRid(Integer id);

}
