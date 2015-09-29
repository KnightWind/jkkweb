package com.jkkp.modules.basedata.service;

import java.util.List;
import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.HelpCategory;
import com.jkkp.modules.basedata.view.VHelpCategory;

public interface IHelpCategoryService extends
		IService<HelpCategory, VHelpCategory, Integer> {
	public int chilItemCount(Integer parentId);

	public void saveOrUpdate(HelpCategory helpCategory);

	public List<HelpCategory> selectAllParents();

	public List<HelpCategory> selectAllSubclass(int pid);
}
