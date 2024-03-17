package com.shopnow.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shopnow.qa.utilities.Elements;

public class SearchPage {

	private WebDriver driver; // WebDriver instance for browser manipulation
	WebDriverWait wait; // WebDriverWait instance for handling dynamic elements
	Elements elements; // An instance of a utility class for enhanced element interactions

	// All the locators of the page
	@FindBy(name = "q")
	private WebElement searchBox;

	@FindBy(className = "_10Ermr")
	private WebElement searchResultHeader;

	@FindBy(xpath = "//div[@class = 'col col-7-12']//div[contains(normalize-space() , 'HP 15s Intel Core i3 12th Gen 1215U - (8 GB/512 GB SSD/Windows 11 Home)')]")
	private WebElement productName;

	@FindBy(xpath = "//img[@alt='HP 15s Intel Core i3 12th Gen 1215U - (8 GB/512 GB SSD/Windows 11 Home) 15s-fq5007TU Thin and Light La...']")
	private WebElement productImg;

	// Constructor to initialize the WebDriver and PageFactory elements
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Methods Requires to perform the test
	public void clickOnSearchBox() {
		elements = new Elements(driver);
		elements.clickOnElement(searchBox, 10);
	}

	public void searchValidProduct(String productname, Keys enter) {
		searchBox.sendKeys(productname);
		searchBox.sendKeys(enter);
	}

	public boolean isSearchResultPageDisplayed() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOf(searchResultHeader)).isDisplayed();
	}

	public void clickOnProduct() {
		/*
		 * Casts driver to JavascriptExecutor for executing JavaScript and Scrolls the
		 * window vertically to make the product name visible
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, arguments[0].offsetTop);", productImg);
		elements = new Elements(driver); // Elements is a utility class for enhanced interactions
		elements.clickOnElement(productName, 10);
	}

	public String retrieveProductName() {
		return productName.getText();
	}
}
