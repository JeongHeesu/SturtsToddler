package kr.or.ddit.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.or.ddit.utils.LogBackInitialize;

public class LogbackInitializeListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LogBackInitialize.logbackInit();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
