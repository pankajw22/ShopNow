package com.shopnow.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopnow.qa.utilities.Elements;

public class OrderSummaryPage {

	private WebDriver driver;
	Elements element;

	// All the locators of the page
	@FindBy(xpath = "//span[normalize-space()='Order Summary']")
	private WebElement successfullyNavigateOSP;

	@FindBy(linkText = "//a[normalize-space() = 'HP 15s Intel Core i3 12th Gen 1215U - (8 GB/512 GB SSD/Windows 11 Home) 15s-fq5007TU Thin and Light Laptop']")
	private WebElement productNameFromSummary;

	@FindBy(xpath = "//input[@value='1']")
	private WebElement productQuntity;

	@FindBy(xpath = "//span[@class='_2-ut7f _1WpvJ7']")
	private WebElement price;

	@FindBy(xpath = "//span[normalize-space()='Remove']")
	private WebElement removebtn;

	@FindBy(xpath = "//button[normalize-space()='CONTINUE']")
	private WebElement continueBtnInOrderSummary;

	@FindBy (xpath = "//button[normalize-space()='Accept & Continue']")
	private WebElement acceptAndContinue;

	// Constructor to initialize the WebDriver and PageFactory elements
	public OrderSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Methods Requires to perform the test
	public String retrieveMessageSuccessfullyNavigate() {
		return successfullyNavigateOSP.getText();
	}

	public String retrieveProductNameFromSummary() {
		return productNameFromSummary.getText();
	}

	public String retrieveProductQuntity() {
		return productQuntity.getText();
	}

	public boolean isPriceDisplayed() {
		return price.isDisplayed();
	}

	public boolean isRemoveButtonDisplayed() {
		return removebtn.isDisplayed();
	}

	public void clickOnContinueButtonInSummary() {
		element = new Elements(driver);
		element.clickOnElement(continueBtnInOrderSummary, 10);
	}

	public void clickOnAcceptAndContinueButton() {
		element = new Elements(driver);
		element.clickOnElement(acceptAndContinue, 10);
	}

	// Comprehensive method to navigate from the home page to the Order summary page.
	public String roadToOrderSummary(String url, String searchName, Keys enter, String pincode, String emailText,
			String otp) {

		HomePage homepage = new HomePage(driver);
		homepage.openApplication(url);
		SearchPage searchpage = new SearchPage(driver);
		searchpage.clickOnSearchBox();
		searchpage.searchValidProduct(searchName, enter);
		String productNameInSearchPage = searchpage.retrieveProductName();
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

		return productNameInSearchPage;
	}
}
