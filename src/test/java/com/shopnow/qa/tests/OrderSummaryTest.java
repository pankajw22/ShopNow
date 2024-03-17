package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.OrderSummaryPage;
import com.shopnow.qa.pages.PaymentPage;

public class OrderSummaryTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	OrderSummaryPage ordersummary; // Declaration of the OrderSummaryPage object
	private String nameInSearPage;

	// Setup method to initialize the WebDriver and navigate to the order summary
	// page
	@BeforeClass
	public void setup() {
		driver = launchBrowser();
		OrderSummaryPage ordersummary = new OrderSummaryPage(driver);
		/*
		 * Navigates to the order summary page by performing a series of actions like
		 * opening the application, searching for a product, and proceeding to shipping
		 * and entering email
		 */
		nameInSearPage = ordersummary.roadToOrderSummary(configData.getProperty("url"),
				testData.getProperty("productSearchName"), Keys.ENTER, testData.getProperty("pincode"),
				configData.getProperty("email"), configData.getProperty("dummyotp"));
	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyCorrectProductAddedInSummary() {
		ordersummary = new OrderSummaryPage(driver);
		ordersummary.retrieveProductNameFromSummary();
		/*
		 * Verify that the products in our search are the same that are shown in the
		 * order summary.
		 */
		Assert.assertEquals(nameInSearPage, ordersummary.retrieveProductNameFromSummary());
	}

	@Test(priority = 2)
	public void verifyQuantiyOfProduct() {
		ordersummary = new OrderSummaryPage(driver);
		// Verifies the quantity of the product
		Assert.assertEquals(ordersummary.retrieveProductQuntity(), "1");
	}

	@Test(priority = 3)
	public void verifyPriceAndRemoveButton() {
		ordersummary = new OrderSummaryPage(driver);
		// Verifies if price is displayed
		Assert.assertTrue(ordersummary.isPriceDisplayed(), "Price is not Displayed");

		// Verifies if remove button is displayed
		Assert.assertTrue(ordersummary.isRemoveButtonDisplayed(), "Remove is Displayed");
	}

	@Test(priority = 4)
	public void VerifyFunctionalityOfContinueButtonInOrderSummary() {
		ordersummary = new OrderSummaryPage(driver);
		ordersummary.clickOnContinueButtonInSummary();

	}

	@Test(priority = 5)
	public void verifyAcceptAndContinueButton() {
		ordersummary = new OrderSummaryPage(driver);
		ordersummary.clickOnAcceptAndContinueButton();

		PaymentPage payment = new PaymentPage(driver);
		// Verifies that the user has navigated to the payment page successfully
		Assert.assertEquals(payment.retrieveMessageSuccessfullyNavigateToPayment(), "PAYMENT OPTIONS");
	}
}
