package com.nehayadav.qe.tests.web;

import com.nehayadav.qe.config.ConfigReader;
import com.nehayadav.qe.driver.DriverFactory;
import com.nehayadav.qe.pages.LoginPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;

public abstract class BaseWebTest {

 protected WebDriver driver;
 protected LoginPage loginPage;

 @BeforeMethod
 public void setUp() {
 driver = DriverFactory.getDriver();
 loginPage = new LoginPage(driver);
 loginPage.open(ConfigReader.get("web.baseUrl"));
 }

 @AfterMethod
 public void tearDown(ITestResult result) {
 if (result.getStatus() == ITestResult.FAILURE) {
 attachScreenshot();
 attachPageSource();
 }
 DriverFactory.quitDriver();
 }

 private void attachScreenshot() {
 try {
 byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
 Allure.addAttachment("Failure screenshot", new ByteArrayInputStream(screenshot));
 } catch (Exception ignored) {
 }
 }

 private void attachPageSource() {
 try {
 Allure.addAttachment("Page source", "text/html", driver.getPageSource(), ".html");
 } catch (Exception ignored) {
 }
 }
}

