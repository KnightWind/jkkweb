package com.jkkp.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.ActionMapper;
import com.jkkp.modules.system.model.Action;
import com.jkkp.modules.system.model.RoleAction;
import com.jkkp.modules.system.service.IActionService;
import com.jkkp.modules.system.service.IRoleActionService;
import com.jkkp.modules.system.view.VAction;

@Service("actionService")
public class ActionServiceImpl extends ServiceSupport<Action, VAction, Integer> implements IActionService {

	@Autowired
	private ActionMapper actionMapper;
	@Autowired
	private IRoleActionService roleActionService;

	@Override
	protected Mapper<Action> getMapper() {
		return actionMapper;
	}

	public List<VAction> findDataList() {
		List<Action> actionList = this.selectByProperty("pid", 0);
		List<VAction> viewList = new ArrayList<VAction>(actionList.size());
		for (Action action : actionList) {
			VAction view = super.convertBeanToView(action);
			view.setChildren(super.convertBeanToView(this.selectByProperty("pid", action.getId())));
			viewList.add(view);
		}
		return viewList;
	}
	
	public List<VAction> findSelectedDataList(Integer roleId) {
		List<Action> actionList = this.selectByProperty("pid", 0);
		List<VAction> viewList = new ArrayList<VAction>(actionList.size());
		List<RoleAction> roleActionList = roleActionService.findByRid(roleId);
		for (Action action : actionList) {
			VAction view = super.convertBeanToView(action);
			List<VAction> datalist = super.convertBeanToView(this.selectByProperty("pid", action.getId()));
			for (VAction viewAction : datalist) {
				this.selectRole(viewAction, roleActionList);
			}
			view.setChildren(datalist);
			viewList.add(view);
		}
		return viewList;
	}

	private void selectRole(VAction viewAction, List<RoleAction> roleActionList) {
		for (RoleAction item : roleActionList) {
			if (viewAction.getId().equals(item.getAid())) {
				viewAction.setSelected(1);
				return;
			}
		}
	}

	public List<Action> findParentList() {
		return this.selectByProperty("pid", 0);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Action action) {
		if (action.getId() == null || action.getId() == 0) {
			this.save(action);
		} else {
			this.update(action);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void remove(Integer id) {
		this.deleteById(id);
	}
}
