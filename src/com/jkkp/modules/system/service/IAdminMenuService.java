package com.jkkp.modules.system.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.view.VAdminMenu;

public interface IAdminMenuService extends IService<AdminMenu,VAdminMenu, Integer> {

	public void saveOrUpdate(AdminMenu menu);

	public void removeMenu(Integer id);

	public List<AdminMenu> loadBanner();

	public List<VAdminMenu> findMenuList(Integer pid);
	
	public List<VAdminMenu> findByAdmin(String username);

	public List<VAdminMenu> findSelectedMenuList(Integer roleId);

	public void saveRoleMenu(Integer roleId, Integer[] keys);
	
	public List<AdminMenu> findBannerByAdmin(String username);
	
	public List<VAdminMenu> findByAdmin(String username, Integer parentId);
}
