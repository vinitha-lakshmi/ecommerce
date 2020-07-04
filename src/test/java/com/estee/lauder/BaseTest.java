package com.estee.lauder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

	public static Properties CONFIG = new Properties();
	public static String url;
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	public static String methodName;

	public static void load_config() throws IOException, FileNotFoundException {
		FileInputStream fn;
		fn = new FileInputStream("config.properties");
		CONFIG.load(fn);
		url = CONFIG.getProperty("url");
		logger.info("Url:" + url);
	}

	public static void getMethodName() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		methodName = e.getMethodName();
		logger.info("Inside method:" + e.getMethodName());
	}

	public void openUrl() throws IOException {
		SeleniumFunctions.getUrl(url);
		SeleniumFunctions.pause(3);
	}

}
