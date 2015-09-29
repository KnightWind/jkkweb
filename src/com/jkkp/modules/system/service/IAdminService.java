package com.jkkp.modules.system.service;

import com.jkkp.common.CommonResult;
import com.jkkp.common.IService;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.view.VAdmin;

/**
 * 梁怡立
 * 
 * @author Administrator
 *
 */
public interface IAdminService extends IService<Admin, VAdmin, Integer> {

	String findNameById(Integer id);

	public void saveOrUpdate(Admin admin) throws Exception;

	CommonResult<String> checkAdmin(Admin admin);

	void deleteAdmin(Integer id);
}
