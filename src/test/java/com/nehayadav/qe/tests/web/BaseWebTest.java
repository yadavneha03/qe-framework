package com.nehayadav.qe.tests.web;

import com.nehayadav.qe.config.ConfigReader;
import com.nehayadav.qe.driver.DriverFactory;
import com.nehayadav.qe.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
 DriverFactory.quitDriver();
 }
}
