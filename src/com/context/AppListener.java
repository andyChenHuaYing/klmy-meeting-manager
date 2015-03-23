package com.context;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * @className: AppListener 
 * @author: gu.xiaogang
 * @description: 自定义应用监听器
 * @date: 2014年9月10日
 */
public class AppListener implements ServletContextListener {

	public AppListener() {
		super();
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		String root = contextEvent.getServletContext().getRealPath("/");
		if (!root.endsWith(File.separator)) {
			root = root + File.separator;
		}
		String logPath = contextEvent.getServletContext().getRealPath("/")
				+ "WEB-INF" + File.separator + "logs";
		System.setProperty("PersonEnqueries.path", logPath);
	}
	
	 
}
