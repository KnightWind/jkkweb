package com.jkkp.modules.basedata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.model.EventType;
import com.jkkp.modules.basedata.service.IEventTypeService;

@Controller
@RequestMapping("/appointment/eventType")
public class EventTypeController extends BaseController {
	@Autowired
	private IEventTypeService eventTypeService;

	@AccessMenu
	@RequestMapping("/list")
	public String eventTypeList(HttpServletRequest request) {
		request.setAttribute("eventType", eventTypeService.select(new EventType()));
		return "/appointment/event_add";
	}

	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object save(EventType eventType) {
		try {
			eventTypeService.saveOrUpdate(eventType);
			return new ResponseObject(true, "保存事件类型成功");
		} catch (Exception e) {
			logger.error("保存事件类型出错", e);
			return new ResponseObject("保存事件类型失败!");
		}
	}
}
