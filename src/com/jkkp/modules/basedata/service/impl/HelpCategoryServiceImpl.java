package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.HelpCategoryMapper;
import com.jkkp.modules.basedata.model.HelpCategory;
import com.jkkp.modules.basedata.service.IHelpCategoryService;
import com.jkkp.modules.basedata.view.VHelpCategory;

@Service("helpCategoryService")
public class HelpCategoryServiceImpl extends
		ServiceSupport<HelpCategory, VHelpCategory, Integer> implements
		IHelpCategoryService {

	@Autowired
	private HelpCategoryMapper helpCategoryMapper;

	@Override
	public int chilItemCount(Integer parentId) {
		HelpCategory help = new HelpCategory();
		help.setParentId(parentId);
		return helpCategoryMapper.selectCount(help);
	}

	@Override
	protected Mapper<HelpCategory> getMapper() {
		return helpCategoryMapper;
	}

	@Override
	public VHelpCategory convertBeanToView(HelpCategory entity) {
		VHelpCategory view = super.convertBeanToView(entity);
		view.setChilItemCount(this.chilItemCount(view.getId()));
		return view;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(HelpCategory helpCategory) {
		if (helpCategory.getId() > 0 && helpCategory.getId() != null) {
			HelpCategory helpHandle = this.findById(helpCategory.getId());
			helpHandle.setName(helpCategory.getName());
			this.update(helpHandle);
		} else {
			this.save(helpCategory);
		}

	}

	@Override
	public List<HelpCategory> selectAllParents() {
		return helpCategoryMapper.selectAllParents();
	}

	@Override
	public List<HelpCategory> selectAllSubclass(int pid) {
		return helpCategoryMapper.selectAllSubclass(pid);
	}

}
