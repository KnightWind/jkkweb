package com.jkkp.common.search;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.jkkp.utils.SpringContextUtil;

public class SearchIndexListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		this.createIndex();
	}

	private void createIndex() {
		try {
			final ISearchService searchService = SpringContextUtil.getBean(ISearchService.class);
			new Thread(new Runnable() {
				public void run() {
					searchService.index();
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
