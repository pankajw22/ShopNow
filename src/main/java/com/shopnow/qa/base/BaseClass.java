package com.shopnow.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	private WebDriver driver;
	public Properties configData;
	public Properties testData;

	public WebDriver launchBrowser() {

		String browserName = configData.getProperty("BrowserName");

		String chromeDriverPath = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ms edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Enter Valid Browser Name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		return driver;
	}

	public BaseClass() {
		configData = new Properties();
		File configFilePath = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\shopnow\\qa\\config\\config.properties");
		try {
			FileInputStream fisConfig = new FileInputStream(configFilePath);
			configData.load(fisConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}

		testData = new Properties();
		File testDataPath = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\shopnow\\qa\\data\\testData.properties");
		try {
			FileInputStream fisData = new FileInputStream(testDataPath);
			testData.load(fisData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
