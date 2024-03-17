package com.shopnow.qa.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshots {

	public static String captureScreenshot(WebDriver driver, String testName) {
		
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			FileHandler.copy(sourceFile, new File(destinationFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationFile;
	}
}
