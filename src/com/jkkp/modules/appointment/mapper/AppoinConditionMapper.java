package com.jkkp.modules.appointment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.appointment.model.AppoinCondition;
import com.jkkp.modules.appointment.view.VAppointment;

public interface AppoinConditionMapper extends Mapper<AppoinCondition> {
	AppoinCondition getById(@Param("id") Integer id);

	List<VAppointment> ajaxGetDate();
}
