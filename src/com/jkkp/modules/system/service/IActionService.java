package com.jkkp.modules.system.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.Action;
import com.jkkp.modules.system.view.VAction;

public interface IActionService extends IService<Action, VAction, Integer> {

	public List<VAction> findDataList();

	public List<Action> findParentList();

	public void saveOrUpdate(Action action);
	public void remove(Integer id);

	public List<VAction> findSelectedDataList(Integer roleId);
}
