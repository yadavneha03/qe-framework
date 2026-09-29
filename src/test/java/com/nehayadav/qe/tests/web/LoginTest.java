package com.nehayadav.qe.tests.web;

import com.nehayadav.qe.config.ConfigReader;
import com.nehayadav.qe.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseWebTest {

 @Test(description = "Valid credentials should reach the Products page")
 public void validLoginSucceeds() {
 ProductsPage productsPage = loginPage.loginAs(
 ConfigReader.get("web.standardUser"),
 ConfigReader.get("web.password"));

 Assert.assertTrue(productsPage.isLoaded(), "Products page did not load after valid login");
 }

 @Test(description = "Locked-out user should see a clear error and stay on login page")
 public void lockedUserSeesError() {
 loginPage.attemptLogin(
 ConfigReader.get("web.lockedUser"),
 ConfigReader.get("web.password"));

 Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error message for locked-out user");
 Assert.assertTrue(
 loginPage.getErrorMessage().contains("locked out"),
 "Error message should mention the account is locked out");
 }

 @Test(description = "Invalid password should be rejected with an error", dataProvider = "invalidCredentials")
 public void invalidCredentialsRejected(String username, String password) {
 loginPage.attemptLogin(username, password);
 Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error for invalid credentials: "
 + username + " / " + password);
 }

 @DataProvider(name = "invalidCredentials")
 public Object[][] invalidCredentials() {
 return new Object[][]{
 {"standard_user", "wrong_password"},
 {"nonexistent_user", "secret_sauce"},
 {"", ""},
 };
 }
}

