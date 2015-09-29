package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EventMapper;
import com.jkkp.modules.basedata.model.Event;
import com.jkkp.modules.basedata.service.IEventService;
import com.jkkp.modules.basedata.view.VEvent;

@Service("eventService")
public class EventServiceImpl extends ServiceSupport<Event, VEvent, Integer> implements
		IEventService {
	
	@Autowired
	private EventMapper eventMapper;
	@Override
	protected Mapper<Event> getMapper() {
		return this.eventMapper;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Event event) {
		 if(event.getId()!=null){
			 Event eventHandle=this.findById(event.getId());
			 eventHandle.setContent(event.getContent());
			 eventHandle.setCreateTime(event.getCreateTime());
			 eventHandle.setMobile(event.getMobile());
			 eventHandle.setName(event.getName());
			 eventHandle.setType(event.getType());
			 this.update(eventHandle);
		 }else{
			 this.save(event);
		 }
	}


	@Override
	public List<VEvent> selectUserEvent(String mobile) {
		return eventMapper.selectUserEvent(mobile);
	}

}
