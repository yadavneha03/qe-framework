package com.nehayadav.qe.tests.web;

import com.nehayadav.qe.config.ConfigReader;
import com.nehayadav.qe.pages.CartPage;
import com.nehayadav.qe.pages.CheckoutInfoPage;
import com.nehayadav.qe.pages.CheckoutOverviewPage;
import com.nehayadav.qe.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseWebTest {

 @Test(description = "End-to-end: add item to cart, complete checkout, order confirmed")
 public void completeCheckoutFlowSucceeds() {
 ProductsPage productsPage = loginPage.loginAs(
 ConfigReader.get("web.standardUser"),
 ConfigReader.get("web.password"));

 productsPage.addProductToCart("sauce-labs-backpack");
 Assert.assertEquals(productsPage.getCartItemCount(), 1, "Cart badge should show 1 item");

 CartPage cartPage = productsPage.goToCart();
 Assert.assertEquals(cartPage.getItemCount(), 1, "Cart page should list 1 item");

 CheckoutInfoPage infoPage = cartPage.proceedToCheckout();
 CheckoutOverviewPage overviewPage = infoPage.fillInfoAndContinue("Neha", "Yadav", "462001");

 Assert.assertTrue(
 overviewPage.getTotalLabel().startsWith("Total"),
 "Overview page should display an order total before finishing");

 overviewPage.finishOrder();

 Assert.assertEquals(
 overviewPage.getCompleteHeaderText(),
 "Thank you for your order!",
 "Order completion header text mismatch");
 }

 @Test(description = "Checkout info step rejects submission with missing fields")
 public void checkoutBlocksIncompleteInfo() {
 ProductsPage productsPage = loginPage.loginAs(
 ConfigReader.get("web.standardUser"),
 ConfigReader.get("web.password"));

 productsPage.addProductToCart("sauce-labs-bike-light");
 CartPage cartPage = productsPage.goToCart();
 CheckoutInfoPage infoPage = cartPage.proceedToCheckout();

 infoPage.submitIncomplete();

 Assert.assertTrue(
 infoPage.getErrorMessage().toLowerCase().contains("first name is required"),
 "Expected a required-field validation error");
 }
}


