package com.shopnow.qa.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListener implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	private String testName;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReport.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, " Test Execution get Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.PASS, " Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName();

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		String destination = Screenshots.captureScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(destination);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " Test Skiped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String extentFilePath = System.getProperty("user.dir") + "\\test-output\\Extent Reports\\report.html";
		File extentFile = new File(extentFilePath);
		try {
			Desktop.getDesktop().browse(extentFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
