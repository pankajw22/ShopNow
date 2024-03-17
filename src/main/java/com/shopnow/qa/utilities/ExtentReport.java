package com.shopnow.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports generateExtentReport() {
		File extentReportPath = new File(System.getProperty("user.dir") + "//test-output//Extent Reports//report.html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportPath);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("ShopNow Automation Result");
		sparkReporter.config().setReportName("ShopNow Test Result");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);

		Properties extentData = new Properties();
		File extentDataPath = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\shopnow\\qa\\config\\config.properties");
		try {
			FileInputStream fisExtent = new FileInputStream(extentDataPath);
			extentData.load(fisExtent);
		} catch (IOException e) {
			e.printStackTrace();
		}

		extentReport.setSystemInfo("Application URl", extentData.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", extentData.getProperty("BrowserName"));
		extentReport.setSystemInfo("Email ID", extentData.getProperty("email"));

		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;
	}
}
