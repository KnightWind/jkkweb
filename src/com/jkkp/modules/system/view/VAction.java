package com.jkkp.modules.system.view;

import java.util.List;

import com.jkkp.modules.system.model.Action;

public class VAction extends Action {

	public List<VAction> children;
	private int selected;

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public List<VAction> getChildren() {
		return children;
	}

	public void setChildren(List<VAction> children) {
		this.children = children;
	}

}
