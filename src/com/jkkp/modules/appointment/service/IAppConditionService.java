package com.jkkp.modules.appointment.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.appointment.model.AppoinCondition;
import com.jkkp.modules.appointment.view.VAppoinCondition;
import com.jkkp.modules.appointment.view.VAppointment;

public interface IAppConditionService extends IService<AppoinCondition, VAppoinCondition, Integer> {

	AppoinCondition getById(Integer id);

	List<VAppointment> ajaxGetDate();

}
