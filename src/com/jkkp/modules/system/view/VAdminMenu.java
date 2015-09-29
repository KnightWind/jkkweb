package com.jkkp.modules.system.view;

import java.util.List;

import com.jkkp.modules.system.model.AdminMenu;

public class VAdminMenu extends AdminMenu {

	private int level;
	private List<VAdminMenu> children;
	private int selected;

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<VAdminMenu> getChildren() {
		return children;
	}

	public void setChildren(List<VAdminMenu> children) {
		this.children = children;
	}

	public boolean getIsOpen() {
		return children != null && !children.isEmpty();
	}
}
