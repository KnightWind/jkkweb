package com.jkkp.modules.appointment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.AppoinConditionFeedbackMapper;
import com.jkkp.modules.appointment.model.AppoinConditionFeedback;
import com.jkkp.modules.appointment.service.IAppoinConditionFeedbackService;
import com.jkkp.modules.appointment.view.VAppoinConditionFeedback;

@Service("appoinConditionFeedbackService")
public class AppoinConditionFeedbackServiceImpl
		extends
		ServiceSupport<AppoinConditionFeedback, VAppoinConditionFeedback, Integer>
		implements IAppoinConditionFeedbackService {
	@Autowired
	private AppoinConditionFeedbackMapper appoinConditionFeedbackMapper;

	@Override
	public List<AppoinConditionFeedback> findByPid(int pid) {
		return this.selectByProperty("pid", pid);
	}

	@Override
	protected Mapper<AppoinConditionFeedback> getMapper() {
		return appoinConditionFeedbackMapper;
	}

	@Transactional
	public void saveOrUpdate(AppoinConditionFeedback feedback) {
		if (feedback.getId() != null) {
			AppoinConditionFeedback bean = this.findById(feedback.getId());
			bean.setContent(feedback.getContent());
			this.update(bean);
		}else{
			feedback.setCreateTime(new Date());
			this.save(feedback);
		}
	}

}
