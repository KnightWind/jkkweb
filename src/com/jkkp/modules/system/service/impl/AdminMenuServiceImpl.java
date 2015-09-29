package com.jkkp.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.AdminMenuMapper;
import com.jkkp.modules.system.mapper.RoleMenuMapper;
import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.model.RoleMenu;
import com.jkkp.modules.system.service.IAdminMenuService;
import com.jkkp.modules.system.view.VAdminMenu;
import com.jkkp.utils.CommonUtil;

@Service("adminMenuService")
public class AdminMenuServiceImpl extends ServiceSupport<AdminMenu, VAdminMenu, Integer> implements IAdminMenuService {

	@Autowired
	private AdminMenuMapper adminMenuMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	protected Mapper<AdminMenu> getMapper() {
		return adminMenuMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(AdminMenu menu) {
		if (menu.getId() != null && menu.getId() > 0) {
			AdminMenu bean = this.findById(menu.getId());
			bean.setName(menu.getName());
			bean.setLink(menu.getLink());
			bean.setIcon(menu.getIcon());
			this.update(bean);
		} else {
			menu.setOrderby(adminMenuMapper.findMaxOrderby(menu.getPid()) + 1);
			this.save(menu);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeMenu(Integer id) {
		this.deleteById(id);
	}

	public List<AdminMenu> loadBanner() {
		return adminMenuMapper.findByPid(0);
	}

	public List<VAdminMenu> findMenuList(Integer pid) {
		List<AdminMenu> menuList = adminMenuMapper.findByPid(pid);
		List<VAdminMenu> viewList = new ArrayList<VAdminMenu>();
		for (AdminMenu adminMenu : menuList) {
			VAdminMenu view = this.convertBeanToView(adminMenu);
			view.setLevel(1);
			this.findChildren(view);
			viewList.add(view);
		}
		return viewList;
	}

	public void findChildren(VAdminMenu parent) {
		List<AdminMenu> menuList = adminMenuMapper.findByPid(parent.getId());
		List<VAdminMenu> viewList = this.convertBeanToView(menuList);
		for (VAdminMenu view : viewList) {
			view.setLevel(parent.getLevel() + 1);
			if (parent.getLevel() <= 2) {
				this.findChildren(view);
			}
		}
		if (!viewList.isEmpty()) {
			parent.setChildren(viewList);
		}
	}

	public VAdminMenu convertBeanToView(AdminMenu adminMenu) {
		return CommonUtil.copyBean(adminMenu, VAdminMenu.class);
	}

	public List<VAdminMenu> convertBeanToView(List<AdminMenu> beanList) {
		List<VAdminMenu> viewList = new ArrayList<VAdminMenu>(beanList.size());
		for (AdminMenu adminMenu : beanList) {
			viewList.add(this.convertBeanToView(adminMenu));
		}
		return viewList;
	}

	public List<AdminMenu> findBannerByAdmin(String username) {
		return adminMenuMapper.findBannerByAdmin(username);
	}

	public List<VAdminMenu> findByAdmin(String username) {
		List<VAdminMenu> viewList = new ArrayList<VAdminMenu>();
		List<AdminMenu> rootList = adminMenuMapper.findBannerByAdmin(username);
		for (AdminMenu menu : rootList) {
			VAdminMenu view = super.convertBeanToView(menu);
			view.setChildren(this.findByAdmin(username, view.getId()));
			viewList.add(view);

		}
		return viewList;
	}

	public List<VAdminMenu> findByAdmin(String username, Integer parentId) {
		List<VAdminMenu> viewList = new ArrayList<VAdminMenu>();
		List<AdminMenu> menuList = adminMenuMapper.findByAdmin(username, parentId);
		for (AdminMenu menu : menuList) {
			VAdminMenu view = super.convertBeanToView(menu);
			view.setChildren(this.findByAdmin(username, view.getId()));
			viewList.add(view);
		}
		return viewList;
	}

	private List<RoleMenu> findRoleMenu(Integer roleId) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		return roleMenuMapper.select(roleMenu);
	}

	public List<VAdminMenu> findSelectedMenuList(Integer roleId) {
		List<RoleMenu> roleMenuList = this.findRoleMenu(roleId);
		List<AdminMenu> menuList = adminMenuMapper.findByPid(0);
		List<VAdminMenu> viewList = this.convertBeanToView(menuList);
		for (VAdminMenu view : viewList) {
			view.setChildren(this.convertBeanToView(adminMenuMapper.findByPid(view.getId())));
			this.selectedList(view.getChildren(), roleMenuList);
			for (VAdminMenu child : view.getChildren()) {
				child.setChildren(this.convertBeanToView(adminMenuMapper.findByPid(child.getId())));
				this.selectedList(child.getChildren(), roleMenuList);
			}
		}
		return viewList;
	}

	private void selectedList(List<VAdminMenu> children, List<RoleMenu> roleMenuList) {
		if (children == null || children.isEmpty()) {
			return;
		}
		for (VAdminMenu view : children) {
			view.setSelected(this.isContains(roleMenuList, view) ? 1 : 0);
		}
	}

	public boolean isContains(List<RoleMenu> roleMenuList, VAdminMenu view) {
		for (RoleMenu roleMenu : roleMenuList) {
			if (roleMenu.getMenuId().equals(view.getId())) {
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveRoleMenu(Integer roleId, Integer[] keys) {
		this.deleteByRoleId(roleId);
		for (Integer menuId : keys) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(menuId);
			roleMenu.setRoleId(roleId);
			roleMenuMapper.insert(roleMenu);
		}
	}

	private void deleteByRoleId(Integer roleId) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenuMapper.delete(roleMenu);
	}
}
