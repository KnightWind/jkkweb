package com.jkkp.modules.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Event;
import com.jkkp.modules.basedata.view.VEvent;

public interface EventMapper extends Mapper<Event> {
	public List<VEvent> selectUserEvent(@Param("mobile") String mobile);
}