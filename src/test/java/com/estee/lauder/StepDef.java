package com.estee.lauder;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(StepDef.class);

	@Given("^page is opened successfully$")
	public void page_is_opened_successfully() throws IOException {
		getMethodName();
		openUrl();
		// Clear prompt
		boolean isPrompt = SeleniumFunctions.isElementVisibleBySelector(PageObjects.ButtonClose.getProperty());
		if (isPrompt) {
			SeleniumFunctions.findElementBySelector(PageObjects.ButtonClose.getProperty()).click();
			SeleniumFunctions.waitForInvisibilityBySelector(PageObjects.ButtonClose.getProperty());
		}
	}

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String searchText) throws Throwable {
		getMethodName();
		SeleniumFunctions.findElementBySelector(PageObjects.SearchIcon.getProperty()).click();
		SeleniumFunctions.waitForVisibilityBySelector(PageObjects.SearchInput.getProperty());
		String value = SeleniumFunctions.getAttributeBySelector("placeholder", PageObjects.SearchInput.getProperty());
		SeleniumFunctions.softAssertValueEquals(value, "What are you looking for?");
		SeleniumFunctions.sendKeysBySelector(PageObjects.SearchInput.getProperty(), searchText);
		SeleniumFunctions.pause(3);
	}

	@Then("^I select the product \"([^\"]*)\"$")
	public void i_select_the_product(String productName) throws Throwable {
		getMethodName();
		List<WebElement> elements = SeleniumFunctions.getWebElementsListBySelector(PageObjects.ProductList.getProperty());
		logger.info("Total Items found: " + elements.size());
		for (WebElement elem : elements) {
			String href = elem.getAttribute("href");
			if (href.contains(productName)) {
				elem.click();
				logger.info("Selected Product: " + productName);
				break;
			}
		}
	}

	@Then("^I select the quantity as \"([^\"]*)\"$")
	public void i_select_the_quantity_as(String quantity) throws Throwable {
		getMethodName();
		SeleniumFunctions.clickElementBySelector(PageObjects.ProductQuantity.getProperty());
		List<WebElement> qtyList = SeleniumFunctions.getWebElementsListBySelector(PageObjects.ProductQuantityList.getProperty());
		for (WebElement elem : qtyList) {
			String qty = elem.getText();
			if (qty.contains(quantity)) {
				elem.click();
				break;
			}
		}
	}

	@Then("^I verify \"([^\"]*)\" items are added to the cart$")
	public void i_verify_items_are_added_to_the_cart(String quantity) throws Throwable {
		getMethodName();
		SeleniumFunctions.clickElementByXpath(PageObjects.AddToBag.getProperty());
		SeleniumFunctions.pause(3);
		String cartCount = SeleniumFunctions.getWebElementTextBySelector(PageObjects.CartCount.getProperty());
		logger.info("Cart Count: " + cartCount);
		if (!cartCount.equals(quantity)) {
			assertFalse("Cart count " + cartCount + " and Expected count " + quantity + " is not matching.", true);
		}
		SeleniumFunctions.clickElementBySelector(PageObjects.CartIcon.getProperty());
	}

	@When("^I clear the cart$")
	public void i_clear_the_cart() throws Throwable {
		getMethodName();
		SeleniumFunctions.waitForVisibilityBySelector(PageObjects.ProductRemove.getProperty());
		SeleniumFunctions.clickElementBySelector(PageObjects.ProductRemove.getProperty());
		SeleniumFunctions.waitForVisibilityBySelector(PageObjects.CartEmpty.getProperty());
	}

	@Then("^I verify cart gets empty$")
	public void i_verify_cart_gets_empty() throws Throwable {
		getMethodName();
		String itemCount = SeleniumFunctions.getWebElementTextBySelector(PageObjects.ItemCount.getProperty());
		int count = Integer.parseInt(itemCount);
		if (count != 0) {
			assertFalse("Cart did not empty", true);
		}
	}

	@When("^I sign in with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_sign_in_with(String userName, String password) throws Throwable {
		getMethodName();
		SeleniumFunctions.waitForVisibilityBySelector(PageObjects.SignInIcon.getProperty());
		SeleniumFunctions.clickElementBySelector(PageObjects.SignInIcon.getProperty());
		SeleniumFunctions.waitForVisibilityBySelector(PageObjects.SignInEmail.getProperty());
		SeleniumFunctions.sendKeysBySelector(PageObjects.SignInEmail.getProperty(), userName);
		SeleniumFunctions.clickElementBySelector(PageObjects.SignInPassword.getProperty());
		SeleniumFunctions.sendKeysBySelector(PageObjects.SignInPassword.getProperty(), password);
		SeleniumFunctions.clickElementBySelector(PageObjects.SignInButton.getProperty());
	}

	@Then("^I verify sign in is unsuccessful \"([^\\\"]*)\"$")
	public void i_verify_sign_in_is_unsuccessful(String expectedMessage) throws Throwable {
		getMethodName();
		String actualMessage = "";
		List<WebElement> elems = SeleniumFunctions.getWebElementsListBySelector(PageObjects.SignInError.getProperty());
		for (WebElement elem : elems) {
			if (!elem.getText().equals("")) {
				actualMessage = elem.getText();
			}
		}
		SeleniumFunctions.softAssertValueEquals(actualMessage, expectedMessage);
	}

}
