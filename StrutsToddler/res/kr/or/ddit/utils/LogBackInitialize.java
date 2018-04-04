package kr.or.ddit.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class LogBackInitialize {
	private final static URL LOGBACK_URL = LogBackInitialize.class.getResource("/kr/or/ddit/logback/confing/logback.xml");
	
	public static LoggerContext logbackInit(){
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(loggerContext);
		loggerContext.reset();
		InputStream inputStream = null;
		try {
			inputStream = FileUtils.openInputStream(new File(LOGBACK_URL.toURI()));
			configurator.doConfigure(inputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try { inputStream.close(); } catch (IOException e2) {}
		}
		return loggerContext;
	}
}
