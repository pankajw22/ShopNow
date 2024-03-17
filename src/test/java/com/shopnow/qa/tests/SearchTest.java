package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.HomePage;
import com.shopnow.qa.pages.ProductPage;
import com.shopnow.qa.pages.SearchPage;

public class SearchTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	SearchPage searchpage; // Declaration of the search page object

	// Setup method to initialize the WebDriver and navigate to the checkoutpage
	@BeforeClass
	public void Setup() {
		driver = launchBrowser();
		HomePage homepage = new HomePage(driver);
		homepage.openApplication(configData.getProperty("url"));
	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchValidProductName() {
		searchpage = new SearchPage(driver);
		// click on search box
		searchpage.clickOnSearchBox();
		// search the laptop and press enter key
		searchpage.searchValidProduct(testData.getProperty("productSearchName"), Keys.ENTER);
		// applying for assertio to check we correctly navigate to search page or not
		Assert.assertTrue(searchpage.isSearchResultPageDisplayed(), "Search Page Result is Not Displayed");

	}

	@Test(priority = 2, dependsOnMethods = "verifySearchValidProductName")
	public void verifyToClickOnSearchResultProduct() {
		searchpage = new SearchPage(driver);
		// click on product
		searchpage.clickOnProduct();
		System.out.println("Click On Product : "+searchpage.retrieveProductName());

		ProductPage productpage = new ProductPage(driver);
		/*
		 * applying assertion for to check we successfully navigate to product page
		 * after clicking on product
		 */
		Assert.assertEquals(productpage.retrieveSuccessMessageFromProductPage(), "Compare");

	}
}
