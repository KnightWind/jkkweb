package com.jkkp.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ProjectContext {
	public static String PROJECT_SITE_PATH;
	public static String PROJECT_BASE_PATH;

	private static String contextPath = null;

	public static void setContextPath(String contextPath) {
		ProjectContext.contextPath = contextPath;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static String getRealPath() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("/");
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		while (file != null) {
			if (file.getName().equals("WEB-INF")) {
				file = file.getParentFile();
				break;
			}
			file = file.getParentFile();
		}
		return file.getAbsolutePath();
	}

}
