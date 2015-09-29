package com.jkkp.modules.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.appointment.model.AppoinConditionFeedback;
import com.jkkp.modules.appointment.service.IAppoinConditionFeedbackService;

@Controller
@RequestMapping("/appoinConditionFeedback")
public class AppoinConditionFeedbackController extends BaseController {
	@Autowired
	private IAppoinConditionFeedbackService appoinConditionFeedbackService;

	@ResponseBody
	@RequestMapping("/save.do")
	public Object save(AppoinConditionFeedback back) {
		try {
			if (back.getContent().isEmpty()) {
				return new ResponseObject(false, "请输入回访文本");
			}
			appoinConditionFeedbackService.saveOrUpdate(back);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
			logger.error("保存活动预约信息出错");
			return new ResponseObject(false, "保存失败");
		}
	}
}
