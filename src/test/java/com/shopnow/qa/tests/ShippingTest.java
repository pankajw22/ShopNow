package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.OrderSummaryPage;
import com.shopnow.qa.pages.ShippingPage;

public class ShippingTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	ShippingPage shipping;

	// Setup method to initialize the WebDriver and navigate to the shipping page
	@BeforeClass
	public void setup() {
		driver = launchBrowser();
		shipping = new ShippingPage(driver);
		/*
		 * Navigates to the shipping page by performing a series of actions like opening
		 * the application, searching for a product, and proceeding to shipping
		 */
		shipping.roadToShippingPage(configData.getProperty("url"), testData.getProperty("productSearchName"),
				Keys.ENTER, testData.getProperty("pincode"), configData.getProperty("email"),
				configData.getProperty("dummyotp"));
	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void verifyCompleteAddressDetails() {
		shipping = new ShippingPage(driver);
		shipping.clickOnAddNewAddress();
		shipping.enterDetailsInAddressField(testData.getProperty("name"), testData.getProperty("mobileNumber"),
				testData.getProperty("pincode"), testData.getProperty("locality"), testData.getProperty("address"),
				testData.getProperty("city"));
		shipping.clickOnSaveAnddeliverButton();

		// Asserts that the locality was successfully added, indicating address
		// acceptance
		Assert.assertTrue(shipping.isLocalityAddedSuccessfully(), "Locality was not added successfully");

		OrderSummaryPage odersummary = new OrderSummaryPage(driver);
		// Verifies that the user has navigated to the Order Summary page successfully
		Assert.assertEquals(odersummary.retrieveMessageSuccessfullyNavigate(), "ORDER SUMMARY");

	}
}
