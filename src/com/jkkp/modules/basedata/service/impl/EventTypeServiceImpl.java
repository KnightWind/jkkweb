package com.jkkp.modules.basedata.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EventTypeMapper;
import com.jkkp.modules.basedata.model.EventType;
import com.jkkp.modules.basedata.service.IEventTypeService;
import com.jkkp.modules.basedata.view.VEventType;

@Service("eventTypeService")
public class EventTypeServiceImpl extends
		ServiceSupport<EventType, VEventType, Integer> implements
		IEventTypeService {
	@Autowired
	private EventTypeMapper eventTypeMapper;

	@Override
	protected Mapper<EventType> getMapper() {
		return eventTypeMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(EventType eventType) {
		if (eventType.getId() !=null) {
			EventType eventTypeHandle = this.findById(eventType.getId());
			eventTypeHandle.setCreateTime(new Date());
			eventTypeHandle.setType(eventType.getType());
			this.update(eventTypeHandle);
		} else {
			eventType.setCreateTime(new Date());
			// 因前期开发无法获取登录管理信息，所以此处管理员id写死 2015-6-9
			eventType.setAdminId(23);
			this.save(eventType);
		}
	}

}
