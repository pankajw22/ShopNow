package com.shopnow.qa.pages;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.shopnow.qa.utilities.Elements;

public class LoginPage {

	private WebDriver driver;
	Elements elements;

	// All the locators of the page
	@FindBy(xpath = "//span[normalize-space() = 'Login or Signup']")
	private WebElement successMessageOnLogin;

	@FindBy(xpath = "//input[@class='_2IX_2- _17N0em']")
	private WebElement email;

	@FindBy(xpath = "//input[@class='_2IX_2- _3mctLh _17N0em']")
	private WebElement otp;

	@FindBy(xpath = "//span[text() = 'CONTINUE']")
	private WebElement continuebtn;

	@FindBy(xpath = "//span[normalize-space() = 'Login']")
	private WebElement loginbtn;

	@FindBy(xpath = "//div[@class = '_2sKwjB']")
	private WebElement errormessage;

	// Constructor to initialize the WebDriver and PageFactory elements
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Methods Requires to perform the test
	public String successfullyNavigateToLogin() {
		return successMessageOnLogin.getText();
	}

	public void enterEmailid(String emailText) {
		email.sendKeys(emailText);
		elements = new Elements(driver);
		elements.clickOnElement(continuebtn, 10);
		elements.staticWait(2000);

		if (errormessage.isDisplayed()) {
			Assert.fail("Error detected on the page: 'Something's not right. Please try again.'");
		}
	}

	public void enterOTP(String otpNum) {
		elements = new Elements(driver);
		elements.visibilityOfWebelement(otp, 10);

		// Now that the OTP field is visible, proceed with entering the OTP
		try {

			Scanner sc = new Scanner(System.in);

			while (true) {
				System.out.println("Enter the OTP: ");
				otpNum = sc.nextLine();

				if (otpNum.matches("\\d{6}")) {
					break; // otp is valid
				} else
					System.out.println("Invalid OTP. Please enter a 6-digit numeric OTP.");
			}

			otp.sendKeys(otpNum);

		} catch (InputMismatchException e) {
			System.out.println("An error occurred while reading the OTP.");

		}
	}

	public void clickOnLoginButton() {
		elements = new Elements(driver);
		elements.clickOnElement(loginbtn, 5);
	}

	// Comprehensive method to navigate from the home page to the Login page
	public void roadToLogin(String url, String searchName, Keys enter, String pincode) {
		HomePage homepage = new HomePage(driver);
		homepage.openApplication(url);
		SearchPage searchpage = new SearchPage(driver);
		searchpage.clickOnSearchBox();
		searchpage.searchValidProduct(searchName, enter);
		searchpage.clickOnProduct();
		ProductPage productpage = new ProductPage(driver);
		productpage.switchToNewWindow();
		productpage.enterAndCheckPincode(pincode);
		productpage.clickOnAddToBagButton();
		CheckOutPage checkout = new CheckOutPage(driver);
		checkout.clickOnPlaceOrderButton();
	}
}
