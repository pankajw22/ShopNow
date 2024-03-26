package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.CheckOutPage;
import com.shopnow.qa.pages.LoginPage;
import com.shopnow.qa.pages.SearchPage;

public class CheckOutTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	CheckOutPage checkout;
	SearchPage searchpage;
	private String nameInSearchPage;

	// Setup method to initialize the WebDriver and navigate to the checkoutpage
	@BeforeClass

	public void setup() {
		driver = launchBrowser();
		checkout = new CheckOutPage(driver);

		/*
		 * Navigates to the checkout page by performing a series of actions like opening
		 * the application, searching for a product, and proceeding to shipping.
		 */

		nameInSearchPage = checkout.roadToCheckOutPage(configData.getProperty("url"),
				testData.getProperty("productSearchName"), Keys.ENTER, testData.getProperty("pincode"));

	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyCorrectProduchAddedInCart() {
		checkout = new CheckOutPage(driver);
		String productNameInCheckout = checkout.retrieveProductNameFromCart();

		System.out.println("Product Name On SearchPage : " + nameInSearchPage);
		System.out.println("Product Name On CheckOut Page : " + productNameInCheckout);

		/*
		 * To verify that the products we previously added are still the same ones in
		 * the cart after adding, we are going to apply assertion.
		 */

		String nameInSearchPageAfterReplace = nameInSearchPage.replace("La...", "");
		String productNameInCheckoutAfterReplace = productNameInCheckout.replace("Laptop", "");

		Assert.assertEquals(nameInSearchPageAfterReplace, productNameInCheckoutAfterReplace);

	}

	@Test(priority = 2)
	public void verifyToPlaceAnOrder() {
		checkout = new CheckOutPage(driver);
		// click on place order button
		checkout.clickOnPlaceOrderButton();

		/*
		 * Applying assertion to verify that, after the click of the PlaceOrder button,
		 * we are navigating to the login page.
		 */

		LoginPage loginpage = new LoginPage(driver);
		// Verifies that the user has navigated to the login page successfully
		Assert.assertEquals(loginpage.successfullyNavigateToLogin(), "LOGIN OR SIGNUP");
	}

}
