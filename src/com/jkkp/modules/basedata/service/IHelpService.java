package com.jkkp.modules.basedata.service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Help;
import com.jkkp.modules.basedata.view.VHelp;

public interface IHelpService extends IService<Help, VHelp, Integer> {
	public VHelp selectOneHelpCata(int id);

	public void saveOrUpdate(Help help);

	public void show(int id);

	public void hidden(int id);
}
