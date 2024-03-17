package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.CheckOutPage;
import com.shopnow.qa.pages.ProductPage;

public class ProductTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	ProductPage productpage; // Declaration of the product page object

	// Setup method to initialize the WebDriver and navigate to the product.
	@BeforeClass
	public void setup() {
		driver = launchBrowser();
		productpage = new ProductPage(driver);
		/*
		 * Navigates to the product page by performing a series of actions like opening
		 * the application, searching for a product.
		 */

		productpage.roadToProduct(configData.getProperty("url"), testData.getProperty("productSearchName"), Keys.ENTER);
	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyPincodeFieldAndCheckButton() {
		productpage = new ProductPage(driver);
		// checking that we are on the product page
		String successfullyOnProductPage = productpage.retrieveSuccessMessageFromProductPage();
		System.out.println("Successfully Navigate To Product Page : " + successfullyOnProductPage);
		productpage.enterAndCheckPincode(testData.getProperty("pincode"));
	}

	@Test(priority = 2, dependsOnMethods = "VerifyPincodeFieldAndCheckButton")
	public void verifyProductAddToBag() {
		productpage = new ProductPage(driver);
		// click on add to bag button
		productpage.clickOnAddToBagButton();

		CheckOutPage checkout = new CheckOutPage(driver);
		// Apply assertion to verify whether a product has been added to the bag.
		Assert.assertEquals(checkout.retriveMessageProductAddedSuccessfully(), "Total Amount");
	}

}
