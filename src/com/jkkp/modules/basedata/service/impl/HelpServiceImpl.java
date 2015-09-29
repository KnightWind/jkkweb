package com.jkkp.modules.basedata.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.HelpMapper;
import com.jkkp.modules.basedata.model.Help;
import com.jkkp.modules.basedata.service.IHelpService;
import com.jkkp.modules.basedata.view.VHelp;

@Service("helpService")
public class HelpServiceImpl extends ServiceSupport<Help, VHelp, Integer>
		implements IHelpService {

	@Autowired
	private HelpMapper helpMapper;

	@Override
	protected Mapper<Help> getMapper() {
		// TODO Auto-generated method stub
		return helpMapper;
	}

	@Override
	public VHelp selectOneHelpCata(int id) {
		return helpMapper.selectOneHelpCata(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Help help) {
		if (help.getId() != null) {
			Help helpHandle = this.findById(help.getId());
			helpHandle.setContent(help.getContent());
			this.update(helpHandle);
		} else {
			help.setStatus(new Byte("0"));
			help.setCreateTime(new Date());
			this.save(help);
		}
	}

	// 显示
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void show(int id) {
		helpMapper.show(id);
	}

	// 隐藏
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void hidden(int id) {
		helpMapper.hidden(id);
	}

}
