package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.PaymentPage;

public class PaymentTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	PaymentPage payment; // Declaration of the PaymentPage object

	@BeforeClass
	public void setup() {
		driver = launchBrowser();
		payment = new PaymentPage(driver);
		/*
		 * Navigates to the payment page by performing a series of actions like opening
		 * the application, searching for a product, and proceeding to shipping and
		 * entering email
		 */
		payment.roadToPayment(configData.getProperty("url"), testData.getProperty("productSearchName"), Keys.ENTER, testData.getProperty("pincode"),
				configData.getProperty("email"), configData.getProperty("dummyotp"));
	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyByClickingCreditCardRadioButton() {
		payment = new PaymentPage(driver);
		payment.clickOnCreditCardRadioButton();
	}

	@Test(priority = 2)
	public void verifyCompleteDetailsOfCards() {
		payment = new PaymentPage(driver);
		payment.enterCompleteDetailsOfCards(configData.getProperty("cardNumber"), configData.getProperty("CVVNumber"));

		// Verifies if card details is displayed
		Assert.assertTrue(payment.isCardNumberFillSuccessfully(), "Card Detail Added Successfully");

		// Verifies if CVV details is displayed
		Assert.assertTrue(payment.isCVVNumberFillSuccessfully(), "CVV Detail Added Successfully");
	}
}
