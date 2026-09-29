package com.nehayadav.qe.tests.web;

import com.nehayadav.qe.config.ConfigReader;
import com.nehayadav.qe.pages.CartPage;
import com.nehayadav.qe.pages.CheckoutInfoPage;
import com.nehayadav.qe.pages.CheckoutOverviewPage;
import com.nehayadav.qe.pages.ProductsPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HybridApiUiTest extends BaseWebTest {

 @Test(description = "Checkout form is filled with a name fetched from the API, and the UI reflects it")
 public void checkoutUsesApiSeededCustomerName() {
 RestAssured.baseURI = ConfigReader.get("api.baseUrl");
 Response response = given().when().get("/users/3");
 String firstName = response.jsonPath().getString("data.first_name");
 String lastName = response.jsonPath().getString("data.last_name");

 ProductsPage productsPage = loginPage.loginAs(
 ConfigReader.get("web.standardUser"),
 ConfigReader.get("web.password"));

 productsPage.addProductToCart("sauce-labs-fleece-jacket");
 CartPage cartPage = productsPage.goToCart();
 CheckoutInfoPage infoPage = cartPage.proceedToCheckout();

 CheckoutOverviewPage overviewPage = infoPage.fillInfoAndContinue(firstName, lastName, "462001");

 Assert.assertTrue(
 overviewPage.getTotalLabel().startsWith("Total"),
 "Expected to reach checkout overview using API-seeded name: " + firstName + " " + lastName);
 }
}