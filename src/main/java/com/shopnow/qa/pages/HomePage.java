package com.shopnow.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;
	// All the locators of the page
	@FindBy (xpath = "//div[@class = '_16ZfEv']//a//picture//img")
	private WebElement pageTitle;

	// Constructor to initialize the WebDriver and PageFactory elements
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Methods Requires to perform the test
	public void openApplication(String url) {
		driver.get(url);
	}

	public String pageTitle() {
		return driver.getTitle();
	}
}
