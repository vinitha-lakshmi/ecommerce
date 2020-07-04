package com.estee.lauder;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SampleTest {

	public static String localPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
	public static String os = System.getProperty("os.name").toLowerCase();
	public static WebDriver driver;

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", localPath + "chromedriver_mac64");
		driver = new ChromeDriver();
		driver.get("https://www.esteelauder.com/");

		sleepThread();
		boolean isPrompt = isElementVisibleBySelector("button#cboxClose");
		if (isPrompt) {
			findElementBySelector("button#cboxClose").click();
			waitForInvisibilityBySelector("button#cboxClose");
		}

		findElementBySelector("span.page-utilities__search-icon").click();
		waitForVisibilityBySelector("input#search");
		String value = getAttributeBySelector("placeholder", "input#search");
		softAssertValueEquals(value, "What are you looking for?");

		driver.findElement(By.cssSelector("input#search")).sendKeys("advanced night repair");
		sleepThread();
		List<WebElement> elements = driver.findElements(By.cssSelector("div.typeahead-product-results > div.product-result div.product-result__name > a"));
		System.out.println("Total Items found: " + elements.size());
		for (WebElement elem : elements) {
			String href = elem.getAttribute("href");
			if (href.contains("concentrated-recovery-powerfoil-mask")) {
				elem.click();
				System.out.println("Item clicked: ");
				break;
			}
		}

		driver.findElement(By.cssSelector("a.product-full__quantity")).click();
		;
		List<WebElement> qtyList = driver.findElements(By.cssSelector("ul.quantity-selectBox-dropdown-menu > li > a"));
		for (WebElement elem : qtyList) {
			String qty = elem.getText();
			if (qty.contains("2")) {
				elem.click();
				break;
			}
		}

		driver.findElement(By.xpath(".//button[text()='Add to Bag' and contains(@class,'product-full__add-button')]")).click();
		driver.findElement(By.cssSelector("span.utility-nav__cart-icon")).click();
		driver.findElement(By.cssSelector("a.remove_link")).click();
		driver.findElement(By.cssSelector("div[id='error_cart.empty']"));

		driver.quit();
		System.out.println("TEST COMPLETE");

	}

	private static WebElement waitForVisibilityBySelector(String selector) {
		WebElement elem = null;
		try {
			elem = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
		} catch (TimeoutException e) {
			System.out.println("Exception: Element not found by CSS: " + selector);
		}
		return elem;
	}

	private static WebElement waitForInvisibilityBySelector(String selector) {
		WebElement elem = null;
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(selector)));
		} catch (TimeoutException e) {
			System.out.println("Exception: Element not found by CSS: " + selector);
		}
		return elem;
	}

	private static boolean isElementVisibleBySelector(String selector) {
		boolean flag = false;
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
			flag = true;
		} catch (TimeoutException e) {
			System.out.println("Exception: Element not visible by CSS: " + selector);
		}
		return flag;
	}

	private static WebElement findElementBySelector(String selector) {
		waitForVisibilityBySelector(selector);
		return driver.findElement(By.cssSelector(selector));
	}

	private static String getAttributeBySelector(String attrName, String selector) {
		WebElement elem = findElementBySelector(selector);
		String value = "";
		value = elem.getAttribute(attrName);
		return value;
	}

	private static void softAssertValueEquals(String actual, String expected) {
		try {
			assertTrue(actual == expected);
		} catch (AssertionError e) {
			System.out.println("Assertion Error. Expected: " + expected + " Actual: " + actual);
		}
	}

	private static void sleepThread() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// ignore
		}
	}

}
