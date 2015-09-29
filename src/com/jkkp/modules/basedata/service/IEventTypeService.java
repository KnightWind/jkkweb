package com.jkkp.modules.basedata.service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.EventType;
import com.jkkp.modules.basedata.view.VEventType;

public interface IEventTypeService extends IService<EventType, VEventType, Integer> {
    public void saveOrUpdate(EventType eventType);
}
