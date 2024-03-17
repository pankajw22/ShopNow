package com.shopnow.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopnow.qa.utilities.Elements;

public class CheckOutPage {

	private WebDriver driver; // WebDriver instance for browser manipulation
	Elements elements; // An instance of a utility class for enhanced element interactions.
	SearchPage searchpage;

	// private String nameInSearchPage;

	// All the locators of the page
	@FindBy(xpath = "//div[text() = 'Total Amount']")
	private WebElement sucessfullyNavigateToCheckout;

	@FindBy(xpath = "//a[@class = '_2Kn22P gBNbID'][contains(normalize-space() , 'HP 15s Intel Core i3 12th Gen 1215U - (8 GB/512 GB SSD/Windows 11 Home)')]")
	private WebElement productNameFromCart;

	@FindBy(xpath = "//div[@class = 'col col-7-12']//div[contains(normalize-space() , 'HP 15s Intel Core i3 12th Gen 1215U - (8 GB/512 GB SSD/Windows 11 Home)')]")
	private WebElement procutNameFromSearchPage;

	@FindBy(xpath = "//span[normalize-space()='Place Order']")
	private WebElement placeOrderBtn;

	// Constructor to initialize the WebDriver and PageFactory elements
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Methods Requires to perform the test
	public String retriveMessageProductAddedSuccessfully() {
		return sucessfullyNavigateToCheckout.getText();
	}

	public String retrieveProductNameFromSearchPage() {
		searchpage = new SearchPage(driver);
		return searchpage.retrieveProductName();

	}

	public String retrieveProductNameFromCart() {
		return productNameFromCart.getText();
	}

	public void clickOnPlaceOrderButton() {
		elements = new Elements(driver); // Elements is a utility class for enhanced interactions
		elements.clickOnElement(placeOrderBtn, 10);
	}

	// Comprehensive method to navigate from the home page to the checkout page
	public String roadToCheckOutPage(String url, String searchName, Keys enter, String pincode) {

		HomePage homepage = new HomePage(driver);
		homepage.openApplication(url);
		searchpage = new SearchPage(driver);
		searchpage.clickOnSearchBox();
		searchpage.searchValidProduct(searchName, enter);
		String nameInSearchPage = searchpage.retrieveProductName();
		searchpage.clickOnProduct();
		ProductPage productpage = new ProductPage(driver);
		productpage.switchToNewWindow();
		productpage.enterAndCheckPincode(pincode);
		productpage.clickOnAddToBagButton();

		return nameInSearchPage;
	}

}
