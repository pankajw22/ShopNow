package com.shopnow.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.shopnow.qa.utilities.Elements;

public class ShippingPage {

	private WebDriver driver; // WebDriver instance for browser manipulation
	Elements elements; // An instance of a utility class for enhanced element interactions

	// All the locators of the page
	@FindBy(xpath = "//div[@class = '_1P2-0f']")
	private WebElement successMessageNavigateToShipping;

	@FindBy(xpath = "//div[@class = '_1P2-0f']")
	private WebElement clickOnAddress;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement name;

	@FindBy(xpath = "//input[@name='phone']")
	private WebElement mobileNum;

	@FindBy(xpath = "//input[@name='pincode']")
	private WebElement pincode;

	@FindBy(xpath = "//input[@name='addressLine2']")
	private WebElement locality;

	@FindBy(xpath = "//textarea[@name='addressLine1']")
	private WebElement address;

	@FindBy(xpath = "//input[@name='city']")
	private WebElement city;

	@FindBy(xpath = "//input[@name='state']")
	private WebElement state;

	@FindBy(xpath = "//label[@for='HOME']//div[@class='_1XFPmK']")
	private WebElement addressTypeRadiobtn;

	@FindBy(xpath = "//button[normalize-space()= 'Save and Deliver Here']")
	private WebElement saveAndDeliverbtn;

	// Constructor to initialize the WebDriver and PageFactory elements
	public ShippingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Methods Requires to perform the test
	public String retrieveSucessMessageNavigateToShipping() {
		return successMessageNavigateToShipping.getText();
	}

	public void clickOnAddNewAddress() {
		elements = new Elements(driver);
		elements.clickOnElement(clickOnAddress, 10);
	}

	// Entering the all the details in address field
	public void enterDetailsInAddressField(String nameText, String mobilenum, String pincodeText, String localityText,
			String addressText, String cityText) {

		name.sendKeys(nameText);
		mobileNum.sendKeys(mobilenum);
		pincode.sendKeys(pincodeText);
		locality.sendKeys(localityText);
		address.sendKeys(addressText);
		city.sendKeys(cityText);
		state.click();
		elements = new Elements(driver);
		elements.clickOnElement(state, 10);

		Select selState = new Select(state);
		selState.selectByVisibleText("Maharashtra");

		elements.clickOnElement(addressTypeRadiobtn, 10);
		elements.clickOnElement(saveAndDeliverbtn, 10);

	}

	public void clickOnSaveAnddeliverButton() {
		elements = new Elements(driver);
		elements.clickOnElement(saveAndDeliverbtn, 10);
	}

	// Comprehensive method to navigate from the home page to the shipping page
	public void roadToShippingPage(String url, String searchName, Keys enter, String pincode, String emailText,
			String otp) {

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
	}

	public boolean isLocalityAddedSuccessfully() {
		return locality.isDisplayed();
	}
}
