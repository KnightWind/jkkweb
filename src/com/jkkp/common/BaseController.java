package com.jkkp.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.jkkp.utils.AllDao;

public class BaseController {
	@Autowired public AllDao allDao;
	protected Logger logger = Logger.getLogger(this.getClass());

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		// 注册自定义的属性编辑器
		// 1、日期
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		// 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
		// 自定义的电话号码编辑器(和【4.16.1、数据类型转换】一样)
	}
}
