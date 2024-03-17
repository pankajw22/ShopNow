package com.shopnow.qa.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopnow.qa.base.BaseClass;
import com.shopnow.qa.pages.LoginPage;
import com.shopnow.qa.pages.ShippingPage;

public class LoginTest extends BaseClass {

	public WebDriver driver; // Declaration of the WebDriver
	LoginPage loginpage;

	// Setup method to initialize the WebDriver and navigate to the login.
	@BeforeClass
	public void setup() {
		driver = launchBrowser();
		loginpage = new LoginPage(driver);
		loginpage.roadToLogin(configData.getProperty("url"), testData.getProperty("productSearchName"), Keys.ENTER,
				testData.getProperty("pincode"));
	}

	// Teardown method to close the browser after test execution
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void verifyValidEmailidAndPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailid(configData.getProperty("email"));
		loginpage.enterOTP(testData.getProperty("dummyotp"));
		loginpage.clickOnLoginButton();

		ShippingPage shipping = new ShippingPage(driver);
		// Verifies that the user has navigated to the shipping page successfully
		Assert.assertEquals(shipping.retrieveSucessMessageNavigateToShipping(), "Add a new address");
	}
}
