package com.shopnow.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.HomePage;

public class HomeTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver

	// Setup method to initialize the WebDriver.
	@BeforeMethod
	public void setup() {
		driver = launchBrowser();
	}

	// Teardown method to close the browser after test execution
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void verifyWebsiteLoadSuccessfully() {
		HomePage homepage = new HomePage(driver);

		// open url of website
		homepage.openApplication(configData.getProperty("url"));

		// Assert that page is load successfully.
		Assert.assertEquals(homepage.pageTitle(), testData.getProperty("pagetitle"));
	}
}
