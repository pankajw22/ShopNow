package com.shopnow.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.shopnow.qa.utilities.Elements;

public class PaymentPage {

	private WebDriver driver;

	// All the locators of the page
	@FindBy(xpath = "//span[@class='_1aULyb']")
	private WebElement successMessageNavigatePayment;

	@FindBy(xpath = "//label[@for='CREDIT']//div[@class='_1XFPmK']")
	private WebElement radiobtncreditcard;

	@FindBy(xpath = "//input[@name='cardNumber']")
	private WebElement cardnumber;

	@FindBy(xpath = "//input[@name='cvv']")
	private WebElement cvvnumber;

	@FindBy (xpath = "//select[@name='month']")
	private WebElement month;

	@FindBy (xpath = "//select[@name='year']")
	private WebElement year;

	// Constructor to initialize the WebDriver and PageFactory elements
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Methods Requires to perform the test
	public String retrieveMessageSuccessfullyNavigateToPayment() {
		return successMessageNavigatePayment.getText();
	}

	public void clickOnCreditCardRadioButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.scrollBy(0,300)");

		Elements element = new Elements(driver);
		element.clickOnElement(radiobtncreditcard, 10);
	}

	public void enterCompleteDetailsOfCards(String cardNum, String cvvNum) {
		cardnumber.sendKeys(cardNum);
		cvvnumber.sendKeys(cvvNum);

		// use select class to select value from dropdown
		Select selMonth = new Select(month);
		selMonth.selectByVisibleText("04");

		Select selYear = new Select(year);
		selYear.selectByVisibleText("28");

	}

	public boolean isCardNumberFillSuccessfully() {
		return cardnumber.isDisplayed();
	}

	public boolean isCVVNumberFillSuccessfully() {
		return cvvnumber.isDisplayed();
	}

	// Comprehensive method to navigate from the home page to the payment page
	public void roadToPayment(String url, String searchName, Keys enter, String pincode, String emailText, String otp) {

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
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailid(emailText);
		loginpage.enterOTP(otp);
		loginpage.clickOnLoginButton();
		ShippingPage shipping = new ShippingPage(driver);
		shipping.clickOnAddNewAddress();
		shipping.clickOnSaveAnddeliverButton();
		OrderSummaryPage ordersummary = new OrderSummaryPage(driver);
		ordersummary.clickOnContinueButtonInSummary();
		ordersummary.clickOnAcceptAndContinueButton();
	}
}
