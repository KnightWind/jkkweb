package com.jkkp.modules.appointment.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppoinConditionFeedback;
import com.jkkp.modules.appointment.view.VAppoinConditionFeedback;

public interface IAppoinConditionFeedbackService extends
		IService<AppoinConditionFeedback, VAppoinConditionFeedback, Integer> {
   public List<AppoinConditionFeedback> findByPid(int pid);
   public void saveOrUpdate(AppoinConditionFeedback feedback);
}
