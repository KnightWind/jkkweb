package com.jkkp.modules.system.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.Role;
import com.jkkp.modules.system.view.VRole;

public interface IRoleService extends IService<Role, VRole, Integer> {

	public void saveOrUpdate(Integer id, String name);

	public void delete(Integer id);

	public List<VRole> selectAllGD();
	
}
