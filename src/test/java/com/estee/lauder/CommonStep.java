package com.estee.lauder;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonStep extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(CommonStep.class);

	@Before
	public void beforeScenario() throws IOException {
		load_config();
		try {
			SeleniumFunctions.initializeDriver("chrome");
		} catch (Exception e) {
			logger.error("Failed To Initialize Webdriver..");
		}
	}

	@After(order = 1)
	public void closeBrowser() {
		SeleniumFunctions.pause(5);
		SeleniumFunctions.closeBrowser();
		if (SeleniumFunctions.getDriver() != null) {
			SeleniumFunctions.quitBrowser();
		}
	}
}
