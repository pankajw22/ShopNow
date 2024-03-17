package com.shopnow.qa.pages;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopnow.qa.utilities.Elements;

public class ProductPage {

	private WebDriver driver; // WebDriver instance for browser manipulation
	Elements elements; // An instance of a utility class for enhanced element interactions

	// All the locators of the page
	@FindBy(xpath = "//div[@class = 'rd9nIL'][normalize-space () = 'Available offers']")
	private WebElement availableoffer;

	@FindBy(xpath = "//label[normalize-space () = 'Compare']")
	private WebElement successMessageOnProductPage;

	@FindBy(xpath = "//span[@class = '_2P_LDn']")
	private WebElement checkbtn;

	@FindBy(xpath = "//div[@class = '_2taUHM']")
	private WebElement deliveryText;

	@FindBy(id = "pincodeInputId")
	private WebElement pincode;

	@FindBy(xpath = "//button[normalize-space()='Add to cart']")
	private WebElement addToCarBtn;

	// Constructor to initialize the WebDriver and PageFactory elements
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void switchToNewWindow() {
		/*
		 * When you click on the product page, a second window will open. You can then
		 * handle the window and choose which window you want to use with the driver.
		 */

		String parentWindowId = driver.getWindowHandle();
		Set<String> allWindowId = driver.getWindowHandles();

		for (String windowId : allWindowId) {
			if (!(windowId.equals(parentWindowId))) {
				driver.switchTo().window(windowId);
				break;
			}
		}
	}

	// Methods Requires to perform the test
	public String retrieveSuccessMessageFromProductPage() {
		// To switch to another window to perform any actions on the second one.
		switchToNewWindow();
		elements = new Elements(driver);
		elements.staticWait(2000);
		return successMessageOnProductPage.getText();
	}

	public void enterAndCheckPincode(String pcode) {
		/*
		 * Casts driver to JavascriptExecutor for executing JavaScript and Scrolls the
		 * window vertically to make the pincode and check button visible.
		 */

		elements = new Elements(driver);
		elements.staticWait(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, arguments[0].offsetTop)", deliveryText);

		pincode.sendKeys(pcode);
		elements.clickOnElement(checkbtn, 10);
	}

	public void clickOnAddToBagButton() {
		elements = new Elements(driver);
		elements.staticWait(2000);
		elements.clickOnElement(addToCarBtn, 10);
	}

	// Comprehensive method to navigate from the product page to the Login page
	public void roadToProduct(String url, String productname, Keys enter) {
		HomePage homepage = new HomePage(driver);
		homepage.openApplication(url);
		SearchPage searchpage = new SearchPage(driver);
		searchpage.clickOnSearchBox();
		searchpage.searchValidProduct(productname, enter);
		searchpage.clickOnProduct();
	}
}
