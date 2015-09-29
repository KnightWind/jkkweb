package com.jkkp.common;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.jkkp.modules.system.service.ISysconfigService;
import com.jkkp.modules.system.service.impl.SysconfigServiceImpl;
import com.jkkp.utils.SpringContextUtil;

public class CacheLoaderListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		ISysconfigService sysconfigService = SpringContextUtil.getBean(SysconfigServiceImpl.class);
		sysconfigService.initConfiguration();
	}
}
