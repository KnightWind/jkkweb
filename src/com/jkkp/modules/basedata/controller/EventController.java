package com.jkkp.modules.basedata.controller;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.model.Event;
import com.jkkp.modules.basedata.service.IEventService;
@Controller
@RequestMapping(value = "/appointment/event")
public class EventController extends BaseController {
    
	@Autowired
	private IEventService eventService;
	
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object handleOne(Event event){
		try {					
			event.setCreateTime(new Date());
			eventService.saveOrUpdate(event);
			return new ResponseObject(true, "保存事件成功！");
		} catch (Exception e) {
			logger.error("保存事件出错",e);
			return new ResponseObject("保存事件失败！");
		}finally{
			
		}
	}
}
