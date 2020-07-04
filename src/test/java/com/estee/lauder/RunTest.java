package com.estee.lauder;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:target/reports", "json:target/reports/cucumber-report.json" }, 
		glue = { "com.estee.lauder" }, 
		features = "src/test/resources/features/",
		//tags="@SIGNIN",
		monochrome = true
)

public class RunTest {
	public void runTest() {
	}
}

