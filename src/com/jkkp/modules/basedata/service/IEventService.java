package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Event;
import com.jkkp.modules.basedata.view.VEvent;

public interface IEventService extends IService<Event, VEvent, Integer> {
	public void saveOrUpdate(Event event);
	public List<VEvent> selectUserEvent(String mobile);
}
